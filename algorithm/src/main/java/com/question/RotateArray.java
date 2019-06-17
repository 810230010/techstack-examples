package com.question;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len == 1){
            return;
        }
        int[] temp = new int[len];

        for(int i=0; i<len; i++){
            temp[i] = nums[i];
        }
        System.arraycopy(temp, 0, nums, k, len-k);
        System.arraycopy(temp, len-k, nums, 0, k);
    }

    public static void main(String[] args) {
        new RotateArray().rotate(new int[]{1,2,3,4,5}, 3);
    }
}
