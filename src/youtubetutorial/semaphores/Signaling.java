package youtubetutorial.semaphores;

import java.util.concurrent.Semaphore;

public class Signaling {
    private static int number = 0;
    private static Semaphore signal = new Semaphore(0);


    static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer Says: Hi " + ++number);
                signal.release();
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    signal.acquire();
                    sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer Says: Bye " + ++number);
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
