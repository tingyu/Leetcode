/**
Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 
*/
/*
自己开始写的解法错了。看来准确性急需要提高。
自己解法里面指针指来指去就错了。只能用利用值创建一个新链表的方法，空间是O(n),时间也是O(n)
                //p1.next = p;
                p1.next = new ListNode(p.val);
CC150书上面有两种解法
1. We iterate through the linked list, inserting elements into our before list or our after list. 
Once we reach the end of the linked list and have completed this splitting, we merge 
the two lists.
*/
//my solution
//开始想的是插入和删除的操作，弄两个新的链表。但是这样很容易出错。所以，如果是弄两个fakeHead，然后不断new新的结点在后面，然后把两个链表合并，
//这样会容易的多
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        
        ListNode fakeHead1 = new ListNode(-1);
        ListNode fakeHead2 = new ListNode(-1);
        ListNode p1 = fakeHead1;
        ListNode p2 = fakeHead2;
        ListNode p = head;
        
        while(p!= null){
            if(p.val < x){
                p1.next = new ListNode(p.val);
                p1 = p1.next;
            }else{
                p2.next = new ListNode(p.val);
                p2 = p2.next;
            }
            p = p.next;
        }
        
        p1.next = fakeHead2.next;
        return fakeHead1.next;
    }
}
//这里是每个new个新的，如果按照原来的指针指来指去的写法就很容易写错。像下面那种写法中判断两个是不是空的就没必要多余了

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
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next ==null) return head;
        
        ListNode fakeHead1 = new ListNode(-1);
        ListNode fakeHead2 = new ListNode(-1);

        ListNode p1 = fakeHead1;
        ListNode p2 = fakeHead2;
        ListNode p = head;
        
        while(p != null){
            if(p.val < x){
                p1.next = new ListNode(p.val);
                p1 = p1.next;
            } else{
                p2.next = new ListNode(p.val);
                p2 = p2.next;
            }
            p = p.next;
        }

        if(fakeHead1.next!=null&&fakeHead2.next!=null){
            fakeHead1 = fakeHead1.next;
            fakeHead2 = fakeHead2.next;
            p1.next = fakeHead2;
        } else if(fakeHead1.next==null){
            return fakeHead2.next;
        } else if(fakeHead2.next ==null){
            return fakeHead1.next;
        }
        
        return fakeHead1;
    }
}


/*
CC150书上面有两种解法
/*
If this were an array,we would need to be careful about how we shifted elements. Array shifts are very expensive.
However, in a linked list, the situation is much easier. Rather than shifting and swapping elements, 
we can actually create two different linked lists: one for elements less than x, 
and one for elements greater than or equal to x.
We iterate through the linked list, inserting elements into our before list or our after list. 
Once we reach the end of the linked list and have completed this splitting, we merge the two lists.
The code below implements this approach
*/

//pass in the head of the linked list and value to partition around
public LinkedListNode partition(LinkedListNode node, int x){
    LinkedListNode beforeStart = null;
    LinkedListNode beforeEnd = null;
    LinkedListNode afterStart = null;
    LinkedListNode afterEnd = null;

    //Partition list
    while(node != null){
        LinkedListNode next = node.next;
        node.next = null;
        if(node.data < x){
            //insert node into end of before list
            if(beforeStart == null){
                beforeStart = node;//node.next = null
                beforeEnd = beforeStart; //move beforeEnd position to beforeEnd
            } else{
                beforeEnd.next = node;//node.next = null, insert after beforeEnd
                beforeEnd = node;//move beforeEnd position to the current tail
            }
        }else{
            //insert node into end of after list
            if(afterStart == null){
                afterStart = node;
                afterEnd = afterStart;
            } else{
                afterEnd.next = node; //node.next = null, insert after afterEnd
                afterEnd = node;//move afterEnd position to the current tail
            }
        }
        node = next; // have store next before assign it to null
    }

    if(beforeStart == null){
        return afterStart;
    }

    //merge before list and after list
    beforeEnd.next = afterStart;
    return beforeStart;
}
