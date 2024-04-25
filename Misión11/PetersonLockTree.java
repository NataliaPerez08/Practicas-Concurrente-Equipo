public class PetersonLockTree implements Tree{
    public class Node{
        PetersonLock lock;
        Node left;
        Node right;
        public Node(PetersonLock lock){
            this.lock = lock;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    public PetersonLockTree(){
        root = null;
    }
    @Override
    public void add(PetersonLock lock){
        if (root == null)
            root = new Node(lock);
        else
            add(root, lock);
    }
    private void add(Node node, PetersonLock lock){
        if (lock.hashCode() < node.lock.hashCode()){
            if (node.left == null)
                node.left = new Node(lock);
            else
                add(node.left, lock);
        } else {
            if (node.right == null)
                node.right = new Node(lock);
            else
                add(node.right, lock);
        }
    }
    @Override
    public void remove(PetersonLock lock){
        root = remove(root, lock);
    }

    private Node remove(Node node, PetersonLock lock){
        if (node == null)
            return null;
        if (lock.hashCode() < node.lock.hashCode())
            node.left = remove(node.left, lock);
        else if (lock.hashCode() > node.lock.hashCode())
            node.right = remove(node.right, lock);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            node.lock = min(node.right).lock;
            node.right = remove(node.right, node.lock);
        }
        return node;
    }   
    private Node min(Node node){
        if (node.left == null)
            return node;
        return min(node.left);
    }
    public String toString(){
        return toString(root);
    }
    private String toString(Node node){
        if (node == null)
            return "";
        return toString(node.left) + node.lock + " " + toString(node.right);
    }

    public void tomaCandado(){
        tomaCandado(root);
    }
    private void tomaCandado(Node node){
        if (node == null)
            return;
        System.out.println("Toma candado "+node.lock.hashCode()
        +" "+Thread.currentThread().getName()+"\n");
        node.lock.lock();
        tomaCandado(node.left);
        tomaCandado(node.right);
    }

    public void sueltaCandado(){
        sueltaCandado(root);
    }
    private void sueltaCandado(Node node){
        if (node == null)
            return;
        node.lock.unlock(); 
        sueltaCandado(node.left);
        sueltaCandado(node.right);
    }
}
