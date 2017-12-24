import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * CS180 - Lab 03 - StringManipulator
 *
 * This program designs a program StringManipulator that
 * generates an e-mail address from a student's full name.
 *
 * @author Ley Yen Choo, lchoo@purdue.edu
 */
import java.util.Scanner;
public class StringManipulator {
    /**
     * This method generates the username
     * @param fullName contains the person's
     * first and last name separated by a name
     * @return user name
     */
    public String makeUserName(String fullName){
        if(fullName == null){
            return null;
        }
       String substr1=fullName.substring(0, 1);
        int position = fullName.indexOf(" ");
        String substr2=fullName.substring(position+1);
        String x = substr1.concat(substr2);

            return x.toLowerCase();
    }
    public String makeEmail(String userName, String domain){
        String y = "@";
        String email = userName.concat(y+domain);
        return email.toLowerCase();

    }

    public static void main (String [] args){
        Scanner input = new Scanner(System.in);
        StringManipulator x = new StringManipulator();

        // Get input from user
        System.out.println("Enter the Full Name of the person. First Name followed by Last Name.");
        String fullName = input.nextLine();
        System.out.println("Enter the domain");
        String domain = input.nextLine();

        // Print output
        String username = x.makeUserName(fullName);
        System.out.println("The user name for the person is: "+username);
        String email = x.makeEmail(username, domain);
        System.out.print("The email id for the person is: "+email);

    }//end method
}//end class
