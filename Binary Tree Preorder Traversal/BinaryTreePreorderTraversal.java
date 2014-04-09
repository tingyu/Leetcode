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

public class Solution{
	public ArrayList<Integer> preorderTraversal(TreeNode root){
		ArrayList<Integer> ret = new ArrayList<Integer>();

		if(root == null)
			return ret;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while(!stack.empty()){
			TreeNode cur = stack.pop();
			ret.add(cur.val);

			if(cur.right!=null){
				stack.push(cur.right);
			}

			if(cur.left!=null){
				stack.push(cur.left);
			}
		}
		return ret;
	}
}