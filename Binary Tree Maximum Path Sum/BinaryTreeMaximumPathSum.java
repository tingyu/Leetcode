/**
Binary Tree Maximum Path Sum

Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/*
Thoughts

1) Recursively solve this problem
2) Get largest left sum and right sum
2) Compare to the stored maximum
*/
//http://www.programcreek.com/2013/02/leetcode-binary-tree-maximum-path-sum-java/

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
 
	TreeNode(int x) {
		val = x;
	}
}
 
public class Solution {
	//store max value
	int max; 
	public int maxPathSum(TreeNode root) {
		max = (root == null)? 0: root.val;
		findMax(root);
		return max;
	}

	public int findMax(TreeNode node){
		if(node == null) return 0;

		//recursively get sum of left and right path
		int left = Math.max(findMax(node.left), 0);
		int right = Math.max(findMax(node.right), 0);

r		max = Math.max(node.val + left + right, max);

		//return sum of largest path of current node
		return node.val + Math.max(left, right);
	}
}


//We can also use an array to store value for recursive methods.
public class Solution {
	public int maxPathSum(TreeNode root) {
		int max[] = new int[1];
		max[0] = Integer.MIN_VALUE;
		calculateSum(root, max);
		return max[0];
	}

	public int calculateSum(TreeNode root, int[] max){
		if(root == null) return 0;

		int left = calculateSum(root.left, max);
		int right = calculateSum(root.right, max);

		int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

		max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

		return current;
	}
}
