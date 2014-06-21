/**
Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

*/

/*
http://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
Thoughts about This Problem

All values on the left sub tree must be less than root, and all values on the right sub tree must be greater than root.

many other solutions refer:
http://blog.csdn.net/likecool21/article/details/23271621

改进一下。想到了AI课上学的alpha-beta pruning。一棵树的根为a，右子树为b，那么右子树的左儿子c的值一定是大于a且小于b。这样coding就好办了

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
 
	public static boolean isValidBST(TreeNode root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
 
	public static boolean validate(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
 
		// not in range
		if (root.val <= min || root.val >= max) {
			return false;
		}
 
		// left subtree must be < root.val && right subtree must be > root.val
		return validate(root.left, min, root.val) && validate(root.right, root.val, max);
	}
}
