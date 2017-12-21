/**
 * CS180 Lab02 : PythagorasSimpleGUI
 * This program is to test a new way to get input from the user
 * through the use of a Graphical User Interface (GUI)
 * @author Ley Yen Choo, lchoo@purdue.edu
 */
import javax.swing.JOptionPane;
public class PythagotasSimpleGUI {
    public static void main (String [] args){
        Pythagoras p = new Pythagoras();
        String input1 = JOptionPane.showInputDialog("Enter the length of side 'a'");
        int side1 = Integer.parseInt(input1);
        String input2 = JOptionPane.showInputDialog("Enter the length of side 'b'");
        int side2 = Integer.parseInt(input2);
        double hypotenuse = p.getHypotenuse(side1 , side2);
        JOptionPane.showMessageDialog(null, "The hypotenuse is: "+hypotenuse);

    }

}
