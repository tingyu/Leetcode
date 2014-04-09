/*
Climbing Stairs Total Accepted: 9472 Total Submissions: 29234 My Submissions
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

*/

//Recursive version, very slow, excedd the time limit
public class Solution {
	public int climbStairs(int n){
		if(n ==1) return 1;
		if(n == 2) return 2;

		return climbStairs(n-1) + climbStairs(n-2);
	}
}

//Iterative version.这种解法还需思考
public class Solution{
	public int climbStairs(int n){
		if(n==0 || n==1) return 1;
		int steps_n_2 = 1;
		int steps_n_1 = 1;
		for(int i = 2; i <=n; ++i){
			int steps_n = steps_n_1 + steps_n_2;
			steps_n_2 = steps_n_1;
			steps_n_1 = steps_n;
		}
		return steps_n_1;
	}
}