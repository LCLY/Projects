#include <stdio.h>
#include <stdlib.h>

int main(){

	int start_index;
	int end_index;
	char * in_string;
	char * out_string;
	char* sub_string(char*, int, int);

	in_string = (char*)malloc(100);
	
	printf("Enter a string: ");
	scanf("%s", in_string);

	printf("Enter the start index: ");
	scanf("%d", &start_index);

	printf("Enter the end index: ");
	scanf("%d", &end_index);

	out_string = sub_string(in_string, start_index, end_index);

	printf("The substring of the given string is '", out_string, "'\n");

	return 0;

}
