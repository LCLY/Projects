/**
 * Created by LeyYen on 4/2/2016.
 */

import java.util.Date;

public class Email {

    private String recipient;
    private String sender;
    private long id;
    private String message;
    private Date date = new Date();//instantiate a date object;

    /* recipient is the username of the person the email is to
    sender is the username of the person the email is from
    id is a unique semi-global id (i.e. ids only have to be unique
    within a user's mailbox)
    message is the text of the email
    You have to record the time when each Email is constructed so
    that you can print this information in GET-EMAILS command.
    */
    public Email(String recipient, String sender, long id, String message) {
        this.recipient = recipient;
        this.sender = sender;
        this.id = id;
        this.message = message;

    }

    // A set of get methods to get private members of Email

    public long getID() {
        return this.id;
    }

    public String getOwner() {
        return this.recipient;
    }

    public String getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }

    //A method to retrieve String representation of this email object
    /* The format of the returned String includes mail id, create time, sender and mail body.
    Each field has to be delimited by semicolons. An example to illustrate the format of returned
     String: “540077535771753178;Sun Oct 18 01:16:36 EDT 2015; From: root “This is the mail body.”
    Note: You can use java.util.Date to format the date.*/
    public String toString() {
        return this.getID() + ";" + this.date.toString() + "; " + "From: " + this.getSender() + " \"" + this
                .getMessage() + "\"";
    }

}
