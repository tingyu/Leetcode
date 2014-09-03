/**
Add Two Numbers 

You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
/*
这题思路很直观，也很简单。只需要遍历，然后相加进位就好。注意while循环写的时候，按照下面||几种情况是最简便的，这样可以把所有情况归为一类。
不然会有很多重复性的代码。注意不要忘了p1 = p1.next。注意判断只有p1!=null的时候，不然会产生很明显的bug

这题有个follow up,在CC150上面有说。就是如果数字在链表里面不是逆序，而是顺序存储的情况。
此时更难，要注意两个特殊情况考虑
1. 长度不一样时。此时需要padding之后才可以求解
2. 这题进位是pass forwar，如果是follow up那么进位就是pass backward

cc150上面不管这题还是follow up都是recursion的解法，可以用来开拓思路
*/
//my new solution
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode newHead = new ListNode(0);
        ListNode p = newHead;
        int carry = 0;
        
        while(p1 != null || p2 != null || carry != 0){
            int sum = carry;
            if(p1 != null){
                sum += p1.val;
                p1 = p1.next;
            }
            if(p2 != null){
                sum += p2.val;
                p2 = p2.next;
            } 
            p.next = new ListNode(sum%10);
            carry = sum/10;
            p = p.next;
        }
        
        return newHead.next;
    }
}



public class Solution{
	public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		int carry = 0;

		ListNode newHead = new ListNode(0);
		ListNode p1 = l1, p2 = l2, p3 = newHead;

		while(p1 !=null ||p2 !=null){
			if(p1 !=null){
				carry += p1.val;
				p1 = p1.next;
			}

			if(p2 !=null){
				carry += p2.val;
				p2 = p2.next;
			}

			p3.next = new ListNode(carry%10);
			p3 = p3.next;
			carry /=10;
		}

		if(carry ==1)
			p3.next=new ListNode(1);

		return newHead.next;
	}
}

//Solution refered from CC150
//recursion solution
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addLists(l1, l2, 0);
    }
    
    private ListNode addLists(ListNode l1, ListNode l2, int carry){
        /* We're done if both lists are null AND the carry value is 0 */
        if(l1 == null && l2 == null && carry == 0)
            return null;
        
        ListNode result = new ListNode(carry);
        
        //add value, and the data from l1, l2
        int value = carry;
        if(l1 != null) value += l1.val;
        if(l2 != null) value += l2.val;
        
        result.val = value%10;//second digital of number
        
        //recursive
        if(l1 != null || l2 != null){
            ListNode more = addLists(l1 == null?null: l1.next, l2 == null?null: l2.next, value >= 10? 1: 0);
            result.next = more;
        }
        return result;
    }
}

/*
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem

自己想了两种解法，一个是先转换成long，然后相加，再转换成linkedList。
另外一个是遍历两遍，第一遍相加，第二遍处理carry。但是第二种方法没有考虑长短
不一致的情况，这个陷阱没考虑，这里首先需要padding

下面是CC150给出的recursive的解法
Part B is conceptually the same (recurse, carry the excess), but has some additional
complications when it comes to implementation:

1. One list may be shorter than the other, and we cannot handle this "on the fly." For
example, suppose we were adding (1 -> 2 -> 3 -> 4) and (5 -> 6 -> 7). We need to
know that the 5 should be "matched" with the 2, not the 1. We can accomplish this
by comparing the lengths of the lists in the beginning and padding the shorter list
with zeros.

2. In the first part, successive results were added to the tail (i.e., passed forward). This
meant that the recursive call would be passed the carry, and would return the result
(which is then appended to the tail). In this case, however, results are added to the
head (i.e., passed backward). The recursive call must return the result, as before, as
well as the carry.This is not terribly challenging to implement, but it is more cumber-
some. We can solve this issue by creating a wrapper class called Partial Sum
*/

public class PartialSum{
    public LinkedListNode sum = null;
    public int carry = 0;
}

LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2){
    int len1 = length(l1);
    int len2 = length(l2);

    //pad the  shorter list with zeros -- see note 1
    if(len1 < len2){
        l1 = padList(l1, len2-len1);
    }else{
        l2 = padList(l2, len1-len2);
    }

    //add lists
    PartialSum sum = addListsHelper(l1, l2);

    // If there was a carry value left over, insert this at the front of the list. Otherwise, just return the linked list.
    if(sum.carry == 0){
        return sum.sum;
    }else{
        LinkedListNode result = insertBefore(sum.sum, carry);
        return result;
    }
}

PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2){
    if(l1 == null && l2 == null){
        PartialSum sum = new PartialSum();
        return sum;
    }

    //add smaller digits recursively
    PartialSum sum = addListsHelper(l1.next, l2.next);

    //add carry to current data
    int val = sum.carry + l1.data + l2.data;

    //insert sum of current digits
    LinkedListNode full_result = insertBefore(sum.sum, val%10);

    //return sum so far, and the current value
    sum.sum = full_result;
    sum.carry = val/10;
    return sum;
}

//pad the list with zeros
LinkedListNode padList(LinkedListNode l, int padding){
    LinkedListNode head = l;
    for(int i = 0; i < padding; i++){
        LinkedListNode n = new LinkedListNode(0, null, null);
        head.prev = n;
        n.next = head;
        head = n;
    }
    return head;
}

// Helper function to insert node in the front of a linked list
LinkedListNode insertBefore(LinkedListNode list, int data){
    LinkedListNode node = new LinkedListNode(data, null, null);
    if(list != null){
        list.prev = node;
        node.next = list;
    }
    return node;
}

/*
Note how we have pulled insertBefore(), padList(), and length() (not listed)
into their own methods. This makes the code cleaner and easier to read—a wise thing
to do in your interviews!
*/

//previous solution: my long solution
//太多重复性代码了
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        int carry = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode res = new ListNode(0);
        ListNode p = res;
        while(p1 != null && p2 != null){
            int sum = p1.val + p2.val + carry;
            if(sum >= 10){
                carry = 1;
                sum = sum -10;
            }else{
                carry = 0;
            }
            p.next = new ListNode(sum);
            p1 = p1.next;
            p2 = p2.next;
            p = p.next;
        }
        
        while(p1 != null){
            int sum = p1.val + carry;
            if(sum >= 10){
                carry = 1;
                sum = sum -10;
            }else{
                carry = 0;
            }
            p.next = new ListNode(sum);
            p1 = p1.next;
            p = p.next;
        }
        
        while(p2 != null){
            int sum = p2.val + carry;
            if(sum >= 10){
                carry = 1;
                sum = sum -10;
            }else{
                carry = 0;
            }
            p.next = new ListNode(sum);
            p2 = p2.next;
            p = p.next;
        }
        
        if(carry == 1){
            p.next = new ListNode(1);
        }
        return res.next;
    }
}