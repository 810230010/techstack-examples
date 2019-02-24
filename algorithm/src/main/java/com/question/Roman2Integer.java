package com.question;

import java.util.HashMap;
import java.util.Map;

public class Roman2Integer {
    private static final Map<String, Integer> ROMAN_NUMBER_MAPPINGS = new HashMap();
    static{
        ROMAN_NUMBER_MAPPINGS.put("I", 1);
        ROMAN_NUMBER_MAPPINGS.put("V", 5);
        ROMAN_NUMBER_MAPPINGS.put("X", 10);
        ROMAN_NUMBER_MAPPINGS.put("L", 50);
        ROMAN_NUMBER_MAPPINGS.put("C", 100);
        ROMAN_NUMBER_MAPPINGS.put("D", 500);
        ROMAN_NUMBER_MAPPINGS.put("M", 1000);
    }
    public int romanToInt(String s) {
        if(s.length() == 1){
            return ROMAN_NUMBER_MAPPINGS.get(s);
        }
        String[] arr = s.split("");
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            if(i == arr.length -1){
                sum += ROMAN_NUMBER_MAPPINGS.get(arr[i]);
                return sum;
            }
            String nextStr = arr[i+1];
            String str = arr[i];
            int nextNumber = ROMAN_NUMBER_MAPPINGS.get(nextStr);
            int prevNumber = ROMAN_NUMBER_MAPPINGS.get(str);
            if(prevNumber < nextNumber){
                prevNumber *= (-1);
                sum += prevNumber;
            }else{
                sum += prevNumber;
            }
        }
        return sum;
    }
}
