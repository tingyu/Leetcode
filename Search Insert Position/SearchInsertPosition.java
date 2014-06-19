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

/*
http://blog.csdn.net/linhuanmars/article/details/20278967
这道题比较简单，就是二分查找。思路就是每次取中间，如果等于目标即返回，否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)。代码如下：
*/
public int searchInsert(int[] A, int target){
        if(A == null || A.length == 0){
            return 0;
        }
        
        int l = 0;
        int r = A.length -1;
        while(l<=r){
            int mid = (l + r)/2;
            if(A[mid] == target){
                return mid;
            } else if(A[mid] < target){
                l = mid+1;            
            } else{
                r = mid - 1;
            }
        }
        return l;
}

/*
注意以上实现方式有一个好处，就是当循环结束时，如果没有找到目标元素，那么l一定停在恰好比目标大的index上，
r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式。
二分查找是一个非常经典的方法，不过一般在面试中很少直接考二分查找，会考一些变体，
例如Search in Rotated Sorted Array，Search for a Range和Search a 2D Matrix，思路其实是类似的，稍微变体一下即可，有兴趣可以练习一下
*/
