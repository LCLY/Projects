/**
 * Created by LCLY on 2/18/2017.
 */
import java.io.*;
import java.util.*;

public class Fast {
    Point[] pt;
    int counter = 0;
    ArrayList<List<Point>> segment = new ArrayList<>();

    public Fast(int n ){
        pt = new Point[n];
    }

    public void addPt(int x, int y){
        Point temp = new Point(x,y);
        pt[counter] = temp;
        counter++;
    }

    public void printFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
      int size = segment.size();
       for(int i = 0 ; i < size ; i++){
           fw.append(String.valueOf(segment.get(i).size()));
           fw.append(":");
           fw.append(toString(segment.get(i)));
           fw.append("\n");
       }
        fw.close();
    }

    public String toString(List<Point> pt){
        int size = pt.size();
        String concat = "";
        for(int i = 0 ; i < size ; i++){
            concat = concat + pt.get(i).toString() + " -> ";
        }
        concat = concat.substring(0, concat.length()-4);

        return concat;
    }

    public ArrayList<List<Point>> outputSegments(Point[] pt){
        Point[] temp = Arrays.copyOf(pt, pt.length);
        Arrays.sort(pt);
        for(Point start : pt){
            Arrays.sort(temp, start.BY_SLOPE_ORDER);
            List<Point> slopes = new ArrayList<>();
            double slope;
            double prev_slope = Double.NEGATIVE_INFINITY;

            for(int i = 1; i < temp.length ; i++){
                slope = start.slopeTo(temp[i]);
                if(slope == prev_slope) {
                    slopes.add(temp[i]);
                }else{
                    if(slopes.size()>=3) {
                        slopes.add(start);
                        if (!(checkDuplicate(slopes, slopes.size()))) {
                            segment.add(slopes);
                        }
                    }
                    slopes = new ArrayList<>();
                    slopes.add(temp[i]);
                }
                prev_slope = slope;
            }
            if(slopes.size() >= 3){
                slopes.add(start);
                if(!(checkDuplicate(slopes, slopes.size()))){
                    segment.add(slopes);
                }
            }
        }
        return segment;
    }

    public boolean checkDuplicate(List<Point> slope, int size){
    Collections.sort(slope);
    int length = segment.size();
    for(int i = 0 ; i < length ; i++){
        if(segment.get(i).get(0).compareTo(slope.get(0))== 0 &&
                segment.get(i).get(segment.get(i).size() - 1).compareTo(slope.get(size - 1)) == 0){
            return true;
        }
    }
    return false;
    }


    public void printSegment(){
        int size = segment.size();
        for (int i = 0; i < size ; i++) {
            StdOut.println(segment.get(i).size() + ":" + toString(segment.get(i)));
        }
    }


    public static void main(String[]args) throws IOException {

        File f = new File("visualPoints.txt");
         if (f.exists()) {
             f.delete();
         }
         f.createNewFile();

         int N = StdIn.readInt();
         Fast fast = new Fast(N);
         while (!StdIn.isEmpty()) {
             int p = StdIn.readInt();
             int q = StdIn.readInt();
             fast.addPt(p, q);
         }

         fast.outputSegments(fast.pt);
         fast.printSegment();
         fast.printFile(f);
    }

}
