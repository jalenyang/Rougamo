package org.pork;

/**
 * Created by jalyang on 2017/11/24.
 */
public class SwapTest {

    public static void swap(Integer source, Integer target) {
        source=source+target;
        target=source-target;
        source=Integer.valueOf(source-target);
    }

    public static void main(String [] args) {
        Integer a=1,b=2;
        System.out.println(String.format("source before swap %s, target before swap %s",a,b));
        swap(a,b);
        System.out.println(String.format("source after swap %s, target after swap %s",a,b));
    }
}
