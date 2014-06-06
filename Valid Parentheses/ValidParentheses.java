/**
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

/*
Thoughts about This Problem

Character is not a frequently used class, so need to know how to use it.
*/

public class Solution {
    public boolean isValid(String s) {
    	HashMap<Character, Character> map = new HashMap<Character, Character>();
    	map.put('(', ')');
    	map.put('[', ']');
    	map.put('{', '}');

    	Stack<Character> stack = new Stack<Character>();

    	for(int i = 0; i < s.length(); i++) {
    		char curr = s.charAt(i);

    		if(map.keySet().contains(curr)){
    			stack.push(curr);
    		} else if(map.values().contains(curr)){
    			if(!stack.empty() && map.get(stack.peek()) == curr){
    				stack.pop();
    			} else{
    				return false;
    			}
    		}
    	}
    	return stack.empty();
    }
}

/*
Simplified Java Solution

Almost identical, but convert string to char array at the beginning.
*/

public class Solution {
    public boolean isValid(String s) {
    	char[] charArray = s.toCharArray();

    	HashMap<Character, Character> map = new HashMap<Character, Character>();
    	map.put('(', ')');
    	map.put('[', ']');
    	map.put('{', '}');

    	Stack<Character> stack = new Stack<Character>();

    	for(Character c: charArray){
    		if(map.keySet.contains(c)){
    			stack.push(c);
    		}else if(map.values().contains(c)){
    			if(!stack.isEmpty() && map.get(stack.peek()) == c){
    				stack.pop();
    			} else{
    				return false;
    			}
    		}
    	}
    	return stack.isEmpty();
    }
}

