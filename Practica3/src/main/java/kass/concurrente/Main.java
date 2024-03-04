package kass.concurrente;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import kass.concurrente.constantes.Contante;
import kass.concurrente.modelos.Habitacion;
import kass.concurrente.modelos.Prisionero;
import kass.concurrente.modelos.Vocero;

import static kass.concurrente.constantes.Contante.LOGS;

/**
 * Clase principal, se ejecuta todo la simulacion
 * Como en el cuento.
 * @author Los hilos del barrio
 * @version 1.0
 */
public class Main implements Runnable {

    Lock lock;
    Habitacion habitacion;
    Prisionero[] prisioneros = new Prisionero[Contante.PRISIONEROS];
    Boolean salir = false;


    public Main(){
        lock = new ReentrantLock();
        habitacion = new Habitacion();
        prisioneros[0] = new Vocero(0, true, true);
        for(int i=1; i<Contante.PRISIONEROS;i++){
            prisioneros[i] = new Prisionero(i, false, false);

        }
    }

    /*
     * INSTRUCCIONES:
     * 1.- Ya genere el lock, es un reentrantLock, investiguen que hace
     * 2.- Tenenemos que tener un lugar el donde se albergaran los prisioneros
     * 3.- Tenemos que tener un lugar donde se albergan los Hilos
     * 4.- Se necesita un objeto de tipo Habitacion para que sea visitada
     * 5.- Aqui controlaremos el acceso a la habitacion, aunque por defecto tenia exclusion mutua
     * aqui hay que especificar el como se controlara
     * 6.- Hay que estar ITERANDO constantemente para que todos los prisiones puedan ir ingresando
     */
    @Override
    public void run() {

        Prisionero prisionero = prisioneros[Integer.valueOf(Thread.currentThread().getName())];
        System.out.println("pasa el prisionero "+prisionero.getId());
        while(!salir){
            lock.lock();
            try{
                habitacion.entraHabitacion(prisionero);
                if(prisionero.getEsVocero()){
                    salir=!habitacion.entraHabitacion(prisionero);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<Contante.PRISIONEROS;i++){
            Thread t = new Thread(m,""+i);
            threads.add(t);
            t.start();
        }

        for(Thread t : threads){
            t.join();
        }
       
        final Logger LOG = Logger.getLogger("paquete.NombreClase"); // EJEMPLO LOGGER

        if(LOGS) LOG.info("Ya salimos xd");
    }
}