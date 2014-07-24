/**
Binary Tree Level Order Traversal 

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
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
    //public List<List<Integer>> levelOrder(TreeNode root) {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> rs = new ArrayList<ArrayList<Integer>>();
        if(root == null) return rs;
        helper(root, rs, 1);//注意这里是1
        return rs;
    }

    public void helper(TreeNode node, ArrayList<ArrayList<Integer>> rs, int depth){
    	if(node == null) return;
    	ArrayList<Integer> tmp = new ArrayList<Integer>();//这个是可以放在这个里面的，因为tmp每次都被添加进了rs中

    	//check if this level has already exist
    	if(rs.size() < depth) rs.add(tmp);//not exist, then add tmp to rs
    	else tmp = rs.get(depth -1); //get tmp from rs to update

    	tmp.add(node.val); //add node to current depthht

    	helper(node.left, rs, depth + 1);
    	helper(node.right, rs, depth + 1);
    }
}

//use queue
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.offer(root);
        int leftCount = 0;
        while(!queue.isEmpty()){
            if(leftCount == 0){
                list = new ArrayList<Integer>();
                res.add(list);
                leftCount = queue.size();
            }
            TreeNode node = queue.poll();
            list.add(node.val);
            leftCount--;
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return res;
    }
}

//Line 15: error: Queue is abstract; cannot be instantiated
//这里使用queue.offer(root);和add都是没有区别的
//可以先把一个空的list加到res中，然后再修改这个空的List,res中的内容会随之发生变化
/*
居然要层次遍历，那么我们如果一层层放入到queue中，然后取出这一层结点，并把值放入到一个ArrayList<Integer>中，
并加入到最终集合list中，接着我们把这一层的孩子结点，即下一层的结点再放入到queue中，这样直到queue为empty的时候，
我们便把所有层次都遍历完毕了,这样子我们将 list 进行翻转，便得到了题目想要的结果了！！
*/