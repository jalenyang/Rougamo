package org.pork.load;

/**
 * Created by jalyang on 2018/3/16.
 */
public class NotInitialization {
    public static class Constant {
        static {
            System.out.println("Constant init!");
        }

        public static final String HELLO_WORLD = "hello world";
    }

    public static void main(String[] args) {
//        System.out.print(SubClass.value);
        System.out.print(Constant.HELLO_WORLD);
//        SuperClass[] sca = new SuperClass[10];

    }
}
