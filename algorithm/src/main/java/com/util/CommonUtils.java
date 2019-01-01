package com.util;

public class CommonUtils {
    public static void swap(int[] arr, int index1, int index2){
        //1. 引入中间变量
//        int temp = arrr[index1];
//        arr[index1] = arr[index2];
//        arr[index2] = temp;

        //2.加减法
//        arr[index1] = arr[index1] + arr[index2];
//        arr[index2] = arr[index1] - arr[index2];
//        arr[index1] = arr[index1] - arr[index2];

        //3.位运算  位运算符相同为假，不同为真。主要运用的原理是a^a的值是0，0^ b的值是 b;
        arr[index1] = arr[index1] ^ arr[index2];
        arr[index2] = arr[index1] ^ arr[index2];
        arr[index1] = arr[index1] ^ arr[index2];
    }
}
