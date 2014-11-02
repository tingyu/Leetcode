/**
Subsets 

Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/


/**
addAll(Collection<? extends E> c)
Appends all of the elements in the specified collection to the end of this list, 
in the order that they are returned by the specified collection's Iterator.
*/

/*
基本思路：
对array中的每个元素进行循环，每次在前一次循环得到的arraylist的基础上，append当前的integer,然后再将所有的结果append的到输出。
注意一定要处理只有一个arraylist中只含有一个元素和空元素的情况
注意多个arraylist嵌套的问题
use of generics
*/

public class Solution {
    //public List<List<Integer>> subsets(int[] S) {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {

 		if(S == null) return null;

 		//sort the array
 		Arrays.sort(S); 

 		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

 		for(int i = 0; i < S.length; i++){
 			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

 			//get all the arraylist that already in result, add them into temp
 			for(ArrayList<Integer> a : result){ //a is the element,所以是arraylist<Integer>
 				temp.add(new ArrayList<Integer>(a));//注意要new
 			}

 			
 			for(ArrayList<Integer> a : temp){
 				a.add(S[i]);
 			}

 			//add S[i] as a single set
 			ArrayList<Integer> single = new ArrayList<Integer>();
 			single.add(S[i]);
 			temp.add(single);

 			//append all the newly created arraylist to the result
 			result.addAll(temp);
 		}      

 		//add empty arraylist
 		result.add(new ArrayList<Integer>());

 		return result;
    }
}



//类似于combination的解法，这里的关键不同之处就是在外面再加上一层循环。因为包含了从0到S.length长度的combination
public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();

      if(S.length == 0) return alist;
      Arrays.sort(S);
      for(int i = 0; i < S.length; i++){
        ArrayList<Integer> ilist = new ArrayList<Integer>();
        getsubsets(S, ilist, alist, i, 0);
      }

      return alist;
    }

    public void getsubsets(int[] S, ArrayList<Integer> ilist, ArrayList<ArrayList<Integer>> alist, int len, int level){
      if(ilist.size() == len){
        alist.add(new ArrayList<Integer>(ilist));
        return;
      }

      for(int i=level; i < S.length; i++){
        ilist.add(S[i]);
        getsubsets(S, ilist, alist, len, i+1);
        ilist.remove(ilist.size() -1);
      }
    }
}
/*
但是这个解法的结果是通过不了的
Submission Result: Wrong Answer

Input:  [0]
Output: [[]]
Expected: [[],[0]]
*/

public class Solution {
    //public List<List<Integer>> subsets(int[] S) {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(S == null || S.length == 0) return res;
        Arrays.sort(S);
        for(int i = 0; i < S.length; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            getSubsets(S, res, tmp, i, 0);
        }
        return res;
    }
    
    private void getSubsets(int[] S, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int len, int start){
        if(tmp.size() == len){
            ArrayList<Integer> l = new ArrayList<Integer>(tmp);
            res.add(l);
            return;
        }
        
        for(int i = start; i < S.length; i++){
            tmp.add(S[i]);
            getSubsets(S, res, tmp, len, i + 1);
            tmp.remove(tmp.size() -1);
        }
    }
}

//a wrong one
public class Solution {
    //public List<List<Integer>> subsets(int[] S) {
    int num = 0;
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(S);
        
        helper(S, res, tmp, 0, num);    
        return res;
    }
    
    private void helper(int[] S, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int start, int num){
        if(start == num){
            res.add(new ArrayList<Integer>(tmp));
            tmp = new ArrayList<Integer>();
            num++;
            return;
        }
        
        for(int i = start; i < S.length; i++){
            tmp.add(S[i]);
            helper(S, res, tmp, i + 1, num);
            tmp.remove(tmp.size() -1);
        }
    }
}
