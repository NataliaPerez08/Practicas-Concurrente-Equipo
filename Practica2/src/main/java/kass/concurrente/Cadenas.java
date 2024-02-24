package kass.concurrente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;
/**
* Clase que genera y manipula las cadenas para obtener la contraseña.
*/
public class Cadenas extends Thread{
    private String alfabeto;
    private int longitud;
    private int[] indicesIni;
    private int[] indicesFin;
    private boolean bandera=true;

    /**
     * Constructor de la clase
     * @param alfabeto conjunto de símbolos que generan a las cadenas
     * @param longitud cantidad de símbolos en la cadena
     * @param indicesFin lista con los índices de la primer cadena que se puede generar , donde cada índice  corresponde a la posición de cada símbolo dentro del alfabeto 
     * @param indicesFin lista con los índices de la última cadena que se puede generar , donde cada índice  corresponde a la posición de cada símbolo dentro del alfabeto 
     * 
     */ 
    public Cadenas(String alfabeto, int longitud,int[] indicesIni,int[] indicesFin){
        this.alfabeto = alfabeto;
        this.longitud = longitud;
        this.indicesIni=indicesIni;
        this.indicesFin=indicesFin;

    }
    
    /**
     * Método que realiza todas las operaciones necesarias que hace el hilo para tratar de encontrar
     * la contraseña.
     */
    public void run(){
        char[] cadenaActual = new char[longitud];
        
        boolean aux = generarCadenasRecursivo(alfabeto, longitud, cadenaActual, 0,indicesIni,indicesFin);
        if (aux) {
            System.err.println("Se ha encontrado la contrasenna");
            return;
        }else{
            System.out.println("No se ha encontrado en el hilo "+ Thread.currentThread().getName());
        }
    }

   /**
     * Genera las cadenas de una longitud determinada de forma recursiva y las procesa para saber si alguna corresponde a la contraseña esperada.
     * @param alfabeto conjunto de símbolos que generan a las cadenas
     * @param longitud cantidad de símbolos en cada cadena.
     * @param cadenaActual cadena sobre la que se hace la recursividad.
     * @param posición índice de un símbolo en la cadena.
     * @param indicesFin lista con los índices de la primer cadena que se puede generar , donde cada índice  corresponde a la posición de cada símbolo dentro del alfabeto 
     * @param indicesFin lista con los índices de la última cadena que se puede generar , donde cada índice  corresponde a la posición de cada símbolo dentro del alfabeto 
     * @return boolean True si se encuentra 
     */
    private boolean generarCadenasRecursivo(String alfabeto, int longitud, char[] cadenaActual, int posicion,int[] indicesIni,int[] indicesFin) {
        if(bandera){
            if (posicion == longitud) {
                procesarCadena(cadenaActual);
                return true;
            }
            int lim=alfabeto.length()-1;
            for (int i = indicesIni[posicion]; i <=lim; i++) {
                cadenaActual[posicion] = alfabeto.charAt(i);
                if(posicion<longitud-1) indicesIni[posicion+1]=0;
                if(limite(cadenaActual, indicesFin, alfabeto)){
                    bandera=false;
                    return false;
                } 
                generarCadenasRecursivo(alfabeto, longitud, cadenaActual, posicion + 1,indicesIni,indicesFin);   
            }
           return false;
        }
        return false;
        
        
        
    }
    /**
     * Procesa una cadena para tratar de encontrar la contraseña.
     * @param cadena cadena que será ocupada para ver si es la contraseña que se busca.
     */
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

    /**
    *Convierte de nanoSegundos a segundos
    * @param tiempo cantidad de nanosegundos que serán convertidos a segundos.
    * @return double tiempo resultante en segundos.
    **/
    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }

     /**
     * Método que verifica si una cadena corresponde con la última cadena que puede generar, 
     * de manera recursiva, un hilo.
     * @param cadena cadena a analizar para saber si corresponde con el límite de cadenas que se pueden generar.
     * @param indicesFin lista con los índices de última cadena que se puede generar , donde cada índice  corresponde a la posición de cada símbolo dentro del alfabeto 
     * @param alfabeto símbolos permitidos para generar las cadenas.
     * @return boolean True si los símbolos la cadena pasada corresonde con con los símbolos obtenidos de índicesFin. False en caso contrario.
     */
    private boolean limite(char[] cadena, int[] indicesFin,String alf){
        
        for(int i= 0;i<cadena.length;i++){
            if(cadena[i]!=alf.charAt(indicesFin[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long tiempoInicio = System.nanoTime();
        System.out.println("Inicio: " + nanoSegundoASegundo(tiempoInicio));
        int longitud = 6;
        System.out.println("Longitud: " + longitud);
        int hilos = 1;
        System.out.println("Hilos: " + hilos);

        List<Cadenas> listaHilos = new ArrayList<>();

        String original = "abcdefghijklmnopqrstuvwxyz";
        int lenAlf = original.length();
        int potencia = lenAlf;

        while(hilos>potencia){

            potencia *=lenAlf;
        }
       
        double cociente = Math.pow(lenAlf, longitud)/hilos;
        System.out.println("Cociente: " + cociente);
        System.exit(0);
        double aux =0;

        int[] indicesIni = new int[longitud];
        int[] indicesFin = new int[longitud];

        for(int j=0;j<hilos;j++){
            aux=(j)*cociente;
            
            for(int k=longitud-1;k>=0;k--){
                indicesIni[k]= ((int) aux)%lenAlf;
                aux=aux/lenAlf;
 
            }
            aux=(j+1)*cociente-1;
            for(int k=longitud-1;k>=0;k--){
                

            indicesFin[k]= ((int) aux)%lenAlf;
            aux=aux/lenAlf;
 
            }

            Cadenas h = new Cadenas(original, longitud,indicesIni,indicesFin);
            h.start();
            listaHilos.add(h);
            System.out.println("indicesIni del hilo "+1+": "+Arrays.toString(indicesIni)+ ", indicesFin del hilo "+1+": "+Arrays.toString(indicesFin));

            
        }

       
        for(Cadenas hilo: listaHilos){
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        Long tiempo_fin = System.nanoTime();
        System.out.println("Tiempo final: " + nanoSegundoASegundo(tiempo_fin));
        
    }
}
