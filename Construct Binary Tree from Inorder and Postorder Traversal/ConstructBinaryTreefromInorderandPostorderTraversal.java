/**
Construct Binary Tree from Inorder and Postorder Traversal 

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/*
Throughts
Solution: Divide and Conquer

This problem can be illustrated by using a simple example.

in-order:   4 2 5  (1)  6 7 3 8
post-order: 4 5 2  6 7 8 3  (1)
From the post-order array, we know that last element is the root. We can find the root in in-order array. 
Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in post-order array. 
Recursively, we can build up the tree.

Detailed description: http://leetcode.com/2011/04/construct-binary-tree-from-inorder-and-preorder-postorder-traversal.html
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0; 
        int inEnd = inorder.length - 1;
        int postStart = 0;
        int postEnd = postorder.length - 1;

        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
    	if(inStart > inEnd || postStart > postEnd){
    		return null;
    	}

    	int rootValue = postorder[postEnd];
    	TreeNode root = new TreeNode(rootValue);

    	//find the index in inorder that is the root
    	int k = 0;
    	for(int i = 0; i < inorder.length; i++){
    		if(inorder[i] == rootValue){
    			k = i;
    			break;
    		}
    	}

    	//because k is not the length, it it need to -(inStart + 1) to get the length
    	//the start and the end are the left sub tree
    	root.left = buildTree(inorder, inStart, k -1, postorder, postStart, postStart+k-(inStart + 1));
    	//postStart + k - inStart = postStart + k - (inStart + 1) + 1 this the postStart for right sub tree
    	root.right = buildTree(inorder, k + 1, inEnd, postorder, postStart + k - inStart, postEnd -1);

    	return root;

    }
}