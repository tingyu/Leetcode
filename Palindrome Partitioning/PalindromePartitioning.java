/**
Palindrome Partitioning 

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

/*
这道题是求一个字符串中回文子串的切割，并且输出切割结果，其实是Word Break II和Longest Palindromic Substring结合，
该做的我们都做过了。首先我们根据Longest Palindromic Substring重大方法建立一个字典，得到字符串中的任意子串是不是回文串的字典，
不熟悉的朋友可以先看看哈。接下来就跟Word Break II一样，根据字典的结果进行切割，然后按照循环处理递归子问题的方法，
如果当前的子串满足回文条件，就递归处理字符串剩下的子串。如果到达终点就返回当前结果。算法的复杂度跟Word Break II一样，
取决于结果的数量，最坏情况是指数量级的。代码如下：
*/

public class Solution {
    public List<List<String>> partition(String s) {
 		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
 		if(s == null || s.length()==0)
 			return res;
 		helper(s, getDict(s), 0, new ArrayList<String>(), res); 
 		return res;      
    }

    private void helper(String s, boolean[] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res){
    	if(start == s.length()){
    		res.add(new ArrayList<String>(item));
    		return;
    	}

    	for(int i = start; i < s.length(); i++){
    		if(dict[start][i]){
    			item.add(s.substring(start, i+1));
    			//找后面的d
    			helper(s, dict, i+1, item, res);
    			item.remove(item.size() - 1);
    		}
    	}
    }

    private boolean[] getDict(String s){
    	boolean[][] dict = new boolean[s.length()][s.length()];
    	for(int i = s.length() - 1; i >=0; i--){
    		for(int j = i; j < s.length(); j++){
    			if(s.charAt(i)==s.charAt(j) && ((j - i)<2 || dict[i+1][j-1]){
    				dict[i][j] = true;
    			}
    		}
    	}
    	return dict;
    }
}