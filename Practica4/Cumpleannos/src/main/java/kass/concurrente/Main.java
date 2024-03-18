package kass.concurrente;
import kass.concurrente.tenedor.Tenedor;
import kass.concurrente.tenedor.TenedorImpl;

import kass.concurrente.candadosImpl.Filtro;
import kass.concurrente.invitados.Inversionista;
import kass.concurrente.invitados.InversionistaFiltro;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Bienvenidos al Hoyo de Kassandra");//Investiguen la referencia, quien la diga primero en discord, se lleva participacion
        Tenedor[] tenedores = new TenedorImpl[5];
        //Inicializamos los tenedores	
        for(int i = 0; i < 5; i++){
            tenedores[i] = new TenedorImpl(i);
        }

        Inversionista[] inversionistas = new InversionistaFiltro[5];
        int TAM_MESA = 5;

        Thread[] hilos = new Thread[5];
        for(int i = 0; i < 5; i++){
            inversionistas[i] = new InversionistaFiltro();
            inversionistas[i].setId(i);
            inversionistas[i].setTenedorIzq(tenedores[i]);
            inversionistas[i].setTenedorDer(tenedores[(i+1)%TAM_MESA]);
            hilos[i] = new Thread(inversionistas[i]);
            hilos[i].setName(String.valueOf(i));
            System.out.println("Hilo " +hilos[i].getName() + " creado");
            System.out.println("Inversionista " + inversionistas[i] + " creado");
        }

        for (Thread thread : hilos) {
            thread.start();
        }
        for (Thread thread : hilos) {
            thread.join();
        }
    }
}