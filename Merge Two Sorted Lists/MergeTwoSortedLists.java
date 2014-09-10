/*
Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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


//弄一个新的链表p,然后不断的比较原来的两个哪个小，不断让p指向更小的那个
 public class Solution{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;

        while(p1 != null && p2 !=null){
            if(p1.val <= p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        if(p1 != null)
            p.next = p1;
        if(p2 != null)
            p.next = p2;

        return fakeHead.next;
    }
 }

//一个更为简练的写法。不需要p1, p2
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode p = res;
        
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if(l1 != null) p.next = l1;
        if(l2 != null) p.next = l2;
        
        return res.next;
    }
}

//a second solution
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        //if(l1 == null && l2 == null) return null;
        //else if(l1 == null) return l2;
        //else if(l2 == null) return l1;
        Comparator<ListNode> cmp = new NodeComparator();
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(2, cmp);
        //这里用2和用3都差不多的。结果都是一样，这里和merge k sorted list不同。merge k sorted list里面capacity有可能是0，所以要加1，这里
        //已经已知是2，所以没必要。关键是前面或者后面判断是不是空的情况。因为编译运行的情况看来，这里PriorityQueue是不能add null的。

        if(l1 != null){
            q.add(l1);
        }
        if(l2 != null){  
            q.add(l2);            
        }

        
        if(q.isEmpty()) return null;
        
        ListNode root = q.peek();
        ListNode tmp = null;
        
        while(!q.isEmpty()){
            ListNode top = q.poll();
            if(tmp != null) tmp.next = top;
            tmp = top;
            if(top.next != null) q.add(top.next);
        }
        return root;
    }
    
    class NodeComparator implements Comparator<ListNode>{
        public int compare(ListNode n1, ListNode n2){
            if(n1.val > n2.val) return 1;
            else if(n1.val == n2.val) return 0;
            else return -1;
        }
    }
}
 
//my solution:
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode preHead = new ListNode(-1);
        ListNode p = preHead;
        
        if(l1 == null){
            p.next = l2;
        }
        
        if(l2 == null){
            p.next = l1;
        }
        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if(l1 != null){
            p.next = l1;
        }
        
        if(l2 != null){
            p.next = l2;
        }
        
        return preHead.next;
    }
}
