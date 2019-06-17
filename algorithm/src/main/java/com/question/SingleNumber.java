package com.question;

/**
 * description:given a array, every word occurs twice except for one, find it
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int theOne = 0;
        for(int item : nums){
            theOne = theOne ^ item;
        }
        return theOne;
    }
}
