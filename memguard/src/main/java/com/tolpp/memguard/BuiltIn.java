package com.tolpp.memguard;

import com.tolpp.memguard.encoder.RandomXorEncoderFactory;
import com.tolpp.memguard.serializer.JavaSerializer;

/**
 * Built in default when no configuration or implementation given.
 */
class BuiltIn {
    /**
     * Default java serializer.
     */
    static final Serializer SERIALIZER = new JavaSerializer.Builder().build();

    /**
     * RandomXorEncoderFactory
     */
    static final Encoder.Factory ENCODER_FACTORY = RandomXorEncoderFactory.create();
}
