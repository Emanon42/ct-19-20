; ModuleID = 'test2.c'
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
  %grade = alloca i8, align 1
  %n = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  store i8 48, i8* %grade, align 1
  store i32 0, i32* %n, align 4
  store i32 5, i32* %b, align 4
  %0 = load i8, i8* %grade, align 1
  %conv = sext i8 %0 to i32
  switch i32 %conv, label %sw.default [
    i32 65, label %sw.bb
    i32 66, label %sw.bb1
    i32 67, label %sw.bb2
    i32 68, label %sw.bb3
    i32 70, label %sw.bb4
  ]

sw.bb:                                            ; preds = %entry
  store i32 1, i32* %n, align 4
  br label %sw.epilog

sw.bb1:                                           ; preds = %entry
  store i32 2, i32* %n, align 4
  br label %sw.epilog

sw.bb2:                                           ; preds = %entry
  store i32 3, i32* %n, align 4
  %1 = load i32, i32* %n, align 4
  %2 = load i32, i32* %b, align 4
  %add = add nsw i32 %1, %2
  store i32 %add, i32* %c, align 4
  store i8 70, i8* %grade, align 1
  br label %sw.epilog

sw.bb3:                                           ; preds = %entry
  store i32 4, i32* %n, align 4
  br label %sw.epilog

sw.bb4:                                           ; preds = %entry
  store i32 5, i32* %n, align 4
  br label %sw.epilog

sw.default:                                       ; preds = %entry
  store i32 6, i32* %n, align 4
  store i32 8, i32* %b, align 4
  br label %sw.epilog

sw.epilog:                                        ; preds = %sw.default, %sw.bb4, %sw.bb3, %sw.bb2, %sw.bb1, %sw.bb
  %3 = load i32, i32* %n, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @foo() #0 {
entry:
  %volly = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store volatile i32 1337, i32* %volly, align 4
  store i32 7, i32* %a, align 4
  %0 = load i32, i32* %a, align 4
  %mul = mul nsw i32 %0, 2
  store i32 %mul, i32* %b, align 4
  %1 = load i32, i32* %b, align 4
  %2 = load i32, i32* %a, align 4
  %sub = sub nsw i32 %1, %2
  store i32 %sub, i32* %c, align 4
  %3 = load i32, i32* %c, align 4
  %4 = load i32, i32* %a, align 4
  %div = sdiv i32 %3, %4
  store i32 %div, i32* %d, align 4
  %5 = load i32, i32* %b, align 4
  ret i32 %5
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32* @something() #0 {
entry:
  %a = alloca i8, align 1
  store i8 97, i8* %a, align 1
  ret i32* @some_number
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @main() #0 {
entry:
  %retval = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 0, i32* %retval, align 4
  %call = call i32 @foo()
  store i32 %call, i32* %a, align 4
  %0 = load i32, i32* %a, align 4
  %add = add nsw i32 %0, 2
  store i32 %add, i32* %b, align 4
  ret i32 1
}

; Function Attrs: noinline nounwind uwtable
define dso_local i32 @sum(i32 %a, i32 %b) #0 {
entry:
  %a.addr = alloca i32, align 4
  %b.addr = alloca i32, align 4
  %i = alloca i32, align 4
  %res = alloca i32, align 4
  store i32 %a, i32* %a.addr, align 4
  store i32 %b, i32* %b.addr, align 4
  store i32 1, i32* %res, align 4
  %0 = load i32, i32* %a.addr, align 4
  store i32 %0, i32* %i, align 4
  br label %for.cond

for.cond:                                         ; preds = %for.inc, %entry
  %1 = load i32, i32* %i, align 4
  %2 = load i32, i32* %b.addr, align 4
  %cmp = icmp slt i32 %1, %2
  br i1 %cmp, label %for.body, label %for.end

for.body:                                         ; preds = %for.cond
  %3 = load i32, i32* %i, align 4
  %4 = load i32, i32* %res, align 4
  %mul = mul nsw i32 %4, %3
  store i32 %mul, i32* %res, align 4
  br label %for.inc

for.inc:                                          ; preds = %for.body
  %5 = load i32, i32* %i, align 4
  %inc = add nsw i32 %5, 1
  store i32 %inc, i32* %i, align 4
  br label %for.cond

for.end:                                          ; preds = %for.cond
  %6 = load i32, i32* %res, align 4
  ret i32 %6
}

attributes #0 = { noinline nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 9.0.1 (/afs/inf.ed.ac.uk/user/s17/s1758009/ug3-ct/llvm-project/clang c1a0a213378a458fbea1a5c77b315c7dce08fd05)"}
