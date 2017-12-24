import java.io.*;

/**
 * Created by Henry on 2/5/2017.
 */
public class PercolationVisualizer {

    int[][] matrix;
    int size;
    int top;
    int bottom = 0;
    WeightedQuickUnionUF wq;

    public PercolationVisualizer(int n){
        size = n;
        top = size * size;
        wq = new WeightedQuickUnionUF(size * size + 1);
        matrix = new int[size][size];
    }

    public void open(int x,int y){
        matrix[x][y] = 1;

        if(x == size - 1){
            wq.union(getIndex(x , y), top);
        }
        if(y > 0 && isOpen(x ,y - 1)){
            wq.union(getIndex(x , y),getIndex(x , y - 1));
        }
        if(y < size - 1 && isOpen(x ,y + 1)){
            wq.union(getIndex(x , y),getIndex(x , y + 1));
        }
        if(x > 0 && isOpen(x - 1 , y)){
            wq.union(getIndex(x , y),getIndex(x - 1 , y));
        }
        if(x < size - 1 && isOpen(x + 1, y)){
            wq.union(getIndex(x , y),getIndex(x + 1, y));
        }
    }

    public boolean isOpen(int x, int y){
        return matrix[x][y] > 0;
    }

    public boolean isFull(int x, int y) {
        if (0 <= x && x < size && 0 <= y && y < size) {
            if (wq.connected(top, getIndex(x,y))) {
                switchState();
                return true;
            } else {
                return false;
            }

        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates(){
        int i = 0;
        while(i < size){
            if(wq.connected(top, bottom + i)){
                switchState();
                return true;
            }
            i++;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        File f = new File("visualMatrix.txt");
        if(f.exists()){
            f.delete();
        }
        f.createNewFile();
        FileWriter fw = new FileWriter(f, true);
        int n = StdIn.readInt();
        fw.append(String.valueOf(n));
        fw.append("\n");
        fw.append("\n");
        fw.close();
        PercolationVisualizer pv = new PercolationVisualizer(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            pv.open(p,q);

            if(pv.isFull(p,q)){
                pv.printMatrix(f);
            }else if(pv.percolates()){
                pv.printMatrix(f);
            }else{
                pv.printMatrix(f);
            }
        }

    }

    public void switchState(){
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(wq.connected(top,getIndex(i,j))){
                    matrix[i][j] = 2;
                }
            }
        }
    }

    public void printMatrix(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for(int i = size - 1  ; i >= 0 ; i--){
            for(int j = 0 ; j < size ; j++){
               fw.append(String.valueOf(matrix[i][j]));
               fw.append(" ");
            }
            fw.append("\n");
        }
        fw.append("\n");
        fw.close();
    }

    public int getIndex(int x, int y){
        return size * x  + y;
    }
}
