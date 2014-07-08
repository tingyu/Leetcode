/**
Regular Expression Matching 

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

//http://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
/*
Thoughts for This Problem

Overall, there are 2 different cases: 1) the second char of pattern is "*" , 
and 2) the second char of pattern is not "*".

For the 1st case, if the first char of pattern is not ".", the first char of pattern and string should be the same. 
Then continue to match the left part.

For the 2nd case, if the first char of pattern is "." or first char of pattern == the first i char of string, 
continue to match the left.

Be careful about the offset.

*/

public class Solution {
    public boolean isMatch(String s, String p) {
    	if(p.length() == 0)
    		return s.length() == 0;

    	//p's length 1 is special case
    	//Second character of p is not '*'
    	if(p.length() == 1 || p.charAt(1)!= '*'){
    		// if first character of p is not '.' and p.charAt(0) != s.charAt(0) return false
            // else keep matching
    		if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
    			return false;
    		return isMatch(s.substring(1), p.substring(1));
    	}else{ //Second character of p is '*'
    		int len = s.length();

    		int i = -1;
    		// first character of p equals first character of s keep matching
    		while(i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
    			if(isMatch(s.substring(i +1), p.substring(2)))
    				return true;
    			i++;
    		}
    		return false;
    	}
	} 
}