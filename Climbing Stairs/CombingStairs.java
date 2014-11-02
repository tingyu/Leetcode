/*
Climbing Stairs 
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

*/

/*
http://blog.csdn.net/kenden23/article/details/17377869
简单题目，相当于fibonacci数列问题，难点就是要会思维转换，转换成为递归求解问题，多训练就可以了。
所以这种类型的题目相对于没有形成递归逻辑思维的人来说，应该算是难题。
我的想法是：
每次有两种选择，两种选择之后又是各有两种选择，如此循环，正好是递归求解的问题。
写成递归程序其实非常简单，三个语句就可以：
*/

//1. Recursive version, very slow, excedd the time limit
public class Solution {
	public int climbStairs(int n){
		if(n ==1) return 1;
		if(n == 2) return 2;

		return climbStairs(n-1) + climbStairs(n-2);
	}
}

//2. 但是递归程序一般都是太慢了，因为像Fibonacci问题一样，重复计算了很多分支，我们使用动态规划法填表，提高效率，程序也很简单，如下：
//Runtime: 344 ms
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n+1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}

//3. 动态规划法用熟了，高手就需要节省空间了，如下：
//Runtime: 344 ms
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i%3] = dp[(i-1)%3] + dp[(i-2)%3];
        }
        
        return dp[n%3];
    }
}

//4. 当然，不使用上面的数组也是可以的，直接使用三个变量保存结果也是一样的。

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