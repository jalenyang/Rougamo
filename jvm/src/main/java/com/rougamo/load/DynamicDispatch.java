package com.rougamo.load;


public class DynamicDispatch {

    static abstract class Human {
        public void sayHello() {
            System.out.println("Hello Guy!!");
        }
    }

    static class Man extends Human {
        public void sayHello() {
            System.out.println("Hello Man!!");
        }
    }

    static class Woman extends Human {
        public void sayHello() {
            System.out.println("Hello Woman!!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }
}
