/*
Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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
//my solution:
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode preHead = new ListNode(-1);
        ListNode p = preHead;
        
        if(l1 == null){
            p.next = l2;
        }
        
        if(l2 == null){
            p.next = l1;
        }
        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if(l1 != null){
            p.next = l1;
        }
        
        if(l2 != null){
            p.next = l2;
        }
        
        return preHead.next;
    }
}


 public class Solution{
 	public ListNode mergeTwoLists(ListNode l1, ListNode l2){
 		ListNode p1 = l1;
 		ListNode p2 = l2;

 		ListNode fakeHead = new ListNode(0);
 		ListNode p = fakeHead;

 		while(p1 != null && p2 !=null){
 			if(p1.val <= p2.val){
 				p.next = p1;
 				p1 = p1.next;
 			}else{
 				p.next = p2;
 				p2 = p2.next;
 			}

 			p = p.next;
 		}

 		if(p1 != null)
 			p.next = p1;
 		if(p2 != null)
 			p.next = p2;

 		return fakeHead.next;
 	}
 }