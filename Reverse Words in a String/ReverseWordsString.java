/**
Reverse Words in a String

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

*/
/*
这题稍微注意下就可以做到Bug free
有几个tricky的地方
1. String[] words = s.trim().split(" ");注意是split,里面是“ ”而不是""
2. 要判断if(!words[i].equals(""))

不然就会
Submission Result: Wrong Answer

Input:  "   a   b "
Output: "b   a"
Expected:   "b a"
如果判断if(!words[i].equals(" ")){也会得到上面的结果
3. 空格的数目要正好
                if(i > 0){
                    res.append(" ");
                }
*/
//my solution
public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" ");
        StringBuilder res = new StringBuilder();
        for(int i = words.length-1; i>= 0; i--){
            if(!words[i].equals("")){//use this to strip whitespace in the String[]
                res.append(words[i]);
                if(i >0){
                    res.append(" ");
                }                
            }
        }
        return res.toString();
    }
}


public class Solution {
	public String reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}

		String[] array = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = array.length - 1; i >= 0; --i) {
			if (!array[i].equals("")) {
				sb.append(array[i]).append(" ");
			}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}
}

public class Solution {
    public String reverseWords(String s) {
        String res = "";
        String[] word = s.trim().split(" ");
        for(int i = word.length -1; i>=0; i--){
            if(!word[i].equals("")){//use this to strip whitespace in the String[]
                res = res + word[i];
                if(i>0){
                    res = res + " ";
                }                
            }
        }
        return res;
    }
}