/**

Remove Duplicates from Sorted List II
Total Accepted: 11103 Total Submissions: 45405

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Have you been asked this question in an interview?
*/

/*
Analysis:

To solve this problem, one pointer is needed to store the previous element. See example to get a better understanding.

p is the current element (now its value is 2 in e.g.), p2 is the next element after p, for each p, we check the elements after p (3 3 4), 

and find the non-duplicate position (4), and remove the duplicates(3->3).

Note. To handle the first is duplicate element, we put another node ahead the head.

e.g.
1 2 3 3 4 4 5
go through, 1 and 2,
                  1->2->3->3->4
                        p->p2             check if p2.val==p2.next.val
                        p->     p2        go to next element and check
                        p-------->      if all duplicates found, let p.next=p2.next, in order to remove the duplicates.
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
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		if(head == null|| head.next == null) return head;
		ListNode pre = new ListNode(0);
		pre.next = head;
		head = pre;

		ListNode p = head;

		while(p.next != null){
			ListNode p2 = p.next;
			while(p2.next !=null &&p2.val == p2.next.val){
				p2 = p2.next;
			}
			if(p2 != p.next){
				p.next = p2.next; //????????????????????????????????????????
			} else{
				p = p.next;
			}
		}	
		return head.next;
    }
}



public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		if(head == null|| head.next == null) return head;

		ListNode sentinel = new ListNode(0);
		sentinel.next = head;
		ListNode current = head;
		ListNode previous = sentinel;

		while(current != null){
			boolean dup = false;
			while(current.next !=null && current.next.val == current.val){
				current = current.next;
				dup = true;
			}

			//remove all dup
			if(dup){
				previous.next = current.next;
			} else{ //set the previous pointer
				previous = current;
			}
			current = current.next;
		}
		return sentinel.next;
    }
}