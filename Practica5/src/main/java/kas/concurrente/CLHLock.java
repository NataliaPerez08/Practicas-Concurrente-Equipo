package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {

    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;
    class QNode{
        volatile boolean lokced = false;
    }

    public CLHLock(){
        tail = new AtomicReference<>(null);
        myNode = ThreadLocal.withInitial(QNode::new);
        myNode = ThreadLocal.withInitial(null);
        
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        qnode.lokced = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while(pred.lokced){}
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.lokced = false;
        myNode.set(myPred.get());
        
    }

}
