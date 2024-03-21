package kas.concurrente.modelos;

import java.util.concurrent.Semaphore;

public class Estacionamiento {

    private final Lugar[] lugares;
    private final int capacidad;
    private int lugaresDisponibles;

    public Estacionamiento(int capacidad) {
        this.capacidad = capacidad;
        this.lugares = new Lugar[capacidad];
        this.lugaresDisponibles = capacidad;
        inicializaLugares();
    }

    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public boolean estaLleno() {
        return lugaresDisponibles == 0;
    }

    public void inicializaLugares() {
        for (int i = 0; i < capacidad; i++) {
            lugares[i] = new Lugar(i);
        }
    }

    public void entraCarro(int nombre) throws InterruptedException {
        int lugar = obtenLugar();
        asignaLugar(lugar);
        System.out.println("El carro " + nombre + " ha entrado al estacionamiento.");
    }

    public void asignaLugar(int lugar) throws InterruptedException {
        lugares[lugar].estaciona();
    }

    public int obtenLugar() {
        return (int) (Math.random() * capacidad);
    }

    // Método para obtener la lista de lugares
    public Lugar[] getLugares() {
        return lugares;
    }
}
