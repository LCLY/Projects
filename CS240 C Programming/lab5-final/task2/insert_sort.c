// Implementation of Insertion Sort in Descending order
void insert_sort (int array[] , int num){
	int i;
	int j;

	for(i = 0 ; i < num ; i++){
		
		j = i - 1;

		while( j >= 0 && array[j] <  array[j+1]){
			int temp = array[j];
			array[j] = array[j+1];
			array[j+1] = temp;
			j = j - 1;
		}

	}

}








