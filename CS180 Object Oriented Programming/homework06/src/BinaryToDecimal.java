/**
 * Homework 06
 * This program returns the decimal form from the binary form.
 *
 * @author Ley Yen Choo, lab sec 01
 * @version February, 4 2016
 */
public class BinaryToDecimal {
    /**
     * @param args The main method prints out the decimal of each binary number
     */
    public static void main(String[] args) {
        BinaryToDecimal obj = new BinaryToDecimal();
        System.out.println(obj.binaryToDecimal(1001000)); // Prints 72
        System.out.println(obj.binaryToDecimal(1011000)); // Prints 88
    }

    /**
     * @param binary The binary number that will be converted to decimal
     * @return decimal of the binary
     */
    public int binaryToDecimal(int binary) {
        int decimal = 0;
        int temp;
        int power = 0;
        while (true) {
            if (binary == 0) {
                break;
            } else {
                temp = binary % 10;
                decimal += temp * Math.pow(2, power);
                binary = binary / 10;
                power++;
            }
        }
        return decimal;
    }
}
