/**
Rotate List

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

*/

/*
关于rotate自己想到了两种思路。都是先遍历一遍找到这个链表的长度，
一种方法是正好到尾部的时候把next结点指向head，形成一个循环链表circle, 然后再从
head开始遍历到len-n的前一个位置然后断开，更新head.
第二种方法是copy这个链表到尾部，然后选取相应的位置断开就可以

里面tricky的地方：
1. 遍历的长度不要计算错了
2. 可以在count长度的时候while(p.next != null){然后直接找到最后的一个点，将它的
next指向head.比较tricky，可以节省代码量
3. 一定要注意n可能大于len.开始就是忘了这一点然后出错了。需要更新下n 
n = n % len;
*/

public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null || head.next == null || n == 0) return head;
        ListNode p = head;
        int len = 1;
        while(p.next != null){
            len++;
            p = p.next;
        }
        p.next = head;
        
        p = head;
        n = n % len;
        //if(n == 0) return head;理论上加上这一句是可以节省时间的。但是不知道为什么加上就超时了
        for(int i = 0; i < len - n -1; i++){
            p = p.next;
        }
        
        head = p.next;
        p.next = null;
        
        return head;
    }
}

/*
    int count = 1;
        while(p.next!=null){
            p = p.next;
            count++;        
        }
    得到的count和
    int count = 0;
        while(p!=null){
            p = p.next;
            count++;        
        }
    得到的count相同
    不过不同的是一样p已经是null一个是最后一个元素
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