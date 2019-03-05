package com.question;

/**
 * 给定两个有序数组numbs1, numbs2，合并到numbs1
 * [1,2,3]  +  [2,3,6] = [1,2,2,3,3,6]
 * sort from end
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m-1, tail2 = n-1, i = m+n-1;
        while(tail1 >= 0 && tail2 >= 0){
            if(nums1[tail1] >= nums2[tail2]){
                nums1[i--] = nums1[tail1--];
            }else{
                nums1[i--] = nums2[tail2--];
            }
        }

        while(tail2 >= 0){
            nums1[i--] = nums2[tail2--];
        }
    }
}
