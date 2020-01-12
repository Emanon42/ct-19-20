#include "llvm/Pass.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/Instructions.h"
#include "llvm/IR/InstIterator.h"
#include "llvm/Support/raw_ostream.h"

#include "llvm/IR/LegacyPassManager.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"

#include <set>
#include <map>

using namespace llvm;
using namespace std;
// get def set: directly use the instruction itself

struct SetCollection{
  map<BasicBlock*, set<Value*>> phiPreSuccMap; // only for phi node: one successor block corresponds to a begin instr
  set<Value*> phiOutSet; // only for adjacent phi node: out set will pass
  set<Value*> prevInSet;
  set<Value*> prevOutSet;
  set<Value*> inSet;
  set<Value*> outSet;
  set<Value*> useSet;
  set<Value*> defSet;
}; 

namespace {
struct MyPass : public FunctionPass {

  map<Instruction*, SetCollection*> livenessTable;
  SmallVector<Instruction*, 64> deadlist;

  static char ID;
  MyPass() : FunctionPass(ID) {}

  void computeLiveness(Function &F){

    int counter = 0;
    
    // initialization
    livenessTable.clear();
    for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
      
      Instruction* I = &*instIter;

      livenessTable[I] = new SetCollection();

      livenessTable[I]->phiPreSuccMap = map<BasicBlock*, set<Value*>>();

      livenessTable[I]->inSet = set<Value*>();
      livenessTable[I]->outSet = set<Value*>();

      livenessTable[I]->prevInSet = set<Value*>();
      livenessTable[I]->prevOutSet = set<Value*>();

      livenessTable[I]->defSet = getDefSet(I);

      if (isa<PHINode>(I)){
        livenessTable[I]->useSet = set<Value*>(); // use set of phi node should be empty
        //set phiPreSuccMap
        auto currPhi = dyn_cast<PHINode>(I);
        for (auto i = 0; i < currPhi->getNumIncomingValues(); i++) {
          auto preSuccOperand = currPhi->getIncomingValue(i);

          if (isa<Instruction>(preSuccOperand) || isa<Argument>(preSuccOperand)) {
            auto preSuccBB = currPhi->getIncomingBlock(i);
            livenessTable[I]->phiPreSuccMap[preSuccBB].insert(preSuccOperand);
            //livenessTable[I]->useSet.insert(preSuccOperand);
          }
        }
      } else {
        livenessTable[I]->useSet = getUseSet(I);
      }
    }
    
