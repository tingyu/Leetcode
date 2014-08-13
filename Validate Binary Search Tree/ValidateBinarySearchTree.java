/**
Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

*/


//上来先Brute force吧，没啥好说的。检查每个node的左子树的所有node都小于这个node，右子树的所有node都大于这个node。
//对于每个节点都要检查sSubTreeLessThan(root.left, root.val)&&isSubTreeGreaterThan(root.right, root.val)&&isValidBST(root.left)&&isValidBST(root.right);
public class Solution {
	public static boolean isValidBST(TreeNode root) {
	    if(root == null) return true;
	    return isSubTreeLessThan(root.left, root.val)&&isSubTreeGreaterThan(root.right, root.val)&&isValidBST(root.left)&&isValidBST(root.right);
	}
	
    private static boolean isSubTreeLessThan(TreeNode node, int max){
        if(node == null) return true;
        return node.val<max&&isSubTreeLessThan(node.left, max)&&isSubTreeLessThan(node.right, max);
    }
    
    private static boolean isSubTreeGreaterThan(TreeNode node, int min){
        if(node == null) return true;
        return node.val>min&&isSubTreeGreaterThan(node.left, min)&&isSubTreeGreaterThan(node.right, min);
    }
}


/*
http://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
Thoughts about This Problem

All values on the left sub tree must be less than root, and all values on the right sub tree must be greater than root.

many other solutions refer:
http://blog.csdn.net/likecool21/article/details/23271621

改进一下。想到了AI课上学的alpha-beta pruning。一棵树的根为a，右子树为b，那么右子树的左儿子c的值一定是大于a且小于b。这样coding就好办了

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
 
	public static boolean isValidBST(TreeNode root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
 
	public static boolean validate(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
 
		// not in range
		if (root.val <= min || root.val >= max) {
			return false;
		}
 
		// left subtree must be < root.val && right subtree must be > root.val
		return validate(root.left, min, root.val) && validate(root.right, root.val, max);
	}
}


public class Solution {  
    public boolean isValidBST(TreeNode root) {  
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);  
          
    }  
      
    public boolean isBST(TreeNode node, int alpha, int beta){  
        if(node == null){  
            return true;  
        }  
        if(alpha < node.val && node.val < beta){  
            return isBST(node.left, alpha, node.val) && isBST(node.right, node.val, beta);  
        }  
        else  
            return false;  
    }        
}  

/*
还有一种方法：如果一棵树是BST，那么如果做一个in order traversal的话产生的数组应该是排好序的。
这样就一边进行in order traversal,一边比较当前值是不是比前一个值大就行了。这里用了个static变量来记录之前的值，使其在递归时能被记住。
如果用C++的话按引用传递就不需要static了。
*/
public class Solution {  
    int previous = Integer.MIN_VALUE;  
    public boolean isValidBST(TreeNode root) {  
        if(root == null)  
            return true;  
        //the left sub tree  
        if(isValidBST(root.left) == false)  
            return false;  
            
        //the current node  
        if(root.val <= previous)  
            return false;  
        previous = root.val;  
      
          
        //the right subtree  
        if(isValidBST(root.right) == false){  
            return false;  
        }  
        return true;  
    }  
}  
/*这种解法为什么会出错？？？因为之前previous之前用static
Submission Result: Wrong Answer

Input:	{0,-1}
Output:	false
Expected:	true
*/