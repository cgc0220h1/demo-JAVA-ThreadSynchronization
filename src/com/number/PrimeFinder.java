package com.number;

public class PrimeFinder {
    public void LazyPrimeFactorization(int number) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        int index = 2;
        boolean isPrime = true;
        while (count < number) {
            for (int divisor = 2; divisor < index; divisor++) {
                if (index % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                System.out.println(Thread.currentThread().getName()
                        + " - Found Prime Number "
                        + count
                        + ": "
                        + index);
            } else {
                isPrime = true;
            }
            index++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()
                + " finished Found "
                + number
                + " Prime Number in: "
                + (endTime - startTime)
                + " millisecond");
    }

    public void OptimizePrimeFactorization(int number) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        int index = 2;
        boolean isPrime = true;
        while (count < number) {
            for (int divisor = 2; divisor <= Math.sqrt(index); divisor++) {
                if (index % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                System.out.println(Thread.currentThread().getName()
                        + " - Found Prime Number "
                        + count
                        + ": "
                        + index);
            } else {
                isPrime = true;
            }
            index++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()
                + " finished Found "
                + number
                + " Prime Number in: "
                + (endTime - startTime)
                + " millisecond");
    }

    public static void main(String[] args) {
        PrimeFinder primeFinder1 = new PrimeFinder();
        PrimeFinder primeFinder2 = new PrimeFinder();

        Thread thread1 = new Thread("Lazy Thread") {
            @Override
            public void run() {
                primeFinder1.LazyPrimeFactorization(1000);
            }
        };
        thread1.start();

        Thread thread2 = new Thread("Optimize Thread") {
            @Override
            public void run() {
                primeFinder2.OptimizePrimeFactorization(1000);
            }
        };
        thread2.start();
    }
}
