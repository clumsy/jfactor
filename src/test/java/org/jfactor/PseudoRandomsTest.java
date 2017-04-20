package org.jfactor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PseudoRandomsTest {

    @Test
    public void xorshift128Reproduces() {
        assertEquals(PseudoRandoms.xorshift128(42, 42).getAsLong(),
            PseudoRandoms.xorshift128(42, 42).getAsLong());
    }

    @Test
    public void splitMix64Reproduces() {
        assertEquals(PseudoRandoms.splitMix64(42).getAsLong(),
            PseudoRandoms.splitMix64(42).getAsLong());
    }
}
