package adventOfCode_2016.day4_16_invalid;

import java.util.HashMap;
import java.util.Map;

public class DAY4_16 {
    public static void main(String[] args) {
        Map<String, Integer > fruts = new HashMap<String, Integer>();
        fruts.put("cherry", 4);
        fruts.put("apple", 3);
        fruts.put("apple", 5);
        fruts.put("orange", 2);
        fruts.put("nectarine", 9);
        System.out.println(fruts);
    }
}
