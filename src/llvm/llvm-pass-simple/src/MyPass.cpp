#include "llvm/Pass.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/Instructions.h"
#include "llvm/IR/InstIterator.h"
#include "llvm/Support/raw_ostream.h"

#include "llvm/IR/LegacyPassManager.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Transforms/Utils/Local.h"


#include <vector>
#include <map>

using namespace llvm;
using namespace std;

// note: it scans llvm IR instead of c code
// llvm IR hierachy: 
// Function -> BasicBlock -> Instruction
// more API need to check in llvm/include and build/include

namespace {
struct MyPass : public FunctionPass {
  SmallVector<Instruction*, 64> Worklist; // record the "dead" instructions
  static char ID;
  MyPass() : FunctionPass(ID) {}

  bool runOnFunction(Function &F) override {
    do{
      // remember to clear the worklist every loop
      Worklist.clear();

      for (Instruction &i : instructions(F)){
        // add instrction to worklist if it is dead
        if (isInstructionTriviallyDead(&i, nullptr)){
          Worklist.push_back(&i);
        }
      }

      // erase it
      for(Instruction* i : Worklist){
        i->eraseFromParent();
      }
    } while (!Worklist.empty()); // loop until there is no more instr to erase
    return true;
  }
};
}

char MyPass::ID = 0;
static RegisterPass<MyPass> X("mypass", "My simple dead code elimination pass");