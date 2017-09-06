package com.tolpp.memguard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.tolpp.memguard.Utils.checkNotNull;

/**
 * Guard converts given value into the another type and stores it for protection purpose.
 * <p>
 * The value and size of stored data is depends on the given {@linkplain Serializer serializer} and {@linkplain Encoder encoder}'s.
 * <p>
 * Given data can be stored as converted, shrunk or even encrypted with giving appropriate {@link Encoder}.
 * <p>
 * Create instances using {@linkplain Builder the builder}
 *
 * @param <T> Type of serializable object that will be stored as guarded.
 */
public final class Guard<T extends Serializable> {

    private final Serializer serializer;
    private final Encoder[] encoders;
    private byte[] encoded;

    Guard(Serializer serializer, Encoder[] encoders) {
        this.serializer = serializer;
        this.encoders = encoders;
    }

    /**
     * Un boxes and returns stored Serializable object.
     * Mind that, this object probably serialized and de-serialized and so that, object reference id probably will be different than given object.
     * If you will use result for comparison, consider to use {@link #equals(Object)} instead of {@code `==`} comparison.
     *
     * @return previously given object. Returns null if not any object given before.
     */
    @SuppressWarnings("unchecked")
    public T get() {
        if (encoded == null) return null;
        byte[] decoded = encoded;
        for (int i = (encoders.length - 1); i >= 0; i--) {
            decoded = encoders[i].decode(decoded);
        }
        return (T) serializer.deserialize(decoded);
    }

    /**
     * Stores and guards given object as boxed.
     *
     * @param value Serializable object that will be stored.
     */
    public void set(T value) {
        byte[] serialized = serializer.serialize(value);
        for (Encoder encoder : encoders) {
            serialized = encoder.encode(serialized);
        }

        this.encoded = serialized;
    }

    /**
     * Build a new {@link Guard}
     * <p>
     *
     * @param <T> Type of serializable object that will be stored as guarded.
     */
    public static class Builder<T extends Serializable> {
        private T value;
        private Serializer serializer;
        private List<Encoder.Factory> encoderFactories = new ArrayList<>();

        /**
         * Builds new Guard instance with given configurations.
         * @return new Guard instance.
         */
        public Guard<T> build() {
            if (serializer == null) {
                serializer(BuiltIn.SERIALIZER);
            }

            if (encoderFactories.isEmpty()) {
                addEncoderFactory(BuiltIn.ENCODER_FACTORY);
            }

            Encoder[] encoders = new Encoder[encoderFactories.size()];
            for (int i = 0; i < encoderFactories.size(); i++) {
                encoders[i] = encoderFactories.get(i).createEncoder();
            }

            Guard<T> guard = new Guard<>(serializer, encoders);
            guard.set(value);
            return guard;
        }

        /**
         * Sets serializer for converting serializable objects into byte array.
         * @param serializer variable serializer
         * @return this builder instance for chaining.
         */
        public Builder<T> serializer(Serializer serializer) {
            checkNotNull(serializer, "serializer == null");
            this.serializer = serializer;
            return this;
        }


        /**
         * Adds new encoder factory. Serialized data encoded in order of given factory oder.
         * @param factory factory which creates encoder.
         * @return this builder instance for chaining.
         */
        public Builder<T> addEncoderFactory(Encoder.Factory factory) {
            checkNotNull(factory, "factory == null");
            encoderFactories.add(factory);
            return this;
        }

        /**
         * Initializes guard member with this value.
         * If not set, {@link Guard#get()} returns null unless set explicitly via {@link Guard#set(Serializable)} method.
         * @param value initialization valeu
         * @return this builder instance for chaining.
         */
        public Builder<T> value(T value) {
            this.value = value;
            return this;
        }
    }
}
