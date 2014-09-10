/**
Sort List

Sort a linked list in O(n log n) time using constant space complexity.
*/
/*
thoughts:
把这个问题转化为merge two sorted linked list的问题
所以需要先把这个list转化成两个sorted linked list
可以通过fast和slow两个指针的方法，fast移动两步，slow移动一步。fast到头的时候，slow正好到中间。
这样可以把这个linked list分成两个。
然后recurse调用这个sortList，这样就把left和right的list都sort了。
然后把这个问题转化成了merge two sorted linked list
*/
//my solution
/*
自己写的时候思路是对的。
不过有两点比较需要注意：
1. 注意需要返回head1, head2。写的时候出错是这一点错了
        head1 = sortList(head1);
        head2 = sortList(head2);
2. 如果用到了while(fast.next != null && fast.next.next !=null){
注意一定要在开始进行判断
        if(head == null || head.next == null)
            return head;
因为如果Head是空的时候while循环本身就错了
*/
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode head1 = head;
        ListNode head2 = slow.next;
        slow.next = null;
        
        head1 = sortList(head1);
        head2 = sortList(head2);
        
        ListNode newHead = new ListNode(-1);
        ListNode p = newHead;
        ListNode p1 = head1;
        ListNode p2 = head2;
        
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                ListNode tmp1 = p1.next;
                p.next = p1;
                p1 = tmp1;
            }else{
                ListNode tmp2 = p2.next;
                p.next = p2;
                p2 = tmp2;
            }
            p = p.next;
        }
        
        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;
        }
        return newHead.next;
    }
}
/*
这里没有必要记录next结点
下面写法也是对的
            if(p1.val <= p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }
           */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        //use fast and slow pointer to find the mid position
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        //separate the linkedlist to left and right two parts
        ListNode right = slow.next;
        slow.next = null;
        ListNode left = head;
        
        //sort the left and right two parts recursively 
        left = sortList(left);
        right = sortList(right);
        
        //use merge two sorted linked list method
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right){
        //process 
        /*
        if(left == null){
            return right;
        }
        
        if(right == null){
            return left;
        }*/
        
        //use a fake node as head to store the result
        ListNode preHead = new ListNode(-1);
        ListNode res = preHead; //use the reference to modify the linkedlist
        
        while(left != null && right != null){
            if(left.val <right.val){
                res.next = left;
                left = left.next;
            }else{
           // if(right.val < left.val){
                res.next = right;
                right = right.next;
            }
            
            res = res.next;
        }
        
        //when one of the two linked list are processed done, use this process the following ones
        if(left != null){
            res.next = left;
        }
        
        if(right != null){
            res.next = right;
        }
        
        //caution: return the preHead.next, not res.next, since res has traverse to almost the end
        return preHead.next;
    }
}


public class SortList1{
  public ListNode sortList(ListNode head){
	        if (head==null || head.next==null){
	            return head;
	        } 
	        
	        ListNode counter=head;
	        int len=0;
	        while(counter!=null){
	            counter=counter.next;
	            len++;
	        }
	        ListNode[] headArray={head};
	        return mergeSort(headArray, len);
	        
	                 
	     }
	     private ListNode mergeSort(ListNode[] headArray, int len){
	         if (len==1){
	             
	             ListNode temp=headArray[0];
	             headArray[0]=headArray[0].next;
	             
	             temp.next=null;
	             
	             return temp;
	         }
	         
	         ListNode left=mergeSort(headArray, len/2);
	         ListNode right=mergeSort(headArray, len-len/2);
	         
	         return merge(left, right);
	         
	         
	     }
	     
	     private ListNode merge(ListNode left, ListNode right){
	         if (left==null)
	             return right;
	         
	         
	         if (right==null)
	             return left;
	         
	         
	         ListNode preHead=new ListNode (-1);
	         
	         ListNode end=preHead;
	         
	         while(left!=null && right !=null){
	             if (left.val<right.val){
	                 end.next=left;
	                 left=left.next;
	             }
	             else{
	                 end.next=right;
	                 right=right.next;
	             }
	             
	             end=end.next;
	         }
	         
	         if (left!=null){
	             end.next=left;
	         }
	         
	         if (right!=null){
	             end.next=right;
	         }
	         
	         
	         return preHead.next;
	     }
  
  
  }






