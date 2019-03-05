package com.question;


public class SearchInsertIndex {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }
    private int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = (left + right) >> 1;
            if(nums[mid] == target){
                return mid;
            }else if(target > nums[mid]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(nums[left] == target){
            return left;
        }else if(nums[left] < target){
            return left + 1;
        }else{
            return left;
        }
    }
}
