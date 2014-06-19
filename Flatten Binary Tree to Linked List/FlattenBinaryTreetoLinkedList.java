/**
Flatten Binary Tree to Linked List 

Flatten Binary Tree to Linked List Total Accepted: 14309 Total Submissions: 52519 My Submissions
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
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
 

 //see discussion

/*
solution 1:
utilize custom preorder recursion
storing the last visited pre-order traversal node in a static "lastVisited" TreeNode, and re-assigning its children
But, It's not good to use recursive approach, as it's not in-place.

recursion solution
*/
public class Solution {
    private static TreeNode lastVisited = null;

    public void flatten(TreeNode root) {
        if(root == null) return;
        flattenHelper(root);
    }
    
    public void flattenHelper(TreeNode node){
        if(node == null) return;
        
        //save the right node
        TreeNode savedRight = node.right;

        //resign the child. The new tree has only right node.
        if(lastVisited != null){
            lastVisited.left = null;
            lastVisited.right = node;
        }
        //move to the right node
        lastVisited = node;
        
        flattenHelper(node.left);
        
        flattenHelper(savedRight);
    }
}

//solution 2:
//http://www.programcreek.com/2013/01/leetcode-flatten-binary-tree-to-linked-list/
//Go down through the left, when right is not null, push right to stack.
//Iterative Code: DFS or pre-order traversal
//如果碰到右结点，放到stack中
//如果碰到左结点，把左子树放到p的右子树，把p的左子树置为空
//如果左子树处理完了，把右子树attach到后面
public class Solution {
    public void flatten(TreeNode root) {
 		Stack<TreeNode> stack = new Stack<TreeNode>();
 		TreeNode p = root;//链表里面经常这样做。。

 		while(p != null || !stack.empty()){
 			//push right node
 			if(p.right != null){
 				stack.push(p.right);
 			}

 			//move left sub-tree to right, leave left subtree empty
 			if(p.left != null){
 				p.right = p.left;
 				p.left = null;
 			}else if(!stack.empty()){//all left are done, now attch right node to p
 				TreeNode temp = stack.pop();
 				p.right = temp;
 			}
 			p = p.right;
 		}
    }
}

