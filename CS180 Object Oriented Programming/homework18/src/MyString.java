
public class MyString {
    private char[] data;

    public MyString() {
        this.data = null;
    }

    public MyString(char[] data) {
        this.data = data;
    }

    public char[] getData() {
        char[] newArray = null;
        if (this.data != null && this.data.length > 0) {
            newArray = new char[this.data.length];
            System.arraycopy(this.data, 0, newArray, 0, this.data.length);
        }
        return newArray;

    }

    public void append(char[] newData) {
        if (newData != null && newData.length > 0) {
            if (this.data == null) {
                this.data = newData;
            } else {
                char[] temp = new char[this.data.length];
                for (int i = 0; i < this.data.length; i++) {
                    temp[i] = this.data[i];
                }
                this.data = new char[this.data.length + newData.length];
                for (int i = 0; i < temp.length; i++) {
                    this.data[i] = temp[i];
                }
                for (int i = temp.length; i < this.data.length; i++) {
                    this.data[i] = newData[i - temp.length];
                }
            }
        }
    }

    public void prepend(char[] newData) {
        if (newData != null && newData.length > 0) {
            if (this.data == null) {
                this.data = newData;
            } else {
                char[] temp = new char[this.data.length];
                for (int i = 0; i < this.data.length; i++) {
                    temp[i] = this.data[i];
                }
                this.data = new char[this.data.length + newData.length];
                for (int i = 0; i < newData.length; i++) {
                    this.data[i] = newData[i];
                }
                for (int i = newData.length; i < this.data.length; i++) {
                    this.data[i] = temp[i - newData.length];
                }
            }
        }
    }

    public int getLength() {
        if (this.data == null) {
            return -1;
        } else {
            return data.length;
        }
    }

    public void removeChars(int startIndex, int endIndex) throws IllegalIndexException {
        if (this.data != null && this.data.length > 0) {
            if (startIndex < 0 || startIndex > getLength() || endIndex < 0 || endIndex > getLength()
                    || startIndex > endIndex) {
                throw new IllegalIndexException();
            } else {
                for (int i = startIndex; i < endIndex; i++) {
                    this.data[i] = 0;
                }

                for (int i = 0; i < this.data.length; i++) {
                    if (i < getLength() - 1 && this.data[i] == 0) {
                        this.data[i] = this.data[i + 1];
                        this.data[i + 1] = 0;
                    }
                }
                char[] newArray = new char[this.data.length];
                for (int i = 0; i < this.data.length; i++) {
                    newArray[i] = this.data[i];
                }
                this.data = new char[this.data.length - (endIndex - startIndex)];
                for (int i = 0; i < this.data.length; i++) {
                    this.data[i] = newArray[i];
                }
            }
        }
    }
}
