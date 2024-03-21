package kas.concurrente.modelos;

/**
 * Clase que modela un Lugar
 * El lugar consta de un id
 * un booleano que nos dice si esta dispoible
 * y un objeto del tipo Semaphore (El semaforo)
 * @author Kassandra Mirael
 * @version 1.0
 */
import java.util.concurrent.Semaphore;

public class Lugar {
    private Integer id;
    private boolean disponible;
    private Semaphore semaforo;
    private int vecesEstacionado;


    public Lugar(int id) {
        this.id = id;
        disponible = true;
        semaforo = new Semaphore(1, true); // Semáforo débil
    }

    public void estaciona() throws InterruptedException {
        semaforo.acquire();
        disponible = false;
        System.out.println("El carro ha sido estacionado en el lugar " + id);
        semaforo.release();
        vePorPastel();
    }

    public void vePorPastel() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000) + 1000); // Tiempo entre 1 y 5 segundos
        System.out.println("El carro ha salido para comprar un pastel.");
    }

    public Integer getId() {
        return id;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public int getVecesEstacionado() {
        return vecesEstacionado;
    }
}
