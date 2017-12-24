import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by LCLY on 2/18/2017.
 */
public class Brute {

    Point[] pt;
    int counter = 0;
    String[] result;
    ArrayList<String> segment = new ArrayList<>();

    public Brute(int n) {
        pt = new Point[n];
    }

    public void addPt(int x, int y) {
        Point temp = new Point(x, y);
        pt[counter] = temp;
        counter++;
    }

    public String toString(int p, int q, int r, int s) {
        return "4:" + pt[p].toString() + " -> " + pt[q].toString() + " -> " +
                pt[r].toString() + " -> " + pt[s].toString();
    }


    public String[] outputSegments (Point[]pt){
        Arrays.sort(pt);

        for(int i = 0 ; i < counter - 3 ; i++){
            for(int j = i + 1 ; j < counter - 2 ; j++){
                for(int k = j + 1 ; k < counter - 1 ; k++){
                    for(int l = k + 1 ; l < counter ; l++){
                        if(Point.areCollinear(pt[i],pt[j],pt[k])){
                            if(Point.areCollinear(pt[i],pt[j],pt[k],pt[l])){
                                segment.add(toString(i,j,k,l));
                            }
                        }
                    }
                }
            }
        }

        result = new String[segment.size()];
        result = segment.toArray(result);
        return result;


    }

    public void printFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < result.length; i++) {
            fw.append(result[i] + "\n");
        }
        fw.close();
    }

    public void printSegment(){
        for (int i = 0; i < result.length; i++) {
            StdOut.println(result[i]);
        }
    }

    public static void main(String[] args) throws IOException {

        File f = new File("visualPoints.txt");
        if (f.exists()) {
            f.delete();
        }
        f.createNewFile();

        int N = StdIn.readInt();
        Brute b = new Brute(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            b.addPt(p, q);
        }

        b.outputSegments(b.pt);
        b.printSegment();
        b.printFile(f);
    }
}