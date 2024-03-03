import java.util.Random;

public class Prisionero extends Thread {
    private Interruptor interruptor;
    private int id;
    private int intentos;

    // Colores ANSI para la consola
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Prisionero(int id, Interruptor interruptor) {
        this.id = id;
        this.interruptor = interruptor;
        this.intentos = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < Constante.NUM_PASADAS_MAX; i++) {
            pasarACuartoD();
        }
        System.out.println(ANSI_RED + "Prisionero " + id + " ha terminado sus pasadas." + ANSI_RESET);
    }

    private void pasarACuartoD() {
        System.out.println(ANSI_BLUE + "Prisionero " + id + " ha pasado a cuarto D." + ANSI_RESET);
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simula tiempo arbitrario en cuarto D
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!interruptor.estaEncendido()) {
            // Espera activamente hasta que el interruptor esté encendido
            // Esto evita el uso de sincronización o variables atómicas
            // Se realiza una verificación continua del estado del interruptor
            if (intentos >= Constante.NUM_MAX_INTENTOS) {
                System.out.println(ANSI_RED + "Prisionero " + id + " ha excedido el límite de intentos." + ANSI_RESET);
                return;
            }
            intentos++;
        }
    }
}
