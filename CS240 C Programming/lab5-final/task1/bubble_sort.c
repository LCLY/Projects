
void bubble_sort(int array[], int num){
	// TODO: Implement Bubble Sort
	int i;
	int j;
	
	for(i = 0 ; i < num ; i++){
		
		for(j = 0 ; j < num - 1 ; j++){
			
			if(array[j] > array[j+1]){
			int temp = array[j];
			array[j] = array[j+1];
			array[j+1] = temp;			
			}
							
		}
	
	}






}
