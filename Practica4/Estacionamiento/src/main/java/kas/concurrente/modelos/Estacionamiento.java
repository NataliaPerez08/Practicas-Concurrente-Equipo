package kas.concurrente.modelos;

/**
 * En esta clase se simula el estacionamiento en si
 * Posee un conjunto de arreglos de tipo Lugar (o arreglo bidimensional?)
 * Posee un entero de lugaresDisponibles (Se podra hacer por pisos?) (Habra otra manera de hacerlo mejor?)
 * @author Kassandra Mirael
 * @version 1.0
 */
import java.util.concurrent.Semaphore;

public class Estacionamiento {
    private Lugar[][] lugares;
    private int lugaresDisponibles;
    private Semaphore mutex;
    private Semaphore disponible;

    public Estacionamiento(int capacidad, int pisos) {
        lugares = new Lugar[pisos][capacidad / pisos];
        lugaresDisponibles = capacidad;
        mutex = new Semaphore(1);
        disponible = new Semaphore(capacidad);
        inicializaLugares();
    }

    public Lugar[][] getLugares() {
        return this.lugares;
    }

    public int getLugaresDisponibles() {
        try {
            mutex.acquire();
            int disponibles = lugaresDisponibles;
            mutex.release();
            return disponibles;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        }
    }

    public boolean estaLleno() {
        try {
            mutex.acquire();
            boolean lleno = lugaresDisponibles == 0;
            mutex.release();
            return lleno;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return true;
        }
    }

    public void inicializaLugares() {
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares[i].length; j++) {
                lugares[i][j] = new Lugar(i * lugares[i].length + j);
            }
        }
    }

    public void entraCarro(int nombre) throws InterruptedException {
        disponible.acquire();
        int lugar = obtenLugar();
        asignaLugar(lugar);
        System.out.println("El carro " + nombre + " ha entrado al estacionamiento.");
        disponible.release();
    }

    public void asignaLugar(int lugar) throws InterruptedException {
        int piso = lugar / lugares[0].length;
        int espacio = lugar % lugares[0].length;
        lugares[piso][espacio].estaciona();
    }

    public int obtenLugar() {
        int randomPiso = (int) (Math.random() * lugares.length);
        int randomEspacio = (int) (Math.random() * lugares[0].length);
        return randomPiso * lugares[0].length + randomEspacio;
    }
}
