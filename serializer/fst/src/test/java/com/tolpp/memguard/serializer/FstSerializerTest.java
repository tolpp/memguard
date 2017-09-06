package com.tolpp.memguard.serializer;

import com.tolpp.memguard.Guard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FstSerializerTest {

    private FstSerializer serializer;

    @Before
    public void setUp() {
        serializer = FstSerializer.create();
    }

    @Test
    public void serialize() throws Exception {
        int intData = 1;
        byte[] serialize = serializer.serialize(intData);

        assertEquals((byte) 1, serialize[1]);
    }

    @Test
    public void deserialize() throws Exception {
        String stringData = "fooBar";
        byte[] serialized = serializer.serialize(stringData);

        Object deserialized = serializer.deserialize(serialized);

        // object references are different, but as value they are same object
        assertEquals(stringData, deserialized);
    }

    @Test
    public void guardString() {
        String stringData = "fooBar";
        Guard<String> guardedString = new Guard.Builder<String>().serializer(FstSerializer.create()).build();
        guardedString.set(stringData);

        assertEquals(stringData, guardedString.get());
    }

    @Test
    public void guardInt() {
        int intData = 127;
        Guard<Integer> guardedInt = new Guard.Builder<Integer>().serializer(FstSerializer.create()).build();
        guardedInt.set(intData);

        Integer unBoxedInt = guardedInt.get();
        assertNotNull(unBoxedInt);
        assertEquals(intData, (int) unBoxedInt);
    }

    @Test
    public void create() throws Exception {
        assertNotNull(FstSerializer.create());
    }

}