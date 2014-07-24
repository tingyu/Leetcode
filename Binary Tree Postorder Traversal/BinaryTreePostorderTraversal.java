/**
Binary Tree Postorder Traversal

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/

//http://lei.case.edu/wordpress/2013/12/27/leetcode-preinpost-order-traversal-of-binary-tree/

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> lst = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null){
            if(p != null){
                lst.add(p.val);
                stack.push(p);
                p = p.right;
            }else{
                p = stack.pop();
                p = p.left;
            }
        }
        Collections.reverse(lst);
        return lst;
    }
}
/*
http://www.programcreek.com/2012/12/leetcode-solution-of-iterative-binary-tree-postorder-traversal-in-java/
The key to to iterative postorder traversal is the following:

    The order of "Postorder" is: left child -> right child -> parent node.
    Find the relation between the previously visited node and the current node
    Use a stack to track nodes

As we go down the tree, check the previously visited node. If it is the parent of the current node, 
we should add current node to stack. When there is no children for current node, pop it from stack. 
Then the previous node become to be under the current node for next loop.
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
    public List<Integer> postorderTraversal(TreeNode root) {
    	ArrayList<Integer> lst = new ArrayList<Integer>();

    	if(root ==null) return lst;

    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);

    	TreeNode prev = null;
    	while(!stack.empty()){
    		TreeNode curr = stack.peek();

    		//go down the tree
    		//check if current node is leaf, if so, process it and pop stack, otherwise, keep going down
    		if(prev ==null || prev.left == curr || prev.right == curr){
    			//prev == null is the situation for the root node
    			if(curr.left !=null){
    				stack.push(curr.left);
    			}else if(curr.right != null){
    				stack.push(curr.right);
    			}else{
    				stack.pop();
    				lst.add(curr.val);
    			}
    		}else if(curr.left == prev){
    			//go up the tree from left node, need to check if there is a right child, if yes, 
                //push it to stack, otherwise, process parent and pop stack
    			if(cur.right !=null){
    				stack.push(curr.right);
    			}else{
    				stack.pop();
    				lst.add(curr.val);
    			}
    		}else if(curr.right == prev){
    			stack.pop();
    			lst.add(curr.val);
    		}

    		prev = curr;
    	}
    	return lst;
    }
}

/*http://blog.csdn.net/u010500263/article/details/18297155
By using recursive approach, it can be solved easily.
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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {  
 		ArrayList<Integer> tree = new ArrayList<Integer>();
 		if(root == null) return tree;

 		helper(tree, root);
 		return tree;
    }

    public void helper(ArrayList<Integer> tree, TreeNode node){
    	//exit
    	if(node == null) return;

    	//left sub-tree
    	helper(tree, node.left);

    	//right sub-tree
    	helper(tree, node.right);

    	//root
    	tree.add(node.val);
    	return;
    }
}

/*        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return null;
如果是这样写那么就是错的
Submission Result: Wrong Answer

Input:  {}
Output: null
Expected:   []
注意一定要返回res，而不是null。返回的是空，而不是null
*/

/*
Using iteration approach is much more difficult (for me) to get the idea.  
In order to get the idea, we can try an example of binary tree for post-order traversal manually, 
record down the path, and mark down in which step should we store the tree node.

Here are some keep points of iteration approach:

1. (As usual) Utilize Stack to store un-processed tree nodes. 


2. Divided each each tree node process into 3 cases (according to the traversal path we drew previously):

2.1. Previous node from current node's parent 

2.1.1. if current node has left child, process left subtree.

2.1.2. if current node has no left child but right child, process right subtree. 
(current child has both left child and right is the case of 2.1.1. <- keep processing the left subtree)

2.1.3. if current has no left or right child (leaf), add the value of current tree node to result set and remove this node from stack, 
which means this node has been processed.


2.2. Previous node from current node's left child (left subtree has been processed)

2.2.1. if there the current node has right child, process the right subtree.

2.2.2. if no right child, add value of current node into result set and remove it from stack (current node processed).


2.3. Previous node from current node's right child (both left subtree and right subtree has been processed). <- add current node into result set and remove it from stack (current node processed).


Note that the order of postorder traversal is left subtree->right subtree -> tree
*/


//my solution 
//why it is not right?????????
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null) return tree;
        
        stack.push(root);
        while(stack.size() > 0){
            TreeNode curr = stack.peek();
            
            TreeNode n = curr.right;
            if(n !=null) stack.push(n);
            
            n = curr.left;
            if(n !=null) stack.push(n);
            
            tree.add(stack.pop().val);
        }
        
        return tree;
    }
}
*/