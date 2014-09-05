/**
Word BreakII

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

// 正确解法：
/*
http://www.binglu.me/leetcode-word-break-and-word-break-ii/
This question asks us to go a step further, which is printing all possible partition result. 
I referred to other’s C++ solutions and figured out this Java solution.

We solve this question still with the help of DP strategy, but there is no explicit DP status array.
The idea is: creating an ArrayList for each characters, then working from right to left, 
if a character is the start of a word, store the word’s ending character’s index
(actually it’s plus one, for the sake of substring()) to its list. After one pass, 
we can build all possible solutions based on the record. For example,

s="catsand"
ArrayList<ArrayList<Integer>> record is as below:

 0 1 2 3 4 5 6 
 c a t s a n d 
| | | | | | | |
 4     7 7
 3     
Since the second argument of substring() is exclusive when get substring, the stored index is the actual 
ending index plus 1. We can use the ArrayList record to build solution set. The Java solution is listed below.
这个算法十分新颖
关键是对每个character构造了是单词的ending character的index，长度是length
比如对c,里面存的时ending character的index的List,有3，有4
首先初始化
然后针对每个end做循环，从length到0，然后找他们的起始点，起始点这样循环for(int runner = end -1; runner>=0; runner--){
*/
public class Solution {
    ArrayList<String> wordBreak(String s, Set<String> dict) {
        int length = s.length();

        //create the word ending character's index list for each character
        ArrayList<ArrayList<Integer>> record = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < length; i++){
            record.add(new ArrayList<Integer>());
        }

        r
        for(int end = length; end >= 0; end--){
            //如果没有下面的判断就会超时，这里相当于剪枝的效果。为什么这样写？最开始的时候肯定都是空的？
            if(end < length && record.get(end).isEmpty())
                continue;

            //find the starting character for the current ending character
            for(int runner = end -1; runner>=0; runner--){
                if(dict.contains(s.substring(runner, end)))
                    record.get(runner).add(end); //add current end to start character's list
            }
        }

        ArrayList<String> solutionSet = new ArrayList<String>();
        buildSolution(record, 0, s, "", solutionSet);

        return solutionSet;
    }

    void buildSolution(ArrayList<ArrayList<Integer>> record, int current, String s, String solution, ArrayList<String> solutionSet){
        //iterate on current character's word ending list
        for(Integer end: record.get(current)){

            String sub = s.substring(current, end);

            /*
            since the loop may have many iterations, we should keep the reference
            of "solution", rather than writing as "solution + = .."
            */

            String newSolution = solution + (current == 0? sub: " " + sub);

            if(end == s.length()){
                solutionSet.add(newSolution);
            }else{//直接跳到end
                buildSolution(record, end, s, newSolution, solutionSet);
        }
    }
}

/*
http://blog.csdn.net/linhuanmars/article/details/22452163

这道题目要求跟Word Break比较类似，不过返回的结果不仅要知道能不能break，如果可以还要返回所有合法结果。
一般来说这种要求会让动态规划的效果减弱很多，因为我们要在过程中记录下所有的合法结果，中间的操作会使得算法的复杂度不再是动态规划的两层循环，
因为每次迭代中还需要不是constant的操作，最终复杂度会主要取决于结果的数量，而且还会占用大量的空间，因为不仅要保存最终结果，
包括中间的合法结果也要一一保存，否则后面需要历史信息会取不到。所以这道题目我们介绍两种方法，一种是直接brute force用递归解，
另一种是跟Word Break思路类似的动态规划。

对于brute force解法，代码比较简单，每次维护一个当前结果集，然后遍历剩下的所有子串，如果子串在字典中出现，
则保存一下结果，并放入下一层递归剩下的字符。思路接近于我们在N-Queens这些NP问题中经常用到的套路。代码如下：
Time Limit Exceeded

这个solution是错的。为什么？？？？
Submission Result: Wrong Answer

Input:  "aaaaaaa", ["aaaa","aaa"]
Output: ["aaa aaaa","aaaa aaa","aaa aaaa","aaaa aaa"]
Expected:   ["aaaa aaa","aaa aaaa"]
*/


public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		if(s == null || s.length() == 0)
			return res;
		helper(s, dict, 0, "", res);
		return res;        
    }

    private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res){
    	if(start >= s.length()){
    		res.add(item);
    		return;
    	}

    	StringBuilder str = new StringBuilder();
    	for(int i = start; i< s.length(); i++){
    		str.append(s.charAt(i));
    		if(dict.contains(str.toString())){
    			String newItem = item.length()>0?(item + " " + str.toString()):str.toString();
    			helper(s, dict, i+1, newItem, res);
    		}
    	}
    }
}

