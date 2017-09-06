package com.tolpp.memguard.encoder;

import com.tolpp.memguard.Encoder;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomXorEncoderFactoryTest {

    @Test
    public void createEncoder(){
        RandomXorEncoderFactory factory = RandomXorEncoderFactory.create();
        Encoder encoder = factory.createEncoder();
        assertNotNull(encoder);
    }

    @Test
    public void create(){
        RandomXorEncoderFactory factory = RandomXorEncoderFactory.create();
        assertNotNull(factory);
    }

}