    // start forward iterating
    do{
      for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
        
        Instruction* I = &*instIter;

        // set the special out set for phi node
        if(isa<PHINode>(I)){
          inst_iterator phiIter = instIter;
          while(isa<PHINode>(&*phiIter)){
            Instruction* phiInst = &*phiIter;
            livenessTable[phiInst]->phiOutSet.insert(phiInst); // add the local var it defines to every follow phi node
            //livenessTable[phiInst]->outSet.insert(phiInst);
            ++phiIter;
          }
        }

        livenessTable[I]->prevInSet = livenessTable[I]->inSet;
        livenessTable[I]->prevOutSet = livenessTable[I]->outSet;

        // update inset
        // out[n] – def[n]
        set<Value*> outMinusDef;
        set_difference(
          livenessTable[I]->outSet.begin(), livenessTable[I]->outSet.end(),
          livenessTable[I]->defSet.begin(), livenessTable[I]->defSet.end(),
          inserter(outMinusDef, outMinusDef.begin())
        );

        // use[n] ∪ (out[n] – def[n])
        set<Value*> newIn;
        set_union(
          livenessTable[I]->useSet.begin(), livenessTable[I]->useSet.end(),
          outMinusDef.begin(), outMinusDef.end(),
          inserter(newIn, newIn.begin())
        );


        // update outset
        set<Value*> newOut;
        set<Instruction*> successors;

        // 2 cases: this instr is branch/return(terminator) or not
        if (I->isTerminator()){
          for(auto i = 0; i < I->getNumSuccessors(); i++){
            // collect all successor(not next!!) block's begin instr
            auto succInst = I->getSuccessor(i)->begin();
            successors.insert(&*succInst);
          }
        }else {
          // peek next instr
          auto nextInst = instIter;
          ++nextInst;
          // TODO: boundary check?
          Instruction* succInst = &*nextInst;
          successors.insert(succInst);
        }
        
        // union them together
        for (Instruction* succI : successors){
          set<Value*> unionResult;
          // special case required for the case of successor is phi
          if(isa<PHINode>(succI) && I->isTerminator()){ // make sure it is the first phi node in the begin of block
            //auto phiIn = (livenessTable[succI]->phiPreSuccMap)[I->getParent()];

            // major fix: mutiple phi node
            set<Value*> allPhiIn;
            BasicBlock::iterator adjPhiIter(succI);
            // need to consider all possible corresponding edge in all phi nodes INSTEAD OF ONLY THE FIRST!!!
            while(isa<PHINode>(&*adjPhiIter)){
              auto singlePhiIn = (livenessTable[&*adjPhiIter]->phiPreSuccMap)[I->getParent()];
              set<Value*> allPhiInRes;
              set_union(
                singlePhiIn.begin(), singlePhiIn.end(), 
                allPhiIn.begin(), allPhiIn.end(),
                inserter(allPhiInRes, allPhiInRes.begin())
              );
              allPhiIn = allPhiInRes;
              ++adjPhiIter;
            }

            set<Value*> phiUnionRes;

            // put corresponding allPhiIn and regular inSet(for propagation) together as "real in set"
            set_union(
              allPhiIn.begin(), allPhiIn.end(),
              livenessTable[succI]->inSet.begin(), livenessTable[succI]->inSet.end(),
              inserter(phiUnionRes, phiUnionRes.begin())
            );

            // union all inSet together
            set_union(
              newOut.begin(), newOut.end(),
              phiUnionRes.begin(), phiUnionRes.end(),
              inserter(unionResult, unionResult.begin())
            );
          } else {
            // union all inSet together
            set_union(
              newOut.begin(), newOut.end(),
              livenessTable[succI]->inSet.begin(), livenessTable[succI]->inSet.end(),
              inserter(unionResult, unionResult.begin())
            );
          }
          newOut = unionResult;
        }

        // update
        livenessTable[I]->inSet = newIn;
        livenessTable[I]->outSet = newOut;
      }
      counter++;
      
    }while(!isLivenessTableCoverage());

    //errs() << "iterated " << counter << " times\n";
  } 

  bool isLivenessTableCoverage(){
    bool coverageFlag = true;
    for (auto const& x : livenessTable){
      bool singleFlag = (x.second->prevInSet == x.second->inSet) && (x.second->prevOutSet == x.second->outSet);
      coverageFlag &= singleFlag;
    }
    return coverageFlag;
  }

  set<Value*> getUseSet(Instruction *I) {
    set<Value*> useSet;
    for (Use &U : I->operands()){
      Value *v = U.get();
      if (isa<Instruction>(v) || isa<Argument>(v)){
        //errs() << v->getName() << "\n";
        useSet.insert(v);
      }
    }
    return useSet;
  }

  set<Value*> getDefSet(Instruction *I) {
    set<Value*> defSet;
    defSet.insert(I);
    return defSet;
  }
  

  bool runOnFunction(Function &F) override {

    computeLiveness(F);
    printLiveness(F);

    do {
      deadlist.clear();

      computeLiveness(F);

      for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
        Instruction* I = &*instIter;

        bool isDead = ((livenessTable[I]->outSet).find(I) == (livenessTable[I]->outSet).end()) && 
          !isa<CallInst>(I) &&
          !I->mayHaveSideEffects() &&
          !I->isTerminator();

        if (isDead){
          deadlist.push_back(I);
        }
      }

      for(Instruction* i : deadlist){
          i->eraseFromParent();
      }

    }while(!deadlist.empty());
    return true;
  }

  void printLiveness(Function &F){
    for (Function::iterator bb = F.begin(), end = F.end(); bb != end; bb++) {
      for (BasicBlock::iterator i = bb->begin(), e = bb->end(); i != e; i++) {
        // skip phis
        if (dyn_cast<PHINode>(i))
          continue;
        
        errs() << "{";
        
        auto operatorSet = livenessTable[&*i]->inSet;
        for (auto oper = operatorSet.begin(); oper != operatorSet.end(); oper++) {
          auto op = *oper;
          if (oper != operatorSet.begin())
            errs() << ", ";
          (*oper)->printAsOperand(errs(), false);
        }
        
        errs() << "}\n";
       }
    }
    errs() << "{}\n";

  }

};
}

char MyPass::ID = 0;
static RegisterPass<MyPass> X("mypass", "My liveness analysis and dead code elimination pass");