package kass.concurrente;
import kass.concurrente.crypto.Cifrar;

public class Hilos extends Thread{
    private int inicio;
    private int fin;
    private boolean esCorrecta;
    private String llave;
    private String alfabeto;
    private String contrasennaCorrecta;

    public Hilos(String llave, String alfabeto, int inicio, int fin){
        this.llave = llave;
        this.alfabeto = alfabeto;
        this.esCorrecta = false;
    }

    public void run(){
        for(int i = inicio; i < fin; ++i){
            String contrasenna = genera_contrasennas(i);
            try {
                esCorrecta = Cifrar.descifraC(llave, contrasenna);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(esCorrecta){
                contrasennaCorrecta = contrasenna;
                System.err.println("La contrasenna es: " + contrasennaCorrecta);
                break;
            }
        }
    }

    public String genera_contrasennas(int longitud_intento){
        String contrasenna = "";
        for(int i = 0; i < longitud_intento; ++i){
            contrasenna += alfabeto.charAt((int)(Math.random()*alfabeto.length()));
        }
        return contrasenna;
    }
}

