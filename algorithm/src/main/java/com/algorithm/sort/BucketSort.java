package com.algorithm.sort;

/**
 * Bucket Sort
 * Time Complexity: O(m+n)   m:桶的个数   n:待排序数组的个数
 * Space Complexity: 不确定  取决于待排序数组的最大值
 * Not Stable
 */
public class BucketSort{

    public static void sort(int[] arr, int min, int max) {
        if(arr == null || arr.length <= 1)
            return;
        int[] container = new int[max+1];
        for(int i=0; i<arr.length; i++){
            int value = arr[i];
            container[value]++;
        }
        for(int i=0; i<container.length; i++){
            if(container[i] > 0){
                for(int j=0; j<container[i]; j++){
                    System.out.print(i + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        sort(new int[]{1,6,23,5}, 1, 23);
    }
}
