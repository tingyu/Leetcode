/**
LeetCode – Balanced Binary Tree (Java)
  
Given a binary tree, determine if it is height-balanced.

**/
/*
For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Thoughts

A typical recursive problem for solving tree problems.
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

//my solution
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return isBalanced(root.left)&&isBalanced(root.right)&&Math.abs(height(root.left) - height(root.right)) <=1;
    }
    
    private int height(TreeNode node){
        if(node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}



public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        if(root.left == null && root.right == null) return true;
        
        if(Math.abs(depth(root.left) - depth(root.right)) > 1){
            return false;
        }
        
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    public int depth(TreeNode root){
        if(node == null) return 0;

        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}


//CC150 上有这道题的另外一种解法。前面的解法因为isBalanced和height()都要对同一个subtree做recursion，所以重复了，其实可以在求
//height的时候一起做，如果不是balanced的话返回-1，否则就返回正常的高度。
//这样做下来
//This code runs in 0(N) time and 0(H) space, where H is the height of the tree.

public class Solution {
    private int checkHeight(TreeNode root){
        if(root == null) return 0;
        
        //check left height
        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1){ //Not balanced
            return -1;
        }
        
        //check right height
        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1){ //Not Balanced 
            return -1;
        }
        
        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1){
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if(checkHeight(root) == -1){
            return false;
        }else{
            return true;
        }
    } 
}

//可以更为简化下代码，如下
 public class Solution{
 	public boolean isBalanced(TreeNode root) {
 		if(root == null)
 			return true;
 		if(getHeight(root) == -1)
 			return false;
 		return true;
 	}

 	public int getHeight(TreeNode root){
 		if(root == null)
 			return 0;

 		int left = getHeight(root.left);
 		int right = getHeight(root.right);

 		if(left == -1 || right == -1)
 			return -1;

 		if(Math.abs(left - right) > 1){
 			return -1;
 		}

 		return Math.max(left, right) + 1;
 	}
 }