/**

Linked List Cycle II
Total Accepted: 12166 Total Submissions: 39759

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

Have you been asked this question in an interview?

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
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next ==null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        //find the first encounter point
        while(fast !=null && fast.next !=null && slow !=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                break;
        }
        
        //no cycle
        if(fast != slow){
            return null;
        }
        
        slow = head;
        while(fast!=null && slow !=null){
            //check first
            if(fast == slow){
                break;
            }
            
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}