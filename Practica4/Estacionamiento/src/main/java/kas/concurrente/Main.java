package kas.concurrente;
import java.util.ArrayList;
import java.util.List;

import kas.concurrente.modelos.Estacionamiento;

/**
 * Clase principal, la usaran para SUS pruebas
 * Pueden modigicar los valores estaticos para ver como funciona
 * NO USEN VALORES EXTREMEDAMENTE ALTOS, puede alentar mucho su compu
 * AQUI EJECUTAN LA SIMULACION
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main implements Runnable{
    private Estacionamiento estacionamiento;

    public Main() {
        estacionamiento = new Estacionamiento(200, 5); // Capacidad: 200, Pisos: 5
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.simular();
    }

    public void simular() throws InterruptedException {
        Thread[] hilos = new Thread[15]; // Número de carros
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(this, "Carro " + i);
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }
    }

    @Override
    public void run() {
        try {
            int nombreCarro = Integer.parseInt(Thread.currentThread().getName().split(" ")[1]);
            estacionamiento.entraCarro(nombreCarro);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
