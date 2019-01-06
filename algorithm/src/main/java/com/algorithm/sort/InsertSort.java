package com.algorithm.sort;

import com.util.CommonUtils;

/**
 * insert sort
 * Time Complexity: Best: O(n)   Average: O(n²)   Worst: O(n²)
 * Space Complexity: O(1)
 * stable
 */
public class InsertSort extends AbstractSort {
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1)
            return;
        int arrLen = arr.length;
        for(int i=1; i<arrLen; i++){
            int pointerIndex = i-1;
            int currentValue = arr[i];
            int currentValueIndex = i;
            while(pointerIndex >= 0){
                if(currentValue < arr[pointerIndex]){
                    CommonUtils.swap(arr, pointerIndex, currentValueIndex);
                    currentValueIndex = pointerIndex;
                }else {
                    break;
                }
            }
        }
    }
}
