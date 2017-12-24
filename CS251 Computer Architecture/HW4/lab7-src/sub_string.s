.section	.data
result: 	.space 100
.section	.text

.global		sub_string
sub_string:
	
	ldr r5, =result
	mov r6, #0 

	sub r1, r1, #1 @adjust start index
	sub r2, r2, #1 @adjust end index
	
	loop:
	ldrb r4, [r0, r1] @load the string, load the byte of the index into r4
	strb r4, [r5, r6] @store byte located in r4 into r5
	add r1, r1, #1 @increment index
	add r6, r6, #1
	cmp r1, r2
	ble loop

	mov r1, #0	
	strb r1, [r5, r6]
	ldr r0, =result
	mov r15, r14		

	