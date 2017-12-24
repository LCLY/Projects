#include "mysort.h"
#include <alloca.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>



/* Compare two integer numbers*/
int compareInt( void * a, void * b )
{
  return *(int*)a - *(int*) b;
}



// Compare students by name 
// Hint: use strcmp 
int compareStudentByName( void * a, void * b )
{
  Student * sa = (Student *) a;
  Student * sb = (Student *) b;
  // Please complete this function 

  if(strcmp((char*)sa -> name, (char*)sb -> name) < 0){

  	return -1;

  }else if (strcmp((char*)sa -> name, (char*)sb -> name) > 0){

	return 1;

  }else if (strcmp((char*)sa -> name, (char*)sb -> name) == 0){

	return 0;

  }
   
}





  /* Compare students by grade first.
     return 1 if grade a is larger than grade b
     return -1 if grade a is less than grade b
     if grades are the same then 
     compare alphabetically
  */ 

int compareStudentByGrade( void * a, void * b )
{
	
  Student* sa = (Student*)a;
  Student* sb = (Student*)b;
  // Please complete this function 

  if(sa -> grade > sb -> grade){

 	return 1;

  }else if(sb -> grade > sa -> grade){

	return -1;  

  }else if(sa -> grade == sb -> grade){

	return compareStudentByName(sa, sb);

  }
	
  return 0;
}




//
// Sort an array of element of any type
// it uses "compFunc" to sort the elements.
// The elements are sorted such as:
//
// if ascending != 0
//   compFunc( array[ i ], array[ i+1 ] ) <= 0
// else
//   compFunc( array[ i ], array[ i+1 ] ) >= 0
//
// See test_sort to see how to use mysort.
//
void mysort( int n,                      // Number of elements
	     int elementSize,            // Size of each element
	     void * array,               // Pointer to an array
	     int ascending,              // 0 -> descending; 1 -> ascending
	     CompareFunction compFunc )  // Comparison function.
{
	// Please complete this function 
	void* tmp = malloc(elementSize);
	int i, j;
	int change;
	change = 0;
	

	for(i = 0; i < n ; i++){
			
			for(j = 0; j < n-i-1; j++){
 				void* p1 = (void*)((char*)array + j* elementSize);
				void* p2 = (void*)((char*)array +(j + 1) * elementSize);
				if(ascending ){
					if(compFunc( p1 , p2 ) > 0){

						memcpy(tmp, p1, elementSize);
						memcpy(p1, p2, elementSize);
						memcpy(p2, tmp, elementSize);
					
					}

				}else if(compFunc(p1, p2) < 0){

						memcpy(tmp, p1, elementSize);
						memcpy(p1, p2, elementSize);
						memcpy(p2, tmp, elementSize);				

				}	
	
	     }

		
	}




}

