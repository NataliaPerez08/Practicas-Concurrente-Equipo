package com.concurrente.stamped;

public class StampedValue<T> {

    public long stamp;
    public T value;

    public StampedValue(T value) {
        stamp = 0;
        this.value = value;
    }

    public StampedValue(long stamp, T value) {
        this.stamp = stamp;
        this.value = value;
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StampedValue{" +
                "stamp=" + stamp +
                ", value=" + value +
                '}';
    }
