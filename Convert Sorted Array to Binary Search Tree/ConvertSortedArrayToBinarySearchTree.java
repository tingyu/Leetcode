/*
Convert Sorted Array to Binary Search Tree 

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

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
//把一个sorted array变成BST，关键是找到中间，然后作为root，然后找左子树和右子树，这样不停的递归。这个过程
public class Solution {
	public TreeNode sortedArrayToBST(int[] num){
		if(num.length == 0)
			return null;

		return sortedArrayToBST(num, 0, num.length -1);
	}

	public TreeNode sortedArrayToBST(int[] num, int start, int end){
		if(start > end)//这很重要，不要忘了
			return null;

		int mid = (start + end) /2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBST(num, start, mid -1);
		root.right = sortedArrayToBST(num, mid + 1, end);

		return root;
	}
}