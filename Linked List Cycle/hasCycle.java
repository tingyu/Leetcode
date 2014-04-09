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
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        do{
            if(fast!=null)
                fast = fast.next;
            if(fast!=null)
                fast = fast.next;
            if(fast==null)
                return false;
            slow = slow.next;
        }while(fast!=slow);
        
        return true;
    }
}