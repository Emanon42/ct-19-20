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
    livenessTable.clear();
    for (Instruction &I : instructions(F)){
      livenessTable[&I] = new SetCollection();

      livenessTable[&I]->inSet = set<Value*>();
      livenessTable[&I]->outSet = set<Value*>();

      livenessTable[&I]->prevInSet = set<Value*>();
      livenessTable[&I]->prevOutSet = set<Value*>();

      livenessTable[&I]->useSet = getUseSet(&I);
      livenessTable[&I]->defSet = getDefSet(&I);
    }

    do{
      //TODO: change all instr iter like that
      for (inst_iterator instIter = inst_begin(F), instEnd = inst_end(F); instIter != instEnd; ++instIter){
        
        Instruction* I = &*instIter;

        livenessTable[I]->prevInSet = livenessTable[I]->inSet;
        livenessTable[I]->prevOutSet = livenessTable[I]->outSet;

        //TODO: phi node

        set<Value*> outMinusDef;
        set_difference(
          livenessTable[I]->outSet.begin(), livenessTable[I]->outSet.end(),
          livenessTable[I]->defSet.begin(), livenessTable[I]->defSet.begin(),
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
          //TODO: phi node
        }else {
          auto nextInst = instIter;
          ++nextInst;
          // TODO: boundary check?
          Instruction* succInst= &*nextInst;
          successors.insert(succInst);
        }
        
        for (Instruction* succI : successors){
          set<Value*> unionResult;
          set_union(
            newOut.begin(), newOut.end(),
            livenessTable[succI]->inSet.begin(), livenessTable[succI]->inSet.end(),
            inserter(unionResult, unionResult.begin())
          );
          //TODO: phi node
          newOut = unionResult;
        }
        //TODO: phi node
        livenessTable[I]->inSet = newIn;
        livenessTable[I]->outSet = newOut;

      }
    }while(isLivenessTableCoverage());


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
    for (auto const& x : livenessTable){
      if (isa<PHINode>(x.first)){
        continue;
      }

      errs() << *(x.first) << "\n";
      for (auto v : x.second->inSet){
        v->printAsOperand(errs(), false);
        errs() << ", ";
      }
      errs() << "\n";
    }

    return false;
  }
};
}

char MyPass::ID = 0;
static RegisterPass<MyPass> X("mypass", "My liveness analysis and dead code elimination pass");

static RegisterStandardPasses Y(
    PassManagerBuilder::EP_EarlyAsPossible,
    [](const PassManagerBuilder &Builder,
       legacy::PassManagerBase &PM) { PM.add(new MyPass()); });