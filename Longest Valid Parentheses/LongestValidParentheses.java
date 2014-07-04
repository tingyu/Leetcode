/**
Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/


/*
http://rleetcode.blogspot.com/2014/01/longest-valid-parentheses.html
解法二：来自leetcode讨论组的写法。本来人有O(n)的解法，被楼主活生生地卖弄成了O(n3)。楼主真想挖个坑把自己埋了！！！

大家首先看，这个解法里面的stack，不是用来存左右括号的。人是来存左括号的index。本来么，右括号也不用存。遍历S。
遇到'('，放入lefts。如果遇到')'，如果lefts是空，说明这是一个无法匹配的')'，记录下last。last里面存放的其实是最后一个无法匹配的')'。
为啥要保存这个值呢？主要是为了计算后面完整的表达式的长度。可以这样理解： “所有无法匹配的')'”的index其实都是各个group的分界点。
Solution:
Use stack to record  '('  position, then check current valid length when a ')' come.
then, max length valid Parentheses is decided by two situation
1) stack is not empty, so the current length is current position i- last second position of '(' in stack, we can calculate it
through stack.pop(), then i-stack.peek() and check the length with max
2) stack is empty, then the longest length we can check currently is i-last (last is the position of last invalid ')')


It is straightforward to turn to a stack-based approach to solve such parenthesis matching problems. 
For this one, the real difficulty is to determine the length of current matching when we encounter a ')'. 
For example, if the given string is "()()", the length of current matching when we arrive at the last 
closing parenthesis should be four, even though it matches with the opening parenthesis right ahead of it. 
As such, we can push the index of opening parentheses into the stack, and keep track of the starting 
index start  of the current matching. Then every time a closing parenthesis at index i is met, 
we first verify whether the stack is empty. If it is, that means this closing parenthesis cannot be matched, 
and there could not be longer valid parentheses containing it. Thus it is safe to advance the starting index 
of the current matching to the next position. If the stack is not empty, we pop up the index of the matched 
opening parenthesis in the stack. And now if the stack is empty, that means the substring s[start,i] is valid, 
and the length of current matching is i−start+1 ; otherwise, the current matching is within the top of the stack 
(exclusive, as this opening parenthesis is not matched yet) and i (inclusive).
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
                    //// no matching left
    				last = i;
    			}else{
                    // find a matching pair
    				stack.pop();

    				//if stack is empty means the position before the valid left parenthesis is "last"
    				if(stack.isEmpty()){//有一个完整的valid的group。计算该group的长度
    					maxLen = Math.max(i - last, maxLen);
    				}else{
                        //栈内还有‘(',一个最外层完整的group还没有匹配完成，
                        //但是通过查询下一个即将匹配还未匹配的"("的index来更新maxLen。
    					//if stack is not empty, then for current i the longest valid parenthesis length is i-stack.peek()
    					maxLen = Math.max(i - stack.peek(), maxLen);
    				}
    			}
    		}
    	}
    	return maxLen;
    }
}


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

