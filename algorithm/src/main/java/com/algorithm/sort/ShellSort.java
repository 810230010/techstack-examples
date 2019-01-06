package com.algorithm.sort;

import com.util.CommonUtils;

/**
 * shell sort
 * Time Complexity: Best: O(n)   Average: O(n1.3)   Worst: O(n²)
 * Space Complexity: O(1)
 * Not Stable
 */
public class ShellSort extends AbstractSort{
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1)
            return;
        int arrLen = arr.length;
        int gap = arrLen / 2;
        while(gap >= 1){
            for(int i=0; i<arrLen; i++){
                int currentValue = arr[i];
                int prevIndex = i - gap;
                while(prevIndex >= 0){
                    if(currentValue < arr[prevIndex]){
                        currentValue = arr[prevIndex];
                        CommonUtils.swap(arr, prevIndex, i);
                        prevIndex = prevIndex - gap;
                    }else{
                        break;
                    }
                }
            }
            gap /= 2;
        }
    }
}
