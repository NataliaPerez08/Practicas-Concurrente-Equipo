package com.concurrente.stamped;

public class StampedSnap<T> {
    public long stamp;
    public T value;
    public T[] snap;

    public StampedSnap(T value){
        stamp = 0;
        this.value = value;
        this.snap = null;
    }

    public StampedSnap(long stamp, T value, T[] snap){
        this.stamp = stamp;
        this.value = value;
        this.snap = snap;
    }
}
