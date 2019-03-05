package com.question;

import java.util.Arrays;

/**
 * [1,2,3] + [0, 0, 1] = [1, 2, 4]
 * [9] + [1] = [1, 0]
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i=len-1; i>=0; i--){
            if(digits[i] != 9){
                digits[i] = ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }
        int[] newArr = new int[len+1];
        newArr[0] = 1;
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = {9, 9};
        new PlusOne().plusOne(arr);
    }
}
