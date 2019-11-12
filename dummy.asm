.data
# nahui as "struct vodka" (size 40)
_g_struct_nahui:    
_gs_nahui_cheeki:    .space 36
_gs_nahui_breeki:    .space 4
_gv_tnok:    .space 4
_gv_yjsp:    .space 4
_gv_laser_shark:    .space 456
.text
fn_cykablyat_prologue:    # cykablyat starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg a offset: 4($fp)
# arg b offset: 5($fp)
# snapshot registers
subi $sp, $sp, 92
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
# Set $ra to epilogue
la $ra, fn_cykablyat_epilogue
fn_cykablyat_content:    
# no local var detected
# return ast.Return@7cca494b
# store return value at $v0
move $v0, null
# jump to epilogue of current func (defined at $ra)
jr $ra
# Store default return value to $v0
li $v0, 0
fn_cykablyat_epilogue:    
move $sp, $fp
subi $sp, $sp, 92
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
addi $sp, $sp, 92
jr $ra
fn_main_prologue:    # main starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# no args in current function
# snapshot registers
subi $sp, $sp, 92
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
# Set $ra to epilogue
la $ra, fn_main_epilogue
fn_main_content:    
# no local var detected
# exprStmt ast.ExprStmt@7ba4f24f
# precall for cykablyat
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 5
# eval and store args
# storing arg a offset: 0($sp)
sw null 0($sp)
# storing arg b offset: 4($sp)
sw null 4($sp)
# perform jump to callee: cykablyat
jal fn_cykablyat_prologue
# postreturn for cykablyat
# dispose args space
addi $sp, $sp, 5
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# Store default return value to $v0
li $v0, 0
fn_main_epilogue:    
move $sp, $fp
subi $sp, $sp, 92
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
addi $sp, $sp, 92
jr $ra
main:    .globl main
jal fn_main_prologue
move $a0, $v0
li $v0, 17
syscall
