package com.rougamo.jcert;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by Jalyang on 2017/8/24.
 */
public class Lock {
    public static void main(String[] args) {
        LockSupport.parkNanos(120000 * 1000000L);
        System.out.println("end now");
    }
}
