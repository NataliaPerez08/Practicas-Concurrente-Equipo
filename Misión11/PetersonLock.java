/**
 * Clase que implementa el candado usando el Legendario
 * algoritmo de PeterGod.
 * No hay mucho que decir, ya saben que hacer
 * @version 1.0
 * @author Kassandra Mirael
 */
public class PetersonLock implements Lock {
    // thread-local index, 0 or 1
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;
    
    @Override
    public void lock() {
        System.out.println("Locking. Hilo: "+Thread.currentThread().getName());
        int i = (Integer.parseInt(Thread.currentThread().getName()))%2;

        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) {
            // wait
        }
    }

    @Override
    public void unlock() {
        System.out.println("Unlocking. Hilo: "+Thread.currentThread().getName());
        int i = (Integer.parseInt(Thread.currentThread().getName()))%2;
        flag[i] = false;
    }

    @Override
    public String toString() {
        String bandera = "";
        if(flag[0]){
            bandera += "1";
        }else{
            bandera += "0";
        }
        return "PetersonLock{" + "flag=" + bandera + ", victim=" + victim + '}';
    }
}
