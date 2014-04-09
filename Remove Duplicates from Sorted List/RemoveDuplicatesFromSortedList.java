/*
Remove Duplicates from Sorted List 
Total Accepted: 10728 Total Submissions: 31568 My Submissions
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/
public class Solution{
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) return head;

		ListNode node = head;

		while(node != null){
			ListNode tmp = node.next;
			while(tmp !=null && node.val == tmp.val){
				tmp = tmp.next;
			}
			node.next = tmp;
			node = node.next;
		}
		return head;
	}
}


public class Solution{
	public ListNode deleteDuplicates(ListNode head){
		if(head ==null || head.next ==null)
			return head;

		ListNode p = head;

		while(p!=null && p.next !=null){
			if(p.val ==p.next.val){
				p.next = p.next.next;
			}else{
				p = p.next;
			}
		}
		return head;
	}
}