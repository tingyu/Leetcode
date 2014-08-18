/**
Reorder List 

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

/*
Analysis:

Let's see some examples:

{1,2,3,4,5,6} ---> {1,6,2,5,3,4}
{1,2,3,4,5,6,7} ---> {1,7,2,6,3,5,4}

One straightforward middle step of such reordering is:
{1,2,3,4,5,6}  --> {1,2,3,6,5,4} --> {1,6,2,5,3,4}
{1,2,3,4,5,6,7}---> {1,2,3,4,7,6,5} ---> {1,7,2,6,3,5,4}

By reversing the last part of the linked list, we do not need to worried about the "parent" pointer anymore. 
The final step is just insert the each element in the last part into the first part (every two element).

So the algorithm implemented below can be summarized as:
Step 1  Find the middle pointer of the linked list (you can use the slow/fast pointers)
Step 2  Reverse the second part of the linked list (from middle->next to the end)
Step 3  Do the reordering. (inset every element in the second part in between the elements in the first part)

while(fast.next != null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
一般都是这样处理fast和slow的。这样的话如果是
1->2->3->4->null 可以最后slow到2位置
1->2->3->4->5->null最后slow到3位置
始终保证了前半段>=后半段
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
http://blog.csdn.net/whuwangyi/article/details/14146461
题目思路比较直接：

1）把整个链表划分成2个等长的子链表，如果原链表长度为奇数，那么第一个子链表的长度多1。

2）翻转第二个子链表；

3）将两个子链表合并。

代码里有些变量可以省去，为了看起来逻辑清晰，还是保留了。整个链表遍历了3次，但是没有使用额外空间。
*/
//my solution
public class Solution {
	public static void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next!= null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
		//2 sublist heads
		ListNode head1= head, head2 = slow.next;
		//detach the two sublists;
		slow.next = null;
        
        //reverse the second sublist
        ListNode cur = head2, post = cur.next;
        cur.next = null;
        while(post != null){
           ListNode temp = post.next;
           post.next = cur;
           cur = post;
           post = temp;
        }
        head2 = cur; // the new head of the reversed sublist
        
        //merge the 2 sublists are required
        ListNode p = head1, q = head2;
        while(q != null){
            ListNode temp1 = p.next;
            ListNode temp2 = q.next;
            p.next = q;
            q.next = temp1;
            p = temp1;
            q = temp2;
        }
	}
}

public class Solution {
	public static void reorderList(ListNode head) {
		if(head == null || head.next == null)
			return;

		//partition the list into 2 sublists of equal length
		ListNode slow = head, fast = head;
		while(fast.next != null){
			fast = fast.next;
			if(fast.next != null){
				fast = fast.next;
			}else{
				break;
			}
			slow = slow.next;
		}

		//2 sublist heads
		ListNode head1= head, head2 = slow.next;
		//detach the two sublists;
		slow.next = null;

		//reverse the second sublist
		ListNode cur = head2, post = cur.next;
		cur.next = null;
		while(post != null){
			ListNode temp = post.next;
			post.next =cur;
			cur = post;
			post = temp;
		}
		head2 = cur; // the new head of the reversed sublist

		//merge the 2 sublists are required
		ListNode p = head1, q = head2;
		while(q!= null){
			ListNode temp1 = p.next;
			ListNode temp2 = q.next;
			p.next = q;
			q.next = temp1;
			p = temp1;
			q = temp2;
		}
	}
}

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
    public void reorderList(ListNode head) {
 		if(head == null || head.next == null) return;
 		ListNode p = head;
 		ListNode q = head;

 		//find the middle pointer
 		while(p.next && q.next.next){
 			p = p.next;
 			q = q.next.next;
 		}

 		//now p is middle pointer
 		//reverse p.next to end
 		q = p.next; //p.next point to end, reverse end
 		//??
 		while(q.next){
 			ListNode tmp = p.next;
 			p.next = q.next;
 			q.next = q.next.next;
 			p.next.next = tmp;
 		}

 		//reorder
 		q = head;
 		while(p!= q && p.next){
 			ListNode tmp = q.next;
 			q.next = p.next;
 			p.next = p.next.next;
 			q.next.next = tmp;
 			q = q.next.next;
 		}
 		return;
    }
}

//http://www.programcreek.com/2013/12/in-place-reorder-a-singly-linked-list-in-java/