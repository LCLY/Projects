/**
 * Created by lchoo on 1/12/16.
 */
import java.util.Scanner;
public class Temperature {
    /*
    CS180 - Lab 01
    This program will convert Fahrenheit to Celcius and vice versa.
    @author Ley Yen Choo, lchoo@purdue.edu, Lab01
     */

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double fahrenheit;
        double celcius;
        fahrenheit=212;
        System.out.println("Enter the temperature in Fahrenheit: ");
        fahrenheit=input.nextDouble();
        celcius=((fahrenheit-32)*5)/9;
        System.out.println("Fahrenheit: "+fahrenheit);
        System.out.println("Celcius: "+celcius);

        System.out.println("Enter the temperature in Celcius: ");
        celcius=input.nextDouble();
        celcius=((fahrenheit-32)*5)/9;
        System.out.println("Celsiu: "+celcius);
        System.out.println("Fahrenheit "+fahrenheit);

    }
}

