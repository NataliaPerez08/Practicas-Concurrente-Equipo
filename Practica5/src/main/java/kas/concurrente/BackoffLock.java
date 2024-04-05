package kas.concurrente;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.TimeUnit;

public class BackoffLock implements Lock {

    private static final int MIN_DELAY = 10;
    private static final int MAX_DELAY = 100;

    private AtomicReference<Thread> owner = new AtomicReference<>(null);

    @Override
    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!owner.compareAndSet(null, currentThread)) {
            backoff();
        }
    }

    private void backoff() {
        try {
            Thread.sleep((long) (MIN_DELAY + Math.random() * (MAX_DELAY - MIN_DELAY)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void unlock() {
          owner.set(null);
    }
    
}
