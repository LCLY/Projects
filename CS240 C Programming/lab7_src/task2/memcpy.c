#include "memcpy.h"
void * memcpy(void *dest, const void *src, size_t n) {
	
unsigned char* string2 = (unsigned char*)src;
unsigned char* string1 = (unsigned char*)dest;
size_t i;

for(i = 0 ; i < n ; i++ , string1++ , string2++){
	
	*string1 = *string2;


}
	return dest;
}
