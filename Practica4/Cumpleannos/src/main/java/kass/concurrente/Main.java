package kass.concurrente;
import kass.concurrente.tenedor.TenedorImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenidos al Hoyo de Kassandra");//Investiguen la referencia, quien la diga primero en discord, se lleva participacion

        System.out.println("Prueba tenedorImpl");
        TenedorImpl tenedor = new TenedorImpl(1);
        tenedor.tomar();
        System.out.println("Tenedor tomado: "+tenedor.getVecesTomado());
        tenedor.soltar();
        System.out.println("Tenedor soltado: "+tenedor.getVecesTomado());
    }
}