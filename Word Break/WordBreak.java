/**
Word Break

Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/
//my navie solution, LTE

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        return helper(s, dict);
    }
    
    private boolean helper(String s, Set<String> dict){
        if(s.length() == 0){
            return true;
        }
        
        for(int i = 1; i <= s.length(); i++){
            if(dict.contains(s.substring(0, i))){
                helper(s.substring(i), dict);
            }else{
                continue;
            }
        }
        
        return false;
    }
}

/*
http://www.programcreek.com/2012/12/leetcode-solution-word-break/
1. Naive Approach

This problem can be solve by using a naive approach, which is trivial. 
A discussion can always start from that though.
对dict的每个element不停的进行匹配
对dict中每个元素，求出其长度len，然后不断的看s中长度为len的substring和dict中的元素是不是匹配。
首先是对start = 0的位置开始，也就是从头开始；先看leet的长度，然后看s从0开始的匹配不匹配，然后看code的长度，看s从0开始的匹配否。
如果都不匹配那么就返回false.不用进行递归了。因为第一个substring都没有符合的，不管后面有没有匹配都是false了，也就不需要递归了。

然后如果里面有个substring是匹配的。那么改变Start的位置，来进入下一个recursion。
在下一个recursion里面，又跟前面一样不停的遍历dict里面所有的情况，因为说不定有重复的。所以要从头遍历。
如果正好到达长度，那么就是true。如果大于的话说明当前dict里面的元素不对，选下一个。
如果找不到匹配的就返回false

这个题的重点是分割，而不是分割成为有空格的再输出。算法过程中只用考虑分割，不用考虑空格
*/

//Time: O(2^n)
//Time Limit Exceeded
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
       return wordBreakHelper(s, dict, 0); 
    }

    public boolean wordBreakHelper(String s, Set<String> dict, int start){
    	if(start == s.length())
    		return true;

    	for(String a: dict){
    		int len = a.length();
    		int end = start + len;

    		//end index should be <= string length
    		if(end > s.length())
    			continue;

    		if(s.substring(start, start + len).equals(a))
    			if(wordBreakHelper(s, dict, start + len))
    				return true;
    	}
    	return false;
    }
}

//Solution2:
//http://www.binglu.me/leetcode-word-break-and-word-break-ii/
//这个解法能看懂
/*
At first glance, this is a practice for recursive strategy. I wrote code based on the idea that 
from left to right, find out the first match word, then do it recursively on the remain part, 
until reach the end. (code is in the below). However, this ends up with a “Time Limit Exceed” 
as a result of large test set.

Another idea is using DP (dynamic programming). The key point of DP is create an array to store 
the “status” for each subproblems and finding out when and how the status will transit.

For this question, I create an array boolean[] dp = new boolean[s.length()+1];. 
dp[i] is true when the substring from i to end can be partitioned according to the provided dictionary. 
So, dp[s.length()] stands for empty string, which is, obviously, true. The value of dp[0] is 
the result that we are looking for. The transition condition is

if(dict.contains(sub) == true && dp[j+1] == true)
*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s == null || s.length() == 0 || dict == null) 
            return true;

        int lengh = s.length();

        //dp[i] is true when the substring from i to the end can be partitioned
        boolean[] dp = new boolean[length + 1];
        for(boolean b: dp){
            b = false;
        }

        //empty string can be partitioned for sure
        dp[length] = true;

        //bottom up dp, start from the end
        for(int i = length - 1; i >= 0; i--){
            for(int j = i; j < length; j++){
                String sub = s.substring(i, j+1);
                if(dict.contains(sub) == true && dp[j+1] == true){
                    dp[i] = true;
                    break; //break, just jump out current level of loop
                }
            }
        }
        return dp[0];
    }
}

/*
2. Dynamic Programming

The key to solve this problem by using dynamic programming approach:

Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
Initial state t[0] == true

http://blog.csdn.net/linhuanmars/article/details/22358863
这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路。首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。
然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值。

接下来我们套用上面的思路来解这道题。首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，
我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式。
思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，写成式子是

假设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，
然后检测i个子串，而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），
而空间复杂度则是字符串的数量，即O(n)。代码如下：
*/

public boolean wordBreak(String s, Set<String> dict){
	if(s == null || s.length() == 0)
		return true;
	boolean[] res = new boolean[s.length() + 1];
	res[0] = true;

	for(int i = 0; i < s.length(); i++){
		StringBuilder str = new StringBuilder(s.substring(0, i + 1));
		for(int j = 0; j <= i; j++){
			if(res[j] && dict.contains(str.toString())){
				res[i + 1] = true;
				break;
			}
			str.deleteCharAt(0);
		}
	}
	return res[s.length()];
}

/*
http://www.cnblogs.com/feiling/p/3357022.html
2.DP

Reference the dicussion in leetcode.
Here we use seg(i, j) to demonstrate whether substring start from i and length is j is in dict?
base case:
when j = 0; seg(i, j) = false;
State transform equation:
seg(i, j) = true. if s.substring(i, j - 1) is in dict.
else seg(i, j) = seg(i, k) && seg(i + k, j - k);

//我能理解这个算法，但是这是个错误的。。。
*/
public boolean wordBreak(String s, Set<String> dict) {
    if(s == null || dict.size() <= 0){
        return false;
    }

    int length = s.length();
    // seg(i, j) means substring t start from i and length is j can be segmented into dictionary words
    boolean[][] seg = new boolean[length][length + 1];
    for(int len = 1; len <= length; len++){
        for(int i = 0; i < length; i++){
            String t = s.substring(i, i + len);
            if(dict.contains(t)){
                seg[i][len] = true;
                continue;
            }

            for(int k = 1; k < len; k++){
                if(seg[i][k] && seg[i + k][len -k]){
                    seg[i][len] = true;
                    break;
                }
            }
        }
        return seg[0][length];
    }
}