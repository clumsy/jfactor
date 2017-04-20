package org.jfactor;

/**
 * @author alexander.jipa@gmail.com
 */
public class Modulos {
    private Modulos() {
        throw new UnsupportedOperationException();
    }

    /**
     * Exponential modulo
     * @param n target number
     * @param exp exponent
     * @param m modulus
     * @return modulo
     */
    public static long modExp(final long n, final long exp, final long m) {
        long c = 1;
        for (int e = 1; e <= exp; e++) {
            c = mod(c * n, m);
        }
        return c;
    }

    /**
     * Modulo
     * @param n target number
     * @param m modulus
     * @return modulo
     */
    public static long mod(long n, long m) {
        long result = n % m;
        return (result < 0) ? result + m : result;
    }
}
