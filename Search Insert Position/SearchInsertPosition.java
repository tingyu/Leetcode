/*
Search Insert Position 


Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

*/


/*Solution 1

Naively, we can just iterate the array and compare target with ith and (i+1)th element. Time complexity is O(n)
*/
public class Solution {
    public int searchInsert(int[] A, int target) {
        if(A == null) return 0;
        
        if(target <= A[0]) return 0;
        
        for(int i =0; i< A.length -1; i++){
            if(target > A[i] && target <= A[i+1])
            return i+1;
        }
        return A.length;
    }
}


/*
Solution 2

This also looks like a binary search problem. We should try to make the complexity to be O(nlogn).
*/

public class Solution{
	public int searchInsert(int[] A, int target) {
		if(A == null || A.length==0)
			return 0;

		return searchInsert(A, target, 0, A.length -1);
	}

	public int searchInsert(int[] A. int target, int start, int end){
		int mid =(start + end)/2;

		if(target == A[mid])
			return mid;
		else if(target<A[mid])
			return start<mid?searchInsert(A, target, start, mid -1):start;
		else
			return end>mid?searchInsert(A, target, mid +1, end):(end+1);
	}
}