package com.question;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int changeIndex = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[changeIndex] = nums[i++];
            }
        }
        return nums.length;
    }
}
