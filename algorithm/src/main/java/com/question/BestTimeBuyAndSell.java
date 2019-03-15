package com.question;

/**
 * [7,1,5,3,6,4]
 * max profit occurs when buy at day 2 and sell at day 5, which the max profit is 5.
 */
public class BestTimeBuyAndSell {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1 || prices == null)
            return 0;
        int max = 0;
        int buy = prices[0];
        for(int i=1; i<prices.length; i++){
            int currentProfit = prices[i] - buy;
            if(currentProfit < 0){
                buy = prices[i];
            }else if(currentProfit > max){
                max = currentProfit;
            }
        }
        return max;
    }
}
