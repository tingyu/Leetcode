/**
Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

public class Solution {
    public int longestValidParentheses(String s) {
 		char[] c = s.toCharArray();
 		Stack<Integer> store = new Stack<Integer>();
 		int[] right = new int[c.length];

 		int res = 0;
 		for(int i = 0; i < c.length; i++){
 			if(c[i]=='('){
 				store.push(i);
 			}else{
 				if(!store.isEmpty()){
 					right[i] = store.pop() + 1;
 					int temp = right[i] - 2;
 					if(temp >= 0 && right[temp] > 0){
 						right[i] = right[temp];
 					}
 					res = Math.max(i - right[i] + 2, res);
 				}
 			}
 		}
 		return res;
    }
}

/*
http://rleetcode.blogspot.com/2014/01/longest-valid-parentheses.html
Solution:
Use stack to record  '('  position, then check current valid length when a ')' come.
then, max length valid Parentheses is decided by two situation
1) stack is not empty, so the current length is current position i- last second position of '(' in stack, we can calculate it
through stack.pop(), then i-stack.peek() and check the length with max
2) stack is empty, then the longest length we can check currently is i-last (last is the position of last invalid ')')
*/
public class Solution {
    public int longestValidParentheses(String s) {
    	if(s == null || s.length() == 0) return 0;

    	int last = -1;
    	int maxLen = 0;
    	Stack<Integer> stack = new Stack<Integer>();
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == '('){
    			stack.push(i);
    		}else{
    			if(stack.isEmpty()){
    				//record the position before first left parenthesis
    				last = i;
    			}else{
    				stack.pop();

    				//if stack is empty means the position before the valid left parenthesis is "last"
    				if(stack.isEmpty()){
    					maxLen = Math.max(i - last, maxLen);
    				}else{
    					//if stack is not empty, then for current i the longest valid parenthesis length is i-stack.peek()
    					maxLen = Math.max(i - stack.peek(), maxLen);
    				}
    			}
    		}
    	}
    	return maxLen;
    }
}

