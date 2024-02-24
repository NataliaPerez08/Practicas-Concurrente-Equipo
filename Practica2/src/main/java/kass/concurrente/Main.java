package kass.concurrente;

import kass.concurrente.constants.Constante;
/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Long inicio = System.nanoTime();
        System.out.println("Inicio "+inicio);
        secuencial();
        Long fin = System.nanoTime();
        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
    }
    public static void secuencial(){
        GeneradorCadena.generarCadenasIncremental(Constante.ALFABETO,6);
    }
    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }
}