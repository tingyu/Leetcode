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


//my solution
/*简单的DFS的做法。对于每个recursion中的string，不断的取从0开始的substring，判断是不是Palindrome。如果是的话，就加到tmp中，
然后就不停helper(s.substring(i+1), res, tmp);，在从递归里面推出之后要tmp.remove(tmp.size() - 1);
判断palindrome就是对string长度取一半，然后判断前后位置的char是不是相等

这里没有用start，当然也可以用start，然后找
start常用在找subsets之类的时候
*/
public class Solution {
//    public List<List<String>> partition(String s) {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ArrayList<String> tmp = new ArrayList<String>();
        helper(s, res, tmp);
        return res;
    }
    
    private void helper(String s, ArrayList<ArrayList<String>> res, ArrayList<String> tmp){
        if(s.length() == 0){
            res.add(new ArrayList<String>(tmp));
            return;
        }
        
        for(int i = 0; i < s.length(); i++){
            String sub = s.substring(0, i+1);
            if(isPalindrome(sub)){
                tmp.add(sub);
                helper(s.substring(i+1), res, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String sub){
        int len = sub.length();
        for(int i = 0; i < len/2; i++){
            if(sub.charAt(i) != sub.charAt(len - 1 -i)){
                return false;
            }
        }
        return true;
    }
}


/*
这道题是求一个字符串中回文子串的切割，并且输出切割结果，其实是Word Break II和Longest Palindromic Substring结合，
该做的我们都做过了。首先我们根据Longest Palindromic Substring重大方法建立一个字典，得到字符串中的任意子串是不是回文串的字典，
不熟悉的朋友可以先看看哈。接下来就跟Word Break II一样，根据字典的结果进行切割，然后按照循环处理递归子问题的方法，
如果当前的子串满足回文条件，就递归处理字符串剩下的子串。如果到达终点就返回当前结果。算法的复杂度跟Word Break II一样，
取决于结果的数量，最坏情况是指数量级的。代码如下：
*/

public class Solution {
//    public List<List<String>> partition(String s) {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if(s == null || s.length() == 0)
            return res;
        helper(s, getDict(s), 0, new ArrayList<String>(), res);
        return res;
    }
    
    private void helper(String s, boolean[][] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res){
        if(start == s.length()){
            res.add(new ArrayList<String>(item));
            return;
        }
        
        for(int i = start; i < s.length(); i++){
            if(dict[start][i]){
                item.add(s.substring(start, i+1));
                
                helper(s, dict, i+1, item, res);
                item.remove(item.size() -1);
            }
        }
    }

    private boolean[][] getDict(String s){
        boolean[][] dict = new boolean[s.length()][s.length()];
        for(int i = s.length()-1; i>=0; i--){
            for(int j = i; j < s.length(); j++){
                if(s.charAt(i) == s.charAt(j) &&((j-i)<2 || dict[i+1][j-1])){
                    dict[i][j] = true;
                }
            }
        } 
        return dict;
    }
}