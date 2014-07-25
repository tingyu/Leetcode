/**
Remove Nth Node From End of List

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.

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

//my solution 1  428 ms
//a custom solution: count the len, then remove the len-nth node, need to process the special case of removing head.
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode p = head;
        int len = 1;
        
        while(p.next!=null){
            p = p.next;
            len++;
        }
        
        p = head;
        if(len == n){
            head = p.next;
        }else{
            for(int i = 0; i < len - n-1; i++){
                p = p.next;
            }
            //if(p.next != null){
                p.next = p.next.next;
            //}else{
            //    return null;
            //} 
        }

        
        return head;
    }
}

//another similar solution, using fakeHead
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        ListNode p = head;
        int count = 0;
        while(p!= null){
            count++;
            p = p.next;
        }
        
        
        int k = count -n;
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        head = fakeHead;
        p = head;
        for(int i = 0; i < k; i++){
            p = p.next;
        }
        
            p.next = p.next.next;    

        return head.next;
    }
}

//my solution 2 : One pass solution   420 ms
//经典题。双指针，一个指针先走n步，然后两个同步走，直到第一个走到终点，第二个指针就是需要删除的节点。唯一要注意的就是头节点的处理，比如，
//1->2->NULL, n =2; 这时，要删除的就是头节点。
 public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        if(fast == null){
            head = slow.next;
        }else{
            while(fast.next!=null){
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;  
        }

        return head;
    }
 }


