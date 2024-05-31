import java.util.concurrent.Semaphore;

public class ABCwithSem {
    private static final int PRINT_COUNT = 30;
    
    // Semafory do kontroli kolejności wypisywania
    private static final Semaphore semA = new Semaphore(1);
    private static final Semaphore semD1 = new Semaphore(0);
    private static final Semaphore semD2 = new Semaphore(0);
    private static final Semaphore semB = new Semaphore(0);
    private static final Semaphore semC = new Semaphore(0);

    static class PrintA implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semA.acquire();
                    System.out.print("A");
                    semD1.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintD1 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semD1.acquire();
                    System.out.print("D");
                    semD2.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintD2 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semD2.acquire();
                    System.out.print("D");
                    semB.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintB implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semB.acquire();
                    System.out.print("B");
                    semC.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintC implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semC.acquire();
                    System.out.print("C");
                    semA.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread tA = new Thread(new PrintA());
        Thread tD1 = new Thread(new PrintD1());
        Thread tD2 = new Thread(new PrintD2());
        Thread tB = new Thread(new PrintB());
        Thread tC = new Thread(new PrintC());

        tA.start();
        tD1.start();
        tD2.start();
        tB.start();
        tC.start();
    }
}
