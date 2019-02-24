package com.question;

import org.junit.jupiter.api.Test;

/**
 * 回文数
 */
public class PalindromeNumber {
    @Test
    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        StringBuilder reversedStr = new StringBuilder();
        String str = x + "";
        String[] arr = str.split("");
        int arrLength = arr.length;
        for(int i=arrLength-1; i>=0; i--){
            reversedStr.append(arr[i]);
        }
        if(str.equals(reversedStr.toString())){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
