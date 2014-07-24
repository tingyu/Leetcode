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

/*
http://fisherlei.blogspot.com/2013/11/leetcode-linked-list-cycle-ii-solution.html
首先，比较直观的是，先使用Linked List Cycle I的办法，判断是否有cycle。如果有，则从头遍历节点，对于每一个节点，查询是否在环里面，是个O(n^2)的法子。但是仔细想一想，发现这是个数学题。

如下图，假设linked list有环，环长Y，环以外的长度是X。

image

现在有两个指针，第一个指针，每走一次走一步，第二个指针每走一次走两步，如果他们走了t次之后相遇在K点

那么       指针一  走的路是      t = X + nY + K        ①

             指针二  走的路是     2t = X + mY+ K       ②          m,n为未知数

把等式一代入到等式二中, 有

2X + 2nY + 2K = X + mY + K

=>   X+K  =  (m-2n)Y    ③

这就清晰了，X和K的关系是基于Y互补的。等于说，两个指针相遇以后，再往下走X步就回到Cycle的起点了。这就可以有O(n)的实现了。

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

//其实  //no cycle的判断不要也是对的