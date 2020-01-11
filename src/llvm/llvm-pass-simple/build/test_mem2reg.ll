; ModuleID = 'test.ll'
source_filename = "test.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @sum(i32 %a, i32 %b) #0 {
entry:
  %0 = icmp slt i32 %a, %b
  br i1 %0, label %bb.nph, label %bb2

bb.nph: ; preds = %entry
  %tmp = sub i32 %b, %a
  br label %bb

bb: ; preds = %bb, bb.nph
  %indvar = phi i32 [ 0, %bb.nph ], [ %indvar.next, %bb ]
  %res.05 = phi i32 [ 1, %bb.nph ], [ %1, %bb ]
  %i.04 = add i32 %indvar, %a
  %1 = mul nsw i32 %res.05, %i.04
  %indvar.next = add i32 %indvar, 1
  %exitcond = icmp eq i32 %indvar.next, %tmp
  br i1 %exitcond, label %bb2, label %bb

bb2: ; preds = %bb, %entry
  %res.0.lcssa = phi i32 [ 1, %entry ], [ %1, %bb ]
  ret i32 %res.0.lcssa
}

attributes #0 = { noinline nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 9.0.1 (/afs/inf.ed.ac.uk/user/s17/s1758009/ug3-ct/llvm-project/clang c1a0a213378a458fbea1a5c77b315c7dce08fd05)"}
