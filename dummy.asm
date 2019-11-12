.data
.text
fn_main_prologue:    # main starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# no args in current function
# snapshot registers
subi $sp, $sp, 96
sw $t0 0($sp)
sw $t1 4($sp)
sw $t2 8($sp)
sw $t3 12($sp)
sw $t4 16($sp)
sw $t5 20($sp)
sw $t6 24($sp)
sw $t7 28($sp)
sw $s0 32($sp)
sw $s1 36($sp)
sw $s2 40($sp)
sw $s3 44($sp)
sw $s4 48($sp)
sw $s5 52($sp)
sw $s6 56($sp)
sw $s7 60($sp)
sw $t8 64($sp)
sw $t9 68($sp)
sw $a0 72($sp)
sw $a1 76($sp)
sw $a2 80($sp)
sw $a3 84($sp)
sw $gp 88($sp)
sw $ra 92($sp)
# Set $ra to epilogue
la $ra, fn_main_epilogue
fn_main_content:    
# local var ema frameOffset(relative to $fp): -100
# pre-alloc 4 bytes for local var
subi $sp, $sp, 4
# assign ast.Assign@7cca494b
addi $t9, $fp, -100
li $t8, 114514
sw $t8 0($t9)
# exprStmt ast.ExprStmt@7ba4f24f
addi $s7, $fp, -100
lw $s6, 0($s7)
move $a0, $s6
# print_i($a0)
li $v0, 1
syscall
# release pre-allocated mem for local vars
addi $sp, $sp, 4
# Store default return value to $v0
li $v0, 0
fn_main_epilogue:    
move $sp, $fp
subi $sp, $sp, 96
# restore registers
lw $t0, 0($sp)
lw $t1, 4($sp)
lw $t2, 8($sp)
lw $t3, 12($sp)
lw $t4, 16($sp)
lw $t5, 20($sp)
lw $t6, 24($sp)
lw $t7, 28($sp)
lw $s0, 32($sp)
lw $s1, 36($sp)
lw $s2, 40($sp)
lw $s3, 44($sp)
lw $s4, 48($sp)
lw $s5, 52($sp)
lw $s6, 56($sp)
lw $s7, 60($sp)
lw $t8, 64($sp)
lw $t9, 68($sp)
lw $a0, 72($sp)
lw $a1, 76($sp)
lw $a2, 80($sp)
lw $a3, 84($sp)
lw $gp, 88($sp)
lw $ra, 92($sp)
addi $sp, $sp, 96
jr $ra
main:    .globl main
jal fn_main_prologue
move $a0, $v0
li $v0, 17
syscall
