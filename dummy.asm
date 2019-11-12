.data
_gv_a11:    .space 1
_gv_a12:    .space 1
_gv_a13:    .space 1
_gv_a21:    .space 1
_gv_a22:    .space 1
_gv_a23:    .space 1
_gv_a31:    .space 1
_gv_a32:    .space 1
_gv_a33:    .space 1
_gv_empty:    .space 1
_str0:    .asciiz "\n"
_str1:    .asciiz "     1   2   3\n"
_str2:    .asciiz "   +---+---+---+\n"
_str3:    .asciiz "a  | "
_str4:    .asciiz " | "
_str5:    .asciiz " | "
_str6:    .asciiz " |\n"
_str7:    .asciiz "   +---+---+---+\n"
_str8:    .asciiz "b  | "
_str9:    .asciiz " | "
_str10:    .asciiz " | "
_str11:    .asciiz " |\n"
_str12:    .asciiz "   +---+---+---+\n"
_str13:    .asciiz "c  | "
_str14:    .asciiz " | "
_str15:    .asciiz " | "
_str16:    .asciiz " |\n"
_str17:    .asciiz "   +---+---+---+\n"
_str18:    .asciiz "\n"
_str19:    .asciiz "Player "
_str20:    .asciiz " has won!\n"
_str21:    .asciiz "Player "
_str22:    .asciiz " select move (e.g. a2)>"
_str23:    .asciiz "That is not a valid move!\n"
_str24:    .asciiz "That move is not possible!\n"
_str25:    .asciiz "It's a draw!\n"
_str26:    .asciiz "Play again? (y/n)> "
.text
fn_reset_prologue:    # reset starts here
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
la $ra, fn_reset_epilogue
fn_reset_content:    
# no local var detected
# assign ast.Assign@16b98e56
la $t9, _gv_a11
la $t8, _gv_empty
lb $s7, 0($t8)
sb $s7 0($t9)
# assign ast.Assign@7ef20235
la $s7, _gv_a12
la $t9, _gv_empty
lb $t9, 0($t9)
sb $t9 0($s7)
# assign ast.Assign@27d6c5e0
la $t9, _gv_a13
la $s7, _gv_empty
lb $s7, 0($s7)
sb $s7 0($t9)
# assign ast.Assign@4f3f5b24
la $s7, _gv_a21
la $t9, _gv_empty
lb $t9, 0($t9)
sb $t9 0($s7)
# assign ast.Assign@15aeb7ab
la $t9, _gv_a22
la $s7, _gv_empty
lb $s7, 0($s7)
sb $s7 0($t9)
# assign ast.Assign@7b23ec81
la $s7, _gv_a23
la $t9, _gv_empty
lb $t9, 0($t9)
sb $t9 0($s7)
# assign ast.Assign@6acbcfc0
la $t9, _gv_a31
la $s7, _gv_empty
lb $s7, 0($s7)
sb $s7 0($t9)
# assign ast.Assign@5f184fc6
la $s7, _gv_a32
la $t9, _gv_empty
lb $t9, 0($t9)
sb $t9 0($s7)
# assign ast.Assign@3feba861
la $t9, _gv_a33
la $s7, _gv_empty
lb $s7, 0($s7)
sb $s7 0($t9)
# Store default return value to $v0
li $v0, 0
fn_reset_epilogue:    
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
fn_full_prologue:    # full starts here
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
la $ra, fn_full_epilogue
fn_full_content:    
# local var n frameOffset(relative to $fp): -100
# pre-alloc 4 bytes for local var
subi $sp, $sp, 4
# assign ast.Assign@5b480cf9
addi $s7, $fp, -100
li $t9, 0
sw $t9 0($s7)
# if ast.If@6f496d9f
la $t9, _gv_a11
lb $s7, 0($t9)
la $t9, _gv_empty
lb $s7, 0($t9)
sne $t9, $s7, $s7
beq $t9, $zero, _if27_end
# assign ast.Assign@723279cf
addi $t9, $fp, -100
addi $s7, $fp, -100
lw $s7, 0($s7)
li $s7, 1
add $t9, $s7, $s7
sw $t9 0($t9)
b _if27_end
_if27_end:    nop
# if ast.If@10f87f48
la $t9, _gv_a21
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if28_end
# assign ast.Assign@b4c966a
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if28_end
_if28_end:    nop
# if ast.If@2f4d3709
la $t9, _gv_a31
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if29_end
# assign ast.Assign@4e50df2e
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if29_end
_if29_end:    nop
# if ast.If@1d81eb93
la $t9, _gv_a12
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if30_end
# assign ast.Assign@7291c18f
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if30_end
_if30_end:    nop
# if ast.If@34a245ab
la $t9, _gv_a22
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if31_end
# assign ast.Assign@7cc355be
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if31_end
_if31_end:    nop
# if ast.If@6e8cf4c6
la $t9, _gv_a32
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if32_end
# assign ast.Assign@12edcd21
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if32_end
_if32_end:    nop
# if ast.If@34c45dca
la $t9, _gv_a13
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if33_end
# assign ast.Assign@52cc8049
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if33_end
_if33_end:    nop
# if ast.If@5b6f7412
la $t9, _gv_a23
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if34_end
# assign ast.Assign@27973e9b
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if34_end
_if34_end:    nop
# if ast.If@312b1dae
la $t9, _gv_a33
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
sne $t9, $t9, $t9
beq $t9, $zero, _if35_end
# assign ast.Assign@7530d0a
addi $t9, $fp, -100
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 1
add $t9, $t9, $t9
sw $t9 0($t9)
b _if35_end
_if35_end:    nop
# if ast.If@27bc2616
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 9
seq $t9, $t9, $t9
beq $t9, $zero, _if37_else
# return ast.Return@3941a79c
li $t9, 1
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
b _if36_end
_if37_else:    # else
# return ast.Return@506e1b77
li $t9, 0
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
_if36_end:    nop
# release pre-allocated mem for local vars
addi $sp, $sp, 4
# Store default return value to $v0
li $v0, 0
fn_full_epilogue:    
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
fn_set_prologue:    # set starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg row offset: 1($fp)
# arg col offset: 5($fp)
# arg mark offset: 6($fp)
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
la $ra, fn_set_epilogue
fn_set_content:    
# local var r frameOffset(relative to $fp): -100
# pre-alloc 4 bytes for local var
subi $sp, $sp, 4
# assign ast.Assign@4fca772d
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
# if ast.If@9807454
addi $t9, $fp, 0
lb $t9, 0($t9)
li $t9, 97
seq $t9, $t9, $t9
beq $t9, $zero, _if39_else
# no local var detected
# if ast.If@3d494fbf
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if41_else
# no local var detected
# if ast.If@1ddc4ec2
la $t9, _gv_a11
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if43_else
# assign ast.Assign@133314b
la $t9, _gv_a11
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if42_end
_if43_else:    # else
# assign ast.Assign@b1bc7ed
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if42_end:    nop
b _if40_end
_if41_else:    # else
# no local var detected
# if ast.If@7cd84586
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 2
seq $t9, $t9, $t9
beq $t9, $zero, _if45_else
# no local var detected
# if ast.If@30dae81
la $t9, _gv_a12
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if47_else
# assign ast.Assign@1b2c6ec2
la $t9, _gv_a12
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if46_end
_if47_else:    # else
# assign ast.Assign@4edde6e5
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if46_end:    nop
b _if44_end
_if45_else:    # else
# no local var detected
# if ast.If@70177ecd
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 3
seq $t9, $t9, $t9
beq $t9, $zero, _if49_else
# no local var detected
# if ast.If@1e80bfe8
la $t9, _gv_a13
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if51_else
# assign ast.Assign@66a29884
la $t9, _gv_a13
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if50_end
_if51_else:    # else
# assign ast.Assign@4769b07b
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if50_end:    nop
b _if48_end
_if49_else:    # else
# no local var detected
# assign ast.Assign@cc34f4d
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
_if48_end:    nop
_if44_end:    nop
_if40_end:    nop
b _if38_end
_if39_else:    # else
# no local var detected
# if ast.If@17a7cec2
addi $t9, $fp, 0
lb $t9, 0($t9)
li $t9, 98
seq $t9, $t9, $t9
beq $t9, $zero, _if53_else
# no local var detected
# if ast.If@65b3120a
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if55_else
# no local var detected
# if ast.If@6f539caf
la $t9, _gv_a21
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if57_else
# assign ast.Assign@79fc0f2f
la $t9, _gv_a21
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if56_end
_if57_else:    # else
# assign ast.Assign@50040f0c
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if56_end:    nop
b _if54_end
_if55_else:    # else
# no local var detected
# if ast.If@2dda6444
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 2
seq $t9, $t9, $t9
beq $t9, $zero, _if59_else
# no local var detected
# if ast.If@5e9f23b4
la $t9, _gv_a22
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if61_else
# assign ast.Assign@4783da3f
la $t9, _gv_a22
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if60_end
_if61_else:    # else
# assign ast.Assign@378fd1ac
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if60_end:    nop
b _if58_end
_if59_else:    # else
# no local var detected
# if ast.If@49097b5d
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 3
seq $t9, $t9, $t9
beq $t9, $zero, _if63_else
# no local var detected
# if ast.If@6e2c634b
la $t9, _gv_a23
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if65_else
# assign ast.Assign@37a71e93
la $t9, _gv_a23
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if64_end
_if65_else:    # else
# assign ast.Assign@7e6cbb7a
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if64_end:    nop
b _if62_end
_if63_else:    # else
# no local var detected
# assign ast.Assign@7c3df479
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
_if62_end:    nop
_if58_end:    nop
_if54_end:    nop
b _if52_end
_if53_else:    # else
# no local var detected
# if ast.If@7106e68e
addi $t9, $fp, 0
lb $t9, 0($t9)
li $t9, 99
seq $t9, $t9, $t9
beq $t9, $zero, _if67_else
# no local var detected
# if ast.If@7eda2dbb
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if69_else
# no local var detected
# if ast.If@6576fe71
la $t9, _gv_a31
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if71_else
# assign ast.Assign@76fb509a
la $t9, _gv_a31
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if70_end
_if71_else:    # else
# assign ast.Assign@300ffa5d
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if70_end:    nop
b _if68_end
_if69_else:    # else
# no local var detected
# if ast.If@1f17ae12
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 2
seq $t9, $t9, $t9
beq $t9, $zero, _if73_else
# no local var detected
# if ast.If@4d405ef7
la $t9, _gv_a32
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if75_else
# assign ast.Assign@6193b845
la $t9, _gv_a32
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if74_end
_if75_else:    # else
# assign ast.Assign@2e817b38
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if74_end:    nop
b _if72_end
_if73_else:    # else
# no local var detected
# if ast.If@c4437c4
addi $t9, $fp, 1
lw $t9, 0($t9)
li $t9, 3
seq $t9, $t9, $t9
beq $t9, $zero, _if77_else
# no local var detected
# if ast.If@433c675d
la $t9, _gv_a33
lb $t9, 0($t9)
la $t9, _gv_empty
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if79_else
# assign ast.Assign@3f91beef
la $t9, _gv_a33
addi $t9, $fp, 5
lb $t9, 0($t9)
sb $t9 0($t9)
b _if78_end
_if79_else:    # else
# assign ast.Assign@1a6c5a9e
addi $t9, $fp, -100
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
sw $t9 0($t9)
_if78_end:    nop
b _if76_end
_if77_else:    # else
# no local var detected
# assign ast.Assign@37bba400
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
_if76_end:    nop
_if72_end:    nop
_if68_end:    nop
b _if66_end
_if67_else:    # else
# no local var detected
# assign ast.Assign@179d3b25
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
_if66_end:    nop
_if52_end:    nop
_if38_end:    nop
# return ast.Return@254989ff
addi $t9, $fp, -100
lw $t9, 0($t9)
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
# release pre-allocated mem for local vars
addi $sp, $sp, 4
# Store default return value to $v0
li $v0, 0
fn_set_epilogue:    
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
fn_printGame_prologue:    # printGame starts here
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
la $ra, fn_printGame_epilogue
fn_printGame_content:    
# no local var detected
# exprStmt ast.ExprStmt@5d099f62
la $t9, _str0
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@37f8bb67
la $t9, _str1
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@49c2faae
la $t9, _str2
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@20ad9418
la $t9, _str3
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@31cefde0
la $t9, _gv_a11
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@439f5b3d
la $t9, _str4
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@1d56ce6a
la $t9, _gv_a12
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@5197848c
la $t9, _str5
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@17f052a3
la $t9, _gv_a13
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@2e0fa5d3
la $t9, _str6
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@5010be6
la $t9, _str7
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@685f4c2e
la $t9, _str8
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@7daf6ecc
la $t9, _gv_a21
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@2e5d6d97
la $t9, _str9
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@238e0d81
la $t9, _gv_a22
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@31221be2
la $t9, _str10
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@377dca04
la $t9, _gv_a23
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@728938a9
la $t9, _str11
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@21b8d17c
la $t9, _str12
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@6433a2
la $t9, _str13
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@5910e440
la $t9, _gv_a31
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@6267c3bb
la $t9, _str14
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@533ddba
la $t9, _gv_a32
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@246b179d
la $t9, _str15
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@7a07c5b4
la $t9, _gv_a33
lb $t9, 0($t9)
move $a0, $t9
# print_c($a0)
li $v0, 11
syscall
# exprStmt ast.ExprStmt@26a1ab54
la $t9, _str16
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@3d646c37
la $t9, _str17
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@41cf53f9
la $t9, _str18
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# Store default return value to $v0
li $v0, 0
fn_printGame_epilogue:    
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
fn_printWinner_prologue:    # printWinner starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg player offset: 4($fp)
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
la $ra, fn_printWinner_epilogue
fn_printWinner_content:    
# no local var detected
# exprStmt ast.ExprStmt@5a10411
la $t9, _str19
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@2ef1e4fa
addi $t9, $fp, 0
lw $t9, 0($t9)
move $a0, $t9
# print_i($a0)
li $v0, 1
syscall
# exprStmt ast.ExprStmt@306a30c7
la $t9, _str20
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# Store default return value to $v0
li $v0, 0
fn_printWinner_epilogue:    
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
fn_switchPlayer_prologue:    # switchPlayer starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg currentPlayer offset: 4($fp)
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
la $ra, fn_switchPlayer_epilogue
fn_switchPlayer_content:    
# no local var detected
# if ast.If@b81eda8
addi $t9, $fp, 0
lw $t9, 0($t9)
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if81_else
# return ast.Return@68de145
li $t9, 2
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
b _if80_end
_if81_else:    # else
# return ast.Return@27fa135a
li $t9, 1
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
_if80_end:    nop
# Store default return value to $v0
li $v0, 0
fn_switchPlayer_epilogue:    
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
fn_get_mark_prologue:    # get_mark starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg player offset: 4($fp)
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
la $ra, fn_get_mark_epilogue
fn_get_mark_content:    
# no local var detected
# if ast.If@46f7f36a
addi $t9, $fp, 0
lw $t9, 0($t9)
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if83_else
# return ast.Return@421faab1
li $t9, 88
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
b _if82_end
_if83_else:    # else
# return ast.Return@2b71fc7e
li $t9, 79
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
_if82_end:    nop
# Store default return value to $v0
li $v0, 0
fn_get_mark_epilogue:    
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
fn_selectmove_prologue:    # selectmove starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg player offset: 4($fp)
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
la $ra, fn_selectmove_epilogue
fn_selectmove_content:    
# local var row frameOffset(relative to $fp): -97
# local var col frameOffset(relative to $fp): -101
# local var selected frameOffset(relative to $fp): -105
# local var success frameOffset(relative to $fp): -109
# local var mark frameOffset(relative to $fp): -110
# pre-alloc 14 bytes for local var
subi $sp, $sp, 14
# assign ast.Assign@5ce65a89
addi $t9, $fp, -105
li $t9, 1
sw $t9 0($t9)
_while84_start:    # while (ast.VarExpr@25f38edc)
addi $t9, $fp, -105
lw $t9, 0($t9)
beq $t9, $zero, _while85_end
# no local var detected
# exprStmt ast.ExprStmt@1a86f2f1
la $t9, _str21
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# exprStmt ast.ExprStmt@3eb07fd3
addi $t9, $fp, 0
lw $t9, 0($t9)
move $a0, $t9
# print_i($a0)
li $v0, 1
syscall
# exprStmt ast.ExprStmt@506c589e
la $t9, _str22
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# assign ast.Assign@69d0a921
addi $t9, $fp, -97
# $v0 = read_c()
li $v0, 12
syscall
move $t9, $v0
sb $t9 0($t9)
# assign ast.Assign@446cdf90
addi $t9, $fp, -101
# $v0 = read_i()
li $v0, 5
syscall
move $t9, $v0
sw $t9 0($t9)
# assign ast.Assign@799f7e29
addi $t9, $fp, -110
# precall for get_mark
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 4
# eval and store args
# storing arg player offset: 0($sp)
addi $t9, $fp, 0
lw $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: get_mark
jal fn_get_mark_prologue
# postreturn for get_mark
# dispose args space
addi $sp, $sp, 4
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
sb $t9 0($t9)
# assign ast.Assign@4b85612c
addi $t9, $fp, -109
# precall for set
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 6
# eval and store args
# storing arg row offset: 0($sp)
addi $t9, $fp, -97
lb $t9, 0($t9)
sw $t9 0($sp)
# storing arg col offset: 1($sp)
addi $t9, $fp, -101
lw $t9, 0($t9)
sw $t9 1($sp)
# storing arg mark offset: 5($sp)
addi $t9, $fp, -110
lb $t9, 0($t9)
sw $t9 5($sp)
# perform jump to callee: set
jal fn_set_prologue
# postreturn for set
# dispose args space
addi $sp, $sp, 6
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
sw $t9 0($t9)
# if ast.If@277050dc
addi $t9, $fp, -109
lw $t9, 0($t9)
li $t9, 0
seq $t9, $t9, $t9
beq $t9, $zero, _if87_else
# no local var detected
# exprStmt ast.ExprStmt@5c29bfd
la $t9, _str23
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
b _if86_end
_if87_else:    # else
# no local var detected
# if ast.If@7aec35a
addi $t9, $fp, -109
lw $t9, 0($t9)
li $t9, 0
li $t9, 1
sub $t9, $t9, $t9
seq $t9, $t9, $t9
beq $t9, $zero, _if89_else
# exprStmt ast.ExprStmt@67424e82
la $t9, _str24
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
b _if88_end
_if89_else:    # else
# assign ast.Assign@42110406
addi $t9, $fp, -105
li $t9, 0
sw $t9 0($t9)
_if88_end:    nop
_if86_end:    nop
b _while84_start
_while85_end:    nop
# release pre-allocated mem for local vars
addi $sp, $sp, 14
# Store default return value to $v0
li $v0, 0
fn_selectmove_epilogue:    
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
fn_won_prologue:    # won starts here
# reset frame pointer
move $fp, $sp
# setting args frame offset (relative to current fp)
# arg mark offset: 1($fp)
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
la $ra, fn_won_epilogue
fn_won_content:    
# local var r frameOffset(relative to $fp): -100
# pre-alloc 4 bytes for local var
subi $sp, $sp, 4
# assign ast.Assign@531d72ca
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
# if ast.If@22d8cfe0
la $t9, _gv_a11
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if90_end
# no local var detected
# if ast.If@579bb367
la $t9, _gv_a21
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if92_else
# no local var detected
# if ast.If@1de0aca6
la $t9, _gv_a31
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if93_end
# no local var detected
# assign ast.Assign@255316f2
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if93_end
_if93_end:    nop
b _if91_end
_if92_else:    # else
# no local var detected
# if ast.If@41906a77
la $t9, _gv_a22
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if95_else
# no local var detected
# if ast.If@4b9af9a9
la $t9, _gv_a33
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if96_end
# no local var detected
# assign ast.Assign@5387f9e0
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if96_end
_if96_end:    nop
b _if94_end
_if95_else:    # else
# no local var detected
# if ast.If@6e5e91e4
la $t9, _gv_a12
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if97_end
# no local var detected
# if ast.If@2cdf8d8a
la $t9, _gv_a13
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if98_end
# no local var detected
# assign ast.Assign@30946e09
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if98_end
_if98_end:    nop
b _if97_end
_if97_end:    nop
_if94_end:    nop
_if91_end:    nop
b _if90_end
_if90_end:    nop
# if ast.If@5cb0d902
la $t9, _gv_a12
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if99_end
# no local var detected
# if ast.If@46fbb2c1
la $t9, _gv_a22
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if100_end
# no local var detected
# if ast.If@1698c449
la $t9, _gv_a32
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if101_end
# no local var detected
# assign ast.Assign@5ef04b5
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if101_end
_if101_end:    nop
b _if100_end
_if100_end:    nop
b _if99_end
_if99_end:    nop
# if ast.If@5f4da5c3
la $t9, _gv_a13
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if102_end
# no local var detected
# if ast.If@443b7951
la $t9, _gv_a23
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if104_else
# no local var detected
# if ast.If@14514713
la $t9, _gv_a33
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if105_end
# no local var detected
# assign ast.Assign@69663380
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if105_end
_if105_end:    nop
b _if103_end
_if104_else:    # else
# no local var detected
# if ast.If@5b37e0d2
la $t9, _gv_a22
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if106_end
# no local var detected
# if ast.If@4459eb14
la $t9, _gv_a31
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if107_end
# no local var detected
# assign ast.Assign@5a2e4553
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if107_end
_if107_end:    nop
b _if106_end
_if106_end:    nop
_if103_end:    nop
b _if102_end
_if102_end:    nop
# if ast.If@28c97a5
la $t9, _gv_a21
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if108_end
# no local var detected
# if ast.If@6659c656
la $t9, _gv_a22
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if109_end
# no local var detected
# if ast.If@6d5380c2
la $t9, _gv_a23
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if110_end
# no local var detected
# assign ast.Assign@45ff54e6
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if110_end
_if110_end:    nop
b _if109_end
_if109_end:    nop
b _if108_end
_if108_end:    nop
# if ast.If@2328c243
la $t9, _gv_a31
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if111_end
# no local var detected
# if ast.If@bebdb06
la $t9, _gv_a32
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if112_end
# no local var detected
# if ast.If@7a4f0f29
la $t9, _gv_a33
lb $t9, 0($t9)
addi $t9, $fp, 0
lb $t9, 0($t9)
seq $t9, $t9, $t9
beq $t9, $zero, _if113_end
# no local var detected
# assign ast.Assign@45283ce2
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
b _if113_end
_if113_end:    nop
b _if112_end
_if112_end:    nop
b _if111_end
_if111_end:    nop
# return ast.Return@2077d4de
addi $t9, $fp, -100
lw $t9, 0($t9)
# store return value at $v0
move $v0, $t9
# jump to epilogue of current func (defined at $ra)
jr $ra
# release pre-allocated mem for local vars
addi $sp, $sp, 4
# Store default return value to $v0
li $v0, 0
fn_won_epilogue:    
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
# local var playing frameOffset(relative to $fp): -100
# local var player frameOffset(relative to $fp): -104
# local var mark frameOffset(relative to $fp): -105
# local var yesno frameOffset(relative to $fp): -106
# pre-alloc 10 bytes for local var
subi $sp, $sp, 10
# assign ast.Assign@7591083d
la $t9, _gv_empty
li $t9, 32
sb $t9 0($t9)
# assign ast.Assign@77a567e1
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
# exprStmt ast.ExprStmt@736e9adb
# precall for reset
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: reset
jal fn_reset_prologue
# postreturn for reset
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# exprStmt ast.ExprStmt@6d21714c
# precall for printGame
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: printGame
jal fn_printGame_prologue
# postreturn for printGame
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# assign ast.Assign@108c4c35
addi $t9, $fp, -104
li $t9, 1
sw $t9 0($t9)
_while114_start:    # while (ast.VarExpr@4ccabbaa)
addi $t9, $fp, -100
lw $t9, 0($t9)
beq $t9, $zero, _while115_end
# no local var detected
# exprStmt ast.ExprStmt@4bf558aa
# precall for selectmove
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 4
# eval and store args
# storing arg player offset: 0($sp)
addi $t9, $fp, -104
lw $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: selectmove
jal fn_selectmove_prologue
# postreturn for selectmove
# dispose args space
addi $sp, $sp, 4
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# assign ast.Assign@2d38eb89
addi $t9, $fp, -105
# precall for get_mark
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 4
# eval and store args
# storing arg player offset: 0($sp)
addi $t9, $fp, -104
lw $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: get_mark
jal fn_get_mark_prologue
# postreturn for get_mark
# dispose args space
addi $sp, $sp, 4
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
sb $t9 0($t9)
# exprStmt ast.ExprStmt@5fa7e7ff
# precall for printGame
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: printGame
jal fn_printGame_prologue
# postreturn for printGame
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# if ast.If@4629104a
# precall for won
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 1
# eval and store args
# storing arg mark offset: 0($sp)
addi $t9, $fp, -105
lb $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: won
jal fn_won_prologue
# postreturn for won
# dispose args space
addi $sp, $sp, 1
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
beq $t9, $zero, _if117_else
# no local var detected
# exprStmt ast.ExprStmt@27f8302d
# precall for printWinner
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 4
# eval and store args
# storing arg player offset: 0($sp)
addi $t9, $fp, -104
lw $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: printWinner
jal fn_printWinner_prologue
# postreturn for printWinner
# dispose args space
addi $sp, $sp, 4
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
# assign ast.Assign@4d76f3f8
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
b _if116_end
_if117_else:    # else
# if ast.If@2d8e6db6
# precall for full
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: full
jal fn_full_prologue
# postreturn for full
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
li $t9, 1
seq $t9, $t9, $t9
beq $t9, $zero, _if119_else
# no local var detected
# exprStmt ast.ExprStmt@23ab930d
la $t9, _str25
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# assign ast.Assign@4534b60d
addi $t9, $fp, -100
li $t9, 0
sw $t9 0($t9)
b _if118_end
_if119_else:    # else
# no local var detected
# assign ast.Assign@3fa77460
addi $t9, $fp, -104
# precall for switchPlayer
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 4
# eval and store args
# storing arg currentPlayer offset: 0($sp)
addi $t9, $fp, -104
lw $t9, 0($t9)
sw $t9 0($sp)
# perform jump to callee: switchPlayer
jal fn_switchPlayer_prologue
# postreturn for switchPlayer
# dispose args space
addi $sp, $sp, 4
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
sw $t9 0($t9)
_if118_end:    nop
_if116_end:    nop
# if ast.If@619a5dff
addi $t9, $fp, -100
lw $t9, 0($t9)
li $t9, 0
seq $t9, $t9, $t9
beq $t9, $zero, _if120_end
# no local var detected
# exprStmt ast.ExprStmt@1ed6993a
la $t9, _str26
move $a0, $t9
# print_s($a0)
li $v0, 4
syscall
# assign ast.Assign@7e32c033
addi $t9, $fp, -106
# $v0 = read_c()
li $v0, 12
syscall
move $t9, $v0
sb $t9 0($t9)
# if ast.If@7ab2bfe1
addi $t9, $fp, -106
lb $t9, 0($t9)
li $t9, 121
seq $t9, $t9, $t9
beq $t9, $zero, _if122_else
# no local var detected
# assign ast.Assign@497470ed
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
# exprStmt ast.ExprStmt@63c12fb0
# precall for reset
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: reset
jal fn_reset_prologue
# postreturn for reset
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
b _if121_end
_if122_else:    # else
# no local var detected
# if ast.If@b1a58a3
addi $t9, $fp, -106
lb $t9, 0($t9)
li $t9, 89
seq $t9, $t9, $t9
beq $t9, $zero, _if123_end
# no local var detected
# assign ast.Assign@6438a396
addi $t9, $fp, -100
li $t9, 1
sw $t9 0($t9)
# exprStmt ast.ExprStmt@e2144e4
# precall for reset
# Store current $ra in stack mem
subi $sp, $sp, 4
sw $ra 0($sp)
# Store current $fp in stack mem
subi $sp, $sp, 4
sw $fp 0($sp)
# pre-allocate space for args
subi $sp, $sp, 0
# eval and store args
# perform jump to callee: reset
jal fn_reset_prologue
# postreturn for reset
# dispose args space
addi $sp, $sp, 0
# restore $fp from stack mem
lw $fp, 0($sp)
addi $sp, $sp, 4
# restore $ra from stack mem
lw $ra, 0($sp)
addi $sp, $sp, 4
# extract return value
move $t9, $v0
b _if123_end
_if123_end:    nop
_if121_end:    nop
b _if120_end
_if120_end:    nop
b _while114_start
_while115_end:    nop
# release pre-allocated mem for local vars
addi $sp, $sp, 10
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
