/**
Reorder List 

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

/*
Analysis:

Let's see some examples:

{1,2,3,4,5,6} ---> {1,6,2,5,3,4}
{1,2,3,4,5,6,7} ---> {1,7,2,6,3,5,4}

One straightforward middle step of such reordering is:
{1,2,3,4,5,6}  --> {1,2,3,6,5,4} --> {1,6,2,5,3,4}
{1,2,3,4,5,6,7}---> {1,2,3,4,7,6,5} ---> {1,7,2,6,3,5,4}

By reversing the last part of the linked list, we do not need to worried about the "parent" pointer anymore. The final step is just insert the each element in the last part into the first part (every two element).

So the algorithm implemented below can be summarized as:
Step 1  Find the middle pointer of the linked list (you can use the slow/fast pointers)
Step 2  Reverse the second part of the linked list (from middle->next to the end)
Step 3  Do the reordering. (inset every element in the second part in between the elements in the first part)
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
 		if(head == null || head.next == null) return;
 		ListNode p = head;
 		ListNode q = head;

 		//find the middle pointer
 		while(p.next && q.next.next){
 			p = p.next;
 			q = q.next.next;
 		}

 		//now p is middle pointer
 		//reverse p.next to end
 		q = p.next; //p.next point to end, reverse end
 		//??
 		while(q.next){
 			ListNode tmp = p.next;
 			p.next = q.next;
 			q.next = q.next.next;
 			p.next.next = tmp;
 		}

 		//reorder
 		q = head;
 		while(p!= q && p.next){
 			ListNode tmp = q.next;
 			q.next = p.next;
 			p.next = p.next.next;
 			q.next.next = tmp;
 			q = q.next.next;
 		}
 		return;
    }
}