package com.tolpp.memguard;

import com.tolpp.memguard.encoder.RandomXorEncoderFactory;
import com.tolpp.memguard.serializer.JavaSerializer;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GuardTest {

    @Test
    public void testInitialization() {
        Guard<Integer> integer = new Guard.Builder<Integer>().value(10).build();

        Integer unboxedInt = integer.get();
        assertNotNull(unboxedInt);
        assertEquals(10, (int) unboxedInt);

        integer.set(20);
        unboxedInt = integer.get();
        assertNotNull(unboxedInt);
        assertEquals(20, (int) unboxedInt);

        integer.set(21);
        unboxedInt = integer.get();
        assertNotNull(unboxedInt);
        assertEquals(21, (int) unboxedInt);

        integer.set(Integer.MAX_VALUE);
        unboxedInt = integer.get();
        assertNotNull(unboxedInt);
        assertEquals(Integer.MAX_VALUE, (int) unboxedInt);

        integer.set(Integer.MIN_VALUE);
        unboxedInt = integer.get();
        assertNotNull(unboxedInt);
        assertEquals(Integer.MIN_VALUE, (int) unboxedInt);
    }

    @Test
    public void speedTestForInt() {
        Guard<Integer> integer = new Guard.Builder<Integer>().value(-1).build();

        for (int i = 0; i < 65535; i++) {
            Integer unBoxedInt = integer.get();
            assertNotNull(unBoxedInt);
            assertEquals(i - 1, (int) unBoxedInt);
            integer.set(unBoxedInt + 1);
        }
    }


    @Test
    public void testEncoderFactory() {
        Guard<String> string = new Guard.Builder<String>()
                .addEncoderFactory(RandomXorEncoderFactory.create())
                .addEncoderFactory(RandomXorEncoderFactory.create())
                .value("Tolga")
                .build();

        assertEquals("Tolga", string.get());
        string.set("deneme");
        assertEquals("deneme", string.get());
    }

    @Test
    public void defaultBuilder() {

    }

    private static class MyGuardBuilder<T extends Serializable> extends Guard.Builder<T> {
        public MyGuardBuilder() {
            serializer(new JavaSerializer.Builder().build());
        }
    }

}