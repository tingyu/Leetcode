/*
Best Time to Buy and Sell Stock 

LeetCode

Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
*/

//my solution
//有些tricky，记住这个不是记录数组中的最大的值，最小的值然后相减。因为里面是时间上的关系，你可以记录以前的min，但是不能记录下以前的max拿来当前减去。
//只能记录当前最大的profit，然后更新
//只能不断更新最小值，和最大的profit,而不是最大值
public class Solution {
    public int maxProfit(int[] prices) {
        //if(prices == null || prices.length < 2) return 0; //如果使用其他解法注意判断条件
        int min = Integer.MAX_VALUE;
        //int max = Integer.MIN_VALUE;
        //int min = prices[0];
        int max = 0;
        
        for(int i = 0; i < prices.length; i++){
            max = Math.max(max, prices[i] - min);//这两个顺序是可以换的，没什么影响，但是逻辑上看，这样会好些
            min = Math.min(min, prices[i]);
        }
        
        return max;
    }
}

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