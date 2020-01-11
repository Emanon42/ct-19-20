// Example of how to write an LLVM pass
// For more information see: http://llvm.org/docs/WritingAnLLVMPass.html

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
// get def set: directly use the instruction

struct SetCollection{
  map<BasicBlock*, set<Value*>> phiPreSuccMap;
  set<Value*> phiOutSet;
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
        livenessTable[I]->useSet = set<Value*>();
        //TODO: set phiPreSuccMap
        auto currPhi = dyn_cast<PHINode>(I);
        for (auto i = 0; i < currPhi->getNumIncomingValues(); i++) {
          auto preSuccOperand = currPhi->getIncomingValue(i);
          if (isa<Instruction>(preSuccOperand) || isa<Argument>(preSuccOperand)) {
            auto preSuccBB = currPhi->getIncomingBlock(i);
            livenessTable[I]->phiPreSuccMap[preSuccBB].insert(preSuccOperand);
          }
        }
      } else {
        livenessTable[I]->useSet = getUseSet(I);
      }
    }
    
    do{
      for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
        
        Instruction* I = &*instIter;

        //TODO: add phi mode for phi nodes

        if(isa<PHINode>(I)){
          inst_iterator phiIter = instIter;
          while(isa<PHINode>(&*phiIter)){
            Instruction* phiInst = &*phiIter;
            livenessTable[phiInst]->phiOutSet.insert(phiInst);
            //livenessTable[phiInst]->prevInSet = livenessTable[phiInst]->inSet;
            //livenessTable[phiInst]->prevOutSet = livenessTable[phiInst]->outSet;
            ++phiIter;
          }
        }

        livenessTable[I]->prevInSet = livenessTable[I]->inSet;
        livenessTable[I]->prevOutSet = livenessTable[I]->outSet;

        set<Value*> outMinusDef;
        set_difference(
          livenessTable[I]->outSet.begin(), livenessTable[I]->outSet.end(),
          livenessTable[I]->defSet.begin(), livenessTable[I]->defSet.end(),
          inserter(outMinusDef, outMinusDef.begin())
        );

        set<Value*> newIn;
        set_union(
          livenessTable[I]->useSet.begin(), livenessTable[I]->useSet.end(),
          outMinusDef.begin(), outMinusDef.end(),
          inserter(newIn, newIn.begin())
        );

        set<Value*> newOut;
        set<Instruction*> successors;

        // 2 cases: this instr is branch/return(terminator) or not
        if (I->isTerminator()){
          for(auto i = 0; i < I->getNumSuccessors(); i++){
            auto succInst = I->getSuccessor(i)->begin();
            successors.insert(&*succInst);
          }
        }else {
          auto nextInst = instIter;
          ++nextInst;
          // TODO: boundary check?
          Instruction* succInst = &*nextInst;
          successors.insert(succInst);
        }
        
        for (Instruction* succI : successors){
          set<Value*> unionResult;
          // TODO: special case required for the case of successor is phi
          // BUG: phi node will stop propagation of in set
          // use pen&paper derivate the working flow then figure it out
          if(isa<PHINode>(succI) && I->isTerminator()){
            auto phiIn = (livenessTable[succI]->phiPreSuccMap)[I->getParent()];
            set<Value*> phiUnionRes;

            set_union(
              phiIn.begin(), phiIn.end(),
              livenessTable[succI]->inSet.begin(), livenessTable[succI]->inSet.end(),
              inserter(phiUnionRes, phiUnionRes.begin())
            );


            set_union(
              newOut.begin(), newOut.end(),
              phiUnionRes.begin(), phiUnionRes.end(),
              inserter(unionResult, unionResult.begin())
            );
          } else {
            set_union(
            newOut.begin(), newOut.end(),
            livenessTable[succI]->inSet.begin(), livenessTable[succI]->inSet.end(),
            inserter(unionResult, unionResult.begin())
          );
          }
          newOut = unionResult;
        }
        livenessTable[I]->inSet = newIn;
        livenessTable[I]->outSet = newOut;

        
      }
      counter++;
      
    }while(!isLivenessTableCoverage()); //BUG: terminate too early

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





    





    // for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
    //   Instruction* I = &*instIter;

    //   // if (isa<PHINode>(I)){
    //   //   auto succPhi = dyn_cast<PHINode>(I);
    //   //   for (auto i = 0; i < succPhi->getNumIncomingValues(); i++){
    //   //     auto incomingVal = succPhi->getIncomingValue(i);
    //   //     if (isa<Instruction>(incomingVal) || isa<Argument>(incomingVal)){
    //   //       auto vb = succPhi->getIncomingBlock(i);
    //   //       errs() << *I << "\n";
    //   //       errs() << "pre-successor: " << *incomingVal << "\n";
    //   //       errs() << "from block " << *vb << "\n";
    //   //     }
          
    //   //   }
    //   // }

    //   if(isa<PHINode>(I)){
    //     errs() << *(I) << "\n";
    //   }else{
    //     auto x = livenessTable[I];

    //   errs() << "in set: {";
    //   for (auto v : x->inSet){
    //     v->printAsOperand(errs(), false);
    //     errs() << ", ";
    //   }
    //   errs() << "}\n";

    //   errs() << *(I) << "\n";

    //   // errs() << "out set: {";
    //   // for (auto v : x->outSet){
    //   //   v->printAsOperand(errs(), false);
    //   //   errs() << ", ";
    //   // }
    //   // errs() << "}\n";
    //   }
      

      

    //}


    // for (auto const& x : livenessTable){
    //   // if (isa<PHINode>(x.first)){
    //   //   continue;
    //   // }
    //   errs() << "in set: {";
    //   for (auto v : x.second->inSet){
    //     v->printAsOperand(errs(), false);
    //     errs() << ", ";
    //   }
    //   errs() << "}\n";

    //   errs() << *(x.first) << "\n";

    //   errs() << "out set: {";
    //   for (auto v : x.second->outSet){
    //     v->printAsOperand(errs(), false);
    //     errs() << ", ";
    //   }
    //   errs() << "}\n";
      
    // }

    return true;
  }

  void printLiveness(Function &F){
    for (Function::iterator bb = F.begin(), end = F.end(); bb != end; bb++) {
      for (BasicBlock::iterator i = bb->begin(), e = bb->end(); i != e; i++) {
        // skip phis
        if (dyn_cast<PHINode>(i))
          continue;
        
        errs() << "{";
        
        // UNCOMMENT AND ADAPT FOR YOUR "IN" SET
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

// static RegisterStandardPasses Y(
//     PassManagerBuilder::EP_EarlyAsPossible,
//     [](const PassManagerBuilder &Builder,
//        legacy::PassManagerBase &PM) { PM.add(new MyPass()); });