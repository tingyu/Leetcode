/*
Remove Duplicates from Sorted List 
Total Accepted: 10728 Total Submissions: 31568 My Submissions
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

//这里delete一个node，只需要把前一个结点的指针指向下一个就可以了。不需要把要去掉的node.next = null
public class Solution{
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) return head;

		ListNode node = head;

		while(node != null){
			ListNode tmp = node.next;
			while(tmp !=null && node.val == tmp.val){
				tmp = tmp.next;
			}
			node.next = tmp;
			node = node.next;
		}
		return head;
	}
}

//这种解法用更少的变量
public class Solution{
	public ListNode deleteDuplicates(ListNode head){
		if(head ==null || head.next ==null)
			return head;

		ListNode p = head;

		while(p!=null && p.next !=null){
			if(p.val ==p.next.val){
				p.next = p.next.next;
			}else{
				p = p.next;
			}
		}
		return head;
	}
}


//my second try

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode node = head;
        
        while(node!=null&& node.next !=null){
            while(node.next !=null && node.next.val == node.val){
           //     if(node.next.next !=null){
                   node.next = node.next.next;
          //      }else{
          //          node.next = null;
         //       }
            }
              node = node.next;  
        
        }
        return head;
    }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next ==null) return head;
        ListNode node = head;
        
        while(node!=null&& node.next !=null){
            if(node.next.val == node.val){
                   node.next = node.next.next;
            }else{
                  node = node.next;   
            }
        }
        return head;
    }
}

//my solution
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            while(cur != null && pre.val == cur.val){
                ListNode temp = cur.next;
                pre.next = temp;
                cur.next = null;
                cur = temp;
            }
            pre = cur;
            if(cur == null || cur.next == null) return head;
            cur = cur.next;
        }
        
        return head;
    }
}