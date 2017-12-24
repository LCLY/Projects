/**
 * Created by lchoo on 2/9/16.
 */
import java.util.Scanner;
public class MathToolsClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MathTools obj = new MathTools();
        boolean ans = true;
        do {
            System.out.println("Select a number from the menu choices:");
            System.out.println("1 - Absolute Value");
            System.out.println("2 - Power");
            System.out.println("3 - Nth Root");
            System.out.println("4 - Scientific Notation");

            int choose = input.nextInt();
            System.out.println(choose);
            if (choose < 1 || choose > 4) {
                System.out.println("Invalid option.");
            }
            if (choose == 1) {
                System.out.println("***Absolute Value***");
                System.out.println("Enter the value: ");
                double number = input.nextDouble();
                double absolute = obj.absoluteValue(number);
                System.out.println(absolute);
            }
            if (choose == 2) {
                System.out.println("***Power***");
                System.out.println("Enter the base: ");
                double base = input.nextDouble();
                System.out.println("Enter the exponent: ");
                int exponent = input.nextInt();
                double power = obj.power(base, exponent);
                System.out.println(power);
            }
            if (choose == 3) {
                System.out.println("***Nth Root***");
                System.out.println("Enter the value: ");
                double value = input.nextDouble();
                System.out.println("Enter the root: ");
                int root = input.nextInt();
                double nthRoot = obj.nthRoot(value, root);
                System.out.println(value + "^" + "(" + root + ") = " + nthRoot);
            }
            if (choose == 4) {
                System.out.println("*** Scientific Notation***");
                System.out.println("Enter your number: ");
                double number = input.nextInt();
                String scNotation = obj.scientificNotation(number);
                System.out.println(scNotation);
            }

        }while(!ans);
    }

}

