package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * Este algoritmo es similar al del filtro, lo diferente es que
 * permite una cantidad m de hilos SIMULTANEOS en la seccion critica
 * Todo es casi igual, solo realiza la modificacion pertinente para esto
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {
    int [] level;
    int [] victim;
    int maxHilosConcurrentes;
    /**
     * Constructor del Filtro
     * @param hilos El numero de Hilos Permitidos
     * @param maxHilosConcurrentes EL numero de hilos concurrentes simultaneos
     */
    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.maxHilosConcurrentes = maxHilosConcurrentes;
        level = new int[hilos];
        victim = new int[hilos];
        for(int i = 0; i < hilos; ++i){
            level[i] = 0;
        }
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilosConcurrentes;
    }

    @Override
    public void acquire() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        for(int j = 1; j < level.length; ++j){
            level[i] = j;
            victim[j] = i;
            /* Aquí tenemos que modificar para permitir un cantidad m
             * de hilos simultaneos en la seccion critica
             */
            
            for(int k = 0; k < level.length; ++k){
                while( existeConflicto(i,j) ){
                    //Espera
                }


            }
        }   
    }

    public boolean existeConflicto(int i, int j){
        int numActualHilosConcurrentes = 0;
        for(int k = 0; k < level.length; ++k){
            if((k != i) && (level[k] >= j) && (victim[j] == i)) ++numActualHilosConcurrentes;
            if(numActualHilosConcurrentes==maxHilosConcurrentes){
                System.out.print("");
                return true;
                

            } 
        }
        return false;

    }

    @Override
    public void release() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        level[i] = 0;
       
        
    }
    
}
