/**
Reverse Words in a String

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

*/

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