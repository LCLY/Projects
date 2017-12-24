@arm assembly template
.section	.data
askinputstring:	.asciz "Enter a string: "
askstartindex:	.asciz "Enter the start index: "
askendindex: 	.asciz "Enter the end index: "
result:		.asciz "The substring of the given string is '%s'\n"
int_pattern:	.asciz "%d"
string_pattern:	.asciz "%s"
start_index:	.word 0
end_index:	.word 0
in_string:	.space 100

.section	.text
.global		main
main: 
	ldr r0, =askinputstring @load message into first argument
	bl printf
	
	ldr r0, =string_pattern @scanf string
	ldr r1, =in_string
	bl scanf

 	ldr r0, =askstartindex 
	bl printf
	
	ldr r0, =int_pattern
	ldr r1, =start_index
	bl scanf
	ldr r4, =start_index
	
	ldr r0, =askendindex
	bl printf
	
	ldr r0, =int_pattern
	ldr r1, =end_index
	bl scanf
	ldr r5, =end_index
	
	ldr r0, =in_string
	ldr r1, [r4]
	ldr r2, [r5]
	bl sub_string
	mov r1, r0	
	
	ldr r0, =result @load message
	bl printf
	
	
	mov r7, $1 @exit syscall
	svc $0	@wake kernel
	.end
	mov r0, $0
	bl flush
