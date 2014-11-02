/**
Linked List Cycle 
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/
//my solution
//1. 一定要注意的是要在fast和slow移动了之后再判断fast == slow。不然会出现很明显的错误。
//！！！！！这样错了是必须的啊。因为刚开始的时候两个都指向的head啊啊
//2. 另外一个要注意的时
/*
只要判断在后面
while(fast != null && fast.next != null)或while(fast.next != null && fast.next.next != null)
都是可以的*/
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

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}
/*
a wrong solution

public class Solution {
    public boolean hasCycle(ListNode head) {        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null){
            if(fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}

Submission Result: Runtime Error

Runtime Error Message:  Line 17: java.lang.NullPointerException
Last executed input:    {}, no cycle

another wrong solution

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null){
            if(fast == slow) return true;//为什么换个位置就错了！！！！！这样错了是必须的啊。因为刚开始的时候两个都指向的head啊啊
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}

Submission Result: Wrong Answer

Input:  {-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5}, no cycle
Output: true
Expected:   false


//another wrong one
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast!= null && fast.next != null){
            if(fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}

Submission Result: Wrong Answer

Input:  {1,2}, no cycle
Output: true
Expected:   false
*/

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