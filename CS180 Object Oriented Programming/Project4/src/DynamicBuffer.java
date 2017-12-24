/**
 * You will double the size of the buffer when there is not enough space for new emails
 * You will reduce the size to half (length/2) when the number of emails is less than or equal
 * to 1/4 the size of the array. i.e. numElements() â‡ emailArray.length / 4
 * The buffer size should never shrink to less than the initial size
 */
public class DynamicBuffer {
    private Email[] array;
    private int counter;
    private int initSize;

    /*The buffer will have an array with an initial size.
    You can choose whatever initial size you want.*/
    public DynamicBuffer(int initSize) {
        this.initSize = initSize;
        this.array = new Email[this.initSize];
        this.counter = 0;
    }


    // Returns the number of emails stored in the array
    public int numElements() {
        return this.counter;
    }


    //Returns the length of the array
    public int getBufferSize() {
        return this.array.length;
    }

    /* Adds an email to the array
    If the array becomes full by adding this email double its size.*/
    public void add(Email email) {
        array[this.counter] = email;
        this.counter++;
        if (this.counter == getBufferSize()) {
            Email[] temp = new Email[getBufferSize() * 2];
            for (int j = 0; j < this.getBufferSize(); j++) {
                temp[j] = this.array[j];
            }
            this.array = temp;
        }

    }

    /*Removes an email at the specified index from the buffer
    Return true if the index is valid and an email is removed; else return false.
     If the number of emails in the buffer becomes less than or equal to one fourth of the
     buffer size after the removal, shrink the buffer size to half of the current buffer size.
     Note that the buffer size should never be lower than the initial size.*/
    public boolean remove(int index) {

        boolean result = false;
        if (index >= 0 && index < getBufferSize() && index < numElements()) {
            array[index] = null;
            this.counter--;
            result = true;

            for (int i = 0; i < getBufferSize(); i++) {
                if (this.array[i] == null && i < getBufferSize() - 1) {
                    this.array[i] = array[i + 1];
                    this.array[i + 1] = null;
                }
            }
            if (numElements() <= (getBufferSize() / 4) && (this.getBufferSize() / 2) >= this.initSize) {
                Email[] temp = new Email[this.getBufferSize() / 2];
                for (int j = 0; j < getBufferSize() / 2; j++) {
                    temp[j] = this.array[j];
                }
                this.array = temp;
            }
            return result;
        } else {
            return result;
        }
    }

    /*Gets the n most recently added Emails to the buffer (the last n)
    Returned emails must be sorted from most recently to least recently added to the buffer.
    Return all emails if n is greater than the number of emails in the buffer
    Return null if the buffer is empty or an invalid number of emails is requested (e.g. -1)*/
    public Email[] getNewest(int n) {
        Email[] temp = new Email[n];


        if (n > numElements() && numElements() != 0) {
            int currentNum = 0;
            int i = numElements() - 1;
            while (i >= 0) {
                temp[currentNum] = this.array[i];
                currentNum++;
                i--;
            }
            return temp;


        } else if (n < 0 || numElements() == 0) {
            return null;

        } else {
            int i = numElements() - 1;
            int otherEmails = numElements() - n;
            int currentNum = 0;
            while (i >= otherEmails) {
                temp[currentNum] = this.array[i];
                currentNum++;
                i--;
            }
            return temp;
        }
    }

    public Email[] getAll() {
        return this.array;
    }

}