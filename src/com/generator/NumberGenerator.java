package com.generator;

public class NumberGenerator implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " bắt đầu với Priority là: "
                + Thread.currentThread().getPriority());
        for (int index = 0; index <= 10000; index++) {
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

            thread2.start();
            thread1.start();
            try {
                thread2.join();
                thread1.join();
            } catch (InterruptedException ie) {
                System.out.println("Main Thread hoàn thành!");
            }
        }
    }

    public static class OddEvenGenerator {
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
}
