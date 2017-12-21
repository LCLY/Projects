/**
 * Created by LeyYen on 3/21/2016.
 */
public class Driver {
    public static void main(String[] args) {
        Thread t = new A();
        t.start();

        Runnable r = new B();
        t = new Thread(r);
        t.start();
    }

}
