import java.io.*;
import java.security.cert.CRL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <b> CS 180 - Project 4 - Email Server Skeleton </b>
 * <p>
 * <p>
 * This is the skeleton code for the EmailServer Class. This is a private email
 * server for you and your friends to communicate.
 *
 * @author (Ley Yen Choo) <(lchoo@purdue.edu)>
 * @version (4/14/2016)
 * @lab03 (Your Lab Section)
 */

public class EmailServer {
    // Useful constants
    public static final String FAILURE = "FAILURE";
    public static final String DELIMITER = "\t";
    public static final String SUCCESS = "SUCCESS";
    public static final String CRLF = "\r\n";
    public ArrayList<User> users;
    File f = null;
    // Used to print out extra information
    private boolean verbose = false;

    /**
     * The server should have a default user with the username root and the password cs180
     * The Server must handle between 1 and 100 users (including the root user)
     * Hint: Try to minimize the use of shared static variables because each EmailServer
     * instance is a unique server with its own mailboxes. Otherwise, you may inexplicably fail test cases.
     * The server should not let you delete the root user
     */

    public EmailServer() {
        users = new ArrayList<>();
        users.add(0, new User("root", "cs180"));
    }

    public EmailServer(String fileName) throws IOException {
        users = new ArrayList<>();
        try {
            f = new File(fileName);
            Scanner s = new Scanner(f);
            users.add(0, new User("root", "cs180"));
            while (s.hasNextLine()) {
                String[] temp = s.nextLine().split(",");
                if (temp.length == 2) {
                    users.add(users.size(), new User("root", "cs180"));
                }
            }
            s.close();
        } catch (IOException e) {
            f.createNewFile();
            users = new ArrayList<>();
            users.add(0, new User("root", "cs180"));
        }
    }

    /**
     * Replaces "poorly formatted" escape characters with their proper
     * values. For some terminals, when escaped characters are
     * entered, the terminal includes the "\" as a character instead
     * of entering the escape character. This function replaces the
     * incorrectly inputed characters with their proper escaped
     * characters.
     *
     * @param str - the string to be edited
     * @return the properly escaped string
     */
    private static String replaceEscapeChars(String str) {
        str = str.replace("\\r\\n", "\r\n"); // may not be necessary, but just in case
        str = str.replace("\\r", "\r");
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");
        str = str.replace("\\f", "\f");

        return str;
    }

