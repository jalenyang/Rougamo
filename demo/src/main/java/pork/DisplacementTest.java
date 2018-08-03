package com.pork;


public class DisplacementTest {
    public static void main(String[] args) {
        int number = 10;

//        System.out.println(Integer.toBinaryString(10));
//        System.out.println(Integer.toBinaryString(10<<1));
//        System.out.println(Integer.toBinaryString(10>>1));
//        System.out.println(10>>1);
//        System.out.println(Integer.toBinaryString(10>>>1));

        byte a = 1;
        int n = ((int) a) & 0xff;
        n<<=4;
        System.out.println(n);

    }
}
