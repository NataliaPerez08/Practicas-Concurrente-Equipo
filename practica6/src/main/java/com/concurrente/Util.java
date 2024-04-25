package com.concurrente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

    public static String leerArchivo(String nombreArchivo) {
        Path path = Paths.get(nombreArchivo);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void crearCarpeta(String nombreCarpeta) {
        File carpeta = new File(nombreCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public static void guardarArchivo(String contenido, String nombreArchivo) {
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            escritor.write(contenido);
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


