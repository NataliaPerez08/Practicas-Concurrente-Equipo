package kass.concurrente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;

public class Cadenas extends Thread{
    private String alfabeto;
    private int longitud;
    private int[] indicesIni;
    private int[] indicesFin;
    private boolean bandera=true;

    public Cadenas(String alfabeto, int longitud,int[] indicesIni,int[] indicesFin){
        this.alfabeto = alfabeto;
        this.longitud = longitud;
        this.indicesIni=indicesIni;
        this.indicesFin=indicesFin;

    }
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

   
    private boolean generarCadenasRecursivo(String alfabeto, int longitud, char[] cadenaActual, int posicion,int[] indicesIni,int[] indicesFin) {
        if(bandera){
            if (posicion == longitud) {
                
    
                //if(limite(cadenaActual, indicesFin, alfabeto)) return false;
    
                procesarCadena(cadenaActual);
                //System.out.println("cadena: "+ Arrays.toString(cadenaActual));
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

    private boolean limite(char[] cadena, int[] indicesFin,String alf){
        
        for(int i= 0;i<cadena.length;i++){
            if(cadena[i]!=alf.charAt(indicesFin[i])) return false;
            

        }
        System.out.println("Si se cumplee/////////////////");

        return true;
    }

    public static void main(String[] args) {
        Long tiempo_inicio = System.nanoTime();
        System.out.println("Inicio: "+nanoSegundoASegundo(tiempo_inicio));
        int longitud = 6;
        System.out.println("Longitud: " + longitud);
        int hilos = 10;

        List<Cadenas> listaHilos = new ArrayList<Cadenas>();

        int lugares = 1;
        
        String original = "abcdefghijklmnopqrstuvwxyz";
        int lenAlf = original.length(); 
        int potencia = lenAlf;

        

        while(hilos>potencia){

            potencia *=lenAlf;
            lugares ++;

        }
       

        double cociente = Math.pow(lenAlf, longitud)/hilos;
        System.out.println(cociente);
        double aux =0;

        int[] indicesIni = new int[longitud];
        int[] indicesFin = new int[longitud];
        double aux2 =0;



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
            }
        }
        Long tiempo_fin = System.nanoTime();
        System.out.println("Tiempo final: " + nanoSegundoASegundo(tiempo_fin));

        //Long tiempo_total = tiempo_fin - tiempo_inicio;
        //System.out.println("Tiempo total: " + nanoSegundoASegundo(tiempo_total));

        
    }
}