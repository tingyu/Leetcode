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
/*
summary:
这题需要从一系列的数字中找到对应的字母，将这些combination输出。但是这个combination和Combination那道题的又不一样。Combination
那题是从一个array里面找出组合，而这里的是从不同的String里面找到。所以就不存在Combination里面那样找了一个之后，只能从后面的一个开始
找的情况。这里每个都是相互独立的。
可以用一个变量i记录一下digits里面的哪一个字母，然后找到一个之后dfs找下一个数字的字母的集合。这里没必要弄个双重循环了。递归return的话
就可以返回上一个重新选择了。
递归终止条件是到达digits.length()
*/
public class Solution {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        String[] array = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder tmp = new StringBuilder();
        dfs(array, digits, res, tmp, 0);
        return res;
    }
    
    private void dfs(String[] array, String digits, ArrayList<String> res, StringBuilder tmp, int i){
        if(i == digits.length()){
            res.add(tmp.toString());
            return;
        }
        
        //String s = array[Integer.valueOf(digits.charAt(i))];
        String s = array[digits.charAt(i) - '0'];
        for(int j = 0; j < s.length(); j++){
            tmp.append(s.charAt(j));
            dfs(array, digits, res, tmp, i+1);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
}

//my solution
public class Solution {
    String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0){
            res.add("");
            return res;
        } 
        StringBuilder tmp = new StringBuilder();
        helper(digits, res, tmp, 0, 0);
        return res;
    }
    
    private void helper(String digits, ArrayList<String> res, StringBuilder tmp, int start, int count){
        if( digits.length() == count){
            res.add(new String(tmp.toString()));
            return;
        }
        
        for(int i = start; i < digits.length(); i++){
            //int index = Integer.valueOf(digits.charAt(i));注释掉的这种写法是错的,为什么？？
            //String s = letters[index];
            String s = letters[digits.charAt(i) - '0'];
            for(int j = 0; j < s.length(); j++){
                tmp.append(s.charAt(j));
                helper(digits, res, tmp, i + 1, count + 1);
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }
    }
}


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



public class Solution {
    private HashMap<Character, String> map;

    public List<String> letterCombinations(String digits) {
        setup();
        char[] cs = new char[digits.length()];
        ArrayList<String> res = new ArrayList<String>();
        helper(digits, 0 ,cs, res);
        return res;
    }

    private void helper(String digits, int i, char[] cs, ArrayList<String> res){
        if(i == digits.length()){
            res.add(new String(cs));
            return;
        }

        String letters = map.get(digits.charAt(i));
        for(int j = 0; j < letters.length(); j++){
            cs[i] = letters.charAt(j);
            helper(digits, i + 1, cs, res);
        }
    }

    private void setup(){
        map = new HashMap<Character, String>();
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        map.put('0', "");
    }
}