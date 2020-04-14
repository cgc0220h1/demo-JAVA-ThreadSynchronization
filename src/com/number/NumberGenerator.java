package com.number;

public class NumberGenerator implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " bắt đầu với Priority là: "
                + Thread.currentThread().getPriority());
        for (int index = 0; index <= 10; index++) {
            System.out.println(index
                    + " - hashCode: " + hashCode()
                    + " - Thread: " + Thread.currentThread().getName()
                    + " - Priority: " + Thread.currentThread().getPriority());
        }
        System.out.println(Thread.currentThread().getName()
                + " hoàn thành với Priority là: "
                + Thread.currentThread().getPriority());
    }

    public static class SequenceGenerator {
        public static void main(String[] args) {
            NumberGenerator generator1 = new NumberGenerator();
            NumberGenerator generator2 = new NumberGenerator();
            Thread thread1 = new Thread(generator1);
            Thread thread2 = new Thread(generator2);

            thread1.setPriority(Thread.MAX_PRIORITY);
            thread2.setPriority(Thread.MIN_PRIORITY);

            long startTime = System.currentTimeMillis();
            thread2.start();
            thread1.start();
            System.out.println("Task Finished in: " + (System.currentTimeMillis() - startTime));
            try {
                thread2.join();
                thread1.join();
            } catch (InterruptedException ie) {
                System.out.println("Main Thread hoàn thành!");
            }
        }
    }

    public static class OddEvenMultiProcess {
        public static void main(String[] args) {
            Thread oddThread = new Thread() {
                @Override
                public void run() {
                    for (int index = 0; index <= 10; index++) {
                        if (index % 2 != 0) {
                            System.out.println(currentThread().getName()
                                    + " - OddNumber: "
                                    + index);
                        }
                    }
                }
            };

            Thread evenTread = new Thread() {
                @Override
                public void run() {
                    for (int index = 0; index <= 10; index++) {
                        if (index % 2 == 0) {
                            System.out.println(currentThread().getName()
                                    + " - EvenNumber: "
                                    + index);
                        }
                    }
                }
            };

            oddThread.setName("Odd Thread");
            evenTread.setName("Even Thread");

            oddThread.start();
            try {
                oddThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            evenTread.start();
        }
    }

    public static class OddEvenSynchronizedMultiThread {
        private int index = 0;

        synchronized public void findNumber(int number) throws InterruptedException {
            while (index <= number) {
                if (index % 2 != 0) {
                    System.out.println(Thread.currentThread().getName()
                            + " - OddNumber: "
                            + index);
                } else {
                    System.out.println(Thread.currentThread().getName()
                            + " - EvenNumber: "
                            + index);
                }
                index++;
                Thread.sleep(1000);
                notify();
                if (index <= number) {
                    wait();
                } else {
                    Thread.currentThread().checkAccess();
                }
            }
        }

        public static void main(String[] args) {
            OddEvenSynchronizedMultiThread generator = new OddEvenSynchronizedMultiThread();
            Thread thread1 = new Thread() {

                @Override
                public void run() {
                    try {
                        generator.findNumber(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread2 = new Thread() {

                @Override
                public void run() {
                    try {
                        generator.findNumber(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
