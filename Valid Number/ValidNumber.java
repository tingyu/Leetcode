/**
Valid Number 

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.
*/

public class Solution {
    public boolean isNumber(String s) {
        if(s.trim().isEmpty()){  
            return false;  
        }  
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";  
        if(s.trim().matches(regex)){  
            return true;  
        }else{  
            return false;  
        }          
    }
}


//http://www.cnblogs.com/midnightcat/p/3364431.html
import java.util.regex.Pattern;
public class Solution {
    Pattern p = Pattern.compile("^[\\+\\-]?((\\d+(\\.\\d*)?)|(\\.\\d+))(e[\\+\\-]?\\d+)?$");
    public boolean isNumber(String s) {
        String ss = s.trim();
        return p.matcher(ss).matches();
    }
}

//http://rleetcode.blogspot.com/2014/01/valid-number-java.html
// This is really a trivial question, a lot of corner cases need to be found, just like the Stirng to Integer question.
public class ValidNumber {
	public boolean isNumber(String s) {
		if(s == null) return false;

		//trim off head and tail zeros which not affect result depend on question
		s = s.trim();
		if(s.length() == 0) return false;

		boolean hasNum = false;
		boolean canSign = true;
		boolean canDot = true;
		boolean canE = false;
		boolean hasE = false;

		int i = 0;
		while(i < s.length()){
			char c = s.charAt(i++);

			if(c == ' '){
				return false;
			}

			if(c == '+' || c == '-'){
				if(!canSign){
					return false;
				}
				canSign = false;
				continue;
			}

			if(c == '.'){
				if(!canDot){
					return false;
				}
				canDot = false;
				canSign = false;
				continue;
			}

			if(c == 'e'){
				if(!canE|| hasE){
					return false;
				}

				canE =false;
				hasE = true;
				hasNum = false;
				canDot = false;
				canSign = true;

				continue;
			}

			if(c >= '0' && c <='9'){
				hasNum = true;

				if(!canE && !hasE){
					canE = true;
				}

				canSign = false;
			}else{
				return false;
			}
		}
		return hasNum;
	}
}