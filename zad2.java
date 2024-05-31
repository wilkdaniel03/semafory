import java.util.concurrent.Semaphore;

public class CBACBADDE {
    private static final int PRINT_COUNT = 30;

    // Semafory do kontroli kolejności wypisywania
    private static final Semaphore semC1 = new Semaphore(1);
    private static final Semaphore semB1 = new Semaphore(0);
    private static final Semaphore semA = new Semaphore(0);
    private static final Semaphore semC2 = new Semaphore(0);
    private static final Semaphore semB2 = new Semaphore(0);
    private static final Semaphore semA2 = new Semaphore(0);
    private static final Semaphore semD1 = new Semaphore(0);
    private static final Semaphore semD2 = new Semaphore(0);
    private static final Semaphore semE = new Semaphore(0);

    static class PrintC1 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semC1.acquire();
                    System.out.print("C");
                    semB1.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintB1 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semB1.acquire();
                    System.out.print("B");
                    semA.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintA implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semA.acquire();
                    System.out.print("A");
                    semC2.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintC2 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semC2.acquire();
                    System.out.print("C");
                    semB2.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintB2 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semB2.acquire();
                    System.out.print("B");
                    semA2.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintA2 implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semA2.acquire();
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
                    semE.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class PrintE implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    semE.acquire();
                    System.out.print("E");
                    semC1.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread tC1 = new Thread(new PrintC1());
        Thread tB1 = new Thread(new PrintB1());
        Thread tA = new Thread(new PrintA());
        Thread tC2 = new Thread(new PrintC2());
        Thread tB2 = new Thread(new PrintB2());
        Thread tA2 = new Thread(new PrintA2());
        Thread tD1 = new Thread(new PrintD1());
        Thread tD2 = new Thread(new PrintD2());
        Thread tE = new Thread(new PrintE());

        tC1.start();
        tB1.start();
        tA.start();
        tC2.start();
        tB2.start();
        tA2.start();
        tD1.start();
        tD2.start();
        tE.start();
    }
}
