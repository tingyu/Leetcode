/**
Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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

/*
Solution: Divide and Conquer
According to the rule of preorder traversal,  the first item in the preorder array must be the root. 
and the question also told us "You may assume that duplicates do not exist in the tree." 
so we can go through inorder array find the root's position, then  we got left tree and right tree. 
finally we can apply recursion to got the tree we want base on above logic.

边界条件容易写错。一定要注意
怎么保证一定写对？？？
注意算长度，inorder的左右长度，左边的时index -inStart, 右边的是
inEnd - index。这样来算preoder里面的值

inorder相关的，永远都是inStart, inEnd, k+1, k-1
*/
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preStart = 0;
        int preEnd = preorder.length -1;
        int inStart = 0;
        int inEnd = inorder.length -1;
        
        return buildTree(preorder, preStart, preEnd, inorder, inStart, inEnd);
    }
    
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        
        int k = 0;
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == rootValue){
                k = i;
                break;
            }
        }
        
        root.left = buildTree(preorder, preStart + 1, preStart + 1 + k - (inStart + 1), inorder, inStart, k -1);
        
        root.right = buildTree(preorder, preStart + k -inStart + 1, preEnd, inorder, k + 1, inEnd);
        
        return root;
    }
}

//里面的for循环也可以改成 for(int i = inStart; i <= inEnd; i++){