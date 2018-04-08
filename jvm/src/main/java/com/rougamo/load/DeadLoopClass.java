package com.rougamo.load;

public class DeadLoopClass {

    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true) {

            }
        }
    }

}
