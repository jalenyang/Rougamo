package com.rougamo;

/**
 * Created by Jalyang on 2017/10/31.
 */
public class InvokeTest {
    class GrandFarther {
        void thinking() {
            System.out.println("Grand farther is thinking");
        }
    }

    class Farther extends GrandFarther {
        void thinking() {
            System.out.println("Farther is thinking");
        }
    }

    class Son extends Farther {
        void thinking() {
            new GrandFarther().thinking();
//            System.out.println("Son is thinking");
        }
    }

    public static void main(String[] args) {
        new InvokeTest().new Son().thinking();
    }
}
