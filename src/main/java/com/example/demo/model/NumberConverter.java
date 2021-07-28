package com.example.demo.model;

import java.util.TreeMap;

public class NumberConverter {

    private static final TreeMap<Integer, String> romanMap = new TreeMap<>();

    static {
        romanMap.put(1000, "M");
        romanMap.put(900, "CM");
        romanMap.put(500, "D");
        romanMap.put(400, "CD");
        romanMap.put(100, "C");
        romanMap.put(90, "XC");
        romanMap.put(50, "L");
        romanMap.put(40, "XL");
        romanMap.put(10, "X");
        romanMap.put(9, "IX");
        romanMap.put(5, "V");
        romanMap.put(4, "IV");
        romanMap.put(1, "I");
    }

    public String convertToRoman(int input) {
        if (input <= 0 || input > 3999) {
            throw new IllegalArgumentException("Value outside of allowed 1-3999 range");
        }
        int l = romanMap.floorKey(input);
        if (input == l) {
            return romanMap.get(input);
        }
        return romanMap.get(l) + convertToRoman(input - l).toString();
    }

    public String convertToHex(int input) {
        return Integer.toString(input, 16);
    }

}
