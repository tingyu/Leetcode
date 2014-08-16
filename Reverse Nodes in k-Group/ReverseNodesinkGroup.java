/**
Reverse Nodes in k-Group 


Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/*
http://www.cnblogs.com/lichen782/p/leetcode_Reverse_Nodes_in_kGroup.html
首先，搞清楚怎么逆转一个单链表。其实O(n)就可以了。第一个肯定是last one。
然后我们每遍历到一个node，就把它放到最链表的首位，最后一个么，最后就成为第一个了。下面是一个简单逆转链表的程序。
*/

ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode pre = dummy;
ListNode cur = head.next;
ListNode last = head;
while(cur != null){
	last.next = cur.next;
	cur.next = pre.next;
	pre.next = cur;
	cur = last.next;
}
head = dummy.next;

/*
因为有“放到链表首位”的操作，我们需要一个dummy的头节点，遇到的新节点我们simply state: pre.next = cur; 
保持一个invariant就是last节点始终在最后（cur的前面一个）
然后我们有如下方法：
*/
/**
     * Reverse a link list between pre and next exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |   
     * pre        next
     * after call pre = reverse(pre, next)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     * @param pre 
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */
private static ListNode reverse(ListNode pre, ListNode next){
	ListNode last = pre.next; //where first will be doomed "last"
	ListNode cur = last.next;
	while(cur !=next){
		last.next = cur.next;
		cur.next = pre.next;
		pre.next = cur;
		cur = last.next;
	}
	return last;
}
/*
就是区间的reverse。因为题目要求的是k group逆转嘛。注意人返回的是最后一个(last)节点，这样下一个k-group就可以用上了。
牛人的想法真是周到体贴～～。主方法里面，遍历的过程中每次都计数，每次到达k个节点，就可以使用pre和head.next调用上面的方法逆转了。
*/
public static ListNode reverseKGroup2(ListNode head, int k) {
	if(head == null || k == l) return head;
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode pre = dummy;
	int i = 0;
	while(head != null){
		i++;
		if(i%k == 0){
			pre = reverse(pre, head.next);
			head = pre.next;
		}else{
			head = head.next;
		}
	}
	return dummy.next;
}


//Another solution:
/*
http://yucoding.blogspot.com/2013/05/leetcode-question-86-reverse-nodes-in-k.html
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

Now we solve this reverse problem, the final step is to scan the whole list:
When we finished one reverse, put p k steps further, set q=p, then put q k steps further to 
find the end node for the new reverse, if meet the end, no more reverse needed, return the list.
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode p = new ListNode(0);
        p.next = head;
        head = p ;
        ListNode q = p;

        while(true){
            int i = 0;
            while(q!=null && i < k){ //move q to the certain position
                q = q.next;
                i++;
            }

            if(q == null){ //if less than k, then return the original one
                break;
            } else{
                while(p.next!= q){
                    ListNode d = p.next;
                    ListNode l = q.next;
                    p.next = p.next.next;
                    q.next = d;
                    d.next = l;
                }
                //last reverse done, move p to the new position
                for(int j = 0; j < k; j++){
                    p = p.next;
                }
                q = p; //move q to p too
            }
        }

        return head.next;
    }
}





