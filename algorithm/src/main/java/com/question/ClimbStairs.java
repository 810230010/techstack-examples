package com.question;

/**
 * 动态规划
 * 状态转移方程:dp[i] = dp[i-1] + dp[i-2];
 * 到达台阶i的方法数=达台阶i-1的方法数+达台阶i-2的方法数。所以该问题是个DP问题。
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        int[] steps = {1, 2};
        if(n < 2){
            return 1;
        }
        int oneStepWays = 1, twoStepWays = 1, res = 0;
        for(int i=2; i<=n; i++){
            res = oneStepWays + twoStepWays;
            twoStepWays = oneStepWays;
            oneStepWays = res;
        }
        return res;
    }
    public int climbStairs2(int n) {
        int[] steps = {1, 2};
        if(n < 2){
            return 1;
        }
        return climbStairs2(n-1) + climbStairs2(n-2);
    }
}
