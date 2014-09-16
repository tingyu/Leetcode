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
but, the tricky point is how to connect the random reference, we can not know whether the 
node connected by the random reference exists or not when we copy node one by one. 
So to solve this problem, I think hashmap should be a good solution. 
With hashMap,do first while loop,  we copy every node in given list and keep them as a 
<originalNode, copiedNode> pair. then do another while loop connect these copied nodes together.
注意一个个拷贝挨着拷贝的时候不知道被random reference指向的node存不存在
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
//弄一个hashmap把原来的和copy的都对应起来。分两次遍历，一次是处理next,一次是处理random

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
 				q.random = map.get(p.random);//tricky!!
 			}else{
 				q.random = null;
 			}
 			p = p.next;
 			q = q.next;
 		}     
 		return newHead;  
    }
}

//my solution
//直接遍历两遍，一遍copy next, 一遍copy random。
//原来写的时候一直错了，其实是因为这一句if(head == null || head.next == null)  return head;
/*
Submission Result: Wrong Answer

Input:  {-1,#}
Output: Node with label -1 was not copied but a reference to the original one.
Expected:   {-1,#}

但是这个代码有点作弊啊， 强制new了一个random的指针
但是事实上这个random的node之前第一遍pass的时候就已经创建了

对吧，这样能过OJ，但是面试的时候别人会指出来的
 me:  所以这样还是不对。看来怎么样都要原来的和copy的一一对应起来，这样才可以在copy的里面找random?

所以就是在原来每一个节点之后new一个新的，然后复制random指针到原来的node的之后一个就可以了
me:  好吧，原来hashmap和insert什么都是干这个用的
是的
 me:  那这种解法没法改了。一定要hashmap或者insert再分开这么做才可以了？
*/

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return head;
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode p = head;
        RandomListNode q = newHead;
        
        while(p.next != null){
            q.next = new RandomListNode(p.next.label); 
            p = p.next;
            q = q.next;
        }
        
        q = newHead;
        p = head;
        while(p != null){
            if(p.random != null){
                q.random = new RandomListNode(p.random.label);    
            }
            p = p.next;
            q = q.next;
        }
        
        return newHead;
    }
}