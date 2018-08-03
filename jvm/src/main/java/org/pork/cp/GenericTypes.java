package org.pork.cp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jalyang on 2018/4/2.
 */
public class GenericTypes {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "Hello");
        map.put("k2", "World");
        System.out.println(map.get("k1"));
        System.out.println(map.get("k2"));

    }
}
