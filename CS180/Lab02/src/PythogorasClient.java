/**
 * PythagorasClient
 * Todo: To test the method pythogoras
 * @author Ley Yen Choo, lchoo@purdue.edu
 */
import java.util.Scanner;
public class PythogorasClient {
    public static void main (String[]args){
        Scanner s = new Scanner (System.in);
        Pythagoras p = new Pythagoras();
        System.out.println("Enter the length of side 'a': ");
        int side1 = s.nextInt();
        System.out.println("Enter the length of side 'b': ");
        int side2 = s.nextInt();
        double hypotenuse = p.getHypotenuse(side1 , side2);
        System.out.println("Side 'a' : " + side1);
        System.out.println("Side 'b' : " + side2);
        System.out.println("Hypotenuse: " + hypotenuse);
    }
}
