import java.io.File;
import java.io.IOException;

public class test {

    int[][] matrix;
    Stopwatch stopwatch;

    public void test_brute(int n, File file) throws IOException{
        Brute brute = new Brute(n);
        matrix = new int[n][2];

        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(32768);
            int y = StdRandom.uniform(32768);

            while(checkDuplicate(x,y)) {
                x = StdRandom.uniform(32768);
                y = StdRandom.uniform(32768);
            }
                matrix[i][0] = x;
                matrix[i][1] = y;
                brute.addPt(x,y);
        }

        stopwatch = new Stopwatch();
        brute.outputSegments(brute.pt);
        brute.printSegment();
        brute.printFile(file);
        printTime();
    }

    public void test_fast(int n, File file) throws IOException {
        Fast fast = new Fast(n);
        matrix = new int[n][2];


        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(32768);
            int y = StdRandom.uniform(32768);

            while(checkDuplicate(x,y)) {
                x = StdRandom.uniform(32768);
                y = StdRandom.uniform(32768);
            }
            matrix[i][0] = x;
            matrix[i][1] = y;
            fast.addPt(x,y);
        }

        stopwatch = new Stopwatch();
        fast.outputSegments(fast.pt);
        fast.printSegment();
        fast.printFile(file);
        printTime();
    }


    public void printTime() {
        StdOut.println("Time: " + stopwatch.elapsedTime());
    }
    public boolean checkDuplicate(int x, int y){
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == x && matrix[i][1] == y) {
                return true;
            }
        }

        return false;
    }



    public static void main(String[] args) throws IOException {
        String type = args[0];
        int n = Integer.valueOf((args[1]));

        File file = new File("visualMatrix.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        test test = new test();

        if (type.compareTo("fast") == 0) {
            test.test_fast(n, file);
        } else if (type.compareTo("brute") == 0) {
            test.test_brute(n, file);
        }
    }
}
