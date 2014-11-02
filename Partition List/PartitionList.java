/**
Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before 
nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 
*/
/*
summary:
这题可以采用三种解法来解。基本的思路都是新建两个linkedlist,然后遍历原来的LinkedList,并且不断把值插入到这两个新的linkedList中，最后
merge这两个linkedList
三个方法的差别之处：
1. 新建了两个fakeHead1,fakeHead2.然后后让p1，p2分别遍历这两个fakeHead，同时让p遍历原来的linkedlist。
需要注意的是 分离出next，然后把当前结点孤立起来。此时然后再p1, p2指向就不会出错了
这种解法写法最简单
    另外还有个根据node的值不断新建结点的做法。感觉是复制了值，这种解法在面试时可能不大好，有些作弊嫌疑
2. CC150上面的弄了四个值beforeStart，beforeEnd, afterStart, afterEnd。然后对两个新建的链表又需要采用两种情况讨论。
如果beforeStart是空，不是空分开讨论。
写法比上一个复杂。思路倒是还比较明确
3. CC150上面的弄了2个值beforeStart，afterStart。然后每次都插入到beforeStart, afterStart前面，并且不断更新beforeStart, afterStart
这种做法在CC150上面是可以使用的，但是如果在leetcode上面对于这题而言就错了。因为不断插入到前面就会导致逆序了。不满足题目要求
可以借鉴用来开拓思路


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
//根据CC150上面的启示，分离出next，然后把当前结点孤立起来。此时然后再p1, p2指向就不会出错了
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(x);
        ListNode p1 = head1;
        ListNode p2 = head2;
        ListNode p = head;
        
        while(p != null){
            ListNode next = p.next;
            p.next = null;
            if(p.val < x){
                p1.next = p;
                p1 = p1.next;
            }else{
                p2.next = p;
                p2 = p2.next;
            }
            p = next;
        }
        
        p1.next = head2.next;
        return head1.next;
    }
}

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
关键是要先记录下来next结点，然后把当前的node孤立起来
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


/*k, we know we have reached the kth to last element of the linked list.

If it bugs you to keep around four different variablesfor tracking two linked lists, you're not alone. 
We can get rid of some of these, with just a minor hit to the efficiency. 

This drop in efficiency comes because we have to traverse the linked list an extra time. 
The big-O time will remain the same though, and we get shorter, cleaner code.

The second solution operates in a slightly different way. Instead of inserting nodes into the end of the before 11st 
and the after list, it inserts nodes into the front of them.

这个解法不适用此题，因为改变了相对顺序，每次都插入在头处，正好逆序了。
*/

public LinkedListNode partition(LinkedListNode node, int x){
    LinkedListNode beforeStart = null;
    LinkedListNode afterStart = null;

    //partition list
    while(node !=null){
        LinkedListNode next = node.next;
        if(node.data < x){
            //insert node into start of before list
            node.next = beforeStart; //move node before beforeStart
            beforeStart = node; //Update before Start Position to Head of list+
        } else{
            //insert node into front of after listk, we know we have reached the kth to last element of the linked list.
            node.next = afterStart;//move node before afterStart
            afterStart = node;//Update after Start Position to Head of list+
        }
        node = next;
    }

    //merge before list and after list
    if(beforeStart == null){
        return afterStart;
    }

    //find end of before list, and merge the list
    LinkedListNode head = beforeStart;
    while(beforeStart.next !=null){
        beforeStart = beforeStart.next;//move
    }
    beforeStart.next = afterStart;

    return head;
}


/*
Note that in this problem, we need to be very careful about null values. 
Check out line 7 in the above solution. The line is here because we are modifying the linked list as we're 
looping through it. We need to store the next node in a temporary variable so that we remember 
which node should be next in our iteration.
