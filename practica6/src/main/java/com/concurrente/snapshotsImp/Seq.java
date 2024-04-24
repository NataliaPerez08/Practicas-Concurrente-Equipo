package com.concurrente.snapshotsImp;

import com.concurrente.snapshots.Snapshot;

public class Seq<T> implements Snapshot<T> {

    T[] a_value;

    @SuppressWarnings("unchecked")
    public Seq(int capacity, T init){
        a_value = (T[]) new Object[capacity];
        for (int i = 0; i< capacity; i++){
            a_value[i] = init;
        }
    }

    public synchronized void update(T v){
        a_value[(int) Thread.currentThread().getId()] = v;
    }

    @SuppressWarnings("unchecked")
    public synchronized T[] scan(){
        T[] result = (T[]) new Object[a_value.length];
        for (int i = 0; i<a_value.length;i++){
            result[i] = a_value[i];
        }
        return result;
    }
    
}
