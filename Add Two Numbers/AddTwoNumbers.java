/**
Add Two Numbers 

You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

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


//my long solution
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