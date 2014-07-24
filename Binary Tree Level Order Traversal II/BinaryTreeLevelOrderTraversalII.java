/**
Binary Tree Level Order Traversal II 
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/
/*
1. 和I完全一模一样，结束之后Collections.reverse(res);
2. 自己写reverse函数        for(int i = rs.size() -1; i >=0; i--){
            tmp = (ArrayList<Integer>)rs.get(i).clone();
            rev.add(tmp);
        }
3. queue. 之后Collections.reverse(res);
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
//Runtime: 480 ms
public class Solution {
    //public List<List<Integer>> levelOrderBottom(TreeNode root) {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> rs = new ArrayList<ArrayList<Integer>>();
        if(root == null) return rs;
        helper(root, rs, 1);
        rs = reverse(rs);
        return rs;
    }
    
    public void helper(TreeNode node, ArrayList<ArrayList<Integer>> rs, int depth){
        if(node == null) return;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        
        if(rs.size() < depth) rs.add(tmp);
        else tmp = rs.get(depth - 1);
        
        tmp.add(node.val);
        
        helper(node.left, rs, depth + 1);
        helper(node.right, rs, depth + 1);
    }
    
    public ArrayList<ArrayList<Integer>> reverse(ArrayList<ArrayList<Integer>> rs){
        ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        
        for(int i = rs.size() -1; i >=0; i--){
            tmp = (ArrayList<Integer>)rs.get(i).clone();
            rev.add(tmp);
        }
        return rev;
    }
}

//another my solution, inspired by the next mentioned method
//key: use  Collections.reverse(rs);
public class Solution {
    //public List<List<Integer>> levelOrderBottom(TreeNode root) {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> rs = new ArrayList<ArrayList<Integer>>();
        if(root == null) return rs;
        helper(root, rs, 1);
        //rs = reverse(rs);
        Collections.reverse(rs);
        return rs;
    }
    
    public void helper(TreeNode node, ArrayList<ArrayList<Integer>> rs, int depth){
        if(node == null) return;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        
        if(rs.size() < depth) rs.add(tmp);
        else tmp = rs.get(depth - 1);
        
        tmp.add(node.val);
        
        helper(node.left, rs, depth + 1);
        helper(node.right, rs, depth + 1);
    }
}
/*
http://blog.csdn.net/u010500263/article/details/18128813
 Analysis:

Binary tree problem -> recursive approach.  
Key problem is how to store all the nodes in the same level into the same array list.  
After analyzing the approaches that are available in Internet, a "depth" parameter is needed for the recursive function 
in order to record the depth/level of node that is processing.  Also, a concision is need to be specified for 
creating the integer array list: if same depth, reuse the same integer array list; if new depth, use a new integer array list.

Another key of this solution is to define the resulting array list as the parameter for recursive function, 
so that this resulting array list can be updated time be time when recursive function repeatedly 
being called without newly created.

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



//queue
/*
http://blog.csdn.net/ljphhj/article/details/22428939
居然要层次遍历，那么我们如果一层层放入到queue中，然后取出这一层结点，并把值放入到一个ArrayList<Integer>中，
并加入到最终集合list中，接着我们把这一层的孩子结点，即下一层的结点再放入到queue中，
这样直到queue为empty的时候，我们便把所有层次都遍历完毕了,这样子我们将 list 进行翻转，便得到了题目想要的结果了！！
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()){
            if(count == 0){
                list = new ArrayList<Integer>();
                res.add(list);
                count = queue.size();
            }            
            TreeNode node = queue.poll();
            list.add(node.val);
            count--;
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        Collections.reverse(res);
        return res;
    }
}