/**
 * The User class to represents a user's information from the server's point of view. Each user should have
 * their own mailbox (inbox), which is implemented using the DynamicBuffer class.
 * For this class, you must implement the following methods:
 */
public class User {
    private String username;
    private String password;
    private DynamicBuffer a = new DynamicBuffer(20);
    private static long id;

    public static synchronized long createID() {
        return id++;
    }

    //Your class must have a constructor that accepts the username and password.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // This method returns the user's username.
    public String getName() {
        return this.username;
    }

    public static boolean checkBoth(String username, String password) {
        if (username.length() >= 1 && username.length() <= 20 &&
                password.length() >= 4 && password.length() <= 40) {
            String[] checkuser = username.split("[A-Za-z0-9]");
            String[] checkpassword = password.split("[A-Za-z0-9]");
            if (checkuser.length == 0 && checkpassword.length == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    /* Returns true if the given password matches the user's password exactly.
     Returns false otherwise.*/
    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    //Returns the number of emails in the user's inbox.
    public int numEmail() {
        return a.numElements();
    }

    /*Add the message to the user's inbox.
    You should call some operations provided by DynamicBuffer*/
    public void receiveEmail(String sender, String message) {
        Email obj = new Email(username, sender, createID(), message);
        a.add(obj);

    }

    /*Retrieve the n most recent emails in the user's inbox.
    You should call some operations provided by DynamicBuffer*/
    public Email[] retrieveEmail(int n) {
        return a.getNewest(n);
    }

    /*Remove an email with the specified emailID
    You should call some operations provided by DynamicBuffer*/
    public boolean removeEmail(long emailID) {

        for (int i = 0; i < a.numElements(); i++) {
            if (a.getNewest(a.numElements())[i].getID()== emailID) {
                a.remove(a.numElements()-1-i);
                return true;
            }
        }
        return false;
    }
    public String getPassword() {
        return this.password;
    }
}