/*
Search in Rotated Sorted Array 
Total Accepted: 9628 Total Submissions: 34343 My Submissions
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/
//循环数组查找，二分查找，几种情况分类讨论
/*
这道题是二分查找Search Insert Position的变体，看似有点麻烦，其实理清一下还是比较简单的。因为rotate的缘故，
当我们切取一半的时候可能会出现误区，所以我们要做进一步的判断。具体来说，假设数组是A，每次左边缘为l，右边缘为r，
还有中间位置是m。在每次迭代中，分三种情况：
（1）如果target==A[m]，那么m就是我们要的结果，直接返回；
（2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，
如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
（3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)
*/

public class Solution{
	public int search(int[] A, int target){
		//special case
		if(A.length==0) return -1;
		if(A.length==1){
			if(A[0]!= target) return -1;
			else return 0;
		}

		int left = 0;
		int right = A.length - 1;
		int middle = 0;

		while(left <= right){
			middle = (left + right)/2;
			if(A[middle] == target) return middle;

			if(A[middle]>= A[left]){ //upper hald sorted，说明前半段是有序的
				if(target < A[middle] && target >= A[left]) right = middle -1;
				else left = middle +1;
			}
			else{//bottom half sorted，注意这里是A[middle]<A[left]的情况，说明前半段无序，那么后半段有序
				if(target>A[middle] && target <= A[right]) left = middle +1;
				else right = middle -1;
			}
		}

		return -1;
	}
}


//my Solution
public class Solution {
    public int search(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(A[mid] == target) return mid;
            
            if(A[mid] < A[right]){
                if(target > A[mid] && target <= A[right]) left = mid + 1;
                else right = mid - 1;
            }else{
                if(target < A[mid] && target >= A[left]) right = mid - 1;
                else left = mid +1;
            }
        }
        return -1;
    }
}


/*
如果把target <= A[right]改成target < A[right]
把target >= A[left]改成target > A[left]
Submission Result: Wrong Answer

Input:	[1,3], 3
Output:	-1
Expected:
*/