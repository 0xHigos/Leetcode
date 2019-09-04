package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock {

    static final Node EMPTY = new Node();
    private volatile int state;
    private volatile Node head;
    private volatile Node tail;

    private static Unsafe unsafe;
    private static long stateOffset;
    private static long tailOffset;
    private static class Node{
        Thread thread;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(Thread thread, Node prev) {
            this.thread =thread;
            this.prev =prev;
        }
    }
    static {
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("tail"));


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    // 原子更新state字段
    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    public MyLock() {
        head=tail=EMPTY;
    }

    public void lock() {
        if(compareAndSetState(0,1))
            return;
        Node node = enqueue();
        Node prev =node.prev;
        while (node.prev != head || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0l);
        }
        head =node;
        node.thread=null;
        node.prev=null;
        prev.prev=null;
    }

    private Node enqueue() {
        while (true) {
            Node t =tail;
            Node node = new Node(Thread.currentThread(), t);
            if (compareAndSetTail(t, node)) {
                t.next =node;
                return node;
            }
        }
    }

    public void unlock() {
        state =0;
        Node next  = head.next;
        if (next != null) {
            unsafe.unpark(next.thread);
        }
    }
    private static int count =0;
    public static void main(String[] args) throws InterruptedException {
        MyLock lock =new MyLock();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 10000).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }, "tt-" + i).start());
        countDownLatch.await();
        System.out.println(count);

    }
}
