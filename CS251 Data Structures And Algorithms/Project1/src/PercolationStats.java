/**
 * Created by Henry on 2/5/2017.
 */
public class PercolationStats {
    int count;
    Percolation p;
    PercolationQuick pq;
    double[] values;
    Stopwatch stopwatch;
    double[] time;
    public PercolationStats(int N, int T, String type){
        if( N <= 0 || T <= 0 ){
          throw new IllegalArgumentException("Invalid");
        }

        count = T;
        values = new double[count];
        time = new double[count];
        if(type.equals("fast")){
            int i = 0;
            while(i < count){
                int cellOpen;
                stopwatch = new Stopwatch();
                p = new Percolation(N);
                cellOpen = 0;
                while(!p.percolates()){
                    int x = StdRandom.uniform(N);
                    int y = StdRandom.uniform(N);
                    if(!p.isOpen(x,y)){
                        p.open(x,y);
                        cellOpen++;
                    }
                }
                double value = (double)cellOpen/(N*N);
                values[i] = value;
                time[i] = stopwatch.elapsedTime();
              i++;
            }
        }else if(type.equals("slow")){
            int j = 0;
            while(j < count){
                int cellOpen;
                stopwatch = new Stopwatch();
                pq = new PercolationQuick(N);
                cellOpen = 0;
                while(!pq.percolates()){
                    int x = StdRandom.uniform(N);
                    int y = StdRandom.uniform(N);
                    if(!pq.isOpen(x,y)){
                        pq.open(x,y);
                        cellOpen++;
                    }
                }
                double value = (double)cellOpen/(N*N);
                values[j] = value;
                time[j] = stopwatch.elapsedTime();
                j++;
            }
        }else{
            throw new IllegalArgumentException("Invalid");
        }
    }

    public double meanThreshold() {
        return StdStats.mean(values);
    }

    public double stdThreshold() {
        return StdStats.stddev(values);
    }

    public double time() {
        return StdStats.sum(time);
    }

    public double meanTime() {
        return StdStats.mean(time);
    }

    public double stdTime() {
        return StdStats.stddev(time);
    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        String type = args[2];

        PercolationStats ps = new PercolationStats(N, T, type);
        StdOut.println("mean threshold=" + ps.meanThreshold());
        StdOut.println("std dev=" + ps.stdThreshold());
        StdOut.println("time=" + ps.time());
        StdOut.println("mean time=" + ps.meanTime());
        StdOut.println("stddev time=" + ps.stdTime());
    }
}

