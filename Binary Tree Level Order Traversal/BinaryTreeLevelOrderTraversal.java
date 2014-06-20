/**
Binary Tree Level Order Traversal 

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
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
    //public List<List<Integer>> levelOrder(TreeNode root) {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> rs = new ArrayList<ArrayList<Integer>>();
        if(root == null) return rs;
        helper(root, rs, 1);
        return rs;
    }

    public void helper(TreeNode node, ArrayList<ArrayList<Integer>> rs, int depth){
    	if(node == null) return;
    	ArrayList<Integer> tmp = new ArrayList<Integer>();

    	//check if this level has already exist
    	if(rs.size() < depth) rs.add(tmp);//not exist, then add tmp to rs
    	else tmp = rs.get(depth -1); //get tmp from rs to update

    	tmp.add(node.val); //add node to current depthht

    	helper(node.left, rs, depth + 1);
    	helper(node.right, rs, depth + 1);
    }
}