/**
Interleaving String

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

/*
http://blog.csdn.net/u011095253/article/details/9248073
这道题，可以用Recursion和DP两种方法解，我们先来看比较直观的Recursion的解法。

我们从头到尾遍历这三个String，比如取名s1,s2,s3，然后取p1,p2,p3三个指针来对应每个String里当前遍历到的字符位置

这么想，如果s1的p1位，和s2的p2位，和s3的p3位都相等，那么在s3挑选的字符的时候，我们可以挑s1的那一位，也可以挑s2的那一位，
所以在递归的时候我们用或（｜｜）把两种情况连接起来，传入s1的下一位，或者s2的下一位

如果只有s1的p1位和s3的p3位相等，那只能传入s1的下一位

如果只有s2的p2位和s3的p3位相等，那只能传入s2的下一位

如果没有发现相等，return false 退回到上一层
*/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
    	if(s1.length() + s2.length() != s3.length()) return false;
    	return rec(s1, 0, s2, 0, s3, 0);
    }

    public boolean rec(String s1, int p1, String s2, int p2, String s3, int p3){
    	if(p3 == s3.length()) return true;
    	if(p1 == s1.length()) return s2.substring(p2).equals(s3.substring(p3));
    	if(p2 == s2.length()) return s1.substring(p1).equals(s3.substring(p3));
    	if(s1.charAt(p1)==s3.charAt(p3) && s2.charAt(p2) == s3.charAt(p3)){
    		return rec(s1, p1 +1, s2, p2, s3, p3 + 1)|| rec(s1, p1, s2, p2 + 1, s3, p3+1);
    	}else if(s1.charAt(p1) == s3.charAt(p3)){
    		return rec(s1, p1 + 1, s2, p2, s3, p3 + 1);
    	}else if(s2.charAt(p2) == s3.charAt(p3)){
    		return rec(s1, p1, s2, p2 + 1, s3, p3 + 1);
    	}else return false;
    }
}