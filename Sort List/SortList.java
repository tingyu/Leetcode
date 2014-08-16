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






