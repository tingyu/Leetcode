/**
Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/ 
//my solution
public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(n < k) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        helper(res, tmp, n, k, 1);
        return res;
    }
    
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int n, int k, int start){
        if(tmp.size() == k){
            res.add(new ArrayList<Integer>(tmp));
            return;
        }
        
        for(int i = start; i <= n; i++){
            tmp.add(i);
            helper(res, tmp, n, k, i+1);
            tmp.remove(tmp.size() - 1);
        }
    }
}

/*
http://blog.csdn.net/u010500263/article/details/18435495
Note that in each combination, former number is smaller than the following number.  
In another word, [1, 2] and [2, 1] is considered as the same combination, 
and only [1, 2] is required as the result in this problem.

As usual, if we don't have any idea about the solution, we can try to manually process 1-2 examples.  
Here is an example I tried, n=5, k=3: 

From this example, we can see that in the first position of the resulting combinations we can chose number 1-5.  
Assume that we chose 1 for the 1 position of the combination, then we can choose 2-5 for the second position.  T
ill we chosen numbers for all position, we can have one possible combination.

However, when we chose 3 for the first position and 5 for the second position, 
we don't have any other number to choose for the 3rd position. 
(former number must be smaller than the following number).


Now we have the rules of this problem, and we can try to implement these rules.  
For choosing number in a specific layer, we can use a iteration, e.g. in the first layer, we try 1-5.  
While for step in another layer, we can use recursive call.  
Parameters we need for a recursive call are the current combination,
 the combination resuitlt set, the n, the k, and the start point of iteration 
 (e.g. if we chose 1 for the first position, start point of 2nd position is 2; 
 while if we chose 2 for the first position, start point of second position is 3).  
*/

public class Solution {
    //public List<List<Integer>> combine(int n, int k) {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> combSet = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> comb = new ArrayList<Integer>();
        
        if(n < k) return combSet;
        helper(n, k, combSet, comb, 1);
        return combSet;
    }
    
    public void helper(int n, int k, ArrayList<ArrayList<Integer>> combSet, ArrayList<Integer> comb, int start){
        //one possible combination constructed
        if(comb.size() == k){
            combSet.add(new ArrayList<Integer> (comb));
            return ;
        }else{
            for(int i = start; i <=n; i++){//try each possiblity number in current position
                comb.add(i);
                helper(n, k, combSet, comb, i+1);//after selection number for current position, process next position
                comb.remove(comb.size() -1);//clear the current position to try next possible number
            }
        }
    }
}