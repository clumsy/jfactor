package org.jfactor;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FactorsTest {
    private static final int LARGE_PRIME = 1_299_709;

    @Test
    public void gcdCompound() {
        assertEquals(4, Factors.gcd(4*7, 4*3));
    }

    @Test
    public void gcdPrime() {
        assertEquals(1, Factors.gcd(42, LARGE_PRIME));
    }

    @Test
    public void square() {
        assertTrue(Factors.isSquare(7*7));
        assertFalse(Factors.isSquare(7*7 + 1));
    }

    @Test
    public void squareRoot() {
        assertEquals(7, Factors.squareRoot(7*7));
        assertEquals(0, Factors.squareRoot(7*7 + 1));
    }

    @Test
    public void trialDivisionCompound() {
        long factor = Factors.trialDivision(2*13);
        assertTrue(Arrays.asList(2, 13).contains((int) factor));
    }

    @Test
    public void trialDivisionPrime() {
        long factor = Factors.trialDivision(LARGE_PRIME);
        assertEquals(1, factor);
    }

    @Test
    public void fermatEven() {
        long factor = Factors.fermat(2 * 13);
        assertEquals(2, factor);
    }

    @Test
    public void fermatCompound() {
        long factor = Factors.fermat(3*13);
        assertTrue(Arrays.asList(3, 13).contains((int) factor));
    }

    @Test
    public void fermatPrime() {
        long factor = Factors.fermat(LARGE_PRIME);
        assertEquals(1, factor);
    }
}
