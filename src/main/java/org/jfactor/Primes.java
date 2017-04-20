package org.jfactor;

import java.util.BitSet;
import java.util.function.LongSupplier;

/**
 * @author alexander.jipa@gmail.com
 */
public class Primes {
    private Primes() {
        throw new UnsupportedOperationException();
    }

    private static boolean isTrivialPrime(final long n) {
        return (n == 2 || n == 3 || n == 5 || n == 7 || n == 11);
    }

    /**
     * Fermat Little Theorem primality test
     * @param n target number
     * @param k accuracy (times to check)
     * @param randomGenerator supplier of random values
     * @return false if the number is definitely composite, true if it's probably a prime
     */
    public static boolean fermatProbablePrime(final long n, long k, LongSupplier randomGenerator) {
        if (isTrivialPrime(n)) {
            return true;
        }
        long nMinusOne = n - 1;
        while (k-- > 0) {
            long test = 2 + randomGenerator.getAsLong() % nMinusOne;
            if (Modulos.modExp(test, nMinusOne, n) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Miller–Rabin primality check
     * @param n target number
     * @param k accuracy (times to check)
     * @param randomGenerator supplier of random values
     * @return false if the number is definitely composite,  true if it's probably a prime
     */
    public static boolean millerRabinProbablePrime(final long n, long k, LongSupplier randomGenerator) {
        if (isTrivialPrime(n)) {
            return true;
        }
        final long nMinusOne = n - 1;
        int r = 0;
        long d = nMinusOne;
        while (Factors.trialDivision(d) == 2) {
            r++;
            d /= 2;
        }
        witness:
        while (k-- > 0) {
            long test = 2 + randomGenerator.getAsLong() % nMinusOne;
            long x = Modulos.modExp(test, d, n);
            if (x == 1 || x == nMinusOne) {
                continue;
            }
            for (int i = 0; i < r; i++) {
                x = Modulos.modExp(x, 2, n);
                if (x == 1) {
                    return false;
                }
                if (x == nMinusOne) {
                    continue witness;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * nth prime calculation using Eratosthenes sieve
     * Time Complexity: O(n*log(log n))
     * Space Complexity: O(n)
     * @param n prime number index
     * @return nth prime number
     */
    public static int nthPrimeEratosthenesSieve(int n) {
        int trivial = trivialNthPrime(n);
        if (trivial > 0) {
            return trivial;
        }
        int bound = (int) (n*Math.log(n*Math.log(n))); // https://en.wikipedia.org/wiki/Prime-counting_function#Inequalities
        BitSet bits = new BitSet(bound);
        for (int i = 2, maxFactor = (int) Math.sqrt(bound); i <= maxFactor; i = bits.nextClearBit(i + 1)) {
            for (int j = i*i; j > 0 /*overflow*/ && j < bound; j += i) {
                bits.set(j);
            }
        }
        for (int i = 2; i < bound; i = bits.nextClearBit(i + 1)) {
            if (--n == 0) {
                return i;
            }
        }
        throw new IllegalStateException("Should have found the number by now");
    }

    private static int trivialNthPrime(int n) {
        switch (n) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 7;
            case 5:
                return 11;
        }
        return 0;
    }

    /**
     * nth prime calculation using Atkin sieve
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param n prime number index
     * @return nth prime number
     */
    public static long nthPrimeAtkinSieve(int n) {
        int trivial = trivialNthPrime(n);
        if (trivial > 0) {
            return trivial;
        }
        int bound = (int) (n*Math.log(n*Math.log(n))); // https://en.wikipedia.org/wiki/Prime-counting_function#Inequalities
        BitSet bits = new BitSet(bound);
        bits.set(2);
        bits.set(3);
        for (int x = 1, x2 = x*x; x2 > 0 /*overflow*/ && x2 < bound; x++, x2 = x*x) {
            for (int y = 1, y2 = y*y; y2 > 0 /*overflow*/ && y2 < bound; y++, y2 = y*y) {
                {
                    int k = 4*x2 + y2;
                    int kMod12 = k % 12;
                    if (k < bound && (kMod12 == 1 || kMod12 == 5)) {
                        bits.flip(k);
                    }
                }
                {
                    int k = 3*x2 + y2;
                    int kMod12 = k % 12;
                    if (k < bound && kMod12 == 7) {
                        bits.flip(k);
                    }
                }
                if (x > y) {
                    int k = 3*x2 - y2;
                    int kMod12 = k % 12;
                    if (k < bound && kMod12 == 11) {
                        bits.flip(k);
                    }
                }
            }
        }
        for (int k = 5, k2 = k*k; k2 > 0 /*overflow*/ && k2 < bound; k++, k2 = k*k) {
            if (bits.get(k)) {
                for (int i = k2; i < bound; i += k2) {
                    bits.clear(i);
                }
            }
        }
        for (int i = 2; i < bound; i = bits.nextSetBit(i + 1)) {
            if (--n == 0) {
                return i;
            }
        }
        throw new IllegalStateException("Should have found the number by now");
    }
}
