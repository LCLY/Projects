.section	.data
hexadecimal:	.space 100
.section	.text

.global		printinteger
printinteger:

	mov r4, #120
	cmp r1, r4
	beq printx

	mov r4, #100
	cmp r1, r4
	beq printd

	printx:
	ldr r5, =hexadecimal;
	mov r6, #0
	mov r7, #0
	str r6, [r5, r7]
	add r7, r7, #1
	mov r6, #120
	str r6, [r5, r7]

	printd:
	bx lr
	