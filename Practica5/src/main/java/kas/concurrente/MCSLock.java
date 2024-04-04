package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;


public class MCSLock implements Lock {
    /**
     * Qnode class
     */
    public class QNode {
        volatile boolean locked = false;
        volatile QNode next = null;
    }
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>(){ 
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
    }

    @Override
    public void unlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unlock'");
    }
    
}
