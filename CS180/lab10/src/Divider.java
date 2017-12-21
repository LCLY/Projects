/**
 * Created by LeyYen on 3/21/2016.
 */
public class Divider implements Runnable {

    private static int counter = 0;
    private int start = 0;
    private int end = 0;
    static Object object = new Object();

    public Divider(int start, int end){
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        Divider d1 = new Divider(1,1001);
        Divider d2 = new Divider(1001,2001);
        Divider d3 = new Divider(2001,3001);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        Thread t3 = new Thread(d3);

        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
        }
        catch(InterruptedException e){
            System.out.println("Big problem");
        }
        System.out.println(counter);
    }


    @Override
    public void run() {
        synchronized (object) {
            for (int i = start; i < end; i++) {
                if (i % 7 == 0) {
                    counter++;
                }
            }
        }

    }
}
