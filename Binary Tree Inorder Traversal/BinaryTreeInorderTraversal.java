/*Binary Tree Inorder Traversal Total Accepted: 11779 Total Submissions: 34483 My Submissions
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/
//http://gongxuns.blogspot.com/2012/12/leetcodebinary-tree-inorder-traversal.html
// Solution 1: Use a stack. Time:O(n) and space O(logn). 
//当前不是空的时候push,然后不停的找左边，然后左边都空了之后才pop,add,然后搜索右边。思路很直观。。。。
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(!stack.isEmpty() || cur!= null){
            if(cur!= null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}

//Solution 2: Morris algorithm. Time: O(nlogn) but space O(1). 

public class Solution {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur=root,next=null;
        while(cur!=null){
            if(cur.left!=null){
                next=cur;
                cur=cur.left;
            
                TreeNode temp=cur;
                while(temp.right!=null && temp.right!=next){
                    temp=temp.right;
                }    
            
                if(temp.right==null){
                    temp.right=next;
                }else{
                    temp.right=null;
                    res.add(next.val);
                    cur=next.right;
                }
            }else{
                res.add(cur.val);
                cur=cur.right;
            }
        }
        return res;
    }

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
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
    	ArrayList<Integer> ret = new ArrayList<Integer>;
    	if(root == null){
    		return ret;
    	}

    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode cur = root;
    	while(true){
    		while(cur !=null){
    			stack.push(cur);
    			cur = cur.left;
    		}
    		if(stack.isEmpty()){
    			break;
    		}
    		cur =stack.pop();
    		ret.add(cur.val);
    		cur = cur.right;
    	}
    	return ret;
    }
}

//a recursion solution
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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;//这个是可以省略的
        
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode root, ArrayList<Integer> res){
        if(root == null) return;
        
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}

//my solution:
//why the solution is wrong??????
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//如果是这样写的话，push进去的肯定又马上被pop出来了
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            if(curr.right != null){
                stack.push(curr.right); 
            }
            res.add(stack.pop().val);
            if(curr.left != null){
                stack.push(curr.left);   
            }
        }
        return res;
    }
}