package com.thedevpiece.utils;

@SuppressWarnings("unchecked")
public class ExceptionUtils {
    public static void doThrow(RuntimeException e) {
        throw e;
    }

    public static void wrapAndThrow(Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    public static void doThrow(Exception e) {
        ExceptionUtils.<RuntimeException>doThrow0(e);
    }

    static <E extends Exception> void doThrow0(Exception e) throws E {
        throw (E) e;
    }
}
