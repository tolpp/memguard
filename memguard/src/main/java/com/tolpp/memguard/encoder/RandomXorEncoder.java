package com.tolpp.memguard.encoder;

import com.tolpp.memguard.Encoder;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Encoder for encoding given data with random seed using XOR. Seed changes during every encode operation.
 * So that, every {@link com.tolpp.memguard.Guard Guard} instance should be encoded using different {@link RandomXorEncoder} instance.
 * <br />
 * Also, decode operation can be performed when only latest encoded bytes provided, because of this encoder mutates itself after every encode operation.
 */
public final class RandomXorEncoder implements Encoder {
    private final Random random;
    private byte[] seed;

    private RandomXorEncoder() {
        random = new Random();
    }

    /**
     * Converts long value into byte array.
     * @param value value that will converted into byte array
     * @return byte array as long as length of Long data type in bytes.
     */
    private static byte[] toByte(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        return buffer.array();
    }

    @Override
    public byte[] encode(byte[] toEncode) {
        synchronized (this) {
            seed = toByte(random.nextLong());
            byte[] encoded = new byte[toEncode.length];
            for (int i = 0; i < toEncode.length; i++) {
                encoded[i] = (byte) (toEncode[i] ^ seed[i % seed.length]);
            }
            return encoded;
        }

    }

    @Override
    public byte[] decode(byte[] toDecode) {
        synchronized (this) {
            byte[] decoded = new byte[toDecode.length];
            for (int i = 0; i < toDecode.length; i++) {
                decoded[i] = (byte) (toDecode[i] ^ seed[i % seed.length]);
            }
            return decoded;
        }

    }

    static class Builder {
        RandomXorEncoder build() {
            return new RandomXorEncoder();
        }
    }
}
