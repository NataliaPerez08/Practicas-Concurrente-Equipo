package kass.concurrente.candadosImpl;

import java.util.concurrent.atomic.AtomicInteger;

import kass.concurrente.candados.Semaphore;
import kass.concurrente.candadosImpl.Filtro;

public class semaPrueba {
    static final int RONDAS = 100;
    static final int HILOS = 6;
    static final int MAX_HILOS_CONCURRENTES = 1;
    static int aprovacion = 0;

    Semaphore semaforo;
    Thread[] hilos;
    boolean esCorrecto;
    AtomicInteger atomicInteger;


    public void setUp(){
        esCorrecto = true;
        atomicInteger = new AtomicInteger(0);
        initThreads();
    }

    void initFiltroSemaforo(){
        semaforo = new Filtro(HILOS, MAX_HILOS_CONCURRENTES);
    }

    void initThreads(){
        hilos = new Thread[HILOS];
        for(int i = 0; i < HILOS-1; ++i){
            hilos[i] = new Thread(this::adquiereRondas, String.format("%d",i));
        }
        hilos[HILOS-1] = new Thread(this::verifica);
    }

    void adquiereRondas(){
        long valor = 0;
        for(int i = 0; i < RONDAS; ++i){
            semaforo.acquire();
            this.atomicInteger.incrementAndGet();
            valor += this.simulaCS(Math.random()*100);
            if((i % 1000) == 0){
                this.duermeHilos(Math.random()*10);
            }
            this.atomicInteger.decrementAndGet();
            semaforo.release();
        }
         System.out.printf("%s valor alcanzado %d\n", Thread.currentThread().getName(), valor);
    }

    Integer simulaCS(Double iteraciones){
        int valor = 0;
        for(int j = 0; j < iteraciones; ++j){
            valor += j & 1;
        }
        return valor;
    }

    void verifica() {
        for(int i = 0; i < (HILOS * RONDAS); i++) {
            for(int j = 0; j < Math.random() * 100; j++) {
                esCorrecto = esCorrecto && (atomicInteger.get() <= MAX_HILOS_CONCURRENTES);
                if(atomicInteger.get() <= MAX_HILOS_CONCURRENTES) ++aprovacion;
            }
            if((i % 6000) == 0) {
                duermeHilos(Math.random() * 50);
            }
        }
        System.out.printf("%s verificacion finalizada %s\n", Thread.currentThread().getName(), esCorrecto);
    }

    void duermeHilos(Double aproxMillisec) {
        try {
            Thread.sleep(aproxMillisec.longValue());
        } catch(InterruptedException ie) {
            System.out.printf("%s  - Algo paso D:", Thread.currentThread().getName());
            throw new RuntimeException("D: :'v'.");
        }

    }

    void semaforoTest() throws InterruptedException {
        initFiltroSemaforo();

        for(Thread t : hilos){
            t.start();
        }

        for(Thread t : hilos){
            t.join();
        }
        System.out.println("Aprovacion del "+ aprovacion);
        System.out.println(esCorrecto);
    }
    public static void main(String[] args) {
        semaPrueba sp = new semaPrueba();
        sp.setUp();
        try {
            sp.semaforoTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}