/**
Rotate List

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

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
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null || head.next ==null || n ==0) return head;
        ListNode p = head;
        ListNode p1 = head;
        int count = 1, right = 0, left = 0; 
        
        //count the number
        while(p.next!=null){
            p = p.next;
            count++;        
        }
        
        //combine two linked list together, copy
        p.next = head;
        
        right = n%count;


        left = count - right;
        
        while(left > 0){
            p = p.next;
            left--;
        }
        
        head = p.next;
        p.next = null;
        /* a failure solution
        if(right!=0){
            for(int i = 0; i < left; i++){
                head = head.next;
            }
            ListNode pNew = head;
            for(int i = 0; i < count; i++){
                pNew = pNew.next;
            }
            pNew = null;
        }*/
        return head;
    }
}