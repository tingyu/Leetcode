/**
Best Time to Buy and Sell Stock III 

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/


/*
http://blog.csdn.net/fightforyourdream/article/details/14503469
最佳时间买入卖出股票：你有一个数组保存了股票在第i天的价钱，现在你最多进行两次买卖，但同一时间你手上只能保持一个股票，如何赚的最多

知道要用DP做，但是一开始思路是错的。后来参考了 http://blog.csdn.net/pickless/article/details/12034365
才意识到可以在整个区间的每一点切开，然后分别计算左子区间和右子区间的最大值，然后再用O(n)时间找到整个区间的最大值。
看来以后碰到与2相关的问题，一定要想想能不能用二分法来做！

O(n^2)的算法很容易想到：
找寻一个点j，将原来的price[0..n-1]分割为price[0..j]和price[j..n-1]，分别求两段的最大profit。
进行优化：
对于点j+1，求price[0..j+1]的最大profit时，很多工作是重复的，在求price[0..j]的最大profit中已经做过了。
类似于Best Time to Buy and Sell Stock，可以在O(1)的时间从price[0..j]推出price[0..j+1]的最大profit。
但是如何从price[j..n-1]推出price[j+1..n-1]？反过来思考，我们可以用O(1)的时间由price[j+1..n-1]推出price[j..n-1]。
最终算法：
数组l[i]记录了price[0..i]的最大profit，
数组r[i]记录了price[i..n]的最大profit。
已知l[i]，求l[i+1]是简单的，同样已知r[i]，求r[i-1]也很容易。
最后，我们再用O(n)的时间找出最大的l[i]+r[i]，即为题目所求。

Solution: Divide and Conquer, DP

O(n^2) solution is easy came out, we can use O(n) time to get max profit depend on the solution of
Best Time to Buy and Sell Stock I. 
so we can just dived the whole prices array at every point , 
try to calculate current max profit from left and right and then add them together is what we want.
However, there are many repeat calculation when calculate left max profit and right max profit. 
So we can apply DP to record max profits for each left side and right side. 
then add them together at each point use one for loop
*/
public class Solution {
	// 基本思想是分成两个时间段，然后对于某一天，计算之前的最大值和之后的最大值 
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
        	return 0;
        }   	

        int max = 0;
        //dp数组保存左边和右边利润的最大值
        int[] left = new int[prices.length]; //计算[0, i]区间的最大值，也就是某天之前的最大值
        int[] right = new int[prices.length]; //计算[i, len -1]区间的最大值，也就是某天之后的最大值

        process(prices, left, right);

        //O(n)找到最大值
        /// max profit made by left side + max profit made by rigth side is the max profit made by two buy and sell
        for(int i = 0; i < prices.length; i++){
        	max = Math.max(max, left[i] + right[i]);//最大值和某天之前、之后最大值的和
        }

        return max;
    }


    public static void process(int[] process, int[] left, int[] right){
    	left[0] = 0;
    	int min = prices[0]; //最低买入价

    	//左边递推公式
        //用到了Best Time to Buy and Sell Stock里面的方法。left和right是相反的，想想就明白这两个是互补的。
        //一般来说。中间的时间越往左移，那么left的最大值越小，right的最大值越大。越往右移，那么left的profit越大，right的profit越小。
        //深入理解就是越往左移，右边的最大值越容易找到个更小的让diff越大，profit越大。而left的找到的最大值越小。反之同理
    	for(int i = 1; i < left.length; i++){
    		left[i] = Math.max(left[i -1], prices[i] - min); //i的最大利润为（i -1的利润）和（当前卖出价和之前买入价之差）的较大的那个
    		min = Math.min(min, prices[i]); //更新最小买入价
    	}

    	right[right.length -1] = 0;
    	int max = prices[right.length - 1]; //最高卖出价
    	//右边递推公式
    	for(int i = right.length - 2; i >= 0; i --){
    		right[i] = Math.max(right[i + 1], max - prices[i]); //i的最大利润为(i + 1的利润)和（最高卖出价和当前买入价之差）的较大的那个
 			max = Math.max(max, prices[i]);//更新最高卖出价
    	}
    }
}

//更为简洁的写法：
//https://github.com/francis-liberty/oj/blob/master/leetcode/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III.java
public class Solution {
    public int maxProfit(int[] prices) {
       	int N = prices.length;
        int numL[] = new int[N];
        int numR[] = new int[N];

        // from left to right:
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
        	min = Math.min(min, prices[i]);
        	numL[i] = Math.max(0, prices[i] - min);
        	if (i > 0)
        		numL[i] = Math.max(f[i], numL[i-1]);
        }
    	// from right to left:
        int max = Integer.MIN_VALUE;
        for (int i = N - 1; i >= 0; i--) {
        	max = Math.max(max, prices[i]);
        	numR[i] = Math.max(0, max - prices[i]);
        	if (i < N - 1)
        		numR[i] = Math.max(numR[i], numR[i+1]);
        }

        // get the max:
        int ans = 0;
        for (int i = 0; i < N; i++)
			ans = Math.max(ans, numL[i] + numR[i]);
		return ans;
    }
}


public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
        	return 0;
        }

        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int min = prices[0];

        for(int i = 1; i < n; i++){
        	left[i] = left[i - 1] > prices[i] - min ? left[i - 1]: prices[i] - min;
        	min = min < prices[i]? min : prices[i]; 
        }

        int max = prices[n -1];
        for(int i = n - 2; i >=0; i--){
        	right[i] = right[i + 1] > max - prices[i] ? right[i + 1]: max - prices[i];
        	max = max > prices[i] ? max : prices[i];
        }

        int value = 0;
        for(int i = 0; i < n; i++){
        	value = value > left[i] + right[i]? value : left[i] + right[i];
        }
        return value;
    }
}