
public class BubbleSorter/**
 * Homework 07
 * This program returns the elements of array sorted from lowest to highest.
 * @author Ley Yen Choo, lab sec 01
 * @version February, 9 2016
 */{
    int temp = 0;
    /**
     *
     * @param array The original array that will be bubblesorted
     *              which means, the array will become ascending
     * @return the array that had been bubblesorted
     */
    int [] bubbleSort(int [] array) {
        if ( array == null ) {
            return null;
        }
        else {

                /*
                 * In bubble sort, we basically traverse the array from first
                 * to array_length - 1 position and compare the element with the next one.
                 * Element is swapped with the next element if the next element is greater.
                 */
            for (int i = 0 ; i < array.length ; i++ ) {
                for (int j = 1 ; j < (array.length - i) ; j++ ) {

                    if ( array[ j - 1 ] > array[ j ] ) {
                        //swap elements
                        temp = array[ j - 1 ];
                        array[ j - 1 ] = array [ j ];
                        array[ j ] = temp;

                    }
                }

            }

        }
        return array;
    }
    /**
     * @param args
     * The main method prints out the bubblesort
     */
    public static void main(String[] args) {
        BubbleSorter b = new BubbleSorter();
        int[] array = b.bubbleSort(new int [] {5, 4, 3, 2, 1}); //should return array "{1, 2, 3, 4, 5}"
        for (int i = 0 ; i < array.length ; i++ ) {
            System.out.print( array[i] + " " );
        }
        System.out.println();

        int[] otherArray = b.bubbleSort(new int [] {95, 97, 100, 96}); //should return array "{95, 96, 97, 100}"
        for (int i = 0 ; i < otherArray.length ; i++ ) {

            System.out.print( otherArray[ i ] + " " );
        }
        System.out.println();
        System.out.println(b.bubbleSort(null) == null); //prints "true"

    }
}