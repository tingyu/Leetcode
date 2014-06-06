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
Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's Iterator.
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

 			//for each arraylist in temp, we append s[i] to the end of the arraylist
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




public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
    	ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();

    	if(s.length == 0) return false;
    	Attays.sort(S);
    	for(int i = 0; i < S.length; i++){
    		ArrayList<Integer> ilist = new ArrayList<Integer>();
    		getsubsets(S, ilist, alist, i, 0);
    	}

    	return alist;
  	}

  	public void getsubsets(int[] S, ArrayList<Integer> ilist, ArrayList<ArrayList<Integer>> alist, int len, int level){
  		if(ilist.size() == len){
  			ArrayList<Integer> temp = new ArrayList<Integer>(ilist);
  			alist.add(temp);
  			return;
  		}

  		for(int i=level; i < S.length; i++){
  			ilist.add(S[i]);
  			getsubsets(S, ilist, alist, len, i+1);
  			ilist.remove(ilist.size() -1);
  		}
  	}
}
