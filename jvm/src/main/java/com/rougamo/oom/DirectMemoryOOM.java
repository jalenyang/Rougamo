package com.rougamo.oom;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryOOM {
    public static final int _1M = 1024 * 1024;

    public void test() {
        System.out.println("This is a test");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe= (Unsafe) unsafeField.get(null);

        int i = 0;
        unsafe.freeMemory(_1M);
        while (true) {
            try {
                unsafe.allocateMemory(_1M);
            } catch (Exception e) {
                System.out.println(++i);
            }
        }

    }
}
