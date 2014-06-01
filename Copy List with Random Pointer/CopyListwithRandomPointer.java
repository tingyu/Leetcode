/**
Copy List with Random Pointer 

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

*/

/*
http://www.programcreek.com/2012/12/leetcode-copy-list-with-random-pointer/

1. Some Thoughts

We can solve this problem by doing the following steps:

copy every node, i.e., duplicate every node, and insert it to the list
copy random pointers for all newly created nodes
break the list to two


This is an interesting question. Deep copy a single linked list with only next reference is easy. 
but, the tricky point is how to connect the random reference, we can not know if the 
node connected by the random reference exists or not when we copy node one by one. 
So to solve this problem, I think hashmap should be a good solution. 
With hashMap,do first while loop,  we copy every node in given list and keep them as a 
<originalNode, copiedNode> pair. then do another while loop connect these copied nodes together.

*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;

        RandomListNode p = head;

        //copy every node and insert to list
        while(p != null){
        	RandomListNode copy = new RandomListNode(p.label);
        	copy.next = p.next;
        	p.next = copy;
        	p = copy.next;
        }

        //copy random pointer for each new node
        p = head; 
        while(p != null){
        	if(p.random !=null){
        		p.next.random = p.random.next;
        	}
        	p = p.next.next;
        }

        //break list to two
        p = head;
        RandomListNode newHead = head.next;
        while(p != null){
        	RandomListNode temp = p.next;
        	p.next = temp.next;
        	if(temp.next != null){
        		temp.next = temp.next.next;
        	}
        	p = p.next;
        }

        return newHead;
    }
}

//4. Correct Solution Using HashMap

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
 		if(head == null) return null;
 		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
 		RandomListNode newHead = new RandomListNode(head.label);

 		RandomListNode p = head;
 		RandomListNode q = newHead;
 		map.put(head, newHead);

 		p = p.next;
 		while(p != null){
 			RandomListNode temp = new RandomListNode(p.label);
 			map.put(p, temp);
 			q.next = temp;
 			q = temp;
 			p = p.next;
 		}

 		p = head;
 		q = newHead;
 		while(p != null) {
 			if(p.random != null){
 				q.random = map.get(p.random);
 			}else{
 				q.random = null;
 			}
 			p = p.next;
 			q = q.next;
 		}     
 		return newHead;  
    }
}