/**
Recover Binary Search Tree 

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/

/*
Analysis: http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
The question requires O(1) space, let's first consider the case without this.
How to traverse BST?
Yep! The inOrder tree traversal.  (left->root->right)
So the easiest way is inorder traverse the BST and find the element pair (two elements) 
which are not consistent with the definition of BST. In order to get the order, a queue is needed, which is O(n).

Now how to do this procedure in O(1)?
What we need is actually two pointers, which point to 2 tree nodes where is incorrect. 
Therefore, we only need to store these two pointers, and, we also need another pointer to 
store the previous element, in order to  compare if the current element is valid or not.

In the code below, you will find, the last step is to replace the wrong pair's value. 
And the inOrder function is to search the whole BST and find the wrong pairs.

Note that, (1)the previous element is NOT the root node of the current element but 
the previous element in the "inOrder" order; (2) To store the wrong pair, 
the first found wrong element is stored in first pointer, while the next is stored in the second pointer.

e.g. The correct BST is below:

The inorder traversal is :  1 3 4 6 7 8 10 13 14

If we change the value 4 and 8:  1 3 8 6 7 4 10 13 14, when it goes to the node 6, 
now the pre->val = 8, check if pre<current node, which is wrong here (8>6). 
So we store the first pointer pointing to the node 6 and second pointer pointing to 
the node 8. To do so, we have stored the wrong nodes, if every other node keep the correct order, 
then swapping these nodes is enough for the problem. In other words, after the whole traversal, 
what we need to do is just changing the values of the first and second. Continue our example here, 
when the traversal goes to the node 4, now the node 7 is its pre, which is also wrong, 
so the second wrong node is found, and we change the second pointer pointing to node 4.
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
	TreeNode p, q;
 	TreeNode pre;
    public void recoverTree(TreeNode root) {
    	if(root == null) return;
    	getWrong(root);
    	int tmp = p.val;
    	p.val = q.val;
    	q.val = tmp;
    }

    //inorder traveral, p is left wrong node, q is the right wrong node
    public void getWrong(TreeNode root){
    	if(root == null) return;
    	getWrong(root.left);
    	//compare the current one and the previous one
    	if(pre != null && root.val < pre.val){
    		if(p == null){
    			p = pre;
    			q = root;
    		}else{
    			q = root;
    		}
    	}
    	pre = root;
    	getWrong(root.right);
    }
}