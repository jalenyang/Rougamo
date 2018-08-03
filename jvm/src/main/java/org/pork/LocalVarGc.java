package org.pork;


public class LocalVarGc {
    public static void main(String[] args) {
        {
            byte[] placeHolder = new byte[1024 *1024* 64];
        }
//        int a=0;
        System.gc();
    }
}
