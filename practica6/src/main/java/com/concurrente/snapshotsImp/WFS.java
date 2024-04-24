package com.concurrente.snapshotsImp;

import com.concurrente.snapshots.Snapshot;

public class WFS<T> implements Snapshot<T> {

    private StampedSnap<T>[] a_table;
    public WFS(int capacity, T init){
        a_table = (StampedSnap<T>[]) new StampedSnap[capacity];
        for(int i = 0; i< capacity; i++){
            a_table[i] = new StampedSnap<T>(init);
        }
    }

    private StampedSnap<T>[] collect(){
        StampedSnap<T>[] copy= (StampedSnap<T>[]) new StampedSnap[a_table.length];
        for(int i = 0; i< a_table.length; i++){
            copy[i] =a_table[i];
        }
        return copy;
    }
    
    public void update(T value){
        int me = (int) Thread.currentThread().getId();
        T[] snap = scan();
        StampedSnap<T> oldValue = a_table[me];
        StampedSnap<T> newValue = new StampedSnap<T>(oldValue.stamp+1,value,snap);
        a_table[me] = newValue;
    }

    @SuppressWarnings({ "unchecked", "unused" })
    public T[] scan(){
        StampedSnap<T>[] oldCopy;
        StampedSnap<T>[] newCopy;
        boolean[] moved = new boolean[a_table.length];
        oldCopy = collect();
        collect : while(true){
            newCopy = collect();
            for (int j = 0;j<a_table.length; j++){
                if(oldCopy[j].stamp!=newCopy[j].stamp){
                    if (moved[j]){
                        return oldCopy[j].snap;
                    } else {
                        moved[j] = true;
                        oldCopy = newCopy;
                        continue collect;
                    }
                }                
            }
            T[] result = (T[]) new Object[a_table.length];
            for (int i = 0;i<a_table.length;i++){
                result[i] = newCopy[i].value;
            }
            return result;
        }
    }   
}
