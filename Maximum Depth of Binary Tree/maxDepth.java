/**
Maximum Depth of Binary Tree Total Accepted: 11195 Total Submissions: 25373 My Submissions
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Have you been asked this question in an interview?  Yes
Discuss


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
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}