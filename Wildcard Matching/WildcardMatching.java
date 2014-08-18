/**
Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

/*
http://blog.csdn.net/perfect8886/article/details/22689147

通过率很低的一题，Java没指针写起来是不顺畅。

贪心的策略，能匹配就一直往后遍历，匹配不上了就看看前面有没有'*'来救救场，再从'*'后面接着试。
*/

public class Solution {
    public boolean isMatch(String s, String p) {
		int i = 0; 
		int j = 0;
		int star = -1;
		int mark = -1;
		while(i < s.length()){
			if(j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))){
				++i;
				++j;
			}else if(j < p.length() && p.charAt(j) == '*'){
				star = j++;
				mark = i;
			}else if(star != -1){
				j = star + 1;
				i = ++ mark;
			}else{
				return false;
			}
		}        

		while(j < p.length() && p.charAt(j) == '*'){
			++j;
		}
		return j == p.length();
    }
}