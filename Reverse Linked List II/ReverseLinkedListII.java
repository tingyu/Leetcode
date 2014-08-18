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
/*
采用类似于reverse Nodes in k-group里面的第二种解法就可以解决。注意以后
reverse linkedlist都采用此通用的解法
Analysis:
First consider the atomic operation in this problem: reverse several nodes.
How to reverse? Let's take an example, we have linked list 3->2->1->4->5->6->7
We wan to reverse 4->5->6 to 6->5->4, so we do the following:
(1) 3->2->1->4->5->6->7
          p        q
(2) 3->2->1----->5->6->4->7
          p         q
(3) 3->2->1--------->6->5->4->7
          p          q

The 1st step is to find the locations q and q, where we want to reverse from p->next to q.
Then while p->next != q,  we do:
     (1) move p->next to q->next
     (2) connect p->next to p->next->next
Note that, p and q are fixed.
*/

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode p = head;

        for(int i = 0; i < m-1; i++){
            p = p.next;
        }
        
        ListNode q = p;
        
        for(int i = 0; i < n -m + 1; i++){
            q = q.next;
        }
        
        while(p.next != q){
            ListNode l = p.next;
            ListNode k = q.next;
            p.next = p.next.next;
            q.next = l;
            l.next = k;
        }
        
        return head.next;
    }
}
//my solution
//这个解法是交换相应位置的数值，而不是移动链表指向。这种解法是错误的
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