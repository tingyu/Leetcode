/*
Best Time to Buy and Sell Stock 

LeetCode

Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
*/

//An interesting question, use valuable min to record current min value, a valuable diff to record current max profits. see code below

public class Solution {
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length < 2){
			return 0;
		}

		int min=Integer.Max_VALUE;
		int diff = 0;

		for(int i = 0; i < prices.length; i++){
			if(prices[i] < min){
				min = prices[i];
			}

			if(diff<prices[i]-min){
				diff = prices[i] -min;
			}
		}

		return diff;
	}
}



public class Solution{
	public int maxProfit(int[] prices){
		int min = Integer.MAX_VALUE, max = 0;
		for(int i = 0; i < prices.length; i++){
			max = Math.max(max, prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		return max;
	}
}