public class Main {
    public static void main(String[] args) {
        Interruptor interruptor = new Interruptor();
        Prisionero[] prisioneros = new Prisionero[Constante.NUM_PRISIONEROS];

        // Crear prisioneros
        for (int i = 0; i < Constante.NUM_PRISIONEROS; i++) {
            prisioneros[i] = new Prisionero(i + 1, interruptor);
        }

        // Iniciar hilos de los prisioneros
        for (Prisionero prisionero : prisioneros) {
            prisionero.start();
        }

        // Esperar a que todos los prisioneros terminen y verificar el estado del interruptor
        boolean todosPasaron = true;
        for (Prisionero prisionero : prisioneros) {
            try {
                prisionero.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar si todos los prisioneros han pasado
        if (interruptor.estaEncendido()) {
            System.out.println("Todos los prisioneros han pasado.");
        } else {
            System.out.println("No todos los prisioneros han pasado.");
        }
    }
}

