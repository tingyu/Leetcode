/**
Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

*/

//my solution:
//use public void add(int index, E element) or public E set(int index, E element)
//Runtime: 468 ms
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
    //public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        
        helper(res, root, 1);
        return res;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> res, TreeNode node, int depth){
        if(node == null) return;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        
        if(res.size() < depth) res.add(tmp);
        else tmp = res.get(depth-1);
        
        if(depth %2 == 0){
            tmp.add(0, node.val);    
        }else{
            tmp.add(node.val);
        }
        
        helper(res, node.left, depth + 1);
        helper(res, node.right, depth + 1);
    }
}
