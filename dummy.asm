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
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
# print_i($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
li $v0, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
# exprStmt ast.ExprStmt@7b23ec81
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
la $t9, _str1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
move $a0, $t9
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
# print_s($a0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
li $v0, 4
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
syscall
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
# assign ast.Assign@6acbcfc0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $t9]
addi $t9, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7]
li $s7, 0
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
sw $s7 0($t9)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
_while3_start:    # while (ast.BinOp@5f184fc6)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $t9, $s7]
addi $s7, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6, $t9]
lw $t9, 0($s7)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5, $s6]
addi $s6, $fp, -100
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4, $s5]
lw $s5, 0($s6)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
slt $s4, $t9, $s5
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3]
beq $s4, $zero, _while4_end
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3]
# no local var detected
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
# if ast.If@3feba861
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3, $s4]
addi $s4, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2, $s3]
lw $s3, 0($s4)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1, $s2]
li $s2, 1
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
sle $s1, $s3, $s2
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
beq $s1, $zero, _if6_else
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
# assign ast.Assign@5b480cf9
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0, $s1]
addi $s1, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7, $s0]
addi $s0, $fp, -116
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $t7]
lw $t7, 0($s0)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6]
sw $t7 0($s1)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6]
b _if5_end
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $s1, $t7]
_if6_else:    # else
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $s1, $t7]
# no local var detected
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $s1, $t7]
# assign ast.Assign@6f496d9f
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $s1, $t7]
addi $t7, $fp, -112
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6, $s1]
addi $s1, $fp, -104
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5, $t6]
lw $t6, 0($s1)
# free regs: [$t0, $t1, $t2, $t3, $t4, $t5]
addi $t5, $fp, -108
# free regs: [$t0, $t1, $t2, $t3, $t4]
lw $t4, 0($t5)
# free regs: [$t0, $t1, $t2, $t3]
add $t3, $t6, $t4
# free regs: [$t0, $t1, $t2]
sw $t3 0($t7)
# free regs: [$t0, $t1, $t2]
# assign ast.Assign@723279cf
# free regs: [$t0, $t1, $t2, $t7, $t3]
addi $t3, $fp, -104
# free regs: [$t0, $t1, $t2, $t7]
addi $t7, $fp, -108
# free regs: [$t0, $t1, $t2]
lw $t2, 0($t7)
# free regs: [$t0, $t1]
sw $t2 0($t3)
# free regs: [$t0, $t1]
# assign ast.Assign@10f87f48
# free regs: [$t0, $t1, $t3, $t2]
addi $t2, $fp, -108
# free regs: [$t0, $t1, $t3]
addi $t3, $fp, -112
# free regs: [$t0, $t1]
lw $t1, 0($t3)
# free regs: [$t0]
sw $t1 0($t2)
# free regs: [$t0]
_if5_end:    nop
# free regs: [$t0, $t2, $t1]
# exprStmt ast.ExprStmt@b4c966a
# free regs: [$t0, $t2, $t1]
addi $t1, $fp, -112
# free regs: [$t0, $t2]
lw $t2, 0($t1)
# free regs: [$t0]
move $a0, $t2
# free regs: [$t0]
# print_i($a0)
# free regs: [$t0, $t2]
li $v0, 1
# free regs: [$t0, $t2]
syscall
# free regs: [$t0, $t2]
# exprStmt ast.ExprStmt@2f4d3709
# free regs: [$t0, $t2]
la $t2, _str2
# free regs: [$t0]
move $a0, $t2
# free regs: [$t0]
# print_s($a0)
# free regs: [$t0, $t2]
li $v0, 4
# free regs: [$t0, $t2]
syscall
# free regs: [$t0, $t2]
# assign ast.Assign@4e50df2e
# free regs: [$t0, $t2]
addi $t2, $fp, -116
# free regs: [$t0]
addi $t0, $fp, -116
# free regs: []
