/**
 * O(n)
 * O(1)
 * 178 ms
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        //here, the array must have elements >= 1;
        
        int min = prices[0], HistoricalMaxProfit = 0;
        int profit = 0;
        
        for(int i = 1; i < prices.length; i++){
           if(prices[i] < min) min = prices[i];
           profit = prices[i] - min;
           if(profit > HistoricalMaxProfit) HistoricalMaxProfit = profit;
        }
        
        return HistoricalMaxProfit;
    }
}