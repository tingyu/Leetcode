/**
Candy 

Total Accepted: 10720 Total Submissions: 60203 My Submissions

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/

/*
http://blog.csdn.net/linhuanmars/article/details/21424783
这道题用到的思路和Trapping Rain Water是一样的，用动态规划。
基本思路就是进行两次扫描，一次从左往右，一次从右往左。
第一次扫描的时候维护对于每一个小孩左边所需要最少的糖果数量，存入数组对应元素中，
第二次扫描的时候维护右边所需的最少糖果数，并且比较将左边和右边大的糖果数量存入结果数组对应元素中。
这样两遍扫描之后就可以得到每一个所需要的最最少糖果量，从而累加得出结果。方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。
空间上需要一个长度为n的数组，复杂度是O(n)。代码如下： 
*/

public class Solution {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
        	return 0;
        }

        int[] nums = new int[ratings.length];
        nums[0] = 1;

        for(int i = 1; i < ratings.length; i++){
        	if(ratings[i] > ratings[i-1]){
        		nums[i] = nums[i-1] + 1;
        	} else{
        		nums[i] = 1;
        	}
        }

        int res = nums[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--){
        	int cur = 1;
        	if(ratings[i] > ratings[i+1]){
        		cur = nums[i + 1] + 1;
        	}

        	res += Math.max(cur, nums[i]);
        	nums[i] = cur;//??? why this one
        }
        return res;
    }
}


/*
这种两边扫描的方法是一种比较常用的技巧，LeetCode中Trapping Rain Water和这道题都用到了，
可以把这种方法作为自己思路的一部分，通常是要求的变量跟左右元素有关系的题目会用到哈。
*/

