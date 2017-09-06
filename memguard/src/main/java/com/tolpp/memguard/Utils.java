package com.tolpp.memguard;


final class Utils {

    private Utils() {
        // there is no need for initialize
    }

    static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
