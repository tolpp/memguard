package com.tolpp.memguard.encoder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomXorEncoderTest {

    private RandomXorEncoder encoder;

    @Before
    public void setup() {
        encoder = new RandomXorEncoder.Builder().build();
    }

    @Test
    public void builder() {
        RandomXorEncoder.Builder builder = new RandomXorEncoder.Builder();
        assertNotNull(builder);

        RandomXorEncoder encoder = builder.build();
        assertNotNull(encoder);
    }

    @Test
    public void encodeAndDecode() {
        byte[] data1 = {5};
        byte[] data2 = {5, 10};
        byte[] encoded = encoder.encode(data1);
        assertEquals(1, encoded.length);

        encoded = encoder.encode(data2);
        assertEquals(2, encoded.length);
        assertArrayEquals(data2, encoder.decode(encoded));
    }

}