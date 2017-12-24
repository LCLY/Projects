import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileParser {

    public void writeToFile(String filename) {
        int answer = 0;
        int counter = 0;
        try {
            File f = new File(filename);
            File f2 = new File("result.txt");
            f2.createNewFile();
            PrintWriter outputfile = new PrintWriter(f2);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                answer = 0;
                String[] number = s.nextLine().split(" ");
                for (int i = 0; i < number.length; i++) {
                    answer = answer + Integer.valueOf(number[i]);
                }
                counter++;
                outputfile.println("Row" + counter + ": " + answer);
            }
            outputfile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error!");
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    public static void main(String[] args) {
        FileParser m = new FileParser();
        m.writeToFile("matrix.txt"); // the contents of matrix.txt are in the example above

    }

}
