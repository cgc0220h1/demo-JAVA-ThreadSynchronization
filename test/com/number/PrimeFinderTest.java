package com.number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeFinderTest {

    @Test
    void lazyPrimeFactorization() throws InterruptedException {
        PrimeFinder primeFinder = new PrimeFinder();
        primeFinder.LazyPrimeFactorization(1000);
    }

    @Test
    void optimizePrimeFactorization() throws InterruptedException {
        PrimeFinder primeFinder = new PrimeFinder();
        primeFinder.OptimizePrimeFactorization(1000);
    }
}