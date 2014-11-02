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
//my solution
/*
1. null: false
首先把前后的空格去掉，然后看如果如果剩下的长度如果是0，false, 
然后看第一个元素是不是+-，如果是的话i++

*/
public class Solution{
	public boolean isNumber(String s) {
		if(s == null) return false;

		//trim off head and tail zeros which not affect result depend on question
		s = s.trim();
		if(s.length() == 0) return false;

		int i = 0;
		if(s.charAt(i) == '+' || s.charAt(i) == '-'){
		    i++;
		}
		
		boolean hasExp = false, hasDot = false, firstPart = false, secondPart = false;
		while(i < s.length()){
		    char c = s.charAt(i);
		    if(c == '.'){
		        if(hasExp || hasDot){//dot cannot exits after E; cannot has two dots 
		            return false;
		        }else{
		            hasDot = true;
		        }
		    }else if(c == 'E' || c == 'e'){
		        if(hasExp || !firstPart){// cannot has two E; first part must exits before E
		            return false;
		        }else{
		            hasExp = true;
		        }
		    }else if(c >= '0' && c <='9'){
		        if(!hasExp){//does not have E yet, here is the firstPart
		            firstPart = true;
		        }else{//already has E, here is the secondPart
		            secondPart = true;
		        }
		    }else if(c == '+' || c == '-'){//inner '+''-',can only exist closely after E
		        if(!hasExp || !(s.charAt(i-1) == 'E' || s.charAt(i-1) == 'e')){
		            return false;
		        }
		    }else if(c == ' '){
		        return false;
		    }else{
		        return false;
		    }
		    i++;
		}
		
		if(!firstPart){//first part does not exist. Wrong!
		    return false;
		}else if(hasExp && !secondPart){//has E, but does not has second part. Wrong!
		    return false;
		}
		return true;
	}
}

/*
最后面循环外面的两个判断是十分有必要的。如果没有第一个判断
Submission Result: Wrong Answer

Input:	"."
Output:	true
Expected:false
如果没有第二个判断
Submission Result: Wrong Answer

Input:	"0e"
Output:	true
Expected:	false

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



