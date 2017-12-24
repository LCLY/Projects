/**
 * Homework 05
 * This program returns the exponent that the base should be raised to
 * @author Ley Yen Choo, lab sec 01
 * @version February, 3 2016
 */
public class PowerCalculator {
    /**
     *@param args
     *The main method prints out the base of each number
     */
    public static void main(String [] args) {
        PowerCalculator p = new PowerCalculator();
        System.out.println(p.whatPower( 8 , 2 ));  //prints "3"
        System.out.println(p.whatPower( 100 , 10 ));  //prints "2"
        System.out.println(p.whatPower( 125 , 5 ));  //prints "3"
        System.out.println(p.whatPower( 12 , 5) );  //prints "-1"
    }

    /**
     * the whatPower method takes 2 argument, number and base,
     * and find the exponent of the number
     *@param number The number to be divide by base
     *@param base The base used to divide the number
     *@return exponent or -1 if there's no exponent
     */

    public int whatPower(int number, int base)  {
        int p = 0;
        int m = 1;
        while (m < number ) {
            m = m * base;
            p++;
        }
        if ( m == number ) {
            return p;
        }
        else
            return -1;

    }
}


