/**
 * Homework 03
 * This program use the method log10() from the Math class in Java.
 * @author Ley Yen Choo, lab sec 01
 * @version January, 25 2016
 */
import java.util.Scanner;
public class Logarithm {
    double logToBase10(int x) {
        if (x <= 0) {
            return -1;
        }
        else {
            return Math.log10(x);
        }

    }
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        Logarithm obj = new Logarithm();
        System.out.print("Please insert your number: ");
        int x = input.nextInt();
        double y = obj.logToBase10(x);
        if (y == -1) {
            System.out.println("-1");
        }
        else {
            System.out.println("The base 10 logarithm of the number is " + y );
        }



    } //end main method
} //end class
