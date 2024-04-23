public class UsoPeterson {
    public static void main(String[] args) {
        /* Ejemplo de ejecucion peterson */
        PetersonLock lock1 = new PetersonLock();
        PetersonLock lock2 = new PetersonLock();

        // Thread 1
        Thread t1 = new Thread("1") {
            @Override
            public void run() {
                lock1.lock();
                System.out.println("Hilo 1 en seccion critica");
                lock1.unlock();
            }
        };

        t1.start();


    }
}
