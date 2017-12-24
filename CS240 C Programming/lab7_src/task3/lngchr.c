#include "lngchr.h"
char *lngchr(long *l, char c) {
size_t i;
char* string  = (char*)l;


for(i = 0 ; i < sizeof(long); i++ , string++){

	if(*string == c){
	 return string;
	}
}


	return NULL;
}
