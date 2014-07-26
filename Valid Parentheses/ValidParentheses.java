/**
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

/*
Thoughts about This Problem

Character is not a frequently used class, so need to know how to use it.
使用HashMap和Stack，当遇到左半边的时候 stack.push，遇到右半边的时候pop看看
是不是一致
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


//my solution
public class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.push(map.get(c));
            }else{
                if(stack.isEmpty()){//这时s中还有值，stack却是空的，说明s中剩下的都是]})之类，肯定是false
                   return false; 
                }else{//stack不是空的时候，pop
                    if(c == stack.pop()){
                        continue;
                    }else{//这里是pop出来的和当前的不匹配，就是括号不匹配的情况。这里的两个else可以像上面的算法中情况一样整合起来。代码看着更整洁些
                        return false;
                    }
                }
            }
        }
        if(!stack.isEmpty()) return false;//这里是s中的都遍历完了，但是stack里面还有值的情况，这里肯定是当时push多了。就是（{[之类的多了
        return true;
    }
}
