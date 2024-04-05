package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

public class MCSLock implements Lock {
    /**
     * Qnode class
     */
    public class QNode {
        volatile boolean locked = false;
        AtomicReference<QNode> next = new AtomicReference<>(null);
    }

    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<>(null);
        myNode = ThreadLocal.withInitial(QNode::new);
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        if (pred != null) {
            pred.next.set(qnode);
            while (qnode.locked) {
                Thread.yield();
            }
        }
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next.get() == null) {
            if (tail.compareAndSet(qnode, null)) {
                return;
            }
            while (qnode.next.get() == null) {
                Thread.yield();
            }
        }
        qnode.next.get().locked = false;
        qnode.next.set(null);
    }
}
