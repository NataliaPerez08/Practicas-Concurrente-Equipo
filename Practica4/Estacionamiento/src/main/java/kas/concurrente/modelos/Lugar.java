package kas.concurrente.modelos;

import java.util.concurrent.Semaphore;

public class Lugar {
    private final int id;
    private boolean disponible;
    private final Semaphore semaforo;
    private int vecesEstacionado;

    public Lugar(int id) {
        this.id = id;
        this.disponible = true;
        this.semaforo = new Semaphore(1, true); // Semáforo débil
        this.vecesEstacionado = 0;
    }

    public int getId() {
        return id;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void estaciona() throws InterruptedException {
        semaforo.acquire();
        try {
            if (disponible) {
                disponible = false;
                System.out.println("El carro ha estacionado en el lugar " + id);
                vecesEstacionado++; // Incrementamos el contador
            }
        } finally {
            semaforo.release();
        }
    }

    public void vePorPastel() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000 + 1000));
        System.out.println("El carro que estaba en el lugar " + id + " ha salido para ir por pastel.");
    }

    // Método para obtener el número de veces que se ha estacionado en este lugar
    public int getVecesEstacionado() {
        return vecesEstacionado;
    }
}
