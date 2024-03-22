package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Lock;

/**
 * Clase que implementa el candado usando el Legendario
 * algoritmo de PeterGod.
 * No hay mucho que decir, ya saben que hacer
 * @version 1.0
 * @author Kassandra Mirael
 */
public class PetersonLock implements Lock {
    // thread-local index, 0 or 1
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;
    private Filtro filtro = new Filtro(2, 1);
    
    @Override
    public void lock() {
        //ystem.out.println("Locking. Hilo: "+Thread.currentThread().getName());
        filtro.acquire();
        int i = (Integer.parseInt(Thread.currentThread().getName()))%2;

        int j = 1 - i;
        
        flag[i] = true;
        victim = i;
        filtro.release();
        while (flag[j] && victim == i) {
            // wait
        }
    }

    @Override
    public void unlock() {
        filtro.acquire();
        int i = (Integer.parseInt(Thread.currentThread().getName()))%2;
        flag[i] = false;
        filtro.release();
    }
}