    /**
     * This main method is for testing purposes only.
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        (new EmailServer()).run();
    }
    //public Email searchEmail(User user,long id){

    //}

    /*There are some limitations on the username and password.
   You can get the details in the Additional Details section below.*/
    public String addUser(String[] args) {
       /* String result = "";

        if (searchUser(args[1]) != null) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.USER_EXIST_ERROR);
        }
        args[2] = args[2].trim();

        if (args[1].length() >= 21 || args[1].length() <= 0) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);

        }
        if (args[2].length() >= 41 || args[2].length() <= 3) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);

        }
        char[] userNameChars = args[1].toCharArray();

        for (char userNameChar : userNameChars) {
            if (!Character.isLetterOrDigit(userNameChar))
                return ErrorFactory.makeErrorMessage(-23);

        }

        char[] passwordChars = args[2].toCharArray();
        for (char passwordChar : passwordChars) {
            if (!Character.isLetterOrDigit(passwordChar))
                return ErrorFactory.makeErrorMessage(-23);

        }
        if (users.size() >= 100) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_ERROR);
        }
        users.add(new User(args[1], args[2]));
        result = SUCCESS + CRLF;
        if (f != null) {
            try {
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter writer = new PrintWriter((fos));
                for (int j = 1; j < users.size(); j++) {
                    writer.print(users.get(j).getName() + "," + users.get(j).getPassword() +
                            "\n");
                    writer.flush();
                }
                writer.close();
            } catch (FileNotFoundException f) {
                result = FAILURE;
            }
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(args[1]))
                    result = SUCCESS + CRLF;
            }

        }

        return result;
    }*/
        String result = "";
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getName().equals(args[1]) && i == users.size() - 1) {
                users.add( new User(args[1], args[2]));
                result = SUCCESS + CRLF;

                if (f != null) {
                    try {
                        FileOutputStream fos = new FileOutputStream(f);
                        PrintWriter writer = new PrintWriter((fos));
                        for (int j = 1; j < users.size(); j++) {
                            writer.print(users.get(j).getName() + "," + users.get(j).getPassword() +
                                    "\n");
                            writer.flush();
                        }
                        writer.close();
                    } catch (FileNotFoundException f) {
                        result = FAILURE;
                    }
                }
                break;

            } else if (users.get(i).getName().equals(args[1])) {
                result = ErrorFactory.makeErrorMessage(-22);
                break;
            }
        }

        return result;
    }

    public User searchUser(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public String getAllUsers(String[] args) {
        if (!args[1].equals("root") || !args[2].equals("cs180"))
            return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
        String str = SUCCESS;
        for (User user : users) {
            str = str + DELIMITER + user.getName();
        }
        str = str + CRLF;
        return str;
    }

    // For the request to succeed, the correct username and password are required.
    public String deleteUser(String[] args) {
        String result = "";
        User u = this.searchUser(args[1]);
        if (u == null) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        }
        if (!u.checkPassword(args[2])) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
        }
        if (args[1].equals("root"))
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        users.remove(u);
        if (f != null) {
            try {
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter writer = new PrintWriter((fos));
                for (int j = 1; j < users.size(); j++) {
                    writer.print(users.get(j).getName() + "," + users.get(j).getPassword()
                            + "\n");
                    writer.flush();
                }
                writer.close();
            } catch (FileNotFoundException f) {
                result = FAILURE + CRLF;
            }
        } else {
            result = ErrorFactory.makeErrorMessage(-21);

        }
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getName().equals(args[1]) && i == users.size() - 1) {
                result = ErrorFactory.makeErrorMessage(-20);
            }

        }

        return result;
    }


		/* The number of messages requested must be >= 1, otherwise an INVALID_VALUE_ERROR (error #-23) is returned.
        The number of messages requested can be higher than the number of available messages;
        in this case the function returns as many as possible. It can also return 0 messages if none are available
        (SUCCESS\r\n).*/

    public String checkAuthenticated(String username, String password) {
        User u = this.searchUser(username);
        if (u == null)
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        if (!u.checkPassword(password))
            return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
        return SUCCESS + CRLF;
    }

    /*For the request to succeed, the message should contain at least 1 character
    after removing leading and trailing white spaces from the message
    Hint: you can use myString.trim() to remove leading and trailing whitespace from a string*/
    public String sendEmail(String[] args) {
        String str;
        if (args[4].trim().length() < 1)
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        if (this.searchUser(args[3]) == null)
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        str = checkAuthenticated(args[1], args[2]);
        if (!str.equals(SUCCESS + CRLF))
            return str;

        User user = this.searchUser(args[3]);
        String str1 = args[4].trim();
        user.receiveEmail(args[1], str1);
        return SUCCESS + CRLF;
    }

    public String getEmails(String[] args) {
        String str;
        str = checkAuthenticated(args[1], args[2]);
        if (!str.equals(SUCCESS + CRLF))
            return str;

        User user = this.searchUser(args[1]);

        if (Integer.parseInt(args[3]) < 1)
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        if (Integer.parseInt(args[3]) < 0)
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        if (Integer.parseInt(args[3]) > user.numEmail())
            args[3] = String.valueOf(user.numEmail());
        String result = SUCCESS;
        for (int i = 0; i < Integer.parseInt(args[3]); i++) {
            result = result + DELIMITER + user.retrieveEmail(Integer.parseInt(args[3]))[i].toString();
        }
        return result + CRLF;

    }

    /*To delete an email, not only the correct username and password should be verified,
    but also a correct email ID must be specified. The email ID information can be retrieved in the GET-EMAILS command*/
    public String deleteEmail(String[] args) {
        String str;
        str = checkAuthenticated(args[1], args[2]);
        if (!str.equals(SUCCESS + CRLF))
            return str;
        User user = this.searchUser(args[1]);
        long id;
        try {
            id = Long.parseLong(args[3]);
        } catch (NumberFormatException e) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }
        if (!user.removeEmail(id)) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }
        return SUCCESS + CRLF;
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.printf("Input Server Request: ");
            String command = in.nextLine();

            command = replaceEscapeChars(command);

            if (command.equalsIgnoreCase("kill") || command.equalsIgnoreCase("kill\r\n"))
                break;

            if (command.equalsIgnoreCase("verbose") || command.equalsIgnoreCase("verbose\r\n")) {
                verbose = !verbose;
                System.out.printf("VERBOSE has been turned %s.\n\n", verbose ? "on" : "off");
                continue;
            }

            String response;
            try {
                response = parseRequest(command);
            } catch (Exception ex) {
                response = ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_ERROR,
                        String.format("An exception of %s occurred.", ex.getClass().toString()));
            }

            //if (response.startsWith("SUCCESS" + DELIMITER))
            //	response = response.replace(DELIMITER, NEWLINE);
            if (response.startsWith(FAILURE) && !DELIMITER.equals("\t"))
                response = response.replace(DELIMITER, "\t");

            if (verbose)
                System.out.print("response: ");
            System.out.printf("\"%s\"\n\n", response);
        }

        in.close();
    }

    /**
     * Determines which client command the request is using and calls
     * the function associated with that command.
     * Returns the server's response to the request
     * The argument request is the complete line of the client request.
     * The input to this method is not validated, you must perform validation.
     *
     * @param request - the full line of the client request (CRLF included)
     * @return the server response
     */

    public String parseRequest(String request) {

        if (!request.endsWith(CRLF)) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String[] str = request.split("\t");
        String check;
        if (str.length < 3)
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);

        switch (str[0]) {
            case ("ADD-USER"):
                if (str.length != 3)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                else {

                    return this.addUser(str);
                }


            case ("DELETE-USER"):
                if (str.length != 3)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                check = this.checkAuthenticated(str[1], str[2]);
                if (!check.equals(SUCCESS + CRLF))
                    return check;

                return this.deleteUser(str);


            case ("GET-ALL-USERS"):
                if (str.length != 3)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                check = this.checkAuthenticated(str[1], str[2]);
                if (!check.equals(SUCCESS + CRLF))
                    return check;

                return this.getAllUsers(str);


            case ("SEND-EMAIL"):
                if (str.length != 5)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                check = this.checkAuthenticated(str[1], str[2]);
                if (!check.equals(SUCCESS + CRLF))
                    return check;

                return this.sendEmail(str);


            case ("GET-EMAILS"):
                if (str.length != 4)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                check = this.checkAuthenticated(str[1], str[2]);
                if (!check.equals(SUCCESS + CRLF))
                    return check;

                return this.getEmails(str);


            case ("DELETE-EMAIL"):
                if (str.length != 4)
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                check = this.checkAuthenticated(str[1], str[2]);
                if (!check.equals(SUCCESS + CRLF))
                    return check;
                return this.deleteEmail(str);


            default:
                return ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_COMMAND_ERROR);
        }


    }
}
