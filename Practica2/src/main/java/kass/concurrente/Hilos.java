package kass.concurrente;
import kass.concurrente.constants.Constante;
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
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run(){
        System.out.println("Hilo: " + this.getName() + " con rango de contrasennas: [" + inicio + ", " + fin + "]");
        int intentos = 0;
        while (!esCorrecta) {
            for(int i = inicio; i <= fin; ){
                String contrasenna = genera_contrasennas(i);
                try {
                    esCorrecta = Cifrar.descifraC(llave, contrasenna);
                    if(esCorrecta){
                        contrasennaCorrecta = contrasenna;
                        System.out.println("Contrasenna: " + contrasenna + " Es correcta: " + esCorrecta);
                        System.out.println("El hilo: " + this.getName() + " ha terminado");
                        return;
                    }
                    if(intentos == Math.pow(Constante.ALFABETO.length(), i)){
                        i++;
                        intentos = 0;
                    }
                } catch (Exception e) {
                    System.out.println("Error al descifrar la contrasenna: " + contrasenna);
                }
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

    public static void main(String[] args) throws Exception {
        System.out.println("Vamos a intentar descifrar la contrasenna concurrentemente");
        System.out.println("A cada hilo de le asignara un rango de contrasennas a probar."+"\n"+"El rango de contrasennas se determina por el numero de hilos y el numero de contrasennas a probar");
        // Le asiganere [7, 13] contrasennas a cada hilo
        Long inicio = System.nanoTime();
        // Inicializamos los hilos
        Hilos h1 = new Hilos(Constante.LLAVE, Constante.ALFABETO, 7, 9);
        Hilos h2 = new Hilos(Constante.LLAVE, Constante.ALFABETO, 9, 12);
        Hilos h3 = new Hilos(Constante.LLAVE, Constante.ALFABETO, 12, 13);
        Hilos h4 = new Hilos(Constante.LLAVE, Constante.ALFABETO, 13, 13);
        // Iniciamos los hilos
        h1.start();h2.start();h3.start();h4.start();
        // Esperamos a que terminen
        h1.join();h2.join();h3.join();h4.join();

        Long fin = System.nanoTime();
        Long total = fin-inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
    }
    public static double nanoSegundoASegundo(Long tiempo){
        return tiempo *1.0 * Math.pow(10,-9);
    }
}

