/*
Binary Tree Preorder Traversal Total Accepted: 11974 Total Submissions: 35077 My Submissions
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<Integer>();
        if(root == null) return tree;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(stack.size() > 0){
            TreeNode curr = stack.pop();
            tree.add(curr.val);
            
            if(curr.right != null){
                stack.push(curr.right);
            }
            
            if(curr.left != null){
                stack.push(curr.left);
            }
        }
        return tree;
    }
}

//my recursion solution
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<Integer>();
        if(root == null) return tree;
        
        helper(tree, root);
        return tree;
    }
    
    public void helper(ArrayList<Integer> tree, TreeNode node){
        if(node == null) return;
        
        tree.add(node.val);
        
        helper(tree, node.left);
        
        helper(tree, node.right);
    }
}