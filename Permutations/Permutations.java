/*
Permutations

Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/
//my solution
//太过于经典的dfs题目。开始的想法是找个记录结点的方法，只想到了start+1什么的。结果忘了用一个visited的数组。
//然后不断的设置visited和取消visited
/*
1. Create a new array "visited[num.size()]" to keep the which element of the original 
array has been visited, so as to ensure only the remaining elements will be processed.  
For example, in case of array[0] = 1, only 2,3,4 can be process for array[1].

2. Remove the last element from the array, and resume the visit flag in order to process next 
possible permutation. For example, after having [1, 2, 3, 4], remove 4 from array (array will be 
fallen back to [1, 2, 3]), and reset visit flag of the 3rd element to un-visited.  
Then go into the next iteration: put 4 into the array.  New array this time would be [1, 2, 4].
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        helper(num, res, tmp, visited);
        return res;
    }
    
    private void helper(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, boolean[] visited){
        if(tmp.size() == num.length){
            res.add(new ArrayList<Integer>(tmp));
            //Arrays.fill(visited, false);这个加上是错误的。不能得到一个之后立即清空
            return;
        }
        
        for(int i = 0; i < num.length; i++){
            if(visited[i] == false){
                tmp.add(num[i]);
                visited[i] = true;
                helper(num, res, tmp, visited);  
                tmp.remove(tmp.size() -1);
                visited[i] = false;
            }
        }
    }
}



//http://www.programcreek.com/2013/02/leetcode-permutations-java/
/*
We can get all permutations by the following steps:

[1]
[2, 1]
[1, 2]
[3, 2, 1]
[2, 3, 1]
[2, 1, 3]
[3, 1, 2]
[1, 3, 2]
[1, 2, 3]
Loop through the array, in each iteration, a new number is added to different locations of 
results of previous iteration. Start from an empty List.
*/

public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
 
    	//start from an empty list
    	result.add(new ArrayList<Integer>());
 
    	for (int i = 0; i < num.length; i++) {
	    	//list of list in current iteration of the array num
		    ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
 
		    for (ArrayList<Integer> l : result) {
			    // # of locations to insert is largest index + 1
			    for (int j = 0; j < l.size()+1; j++) {
				    // + add num[i] to different locations
				    l.add(j, num[i]);
 
				    ArrayList<Integer> temp = new ArrayList<Integer>(l);
				    current.add(temp);

				    // - remove num[i] add
				    l.remove(j);
			    }
		    }
 
		    result = new ArrayList<ArrayList<Integer>>(current);
	    }
 
    	return result;
    }
}

/*
Java Solution 2

We can also recursively solve this problem. Swap each element with each element after it
*/
//http://blog.csdn.net/u010500263/article/details/18178243
/*
A more systematic for solving permutation problems, which is also to align with problem [leet code] N-Queens & II.  
Description can be referred to N-Queens problem.
*/

public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        helper(num, res, 0);
        return res;
    }
    
    private void helper(int[] num, ArrayList<ArrayList<Integer>> res, int start){
        if(start >= num.length){
            ArrayList<Integer> tmp = convertArrayToList(num);
            res.add(tmp);
            return;
        }
        
        for(int i = start; i < num.length; i++){
            swap(num, start, i);
            helper(num, res, start + 1);
            swap(num, start, i);
        }
    }
    
    private ArrayList<Integer> convertArrayToList(int[] num){
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for(int i = 0; i < num.length; i++){
            tmp.add(num[i]);
        }
        return tmp;
    }
    
    private void swap(int[] num, int i, int j){
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}

/*
i = start = 0:
[1, 2, 3] -> [1, 2, 3] -> [1, 2, 3] or [1, 3, 2]

start = 0, i = 1:
[2, 1, 3] -> [2, 1, 3] -> [2, 1, 3] or [2, 3, 1]

start = 0, i = 2:
[3, 2, 1] -> [3, 2, 1] -> [3, 2, 1] or [3, 1, 2]
