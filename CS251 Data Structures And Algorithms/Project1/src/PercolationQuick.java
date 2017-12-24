public class PercolationQuick {

    boolean[][] matrix;
    int size;
    int top;
    QuickUnionUF qu;
    int bottom = 0;

    public PercolationQuick(int n){
        size = n;
        top = size * size + 1;
        qu = new QuickUnionUF(size * size + 2);
        matrix = new boolean[size][size];
    }

    public void open(int x,int y){
        matrix[x][y] = true;
        if(x == 0){
            qu.union(getIndex(x , y),bottom);
        }
        if(x == size - 1){
            qu.union(getIndex(x , y), top);
        }
        if(y > 0 && isOpen(x ,y - 1)){//when y is
            qu.union(getIndex(x , y),getIndex(x , y - 1));
        }
        if(y < size - 1 && isOpen(x ,y + 1)){
            qu.union(getIndex(x , y),getIndex(x , y + 1));
        }
        if(x > 0 && isOpen(x - 1 , y)){
            qu.union(getIndex(x , y),getIndex(x - 1 , y));
        }
        if(x < size - 1 && isOpen(x + 1, y)){
            qu.union(getIndex(x , y),getIndex(x + 1, y));
        }
    }

    public boolean isOpen(int x, int y){
        return matrix[x][y];
    }

    public boolean isFull(int x, int y){
        if(0 <= x && x < size && 0 <= y && y < size){
            if(qu.connected(top, size * x + y + 1)){
                return true;
            }else{
                return false;
            }
        }else{
            throw new IndexOutOfBoundsException();
        }

    }

    public boolean percolates(){
        return(qu.connected(top,bottom));
    }

    public static void main(String[] args){

        int n = StdIn.readInt();
        PercolationQuick percolation = new PercolationQuick(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(percolation.isFull(p,q)){
                continue;
            }
            percolation.open(p,q);
        }
        if (percolation.percolates()) {
            StdOut.println("Yes");
        }else{
            StdOut.println("No");
        }
    }

    public int getIndex(int x, int y){
        return size * x  + y + 1;
    }
}
