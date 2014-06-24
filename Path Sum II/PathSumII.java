/**
Path Sum II 

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/


/*
Solution:  DFS

Apply DFS check every possible combination, record result if meet requirement
*/


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //public List<List<Integer>> pathSum(TreeNode root, int sum) {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        dfs(root, sum, tmp, res);
        return res;
    }
    
    public void dfs(TreeNode node, int sum, ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> res){
        if(node == null) return;
        
        tmp.add(node.val);

        if(node.left == null && node.right == null){
            int calSum = 0;
            for(int a: tmp){
                calSum += a;
            } 

            if(calSum == sum){
                res.add(new ArrayList<Integer>(tmp));
                return;
            }
        }
        
        //这两个判断和两个remove一定要加上，不加上就出错了。
        if(node.left != null){
            dfs(node.left, sum, tmp, res);
            tmp.remove(tmp.size() -1);    
        }
        if(node.right != null){
            dfs(node.right, sum, tmp, res);
            tmp.remove(tmp.size() -1);           
        }
    }
}