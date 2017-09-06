package com.tolpp.memguard.encoder;

import com.tolpp.memguard.Encoder;

/**
 * Factory for RandomXorEncoder
 */
public class RandomXorEncoderFactory implements Encoder.Factory {
    @Override
    public Encoder createEncoder() {
        return new RandomXorEncoder.Builder().build();
    }

    private RandomXorEncoderFactory(){

    }

    public static RandomXorEncoderFactory create(){
        return new RandomXorEncoderFactory();
    }
}
