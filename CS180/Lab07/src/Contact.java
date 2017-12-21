/**
 * CS180 - Lab 07 - Methods and classes
 * This program implements two classes that will be used to model
 * an address book which can store contacts with their information
 * @author Ley Yen Choo, lchoo@purdue.edu
 */
public class Contact {
    private String name;
    private long number;
    private String address;

    public Contact(String name) {

        this.name = name;
        this.number = 0;
        this.address = null;
    }

    public Contact(String name, long number) {
        this(name);
        this.number = number;
        this.address = null;
    }

    public Contact(String name, long number, String address) {
        this(name, number);
        this.address = address;
    }
    public String getName(){

        return this.name;
    } //gets the name of the Contact
    public void setName(String name){

        this.name = name;
    } //sets the name of the Contact
    public long getNumber(){

        return this.number;
    } //gets the phone number of the Contact

    public void setNumber(long number){

        this.number = number;
    } //sets the phone number of the Contact

    public String getAddress(){

        return this.address;
    } //gets the address of the Contact
    public void setAddress(String address){

        this.address = address;
    } //sets the address of the Contact

    public boolean equals(Contact contact){
        if (name.equals(contact.getName()) ){
            return true;
        }
        return false;
    } /*checks if this Contact is equal to the Contact specified as argument. Returns true if they are equal and false otherwise.
    The two Contacts are equal if they have the same name*/

}
