public class Main {
    
    public static void main(String[] args) {
        PetersonLockTree tree = new PetersonLockTree();
        PetersonLock lock1 = new PetersonLock();
        PetersonLock lock2 = new PetersonLock();
        tree.add(lock1);
        tree.add(lock2);


        // Thread 1
        Thread t1 = new Thread("1") {
            @Override
            public void run() {
                tree.tomaCandado();

                //lock1.lock();
                //System.out.println("Hilo 1 en seccion critica");
                //lock1.unlock();
            }
        };

        t1.start();
    }
}
