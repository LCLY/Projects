
#include <string.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

#include "mystring.h"
void test1() {
	int result;
	char *s1, *s2;

	s1 = "banana";
	s2 = "apple";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "apple";
	s2 = "banana";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "appleapple";
	s2 = "applebanana";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "applebanana";
	s2 = "appleapple";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "apple";
	s2 = "apple";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "appleapple";
	s2 = "apple";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "apple";
	s2 = "applebanana";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);

	s1 = "applebanana";
	s2 = "apple";
	result = mystrcmp(s1,s2);
	printf("mystrcmp(\"%s\", \"%s\")=%d\n",s1, s2, result);
}

int main(int argc, char ** argv) {

	char * test;
	
	if (argc <2) {
		printf("Usage: test_resizable_table test1|test2|...test7\n");
		exit(1);
	}

	test = argv[1];
	printf("Running %s\n", test);
	if (strcmp(test, "test1")==0) {
		test1();
	}
	else {
	        printf("Test not found!!n");
		exit(1);
	}

	return 0;

}
