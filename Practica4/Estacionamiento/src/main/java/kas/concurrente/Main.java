package kas.concurrente;

import kas.concurrente.modelos.Estacionamiento;

public class Main implements Runnable {
    private final Estacionamiento estacionamiento;

    public Main() {
        estacionamiento = new Estacionamiento(50); // Capacidad de 50 carros
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.runSimulation();
    }

    public void runSimulation() throws InterruptedException {
        Thread[] hilos = new Thread[50]; // 50 carros

        // Inicializa y arranca los hilos
        for (int i = 0; i < 50; i++) {
            hilos[i] = new Thread(this, "Carro " + (i + 1));
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (Thread hilo : hilos) {
            hilo.join();
        }
    }

    @Override
    public void run() {
        String nombreCarro = Thread.currentThread().getName();
        try {
            estacionamiento.entraCarro(Integer.parseInt(nombreCarro.split(" ")[1]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
