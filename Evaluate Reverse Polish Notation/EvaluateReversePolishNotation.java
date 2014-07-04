/**
Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

/*

Reverse Polish notation (RPN) is a mathematical notation in which every operator follows all of its operands, 
in contrast to Polish notation, which puts the operator in the prefix position. 
It is also known as postfix notation and is parenthesis-free as long as operator arities are fixed. 
The description "Polish" refers to the nationality of logician Jan Łukasiewicz, 
who invented (prefix) Polish notation in the 1920s.
就是把运算符放在operands的后面，这样可以省掉括号。

In computer science, postfix notation is often used in stack-based and concatenative programming languages.
 It is also common in dataflow and pipeline-based systems, including Unix pipelines.
Most of what follows is about binary operators. A unary operator for which the reverse Polish notation 
is the general convention is the factorial.
基本上是用stack来解决

http://joycelearning.blogspot.com/2014/03/leetcode-evaluate-reverse-polish.html
解题思路：
1. //build a stack for this kind of problem
2. if s is not a valid operator, then s is a num, push in stack
3. if s is an valid operator, pop two nums, calculate, push back
4. after loop, pop the last num as res
*/

public class Solution {
    public int evalRPN(String[] tokens) {
		int res = 0;
		if(tokens.length == 0) return res;

		//build a stack for this kind of problem
		String operators = "+-*/";
		Stack<String> stack = new Stack<String>();

		for(String s: tokens){
			//if s is not a valid operator, then s is a num, push in stack
			if(!operators.contains(s)){
				stack.push(s);
			}else{
				//if s is an valid operator, pop two nums, calculate, push back
				int a = Interger.valueOf(stack.pop());
				int b = Interger.valueOf(stack.pop());

				//calculate
				switch(s){
					case "+": stack.push(String.valueOf(a + b)); break;
					case "-": stack.push(String.valueOf(b - a)); break;
					case "*": stack.push(String.valueOf(a * b)); break;
					case "/": stack.push(String.valueOf(b / a)); break;
				}
			}
		}

		//after loop, pop the last num as res
		res = Interger.valueOf(stack.pop());

		return res;
    }
}
