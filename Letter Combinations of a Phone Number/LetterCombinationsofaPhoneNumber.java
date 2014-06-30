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
        
        ArrayList<String> res = new ArrayList<String>();
        helper(res, digits.length(), ss, digits, new StringBuffer());
        return res;
    }
    
    public void helper(ArrayList<String> res, int remain, String[] ss, String digits, StringBuffer tmp){
        //说明对digits遍历结束
        if(remain == 0){
            res.add(tmp.toString());
            return;
        }
        
        String s = ss[digits.charAt(0) - '0'];//得到digits[0]对应的String s
        //DFS
        for(int i = 0; i < s.length(); i++){
            tmp.append(s.charAt(i));
            helper(res, remain - 1, ss, digits.substring(1), tmp);
            tmp.deleteCharAt(tmp.length() - 1);//恢复现场
        }
    }
}