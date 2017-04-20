package org.jfactor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitsTest {

    @Test
    public void rotatesLeft() {
        assertEquals(0x00000000FFFFFFFFL, Bits.rotateLeft(0xFFFFFFFF00000000L, 32));
    }
}
