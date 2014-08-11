/**
Reversing linked list iteratively and recursively

    Implement the reversal of a singly linked list iteratively and recursively.


Reversing linked list can be done both iteratively and recursively. 
In my opinion, the iterative solution should be more efficient and less memory overhead 
than its recursive counterpart (Imagine reversing a link list that has one million elements recursively! 
It would quickly run out of stack space).

The recursive solution can be coded in fewer lines of code, but is harder to code correctly. 
On the other hand, the iterative solution requires more code but is easier to verify.

http://leetcode.com/2010/04/reversing-linked-list-iteratively-and.html
http://www.programmerinterview.com/index.php/data-structures/reverse-a-linked-list/
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
//iterative way
//关键是用三个指针,prev, curr, next。next是保存下一个，curr.next -> prev，然后更新prev = curr, curr = next， 在curr != null的时候循环
public void reverse(ListNode head){
	if(head == null || head.next == null) return;

	ListNode prev = null;
	ListNode curr = head;
	while(curr != null){
		ListNode next = curr.next;
		curr.next = prev;
		prev = curr;
		curr = next;
	}

	head = prev;//如果curr是空，那么最后更新到的prev就是末尾，把末尾置为头
}

//2) The recursive way:
public void reverse(ListNode p){
	if(p == null) return;
	ListNode rest = p.next;
	if(rest == null) return;
	reverse(rest);
	p.next.next = p;
	p.next = null;
	p = rest;
}

/*
思路：首先确定base case, base case是在链表到达尾端的时候，需要把头指针指向尾端结点
然后考虑recursive case, 在recursive case里面，我们需要改变指向从而使得链表反转
12->99->37->null
如果是在处理99的时候，99后面的结点是node37表示为Node99->next，如果想要37指向回99，就要把37的next结点指向99，
也就是Node99->next->next = Node99
同时我们也需要摆脱掉从99到37的指针， 所以Node99->next = null

这个recursion的过程是啥样的？
recursive(12)
	recursive(99)
		recursive(37)
		base: 37->next== null, return;
	99.next.next = 99, 99.next = null; 37->99(return在哪？？？)
12.next.next = 12, 12.next = null; 99->12

12指向null呢？？？？？？
*/

public void recursiveReverse(Node currentNode){
	//check for an empty list
	if(currentNode == null) return;

	//if we are at the tail node, recursive base case
	if(currentNode.next == null){
		//set head to the current tail since we are reversing list
		head = currentNode;
		return; //since this is base case;
	}

	recursiveReverse(currentNode.next);
	recursiveReverse.next.next = currentNode;
	currentNode.next = null; //set old next pointer to null	
}