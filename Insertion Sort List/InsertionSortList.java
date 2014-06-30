/**
Insertion Sort List

Sort a linked list using insertion sort.

*/

/*
这道题跟Sort List类似，要求在链表上实现一种排序算法，这道题是指定实现插入排序。
插入排序是一种O(n^2)复杂度的算法，基本想法相信大家都比较了解，就是每次循环找到一个元素在当前排好的结果中相对应的位置，然后插进去，
经过n次迭代之后就得到排好序的结果了。了解了思路之后就是链表的基本操作了，搜索并进行相应的插入。
时间复杂度是排序算法的O(n^2)，空间复杂度是O(1).

这道题其实主要考察链表的基本操作，用到的小技巧也就是在Swap Nodes in Pairs
中提到的用一个辅助指针来做表头避免处理改变head的时候的边界情况。作为基础大家还是得练习一下哈。
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
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null){
        	return null;
        }

        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        ListNode cur = head;

        while(cur != null){
        	ListNode next = cur.next;
        	pre = helper;
        	while(pre.next != null && pre.next.val < cur.val){
        		pre = pre.next;
        	}
        	cur.next = pre.next;
        	pre.next = cur;
        	cur = next;
        }
        return helper.next;
    }
}



