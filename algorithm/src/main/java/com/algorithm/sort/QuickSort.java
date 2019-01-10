package com.algorithm.sort;

import com.google.common.base.MoreObjects;

/**
 * quick sort
 * Time Complexity: O(nlog2n)(best)、O(n²)(worst)、 O(nlog2n)(best)
 * Space Complexity: O(log2n)
 * Not Stable
 */
public class QuickSort extends AbstractSort{

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }
    public int partition(int[] array, int left, int right){
        //固定的切分方式
        int key=array[left];
        while(left<right){
            while(array[right]>=key&&right>left){//从后半部分向前扫描
                right--;
            }
            array[left]=array[right];
            while(array[left]<=key&&right>left){//从前半部分向后扫描
                left++;
            }
            array[right]=array[left];
        }
        array[right]=key;
        return right;
    }
    public void sort(int[] arr, int left, int right){
        if(left >= right)
            return;
        int index = partition(arr, left, right);
        sort(arr, left, index-1);
        sort(arr, index+1, right);
    }
}
