import java.util.concurrent.atomic.AtomicInteger;

public class Counter2 implements Counter {
    private AtomicInteger x = new AtomicInteger(0);

    public void inc() {
        x.incrementAndGet();
    }

    public void dec() {
        x.decrementAndGet();
    }

    public int get() {
        return x.get();
    }
}