package com.concurrente.git;
/* Arraylist */
import java.util.ArrayList;

import com.concurrente.Util;

/**
 * Clase que representa un repositorio de git
 * proporcionara los comandos add, commit y push
 * 
 * Debera permitir seleccionar versiones, por defecto 
 * la ultima version
 * @version 1.0
 */	

public class Git {
    /** Almacenar las versiones */
    private ArrayList<String> versions;

    /**
     * Constructor de la clase
     */
    public Git(){
        versions = new ArrayList<String>();
    }

    /**
     * Metodo que agrega un archivo al repositorio
     * @param file archivo a agregar
     */
    public void add(String contenido, String nombreArchivo){
        System.out.println("Escritura de archivo: "+nombreArchivo);
        //Util.guardarArchivo(contenido, nombreArchivo);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metodo que realiza un commit de los archivos
     * agregados al repositorio
     * @param message mensaje del commit
     */
    public void commit(String message){
        System.out.println("Realizando commit con mensaje: "+message);
        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Metodo que realiza un push de los archivos
     * al repositorio remoto
     */
    public void push(){
        System.out.println("Realizando push");
        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Metodo que permite seleccionar una version
     * @param version version a seleccionar
     */
    public void selectVersion(int version){
        System.out.println("Seleccionando version: "+version);
        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Metodo que permite seleccionar la ultima version
     */
    public void selectLastVersion(){
        System.out.println("Seleccionando ultima version");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
