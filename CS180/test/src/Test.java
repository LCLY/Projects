import java.io.*;

public class Test {
    public String testMethod(String filename) {
        String result = null;
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
                result = bfr.readLine();

            bfr.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found.");
        } catch (IOException e) {
            System.out.print("LOLS");
        }



        return result;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.testMethod("C:\\Users\\LeyYen\\Desktop\\CS180\\test\\src\\testFile.txt");
    }
}
