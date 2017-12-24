// Implementation of Selection Sort in Descending order
void select_sort( int array[] , int num ){
	int i;
	int j;
	int max;

	for( i = 0 ; i < num - 1; i ++ ){
		max = i;

		for( j = i + 1 ; j < num ; j ++ ){
			if(array[j] > array[max]){
				max = j;			
			}
		}
			int temp = array[i];
			array[i] = array[max];
			array[max] = temp;
	}
}










