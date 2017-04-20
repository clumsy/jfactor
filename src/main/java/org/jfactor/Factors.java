package org.jfactor;

import java.util.function.LongSupplier;

/**
 * @author alexander.jipa@gmail.com
 */
public class Factors {
    private Factors() {
        throw new UnsupportedOperationException();
    }

    /**
     * Recursive greatest common divisor calculation
     * @param a first value
     * @param b second value
     * @return greatest common divisor
     */
    public static long gcd(final long a, final long b) {
        if (a == b) {
            return a;
        }
        if (a < 2 || b < 2) {
            return 1;
        }
        if (a % 2 == 0) {
            if (b % 2 == 0) {
                return 2*gcd(a/2, b/2);
            }
            return gcd(a/2, b);
        }
        if (b % 2 == 0) {
            return gcd(a, b/2);
        }
        if (a > b) {
            return gcd((a - b)/2, b);
        }
        return gcd((b - a)/2, a);
    }

    /**
     * Trial division factorization method
     * @param n target number
     * @return
     */
    public static long trialDivision(final long n) {
        for (long i = 2, bound = (long) Math.sqrt(n); i < bound; i++) {
            if (n % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * Fermat factorization method
     * @param n target n
     * @return 1 if it's a prime, otherwise a factor
     */
    public static long fermat(final long n) {
        if (n % 2 == 0) {
            return 2;
        }
        for (long x = (long) Math.ceil(Math.sqrt(n)); x < n; x++) {
            long y = squareRoot(x*x - n);
            if (y > 0) {
                return x - y;
            }
        }
        return 1;
    }

    /**
     * Checks if specified number is a square
     * @param n target number
     * @return whether or not the number is a square
     */
    public static boolean isSquare(final long n) {
        return squareRoot(n) > 0;
    }

    /**
     * Calculates square root of a square number
     * @param n target number
     * @return 0 if not a square number otherwise the square root value
     */
    public static long squareRoot(final long n) {
        int lastDigit = (int) (n % 10);
        if (lastDigit != 2 && lastDigit != 3 && lastDigit != 7 && lastDigit != 8) {
            double r = Math.sqrt(n);
            long r_long = (long) r;
            if ((r - r_long) == 0) {
                return r_long;
            }
        }
        return 0;
    }
}
