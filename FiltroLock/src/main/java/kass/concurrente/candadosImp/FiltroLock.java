package kass.concurrente.candadosImp;

import kass.concurrente.candados.Lock;

public class FiltroLock  implements Lock {
    int[] nivel;
    int[] victima;

    public FiltroLock(Integer capacidad){
        nivel = new int[capacidad];
        victima = new int[capacidad];
        for(int i = 0; i < capacidad; ++i){ // Un hilo por cada nivel
            nivel[i] = 0;
        }
    }

    @Override
    public void lock() {
        int me = Integer.parseInt(Thread.currentThread().getName());
        for(int i = 1; i < nivel.length; ++i){ // Intenta con nivel 1
            nivel[me] = i;
            victima[i] = me;
            for(int k = 0; k < nivel.length; ++k){
                while((k != me) && (nivel[k] >= i && victima[i] == me)){ 
                    System.out.printf("Hilo %d esperando a %d\n", me, k);
                }
            }
        }
    }

    @Override
    public void unlock() {
        int me = Integer.parseInt(Thread.currentThread().getName());
        nivel[me] = 0;
    }

    /**
     * Metodo que nos dice la capacidad del Filtro
     * @return La capacidad del Filtro
     */
    public int capacidad(){
        return -1;
    }
}
