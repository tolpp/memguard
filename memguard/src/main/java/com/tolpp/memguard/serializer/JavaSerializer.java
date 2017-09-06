package com.tolpp.memguard.serializer;

import com.tolpp.memguard.Serializer;

import java.io.*;

public class JavaSerializer implements Serializer {

    private JavaSerializer() {

    }

    @Override
    public byte[] serialize(Serializable object) {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bous);
            oos.writeObject(object);
            return bous.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bous.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    @Override
    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public static class Builder {

        public JavaSerializer build() {
            return new JavaSerializer();
        }
    }
}
