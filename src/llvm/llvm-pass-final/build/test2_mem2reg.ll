; ModuleID = 'test2.ll'
source_filename = "test2.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

@some_number = dso_local global i32 1234, align 4

; Function Attrs: noinline nounwind uwtable
define dso_local signext i8 @another() #0 {
entry:
  ret i8 97
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @switchy() #0 {
entry:
  %conv = sext i8 48 to i32
  switch i32 %conv, label %sw.default [
    i32 65, label %sw.bb
    i32 66, label %sw.bb1
    i32 67, label %sw.bb2
    i32 68, label %sw.bb3
    i32 70, label %sw.bb4
  ]

sw.bb:                                            ; preds = %entry
  br label %sw.epilog

sw.bb1:                                           ; preds = %entry
  br label %sw.epilog

sw.bb2:                                           ; preds = %entry
  %add = add nsw i32 3, 5
  br label %sw.epilog

sw.bb3:                                           ; preds = %entry
  br label %sw.epilog

sw.bb4:                                           ; preds = %entry
  br label %sw.epilog

sw.default:                                       ; preds = %entry
  br label %sw.epilog

sw.epilog:                                        ; preds = %sw.default, %sw.bb4, %sw.bb3, %sw.bb2, %sw.bb1, %sw.bb
  %n.0 = phi i32 [ 6, %sw.default ], [ 5, %sw.bb4 ], [ 4, %sw.bb3 ], [ 3, %sw.bb2 ], [ 2, %sw.bb1 ], [ 1, %sw.bb ]
  ret i32 %n.0
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @foo() #0 {
entry:
  %volly = alloca i32, align 4
  store volatile i32 1337, i32* %volly, align 4
  %mul = mul nsw i32 7, 2
  %sub = sub nsw i32 %mul, 7
  %div = sdiv i32 %sub, 7
  ret i32 %mul
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32* @something() #0 {
entry:
  ret i32* @some_number
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @main() #0 {
entry:
  %call = call i32 @foo()
  %add = add nsw i32 %call, 2
  ret i32 1
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @sum(i32 %a, i32 %b) #0 {
entry:
  br label %for.cond

for.cond:                                         ; preds = %for.inc, %entry
  %i.0 = phi i32 [ %a, %entry ], [ %inc, %for.inc ]
  %res.0 = phi i32 [ 1, %entry ], [ %mul, %for.inc ]
  %cmp = icmp slt i32 %i.0, %b
  br i1 %cmp, label %for.body, label %for.end

for.body:                                         ; preds = %for.cond
  %mul = mul nsw i32 %res.0, %i.0
  br label %for.inc

for.inc:                                          ; preds = %for.body
  %inc = add nsw i32 %i.0, 1
  br label %for.cond

for.end:                                          ; preds = %for.cond
  ret i32 %res.0
}

attributes #0 = { noinline nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 9.0.1 (/afs/inf.ed.ac.uk/user/s17/s1758009/ug3-ct/llvm-project/clang c1a0a213378a458fbea1a5c77b315c7dce08fd05)"}
