#include "memcmp.h"
int memcmp(const void *s1, const void *s2, size_t n) {

  unsigned char* string1 =(unsigned char*) s1;
  unsigned char* string2 =(unsigned char*) s2;
  size_t i ;

  for(i = 0 ; i < n ; i++ ,  string1++ ,  string2++){
  	
	if(*string1 < *string2){
	   
	   return -1;

	}else if (*string1 > *string2){
	
	   return 1;
	}
  
  
  }
  return 0;

}
