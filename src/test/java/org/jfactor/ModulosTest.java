package org.jfactor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModulosTest {

    @Test
    public void modForNegativeNumbers() {
        assertEquals(1, Modulos.mod(-4, 5));
    }

    @Test
    public void modExpDoesNotOverflow() {
        assertEquals(375, Modulos.modExp(2, 65, 497));
    }
}
