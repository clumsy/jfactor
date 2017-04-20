package org.jfactor;

/**
 * @author alexander.jipa@gmail.com
 */
public class Bits {
    private Bits() {
        throw new UnsupportedOperationException();
    }

    /**
     * Bitwise rotating to the left
     * @param n target number
     * @param k number of positions to rotate
     * @return left rotation result
     */
    public static long rotateLeft(long n, int k) {
        return (n << k) | (n >>> (64 - k));
    }
}
