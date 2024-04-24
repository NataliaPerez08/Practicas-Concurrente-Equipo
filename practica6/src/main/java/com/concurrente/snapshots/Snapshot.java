package com.concurrente.snapshots;
public interface Snapshot<T> {
    public T[] scan();
    public void update(T v);

}
