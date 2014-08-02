/**
Reverse Linked List II

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list. 
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
//my solution
//这个解法是交换相应位置的数值，而不是移动链表指向
//408 ms
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fast = head, slow = head;
        int count = 1, countFast= n;
        
        while(slow != null){
  
            if(count >= m){
                if(countFast > count){
                    fast = slow;
                    for(int i = 0; i < countFast - count; i++){
                        fast = fast.next;
                    }
                    int tmp = slow.val;
                    slow.val = fast.val;
                    fast.val = tmp;

                    countFast--;  
                }
            }
            slow = slow.next;
            count++;
        }
        return head;
    }
}