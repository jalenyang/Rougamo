package com.pork;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jalyang on 2018/3/30.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("D:\\TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        System.out.println(JavaClassExecuter.execute(b));
    }


}
