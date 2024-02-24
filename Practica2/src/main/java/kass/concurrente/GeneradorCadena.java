package kass.concurrente;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;

/**
 * Clase que genera y manipula las cadenas para obtener la contraseña.
 */
public class GeneradorCadena {

    /**
     * Genera las cadenas de una longitud determinada
     * @param alfabeto
     * @param longitud
     */
    public static void generarCadenasIncremental(String alfabeto, int longitud) {
        char[] cadenaActual = new char[longitud];
        generarCadenasRecursivo(alfabeto, longitud, cadenaActual, 0);
    }

    /**
     * Genera las cadenas de una longitud determinada de forma recursiva y las procesa para saber si alguna corresponde a la contraseña esperada.
     * @param alfabeto
     * @param longitud
     * @param cadenaActual
     * @param posicion
     */
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
    /**
     * Procesa la cadena para saber si corresponde a la contraseña esperada.
     * @param cadena
     */
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
