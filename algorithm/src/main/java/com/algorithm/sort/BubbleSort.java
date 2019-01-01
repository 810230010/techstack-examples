package com.algorithm.sort;

import com.util.CommonUtils;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort extends AbstractSort{

    @Override
    protected void sort(int[] arr) {
        System.out.println("排序前: " + Arrays.toString(arr));
        int arrLen = arr.length;
        for(int i=0; i<arrLen-1; i++){
            for(int j=i; j<arrLen-i-1; j++){
                if(arr[j] > arr[j+1]){
                   CommonUtils.swap(arr, j, j+1);
                }
            }
        }
        System.out.println("排序后: " + Arrays.toString(arr));
    }

}
