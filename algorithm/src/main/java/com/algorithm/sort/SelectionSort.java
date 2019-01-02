package com.algorithm.sort;

import com.util.CommonUtils;

/**
 * selection sort
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 * Not Stable
 */
public class SelectionSort extends AbstractSort {
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1)
            return;
        int arrLen = arr.length;
        for(int i=0; i<arrLen-1; i++){
            int min = arr[i];
            int minIndex = i;
            for (int j=i+1; j<arrLen; j++){
                if(arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                CommonUtils.swap(arr, i, minIndex);
            }
        }
    }
}
