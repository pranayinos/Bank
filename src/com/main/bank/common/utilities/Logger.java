package com.main.bank.common.utilities;

public class Logger {

    public static void info(String className, String message) {
        System.out.println("LOGGER : INFO | " + className + " | THREAD "
                + Thread.currentThread().getName() + " | " + System.currentTimeMillis() + " | " + message);
    }

    public static void error(String className, String message) {
        System.err.println("LOGGER : ERROR | " + className + " | THREAD " + Thread.currentThread().getName() + " | "
                + System.currentTimeMillis() + " | " + message);
    }
}
