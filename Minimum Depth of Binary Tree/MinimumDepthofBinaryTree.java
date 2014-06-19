/**
Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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

//recursion solution
//1. 类似于Maximum Depth of Binary Tree，是左右最小的recursion，不过存在特殊情况就是左或右为空时
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        
        if(root.left == null && root.right == null) return 1;
        
        if(root.left == null){
            return 1 + minDepth(root.right);
        }else if(root.right == null){
            return 1 + minDepth(root.left);
        }else{
            return 1+ Math.min(minDepth(root.right), minDepth(root.left));
        }
    }
}


//another solution: http://www.programcreek.com/2013/02/leetcode-minimum-depth-of-binary-tree-java/
public class Solution {
    public int minDepth(TreeNode root) {
 		if(root == null) return 0;

 		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
 		LinkedList<Integer> counts = new LinkedList<Integer>();

 		nodes.add(root);
 		counts.add(1);

 		while(!nodes.isEmpty()){
 			TreeNode curr = nodes.remove();
 			int count = counts.remove();

 			if(curr.left != null){
 				nodes.add(curr.left);
 				counts.add(count + 1);
 			}

 			if(curr.right != null){
 				nodes.add(curr.right);
 				counts.add(count + 1);
 			}

 			if(curr.left == null && curr.right == null){
 				return count;
 			}
 		}
 		return 0;
    }
}


//http://gongxuns.blogspot.com/2012/12/leetcode-minimum-depth-of-binary-tree.html
public class Solution {
    public int minDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
       if(root==null) return 0;
       
       ArrayList<TreeNode> last =new ArrayList<TreeNode>();
       last.add(root);
       int count=1;
       while(!last.isEmpty()){           
        ArrayList<TreeNode> curr = new ArrayList<TreeNode>();
        for(TreeNode n:last){
           if(n.left==null && n.right==null) return count;
           if(n.left!=null) curr.add(n.left);
           if(n.right!=null) curr.add(n.right);
        }
        count++;
        last=new ArrayList<TreeNode>(curr);
       }
       return count;
    }
}