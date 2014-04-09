/*
Populating Next Right Pointers in Each Node Total Accepted: 9878 Total Submissions: 29017 My Submissions
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
Have you been asked this question in an interview? 
*/


/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        //空节点就直接返回
        if(root == null){
        	return;
        }

        //左节点非空，链接右节点
        if(root.left != null){
        	root.left.next = root.right;
        }

        //借助root.next处理5连6的情况
        if(root.right !=null && root.next !=null){
        	root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
    }
}