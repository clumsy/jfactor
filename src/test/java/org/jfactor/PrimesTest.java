package org.jfactor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PrimesTest {

    @Test
    public void fermatCompositesDetection() {
        int n = 13 * 17;
        int k = 1;
        assertFalse(Primes.fermatProbablePrime(n, k, PseudoRandoms.xorshift128(n, k)));
    }

    @Test
    public void millerRabinCompositesDetection() {
        int n = 13 * 17;
        int k = 1;
        assertFalse(Primes.millerRabinProbablePrime(n, k, PseudoRandoms.xorshift128(n, k)));
    }

    @Test
    public void nthPrimeEratosthenesSieveDetection() {
        assertEquals(1299721, Primes.nthPrimeEratosthenesSieve(100001));
    }

    @Test
    public void nthPrimeAtkinSieveDetection() {
        assertEquals(1299721, Primes.nthPrimeAtkinSieve(100001));
    }
}
