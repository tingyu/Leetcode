/**
Combination Sum

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

*/


/*
http://blog.csdn.net/linhuanmars/article/details/20828631 
这个题是一个NP问题，方法仍然是N-Queens中介绍的套路。基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，
并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。算法复杂度因为是NP问题，所以自然是指数量级的。
*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(candidates == null || candidates.length == 0)
			return res;
		Arrays.sort(candidates);
		helper(candidates, 0, target, new ArrayList<Integer>(), res);
		return res;        
    }

    private void helper(int[] candidates, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
    	if(target < 0)
    		return;
    	if(target == 0){
    		res.add(new ArrayList<Integer>(item));
    		return;
    	}
    	for(int i = start; i < candidates.length; i++){
    		//判断重复
    		if(i > 0 && candidates[i] == candidates[i - 1])
    			continue;
    		item.add(candidates[i]);
    		//因为排序之后每次先减去的都是小的，剩下的都是大的，所以下次直接从i开始搜索就可以了
    		helper(candidates, i, target - candidates[i], item, res);
    		item.remove(item.size() - 1);
    	}
    }
}

/*
注意在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，
所以重复的元素也就没有作用了，所以应该跳过那层递归。这道题有一个非常类似的题目Combination Sum II，有兴趣的朋友可以看看，一次搞定两个题哈。
更多 0
*/