/**
Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that 
adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

//my solution
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        boolean[] exist = {false};
        int calSum = 0;
        dfs(root, sum, calSum, exist);
        return exist[0];
    }
    
    private void dfs(TreeNode node, int sum, int calSum, boolean[] exist){
        if(node == null) return;
        calSum += node.val;
        if(node.left == null && node.right ==null){
        	//只要有一个true就好了
            if(calSum == sum){
                exist[0] = true;
                return;
            }
        }
        
        dfs(node.left, sum, calSum, exist);
        dfs(node.right, sum, calSum, exist);
    }
}

//other solutions
//http://gongxuns.blogspot.com/2012/12/leetcodepath-sum.html

/*
Add all node to a queue and store sum value of each node to another queue. 
When it is a leaf node, check the stored sum value.

For example above, the queue would be: 5 - 4 - 8 - 11 - 13 - 4 - 7 - 2 - 1. It will check node 13, 7, 2 and 1.

This is a typical breadth first search(BFS) problem.
非recursion的解法
*/

public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return false;

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		LinkedList<Integer> accSums = new LinkedList<Integer>();

		nodes.add(root);
		nodes.add(root.val);

		while(!nodes.isEmpty()){
			TreeNode curr = nodes.poll();
			int sumValue = accSums.poll();
			if(node.left == null || node.right == null && accSums == sum){
				return true;
			}

			if(node.left != null){
				nodes.add(node.left);
				accSums.add(accSums + node.left.val);
			}

			if(node.right != null){
				nodes.add(node.right);
				accSums.add(accSums + node.right.val);
			}
		} 
		return false;
    }
}