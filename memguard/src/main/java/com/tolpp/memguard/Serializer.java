package com.tolpp.memguard;

import java.io.Serializable;

/**
 * Object to byte array serializers.
 */
public interface Serializer {
    /**
     * Serializes given serializable object into the byte array.
     *
     * @param object object that will be serialized
     * @return serialized byte array
     */
    byte[] serialize(Serializable object);

    /**
     * Deserializes byte array that serialized by {@link #serialize(Serializable)} method.
     *
     * @param bytes byte array that will be deserialized
     * @return deserialized object
     */
    Object deserialize(byte[] bytes);
}
