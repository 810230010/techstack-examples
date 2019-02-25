package com.question;

import java.util.*;

public class RemoveDupInArray {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0 || nums == null)
            return 0;
        List<Integer> list = new ArrayList(nums.length);
        for(int num : nums){
            if(!list.contains(num)){
                list.add(num);
            }
        }
        int size = list.size();
        int[] newArr = new int[size];
        for(int i=0; i<size; i++){
            newArr[i] = list.get(i);
        }
        nums = newArr;
        System.out.println(Arrays.toString(nums));
        return list.size();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDupInArray().removeDuplicates(new int[]{1,1,2}));
    }
}
