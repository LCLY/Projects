import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class    FileParser {

    public int[] readFromFile(String filename) {
        int[] result;
        int[] error = new int[0];
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            FileReader fr2 = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            if (bfr.readLine() != null) {
                bfr.close();
                BufferedReader bfr2 = new BufferedReader(fr2);
                String[] integers = bfr2.readLine().split(",");
                result = new int[integers.length];
                for (int i = 0; i < integers.length; i++) {
                    result[i] = Integer.parseInt(integers[i]);
                }
                return result;
            } else {
                return error;
            }

        } catch (FileNotFoundException e) {
            return error;
        } catch (IOException e) {
            return error;
        }

    } //Returns an int array after parsing the data in the filename.
    // If there is an error, or the file has no contents return null.

    public static void main(String[] args) {

        FileParser obj = new FileParser();
        int[] array = obj.readFromFile("file.txt");
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]); // prints each element in the array on a new line

    }
}
