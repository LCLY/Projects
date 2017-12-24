#include <stdio.h>
#include <stdlib.h>

char* sub_string(char* in_string, int start_index, int end_index){
     int i = 0;
     char* out_string = (char*)malloc(sizeof(char)*100);
     int j;
     for(j = start_index-1; j < end_index; j++){
       out_string[i] = in_string[j];
      i++;
     }
     
     return out_string;
          
}

/*int main(){
    char in_string[100];
    int start_index;
    int end_index;

    printf("Enter a string: ");
    fgets(in_string, 100, stdin);
    printf("Enter the start index: ");
    scanf("%d", &start_index);
    printf("Enter the end index: ");
    scanf("%d", &end_index);

   
     char* output;
     output = sub_string(in_string, start_index, end_index);
     printf(output);
     printf("\n");
            

    return 0;

}*/