package kass.concurrente;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;

public class GeneradorCadena {

    public static void generarCadenasIncremental(String alfabeto, int longitud) {
        char[] cadenaActual = new char[longitud];
        generarCadenasRecursivo(alfabeto, longitud, cadenaActual, 0);
    }

    private static void generarCadenasRecursivo(String alfabeto, int longitud, char[] cadenaActual, int posicion) {
        if (posicion == longitud) {
            procesarCadena(cadenaActual);
            return;
        }

        for (int i = 0; i < alfabeto.length(); i++) {
            cadenaActual[posicion] = alfabeto.charAt(i);
            generarCadenasRecursivo(alfabeto, longitud, cadenaActual, posicion + 1);
        }
    }

    private static void procesarCadena(char[] cadena) {
        String aux = new String(cadena);
        try {
            if(Cifrar.descifraC(Constante.LLAVE, aux)){
                System.out.println("La contrasenna es: " + aux);
                Long tiempo_fin = System.nanoTime();
                System.out.println("Tiempo final: " + tiempo_fin);
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error al descifrar la contrasenna: " + aux);
        }

    }
}
