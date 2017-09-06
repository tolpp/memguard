package com.tolpp.memguard.serializer;

import com.tolpp.memguard.Serializer;
import org.nustaq.serialization.FSTConfiguration;

import java.io.Serializable;

public final class FstSerializer implements Serializer {
    private static FSTConfiguration fst = FSTConfiguration.createDefaultConfiguration();

    private FstSerializer() {

    }

    @Override
    public byte[] serialize(Serializable object) {
        return fst.asByteArray(object);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        return fst.asObject(bytes);
    }

    public static FstSerializer create() {
        return new FstSerializer();
    }
}
