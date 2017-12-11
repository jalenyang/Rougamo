package com.rougamo.jcert;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Jalyang on 2017/8/23.
 */
public class Application {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
//        try {
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(new FileInputStream("D:\\cert\\lbwas-client-cert.jks"),
//                    "elm3kh2ifjstmjh3qudd15ibik".toCharArray());
//            System.out.println(keyStore.getType());
//            System.out.println(keyStore.getCertificate("lbwas-client-cert"));
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            Thread.sleep(1000);
            unsafe.allocateMemory(_1MB);
            System.out.println("hello.....");
        }
    }
}
