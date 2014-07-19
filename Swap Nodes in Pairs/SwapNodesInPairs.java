/*
Swap Nodes in Pairs 
Total Accepted: 8993 Total Submissions: 28406 My Submissions
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution{
	public ListNode swapPairs(ListNode head){
		if(head == null || head.next == null) return head; //注意是return head,不是null
		//create fake header node
		ListNode fake = new ListNode(0);
		fake.next = head;

		ListNode pre = fake;
		ListNode cur = head;

		while(cur != null && cur.next != null){
			pre.next = cur.next;
			cur.next = cur.next.next;
			pre.next.next = cur;

			pre = cur;
			cur = pre.next;
		}
		return fake.next;
	}
}


public class Solution{
	public ListNode swapPairs(ListNode head){
		if(head == null || head.next == null) return head ;

		return swapSinglePairs(head);
	}

	public ListNode swapSinglePairs(ListNode node){
		if(node == null || node.next == null) return node;
		//stor next node for recursive call
		ListNode next = node.next.next;

		//swap node pair
		ListNode temp = node;
		node = node.next;
		node.next = temp;

		//swap next pair of nodes
		node.next.next = swapSinglePairs(next);

		//return header
		return node;
	}
}