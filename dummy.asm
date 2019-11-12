.data
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
_str0:    .asciiz "First "
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
_str1:    .asciiz " terms of Fibonacci series are : "
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
_str2:    .asciiz " "
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
.text
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
fn_main_prologue:    # main starts here
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# reset frame pointer
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
move $fp, $sp
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# setting args frame offset (relative to current fp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# no args in current function
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# snapshot registers
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
subi $sp, $sp, 96
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t0 0($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t1 4($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t2 8($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t3 12($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t4 16($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t5 20($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t6 24($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t7 28($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s0 32($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s1 36($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s2 40($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s3 44($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s4 48($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s5 52($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s6 56($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $s7 60($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t8 64($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $t9 68($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $a0 72($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $a1 76($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $a2 80($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $a3 84($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $gp 88($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
sw $ra 92($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# Set $ra to epilogue
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
la $ra, fn_main_epilogue
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
fn_main_content:    
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var n frameOffset(relative to $fp): -100
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var first frameOffset(relative to $fp): -104
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var second frameOffset(relative to $fp): -108
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var next frameOffset(relative to $fp): -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var c frameOffset(relative to $fp): -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# local var t frameOffset(relative to $fp): -117
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# pre-alloc 21 bytes for local var
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
subi $sp, $sp, 21
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# assign ast.Assign@16b98e56
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
addi $t9, $fp, -100
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
# $v0 = read_i()
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
li $v0, 5
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
move $t8, $v0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
sw $t8 0($t9)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
# assign ast.Assign@7ef20235
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
addi $t8, $fp, -104
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
li $t9, 0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
sw $t9 0($t8)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
# assign ast.Assign@27d6c5e0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
addi $t9, $fp, -108
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
li $t8, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
sw $t8 0($t9)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
# exprStmt ast.ExprStmt@4f3f5b24
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
la $t8, _str0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
move $a0, $t8
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
# print_s($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
li $v0, 4
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
# exprStmt ast.ExprStmt@15aeb7ab
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
addi $t8, $fp, -100
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
lw $t9, 0($t8)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
move $a0, $t9
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
# print_i($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
li $v0, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# exprStmt ast.ExprStmt@7b23ec81
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
la $t9, _str1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
move $a0, $t9
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
# print_s($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
li $v0, 4
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
# assign ast.Assign@6acbcfc0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8, $t9]
addi $t9, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t8]
li $t8, 0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
sw $t8 0($t9)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
_while3_start:    # while (ast.BinOp@5f184fc6)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9, $t8]
addi $t8, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
lw $t9, 0($t8)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
addi $t8, $fp, -100
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
lw $s7, 0($t8)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
slt $t8, $t9, $s7
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
beq $t8, $zero, _while4_end
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
# no local var detected
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $t8]
# if ast.If@3feba861
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $t8]
addi $t8, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
lw $s6, 0($t8)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5]
li $t8, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5]
sle $s5, $s6, $t8
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
beq $s5, $zero, _if6_else
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
# assign ast.Assign@5b480cf9
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5]
addi $s5, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
addi $s4, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3]
lw $s3, 0($s4)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2]
sw $s3 0($s5)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4]
b _if5_end
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4, $s5, $s3]
_if6_else:    # else
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4, $s5, $s3]
# no local var detected
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4, $s5, $s3]
# assign ast.Assign@6f496d9f
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4, $s5, $s3]
addi $s3, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4, $s5]
addi $s5, $fp, -104
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s4]
lw $s4, 0($s5)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2]
addi $s5, $fp, -108
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2]
lw $s2, 0($s5)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
add $s5, $s4, $s2
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
sw $s5 0($s3)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
# assign ast.Assign@723279cf
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s3, $s5]
addi $s5, $fp, -104
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s3]
addi $s3, $fp, -108
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
lw $s1, 0($s3)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
sw $s1 0($s5)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s3]
# assign ast.Assign@10f87f48
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s3, $s5, $s1]
addi $s1, $fp, -108
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s3, $s5]
addi $s5, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s3]
lw $s3, 0($s5)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
sw $s3 0($s1)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5]
_if5_end:    nop
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s1, $s3]
# exprStmt ast.ExprStmt@b4c966a
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s1, $s3]
addi $s3, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s1]
lw $s1, 0($s3)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5]
move $a0, $s1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3]
# print_i($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
li $v0, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
# exprStmt ast.ExprStmt@2f4d3709
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
la $s1, _str2
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3]
move $a0, $s1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3]
# print_s($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
li $v0, 4
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
# assign ast.Assign@4e50df2e
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3, $s1]
addi $s1, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5, $s3]
addi $s3, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s5]
lw $s5, 0($s3)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
li $s3, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
add $s0, $s5, $s3
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7]
sw $s0 0($s1)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7]
b _while3_start
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
_while4_end:    nop
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
# release pre-allocated mem for local vars
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
addi $sp, $sp, 21
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
# Store default return value to $v0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
li $v0, 0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
fn_main_epilogue:    
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
move $sp, $fp
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
subi $sp, $sp, 96
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
# restore registers
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t0, 0($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t1, 4($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t2, 8($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t3, 12($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t4, 16($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t5, 20($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t6, 24($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t7, 28($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s0, 32($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s1, 36($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s2, 40($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s3, 44($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s4, 48($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s5, 52($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s6, 56($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $s7, 60($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t8, 64($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $t9, 68($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $a0, 72($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $a1, 76($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $a2, 80($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $a3, 84($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $gp, 88($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
lw $ra, 92($sp)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
addi $sp, $sp, 96
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
jr $ra
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
main:    .globl main
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
jal fn_main_prologue
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
move $a0, $v0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
li $v0, 17
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s1, $s0]
