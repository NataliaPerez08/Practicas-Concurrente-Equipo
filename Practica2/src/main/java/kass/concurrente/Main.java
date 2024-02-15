package kass.concurrente;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;

/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        calcular_combinaciones(Constante.ALFABETO.length());

    }

    public static void calcular_combinaciones(int longitudAlfabeto){
        int totalCombinaciones = 0;
        System.out.print("Sabemos que la contrasenna tiene entre 7 y 13 caracteres, por lo que el numero de combinaciones posibles es: ");
        
        for(int i = 7; i <= 13; ++i){
            totalCombinaciones += Math.pow(longitudAlfabeto, i);
        }
        System.out.println(totalCombinaciones);
    }

    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }

    public static String genera_contrasennas(int longitud_intento){
        String contrasenna = "";
        for(int i = 0; i < longitud_intento; ++i){
            contrasenna += Constante.ALFABETO.charAt((int)(Math.random()*Constante.ALFABETO.length()));
        }
        return contrasenna;
    }

    public static void secuencial() throws Exception {
        Long inicio = System.nanoTime();
        System.out.println("Vamos a intentar descifrar la contrasenna");
        String contrasenna = "";
        boolean esCorrecta = false;
        int i = 7;
        while (!esCorrecta) {
            contrasenna = genera_contrasennas(i);
            esCorrecta = Cifrar.descifraC(Constante.LLAVE, contrasenna);
            System.out.println("Contrasenna: " + contrasenna + " Es correcta: " + esCorrecta);
            i++;
        }
        Long fin = System.nanoTime();
        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
    }
}