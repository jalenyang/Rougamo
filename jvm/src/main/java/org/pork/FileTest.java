package org.pork;

import java.io.*;

public class FileTest {

    public static void main(String [] args) throws IOException {
        File file =new File("D:\\test.jsp ");
        FileWriter fw=new FileWriter(file);
        fw.append("Hello");
        fw.flush();
        fw.close();
    }
}
