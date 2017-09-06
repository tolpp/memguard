package com.tolpp.memguard;

/**
 * Convert byte array to and from
 */
public interface Encoder {
    /**
     * Returns new array with encoded bytes. This method should not mutate given array.
     *
     * @param toEncode byte array to be encoded
     * @return encoded byte array. Also this array is decodable.
     */
    byte[] encode(final byte[] toEncode);

    /**
     * Returns new array with decoded bytes. This method should not mutate given array.
     *
     * @param toDecode byte array to be decoded
     * @return decoded byte array
     */
    byte[] decode(final byte[] toDecode);

    /**
     * Creates {@link Encoder} instances.
     */
    interface Factory {
        Encoder createEncoder();
    }
}
