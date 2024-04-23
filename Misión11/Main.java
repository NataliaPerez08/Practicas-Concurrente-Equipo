public class Main {
    
    public static void main(String[] args) {
        PetersonLockTree tree = new PetersonLockTree();
        PetersonLock lock1 = new PetersonLock();
        PetersonLock lock2 = new PetersonLock();
        
        System.out.println("Adding locks");
        tree.add(lock1);
        tree.add(lock2);

        System.out.println(tree);

        System.out.println("Removing lock1");
        tree.remove(lock1);
        System.out.println(tree);
    }
}
