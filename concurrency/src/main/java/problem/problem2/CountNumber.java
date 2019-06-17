package problem.problem2;

/**
 * 两个线程，相继打印数字到100
 */
public class CountNumber {
    private static Object mutex = new Object();
    public static void main(String[] args) {
        Thread t1 = new EvenThread();
        Thread t2 = new OddThread();
        t1.start();
        t2.start();
    }

    public static class EvenThread extends Thread{
        @Override
        public void run() {
            int start = 0;
            while(start <= 100){
                synchronized (mutex){
                    if(start <= 100){
                        System.out.println("even thread: " + start);
                        start += 2;
                        mutex.notify();
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
    public static class OddThread extends Thread{
        @Override
        public void run() {
            int start = 1;
            while(start <= 100){
                synchronized (mutex){
                    if(start <= 100){
                        System.out.println("odd thread: " + start);
                        start += 2;
                        mutex.notify();
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
