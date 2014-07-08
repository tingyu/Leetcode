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
//my solution
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
        if(head == null || head.next == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){//所以说这样也是可以的
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}

//用do while,两个不等的时候不停的往后移动，如果相等的话就return true
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