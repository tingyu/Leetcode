/**
Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

*/

//my solution
public class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        s = s.trim().replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        //or s = s.trim().replaceAll("\\W","").toLowerCase();
        int len = s.length();
        for(int i = 0; i < len/2; i++){
            if(s.charAt(i) != s.charAt(len -1- i)){
                return false;
            }
        }
        return true;
    }
}

//my solution
public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return false;
        if(s ==" ") return true;
        
        //String strip = s.trim().replaceAll("[^A-Za-z0-9]","").toLowerCase();
        String strip = s.trim().replaceAll("\\W", "").toLowerCase();
        
        int i = 0, j = strip.length() -1;
        while(i <= j){
            if(strip.charAt(i)!=strip.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

//my stack solution
public class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        s = s.trim().replaceAll("\\W","").toLowerCase();

        Stack<Character> stack = new Stack<Character>();
        int index = 0;
        for(index = 0; index < s.length()/2; index++){
            stack.push(s.charAt(index));
        }
        
        if(s.length() %2 == 1){
            index++;
        }
        
        while(index < s.length()){
            if(stack.isEmpty()){
                return false;
            }
            char tmp = stack.pop();
            if(s.charAt(index) != tmp){
                return false;
            }
            index++;
        }
        if(!stack.isEmpty()){
            return false;
        }
        
        return true;
    }
}


//another solution: use stack
public boolean isPalindrome(String s) {
	s = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();

	int len = s.length();
	if(len < 2){
		return true;
	}

	Stack<Character> stack = new Stack<Character>();

	int index = 0;
	while(index < len/2){
		stack.push(s.charAt(index));
		index++;
	}

	if(len%2 == 1){
		index++;
	}

	while(index < len){
		if(stack.empty()){
			return false;
		}

		char temp = stack.pop();
		if(s.charAt(index) != temp){
			return false;
		}else{
			index ++;
		}
	}
	return true;
}