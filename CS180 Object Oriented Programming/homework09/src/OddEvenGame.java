/**
 * Homework 09
 * This program is a game in which you need to keep track of the sum of all odd and even numbers occurring on a dice
 *
 * @author Ley Yen Choo, lab sec 01
 * @version February, 15 2016
 */
public class OddEvenGame {
    int oddsum;  //non-static field for keeping track of a local sum of odd numbers in a game.
    int evensum; //non-static field for keeping track of a local sum of even numbers in a game.
    static int totalOddSum;  //static field for keeping track of total sum of odd numbers across all games.
    static int totalEvenSum;  //static field for keeping track of total sum of even numbers across all games.

    /**
     * @param array this is the array that is going to sum up the even numbers in local game
     *              and the total even numbers throughout the entire game.
     */
    public void addEven(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) { //if the remainder equals to zero it is even number
                evensum = evensum + array[i]; //add even sum in local game
            }
        }
        totalEvenSum = totalEvenSum + evensum;
    }  //adds the value of every Even element in array to both the field evensum and the static field totalEvenSum.

    /**
     * @param array this is the array that is going to sum up the odd numbers in local game
     *              and the total odd numbers throughout the entire game.
     */
    public void addOdd(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) { //use remainder to find whether it is odd or not
                oddsum = oddsum + array[i]; //add odd in local game
            }
        }
        totalOddSum = totalOddSum + oddsum; //the total odd number throughout the whole game
    }  //adds the value of every Odd element in array to both the field oddsum and the static field totalOddSum.

    /**
     * @param
     * @return the oddsum in the local games
     */
    public int getOddSum() {

        return oddsum;

    }  //returns the value of the field oddsum.

    /**
     * @return the evensum in the local game
     */
    public int getEvenSum() {

        return evensum;

    } //returns the value of the field evensum.

    /**
     * @return the total odd sum throughout the whole game
     */
    public int getTotalOddSum() {

        return totalOddSum;

    } //returns the value of the static field totalOddSum.

    /**
     * @return the total even sum throughout the whole game
     */
    public int getTotalEvenSum() {

        return totalEvenSum;

    } //returns the value of the static field totalEvenSum.

    /**
     * @param args the main method to provide all the numbers
     */
    public static void main(String[] args) {
        OddEvenGame obj1 = new OddEvenGame();
        OddEvenGame obj2 = new OddEvenGame();
        obj1.addEven(new int[]{1, 2, 3, 4, 5, 6});
        obj1.addOdd(new int[]{1, 2, 3, 4, 5, 6});

        obj2.addEven(new int[]{100, 101, 241, 302, 501, 623});
        obj2.addOdd(new int[]{100, 101, 241, 302, 501, 623});

        System.out.println(obj1.getEvenSum()); // Prints 12.
        System.out.println(obj2.getOddSum()); // Prints 1466.
        System.out.println(obj1.getTotalOddSum()); // Prints 1475.
        System.out.println(obj2.getTotalEvenSum()); // Prints 414.
    }
}
