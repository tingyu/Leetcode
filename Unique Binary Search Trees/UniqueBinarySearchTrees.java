/**

Unique Binary Search Trees

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

public class Solution {
	public int numTrees(int n) {
		if(n == 0) return 1;
		if(n == 1) return 1;
		int res = 0;
		for(int i = 1; i <=n; i++){
			int left = numTrees(i-1);
			int right = numTrees(n - i);
			res += left*right;
		}
		return res;
	}
}
/*
https://gist.github.com/benjaminwu7/6481670
*/
public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <=n; i++){
        	for(int j = 0; j <=i -1; j++){
        		dp[i] += dp[j]*dp[i - j - 1];
        	}
        }
        return dp[n];
    }
}


public class Solution {
	public int numTrees(int n) {
		int[] dp = new int[n + 1];
		return count(n, dp);
	}

	private int count(int n, int[] dp){
		if(n == 0) return 1;
		if(dp[n] !=0) return dp[n];
		int sum = 0;
		for(int i = 0; i <= n; i++){
			sum += count(i, dp) * count(n-1-i, dp);
		}
		dp[n] = sum;
		return sum;
	}
}