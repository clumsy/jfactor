package org.jfactor;

import java.util.function.LongSupplier;

/**
 * @author alexander.jipa@gmail.com
 */
public class PseudoRandoms {
    private PseudoRandoms() {
        throw new UnsupportedOperationException();
    }

    public static final class Xorshift128 implements LongSupplier {
        private long s0;
        private long s1;

        private Xorshift128(long seed0, long seed1) {
            this.s0 = seed0;
            this.s1 = seed1;
        }

        @Override
        public long getAsLong() {
            final long s0 = this.s0;
            long s1 = this.s1;
            final long result = s0 + s1;

            s1 ^= s0;
            this.s0 = Bits.rotateLeft(s0, 55) ^ s1 ^ (s1 << 14);
            this.s1 = Bits.rotateLeft(s1, 36);

            return result;
        }
    }

    public static Xorshift128 xorshift128(long seed0, long seed1) {
        return new Xorshift128(seed0, seed1);
    }

    public static final class SplitMix64 implements LongSupplier {
        private long s;

        private SplitMix64(long seed) {
            this.s = seed;
        }

        @Override
        public long getAsLong() {
            long z = (s += 0x9E3779B97F4A7C15L);
            z = (z ^ (z >> 30)) * 0xBF58476D1CE4E5B9L;
            z = (z ^ (z >> 27)) * 0x94D049BB133111EBL;
            return z ^ (z >> 31);
        }
    }

    public static SplitMix64 splitMix64(long seed) {
        return new SplitMix64(seed);
    }
}
