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