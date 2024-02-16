package kass.concurrente;
import java.util.ArrayList;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;

public class Hilos extends Thread{
    private String alfabeto;
    private int longitud;
    public Hilos(String alfabeto, int longitud){
        this.alfabeto = alfabeto;
        this.longitud = longitud;
    }
    public void run(){
        char[] cadenaActual = new char[longitud];
        boolean aux = generarCadenasRecursivo(alfabeto, longitud, cadenaActual, 0);
        if (aux) {
            System.err.println("Se ha encontrado la contrasenna");
            return;
        }
    }
    private boolean generarCadenasRecursivo(String alfabeto, int longitud, char[] cadenaActual, int posicion) {
        if (posicion == longitud) {
            procesarCadena(cadenaActual);
            return true;
        }
        for (int i = 0; i < alfabeto.length(); i++) {
            cadenaActual[posicion] = alfabeto.charAt(i);
            generarCadenasRecursivo(alfabeto, longitud, cadenaActual, posicion + 1);
        }
        return false;
    }
    private void procesarCadena(char[] cadena) {
        String aux = new String(cadena);
        try {
            if(Cifrar.descifraC(Constante.LLAVE, aux)){
                System.out.println("La contrasenna es: " + aux);
                Long tiempo_fin = System.nanoTime();
                System.out.println("Tiempo final: " + nanoSegundoASegundo(tiempo_fin));
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error al descifrar la contrasenna: " + aux);
        }
    }

    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }

    public static void main(String[] args) {
        Long tiempo_inicio = System.nanoTime();
        System.out.println("Inicio: "+nanoSegundoASegundo(tiempo_inicio));
        int longitud = 11;
        int hilos = Constante.HILOS;

        List<Hilos> listaHilos = new ArrayList<Hilos>();
        for(int i = 0; i < hilos; ++i){
            String original = Constante.ALFABETO;
            String reverse = new StringBuilder(Constante.ALFABETO).reverse().toString();
            
            String primeraMitad = original.substring(0, original.length()/2);
            String ultimaMitad = original.substring(original.length()/2, original.length());

            String mitad = ultimaMitad + primeraMitad;

            String otraMitad = new StringBuilder(mitad).reverse().toString();

            if (i % 4 == 0) {
                Hilos hilo = new Hilos(original, longitud);
                hilo.start();
                listaHilos.add(hilo);
            } else if (i % 4 == 1) {
                Hilos hilo = new Hilos(reverse, longitud);
                hilo.start();
                listaHilos.add(hilo);
            } else if (i % 4 == 2) {
                Hilos hilo = new Hilos(mitad, longitud);
                hilo.start();
                listaHilos.add(hilo);
            } else if (i % 4 == 3) {
                Hilos hilo = new Hilos(otraMitad, longitud);
                hilo.start();
                listaHilos.add(hilo); 
            }
        }
        for(Hilos hilo: listaHilos){
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Long tiempo_fin = System.nanoTime();
        System.out.println("Tiempo final: " + nanoSegundoASegundo(tiempo_fin));

        Long tiempo_total = tiempo_fin - tiempo_inicio;
        System.out.println("Tiempo total: " + nanoSegundoASegundo(tiempo_total));
    }
}

