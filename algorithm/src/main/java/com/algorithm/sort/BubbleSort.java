package com.algorithm.sort;

import com.util.CommonUtils;

import java.util.Arrays;

/**
 * bubble sort
 * Time Complexity: Best: O(n)   Average: O(n²)   Worst: O(n²)
 * Space Complexity: O(1)
 * stable
 */
public class BubbleSort extends AbstractSort{

    @Override
    public void sort(int[] arr) {
        int arrLen = arr.length;
        for(int i=0; i<arrLen-1; i++){
            for(int j=i; j<arrLen-i-1; j++){
                if(arr[j] > arr[j+1]){
                   CommonUtils.swap(arr, j, j+1);
                }
            }
        }
    }

}
