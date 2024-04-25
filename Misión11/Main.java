public class Main {
    
    public static void main(String[] args) {
        PetersonLockTreeTest test = new PetersonLockTreeTest();
        test.setUp();
        try {
            test.lockTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
