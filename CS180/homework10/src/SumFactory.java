/**
 * Homework 10
 * This program produce local sum and total sum of the array
 * @author Ley Yen Choo, lab sec 01
 * @version February, 15 2016
 */
public class SumFactory {
    int sum; //non-static field for keeping track of a local sum
    static int totalSum;  //static field for keeping track of total sum across all factories

    /**
     *@param array array that produce sum and total sum throughout the program
     */
    public void add(int[] array) {
        for(int i = 0 ; i < array.length ; i++){
            sum = sum + array[i];
        }
        totalSum = totalSum + sum;
    } //adds the value of every element in array to both the field sum and the static field totalSum.

    /**
     *@return the sum of every local method
     */
    public int getSum() {

        return sum;

    } //returns the value of the field sum.

    /**
     * @return the total sum of the array throughout the whole program
     */
    public int getTotalSum() {

        return totalSum;

    } //returns the value of the static field totalSum.

    /**
     * @param args main method that provides the array
     */
    public static void main(String[] args) {
        SumFactory s1 = new SumFactory();
        SumFactory s2 = new SumFactory();
        SumFactory s3 = new SumFactory();
        s1.add(new int[]{1, 2, 3});
        s2.add(new int[]{100, 200, 300});
        s3.add(new int[]{16, 32, 64, 128, 256});
        System.out.println(s1.getSum()); //prints "6"
        System.out.println(s2.getSum()); //prints "600"
        System.out.println(s3.getSum()); //prints "496"
        System.out.println(s1.getTotalSum()); //prints "1102
    }
}
