package org.examples;


import java.lang.instrument.Instrumentation;

public class Agent {
    public static  void  premain(String agentArgs, Instrumentation ins){
        System.out.println("args:"+agentArgs);
        ins.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            System.out.println("premain load Class======:" + className);
            return classfileBuffer;
        },false);

    }
}
