
#include <stdlib.h>
#include "mystring.h"

int mystrcmp(char * s1, char * s2) {
	int len_S1 = 0;
	int len_S2 = 0;
	int i = 0;
	int j = 0;
	int result =0;

	for(i = 0; s1[i] != '\0'; i++){
		len_S1++;
	}//to find the length of the first string
	
	
	for(j = 0; s2[j] != '\0'; j++){
		len_S2++;
	}
	int pos;
	for(pos = 0; pos < len_S1; pos++){
		if(s1[pos] > s2[pos])
			result = 1;
		else if(s2[pos] > s1[pos])
			result = -1;
		else if(s1[pos] == s2[pos] && pos == (len_S1 - 1))
			result = 0;
		else continue;
	
	}
	if(len_S1 == len_S2){
		result =  0;
	}

	if(len_S1 > len_S2){
		result =  1;
	}
	if(len_S2 > len_S1){
		result = -1;
	}

	return result;
}


