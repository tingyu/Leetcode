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
        
    }
}