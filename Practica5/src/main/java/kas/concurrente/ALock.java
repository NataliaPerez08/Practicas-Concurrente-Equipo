package kas.concurrente;

import java.util.concurrent.atomic.AtomicInteger;

public class ALock implements Lock{
    ThreadLocal<Integer> mySloyIndex = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    AtomicInteger tail;
    volatile boolean[] flag;
    int size;

    public ALock(int hilos){
        size = hilos; // size = capacity
        tail = new AtomicInteger(0);
        flag = new boolean[hilos];
        flag[0] = true;
    }

    @Override
    public void lock() {
        int slot = tail.getAndIncrement() % size;
        mySloyIndex.set(slot);
        while(!flag[slot]){
            Thread.yield();
        };
    }

    @Override
    public void unlock() {
        int slot = mySloyIndex.get();
        flag[slot] = false;
        flag[(slot + 1) % size] = true;
    }
}
