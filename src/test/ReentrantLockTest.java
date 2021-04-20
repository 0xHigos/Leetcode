package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition  =lock.newCondition();
        new Thread(()->{
            try {
                lock.lock();
                try {
                    System.out.println("condition before");
                    condition.await();
                    System.out.println("condition after");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            lock.lock();
            try {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("signal before ");
                condition.signal();
                System.out.println("signal after");
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
