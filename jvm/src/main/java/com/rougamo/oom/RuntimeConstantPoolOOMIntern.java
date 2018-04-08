package com.rougamo.oom;


public class RuntimeConstantPoolOOMIntern {

    public static void main(String [] args){
        String str1=new StringBuilder().append("你好").append("世界").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder().append("ja").append("va").toString();
        System.out.println(str1.intern()==str2);
    }

}
