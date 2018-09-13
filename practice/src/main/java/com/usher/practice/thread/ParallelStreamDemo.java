package com.usher.practice.thread;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * java 8 并行流
 * 计算1-100和
 */
public class ParallelStreamDemo {
    public static void main(String[] args) {
        getSumByParallelStream(1, 100);
    }
    public static int getSumByParallelStream(int start, int end){
        StopWatch stopWatch = new StopWatch();
        List<Integer> toBeCalculatedNums = new ArrayList<>();
        for(int i=start; i<=end; i++){
            toBeCalculatedNums.add(i);
        }

        stopWatch.start();
        int sum = toBeCalculatedNums.parallelStream().reduce(0, Integer::sum);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        return sum;
    }
}
