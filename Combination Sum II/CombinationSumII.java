/**
Combination Sum II 

Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

//my solutions
public class Solution {
  //  public List<List<Integer>> combinationSum2(int[] num, int target) {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(num);
        helper(num, res, tmp, target, 0);
        return res;
    }
    
    private void helper(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int target, int start){
        if(target == 0){
            res.add(new ArrayList<Integer>(tmp));
            return;
        }else if(target < 0){
            return;
        }
        
        for(int i = start; i < num.length; i++){
            if(i > start && num[i] == num[i-1]) continue;
            tmp.add(num[i]);
            helper(num, res, tmp, target - num[i], i +1);
            tmp.remove(tmp.size() -1);
        }
    }
}
/*
为什么一定要i > start 在if(i > start && num[i] == num[i-1]) continue里面。如果是 i > 1就会报错
Submission Result: Wrong Answer
Input:  [1,1], 1
Output: [[1],[1]]
Expected:   [[1]]
若是if(i > 0 && num[i] == num[i-1]) continue;
Submission Result: Wrong Answer

Input:  [1,1], 2
Output: []
Expected:   [[1,1]]

*/

/*
这道题跟Combination Sum非常相似，不了解的朋友可以先看看，唯一的区别就是这个题目中单个元素用过就不可以重复使用了。
乍一看好像区别比较大，但是其实实现上只需要一点点改动就可以完成了，就是递归的时候传进去的index应该是当前元素的下一个。代码如下： 
*/

public class Solution {
  //  public List<List<Integer>> combinationSum2(int[] num, int target) {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return res;
        Arrays.sort(num);
        helper(num, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(target < 0)
            return;
        if(target == 0){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        for(int i = start; i < num.length; i++){
            if(i > start && num[i] == num[i-1])//避免因为数组中重复的元素，出现重复的元素集
                continue;
            item.add(num[i]);//因为有i>start，每次都允许加入当前的, i > 0的话就是要去除所有的重复的了
            
            helper(num, i+1, target - num[i], item, res);//避免重复使用当前元素
            item.remove(item.size() - 1);
        }
    }
}
/*
在这里我们还是需要在每一次for循环前做一次判断，因为虽然一个元素不可以重复使用，但是如果这个元素重复出现是允许的，
但是为了避免出现重复的结果集，我们只对于第一次得到这个数进行递归，接下来就跳过这个元素了，
因为接下来的情况会在上一层的递归函数被考虑到，这样就可以避免重复元素的出现。这个问题可能会觉得比较绕，大家仔细想想就明白了哈。
*/


