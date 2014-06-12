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
http://blog.csdn.net/u010500263/article/details/18128813
 Analysis:

Binary tree problem -> recursive approach.  
Key problem is how to store all the nodes in the same level into the same array list.  
After analyzing the approaches that are available in Internet, a "depth" parameter is needed for the recursive function 
in order to record the depth/level of node that is processing.  Also, a concision is need to be specified for 
creating the integer array list: if same depth, reuse the same integer array list; if new depth, use a new integer array list.

Another key of this solution is to define the resulting array list as the parameter for recursive function, 
so that this resulting array list can be updated time be time when recursive function repeatedly being called without newly created.

*/
public class Solution{
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root){
		ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
		helper(root, 0, tree); // use ArrayList as parameter so that recursive function can use it
		Collections.reverse(tree);
		return tree;
	}

	public void helper(TreeNode node, int depth, ArrayList<ArrayList<Integer>> tree){
		if(node == null) return; //exit

		ArrayList<Integer> leaf = null; //leaf array list may got from tree array list or newly created
		if(depth<tree.size()){
			leaf = tree.get(depth); //same depth, got from tree array list (current leaf)
		}else{
			leaf = new ArrayList<Integer>(); //new depth, create new array list for this depth
			tree.add(leaf);
		}
		leaf.add(node.val);	// add current node value to leaf array list

		//process next depth
		helper(node.left, depth + 1, tree);
		helper(node.right, depth + 1, tree);
	}
}