/**
Unique Binary Search Trees II

Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
 		return dfs(1, n);       
    }

    private ArrayList<TreeNode> dfs(int start, int end){
    	ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    	if(start > end){
    		res.add(null);f
    		return res;
    	}

    	for(int i = start; i <= end; i++){
    		ArrayList<TreeNode> lefts = dfs(start, i -1);//lefts里面保存从start到i-1的所有组合  
    		ArrayList<TreeNode> rights = dfs(i + 1, end);//rights里面保存从i+1到endAr的所有组合
    		for(TreeNode left: lefts){
    			for(TreeNode right: rights){
    				TreeNode node = new TreeNode(i);
    				node.left = left;
    				node.right = right;
    				res.add(node);
    			}
    		}
    	}
    	return res;
    }
}