/**
Letter Combinations of a Phone Number
https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

public class Solution {
    public List<String> letterCombinations(String digits) {
		String[] ss = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

		ArrayList<String> ret = new ArrayList<String>();
		rec(ret, digits.length(), ss, digits, new StringBuffer());
		return ret;        
    }

    public void rec(ArrayList<String> ret, int remain, String[] ss, String digits, StringBuffer sb){
    	//说明对digits遍历结束
    	if(remain == 0){
    		ret.add(sb.toString());
    		return;
    	}

    	String s = ss[digits.charAt(0) - '0'];//得到digits[0]对应的String s
    	//DFS
    	for(int i = 0; i < s.length(); i++){
    		sb = sb.append(s.charAt(i));
    		rec(ret, remain - 1, ss, digits.substring(1), sb);
    		sb.deleteCharAt(sb.length() -1); //恢复现场
    	}
    }
}