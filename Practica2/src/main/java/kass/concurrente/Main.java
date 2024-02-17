package kass.concurrente;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;
import kass.concurrente.GeneradorCadena;
/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //calcular_combinaciones(Constante.ALFABETO.length());
        Long inicio = System.nanoTime();
        System.out.println("Inicio "+inicio);
        secuencial();
        //System.out.println(Cifrar.descifraC(Constante.LLAVE, Constante.CONTRASENNA));
        Long fin = System.nanoTime();
        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
        System.out.println("Practica 2");
    }

    public static void calcular_combinaciones(int longitudAlfabeto){
        int totalCombinaciones = 0;
        System.out.print("Sabemos que la contrasenna tiene entre 7 y 13 caracteres, por lo que el numero de combinaciones posibles es: ");
        
        for(int i = 7; i <= 13; ++i){
            totalCombinaciones += Math.pow(longitudAlfabeto, i);
        }
        System.out.println(totalCombinaciones);
    }

    public static void secuencial(){
        GeneradorCadena.generarCadenasIncremental(Constante.ALFABETO,4);
    }
    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }
}