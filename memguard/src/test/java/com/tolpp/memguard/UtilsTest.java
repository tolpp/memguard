package com.tolpp.memguard;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void checkNotNull_valid() {
        String data = "string";
        String sameAsData = Utils.checkNotNull(data, "data == null");
        // both of them should be equals by both value and both reference
        assertEquals(data, sameAsData);

        Object o1 = Arrays.asList(1, 2, 3);
        Object sameAsO1 = Utils.checkNotNull(o1, "o1 == null");
        assertEquals(o1, sameAsO1);
    }

    @Test(expected = NullPointerException.class)
    public void checkNotNull_invalid() {
        Utils.checkNotNull(null, "data == null");
    }


}