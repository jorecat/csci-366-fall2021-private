# CSCI 366 HW1: MIPS assembly component.
# Author: Brian Jore
# 10/4/21
# Print from 1 to 10.
.text
main:
	li $t0, 1	#move 1 into $t0
sum_loop:
	li $v0, 1 	#print value
	move $a0, $t0
	syscall
	addi $t0, $t0, 1
	blt $t0, 11, sum_loop
	add $t0, $t0, $v0
	
	li $v0, 10 #exit
	syscall
	