/*
接下来我们列出动态规划的解法，递推式跟Word Break是一样的，只是现在不只要保存true或者false，
还需要保存true的时候所有合法的组合，以便在未来需要的时候可以得到。不过为了实现这点，代码量就增大很多，
需要一个数据结构来进行存储，同时空间复杂度变得非常高，因为所有中间组合都要一直保存。时间上还是有提高的，
就是当我们需要前面到某一个元素前的所有合法组合时，我们不需要重新计算了。不过最终复杂度还是主要取决于结果的数量。代码如下：
*/

class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start; this.end = end;
    }
    public Interval(Interval i) {
        start = i.start;
        end = i.end;
 }
}
ArrayList<ArrayList<Interval>> deepCopy(ArrayList<ArrayList<Interval>> paths) {
    if (paths==null) return null;
    ArrayList<ArrayList<Interval>> res = new ArrayList<ArrayList<Interval>>(paths.size());
    for (int i=0; i<paths.size(); i++) {
     ArrayList<Interval> path = paths.get(i);
     ArrayList<Interval> copy = new ArrayList<Interval>(path.size());
        for (int j=0; j<path.size(); j++) {
         copy.add(new Interval(path.get(j)));
     }
     res.add(copy);
    }
    return res;
}
public ArrayList<String> wordBreak(String s, Set<String> dict) {
    ArrayList<String> res = new ArrayList<String>();
    if (s==null || s.length()==0) return res;
    Map<Integer, ArrayList<ArrayList<Interval>>> dp = new HashMap<Integer, ArrayList<ArrayList<Interval>>>();
    dp.put(0, new ArrayList<ArrayList<Interval>>());
    dp.get(0).add(new ArrayList<Interval>());
    for (int i=1; i<=s.length(); i++) {
        for (int j=0; j<i; j++) {
            String cur = s.substring(j, i);
            ArrayList<ArrayList<Interval>> paths = null;
            if (dp.containsKey(j) && dict.contains(cur)) {
                paths = deepCopy(dp.get(j));
                Interval interval = new Interval(j, i);
                for (ArrayList<Interval> path:paths) {
                    path.add(interval);
                }
            }
            if (paths != null) {
                if (!dp.containsKey(i)) {
                    dp.put(i, paths);
                } 
                else {
              dp.get(i).addAll(paths);
             }
            }
        }
    }
    if (!dp.containsKey(s.length())) {
        return res;
    }
    ArrayList<ArrayList<Interval>> paths = dp.get(s.length());
    for (int j=0; j<paths.size(); j++) {
        ArrayList<Interval> path = paths.get(j);
        StringBuilder str = new StringBuilder();
        for (int i=0; i<path.size(); i++) {
            if (i!=0) {str.append(" ");}
            int start = path.get(i).start;
            int end = path.get(i).end;
            str.append(s.substring(start, end));
        }
        res.add(str.toString());
    }
    return res;
}


/*
这个solution是错的。为什么？？？？
Submission Result: Wrong Answer

Input:  "aaaaaaa", ["aaaa","aaa"]
Output: ["aaa aaaa","aaaa aaa","aaa aaaa","aaaa aaa"]
Expected:   ["aaaa aaa","aaa aaaa"]
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0)
            return res;
        boolean[][] dp = new boolean[s.length()][s.length()];
        dp = getDP(s, dict);
        for(int i = 0; i < s.length(); i++){
            if(dp[i][s.length()-1]){
                helper(s, dp, dict, 0, "", res);   
            }    
        }
        return res;        
    }
    
    private boolean[][] getDP(String s, Set<String> dict){
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(dict.contains(s.substring(i, j+1))){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = false;
                }
            }
        }
        return dp;
    }

    private void helper(String s, boolean[][] dp, Set<String> dict, int start, String item, ArrayList<String> res){
        if(start == s.length()){
            res.add(item);
            return;
        }

        for(int i = start; i< s.length(); i++){
           // for(int j = i + 1; j < s.length(); j++){
                    if(dp[start][i]){
                       // String str = s.substring(start, i+1);
                        String newItem = item.length()>0?(item + " " + s.substring(start, i+1)):s.substring(start, i+1);
                        helper(s, dp, dict, i+1, newItem, res);
                }
        //  }
        }
    }
}

