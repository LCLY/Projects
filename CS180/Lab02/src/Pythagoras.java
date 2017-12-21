/**
 * CS180 - Lab02: Pythagoras
 *This program will compute the hypotenuse of a right triangle given its other two sides
 *@author Ley Yen Choo, lchoo@Purdue.edu, sec01
 */
public class Pythagoras {
    public double getHypotenuse(int a, int b){
        double result;
        result = Math.sqrt(a * a + b * b);
        return result;
    }

}
