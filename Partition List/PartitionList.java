/**
Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 
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
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next ==null) return head;
        
        ListNode fakeHead1 = new ListNode(-1);
        ListNode fakeHead2 = new ListNode(-1);

        ListNode p1 = fakeHead1;
        ListNode p2 = fakeHead2;
        ListNode p = head;
        
        while(p != null){
            if(p.val < x){
                p1.next = new ListNode(p.val);
                p1 = p1.next;
            } else{
                p2.next = new ListNode(p.val);
                p2 = p2.next;
            }
            p = p.next;
        }

        if(fakeHead1.next!=null&&fakeHead2.next!=null){
            fakeHead1 = fakeHead1.next;
            fakeHead2 = fakeHead2.next;
            p1.next = fakeHead2;
        } else if(fakeHead1.next==null){
            return fakeHead2.next;
        } else if(fakeHead2.next ==null){
            return fakeHead1.next;
        }
        
        return fakeHead1;
    }
}