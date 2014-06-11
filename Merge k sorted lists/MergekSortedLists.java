/**
Merge k Sorted Lists 

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

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

/*
http://rafal.io/posts/leetcode-22-merge-k-sorted-lists.html
To merge k sorted lists, we can use a priority queue that will keep the ordering of the nodes. 
First, take the first element of all the k sorted lists, and insert it into the priority queue. 
The smallest element must be one of these elements. Pop an element off from the priority queue, 
call it m and append it to the sorted output. Then, if there exists one, 
add the successor of m to the priority queue. Continue this process until all the k sorted lists are exhausted.
*/

public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
		Comparator<ListNode> cmp = new NodeComparator();
		//PriorityQueue(int initialCapacity, Comparator<? super E> comparator) 
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(list.size()+1, cmp);//把comparator传到priority queue里面
		//why??list.size()+1
		//priority queue里面这里实现的是小根堆，每次只对根进行操作，根得到的是最小值。

		//这里是做初始化，把k个List的头加到priority queue里面。
		for(int i = 0; i < lists.size(); i++){
			if(lists.get(i) != null){
				q.add(Lists.get(i));
			}
		}

		if(q.isEmpty()) return null;

		//root即是最小值
		ListNode root = q.peek();
		ListNode tmp = null;

		//q不为空时
		while(!q.isEmpty()){
			//这里是取出最小值
			ListNode top = q.poll();
			if(tmp !=null) tmp.next = top;//如果tmp不是空，这里是把最小值的节点，指向下一个最小值
			tmp = top; //用tmp保存最小值的Node
			if(top.next !=null) q.add(top.next);//如果top.next不是空的时候，把最小值节点所在的List中最小值节点的下一个存到q里面再进行比较。
		}
		return root;
    }

    //实现最小堆。根分为大根堆和小根堆：小根堆（根小于所有的子节点），大根堆（根大于所有的子节点）
    class NodeComparator implements Comparator<ListNode>{
    	public int compare(ListNode n1, ListNode n2){
    		if(n1.val > n2.val) return 1;
    		else if(n1.val == n2.val) return 0;
    		else return -1;
    	}
    }
}