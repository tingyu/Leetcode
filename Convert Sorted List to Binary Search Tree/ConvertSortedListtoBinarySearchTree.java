/**
Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


*/

/*
Thoughts

If you are given an array, the problem is quite straightforward. 
But things get a little more complicated when you have a singly linked list instead of an array. 
Now you no longer have random access to an element in O(1) time. 
Therefore, you need to create nodes bottom-up, and assign them to its parents. 
The bottom-up approach enables us to access the list in its order at the same time as creating nodes.

http://joycelearning.blogspot.com/2013/09/leetcode-convert-sorted-list-to-binary.html

Naive Solution:
A naive way is to apply the previous solution directly. In each recursive call, you would have to 
traverse half of the list’s length to find the middle element. The run time complexity is clearly 
O(N lg N), where N is the total number of elements in the list. This is because each level of recursive 
call requires a total of N/2 traversal steps in the list, and there are a total of lg N number of levels 
(ie, the height of the balanced tree).

Hint:
How about inserting nodes following the list’s order? If we can achieve this, we no longer need to find 
the middle element, as we are able to traverse the list while inserting nodes to the tree.

Best Solution:
As usual, the best solution requires you to think from another perspective. In other words, we no longer 
create nodes in the tree using the top-down approach. We create nodes bottom-up, and assign them to its parents.
 The bottom-up approach enables us to access the list in its order while creating nodes.

Isn’t the bottom-up approach neat? Each time you are stucked with the top-down approach, give bottom-up a try.
 Although bottom-up approach is not the most natural way we think, it is extremely helpful in some cases. 
 However, you should prefer top-down instead of bottom-up in general, since the latter is more difficult 
 to verify in correctness.

Below is the code for converting a singly linked list to a balanced BST. Please note that the algorithm 
requires the list’s length to be passed in as the function’s parameters. The list’s length could be found 
in O(N) time by traversing the entire list’s once. The recursive calls traverse the list and create tree’s 
nodes by the list’s order, which also takes O(N) time. Therefore, the overall run time complexity is still O(N).

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
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
	static ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null) return null;

    	this.head = head;
    	int len = getLength(head);
    	return sortedListToBST(0, len -1);
    }

    //get list length
    public int getLength(ListNode head){
    	int len = 0;
    	ListNode p = head;

    	while(p != null){
    		len++;
    		p = p.next;
    	}
    	return len;
    }

    //build tree bottom-up
    public TreeNode sortedListToBST(int start, int end){
    	if(start > end){
    		return null;
    	}

    	//mid
    	int mid = (start + end)/2;

    	//build left sub tree
    	TreeNode left = sortedListToBST(start, mid -1);
    	//build root node
    	TreeNode root = new TreeNode(head.val);
    	root.left = left;
    	//move to next node to build right sub tree
    	head = head.next;
    	root.right = sortedListToBST(mid + 1, end);

    	return root;
    }
    
}

/*
Do we need to use ceiling function to get the middle value?
If the list is 1 -> 2 -> 3 -> 4 -> 5 -> 6, then the solution you provide would first get 3 not 4. 
(Using 4 as root would generate a good-looking tree).
*/