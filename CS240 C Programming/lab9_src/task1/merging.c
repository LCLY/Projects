#include "merging.h"
#include <stdio.h>
#include <stdlib.h>

int*merging(int* nums1, int* nums2, int size1, int size2){
	
int totalsize = size1 + size2;
int *merge = malloc(totalsize * sizeof(int));

int counter1 = 0;
int counter2 = 0;
int i;

   for(i = 0 ; i < totalsize ; i++){

      if(nums1[counter1] <= nums2[counter2] && counter1 < size1 && counter2 < size2){
	 
	         merge[i] = nums1[counter1];
             counter1++;
	  

      }else if(nums2[counter2] <= nums1[counter1] && counter1 < size1 && counter2 < size2){
 		 
	         merge[i] = nums2[counter2];
             counter2++;
	  
      }else if(counter1 == size1){

         merge[i] = nums2[counter2];
         counter2++;

      }else if(counter2 == size2){

         merge[i] = nums1[counter1];
         counter1++;
      }
	

    }
    return merge;

}
