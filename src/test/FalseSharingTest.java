package test;

public class FalseSharingTest {
    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());

    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start =System.currentTimeMillis();
        Thread t1=new Thread(()->{
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2=new Thread(()->{
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }
    private static class Pointer {
        volatile  long x;
        volatile long y;

        @Override
        public String toString() {
            return x+" "+y;
        }
    }
}
