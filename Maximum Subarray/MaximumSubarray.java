/*
Maximum Subarray 

Find the contiguous subarray within an array (containing at least one number) which has the largest sum. 
For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6. 
click to show more practice.
More practice: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/*
 1. Solution #1, O(n)
Just simply loop from left to right and return the max val.
*/

public class Solution{
	public int maxSubArray(int[] A){
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for(int i = 0; i < A.length; i++){
			sum +=A[i];
			maxSum = Math.max(maxSum, sum);
			//re-set sum when < 0 (no need to keep neg value)
			if(sum < 0) sum = 0;
		}
		return maxSum;
	}
}

/*
2. Solution #2, Divide and conquer, O(n) also?
The max range can be in the left half, or in the right half, or across the mid of the array, s
o we just divide it to tree parts and recursive until we get the max value of each part, and then get the largest value.
*/
//Devide and conquer
public class Solution{
	public int maxSubArray(int[] A){
		int maxSum = Integer.MIN_VALUE;
		return findMaxSub(A, 0, A.length -1, maxSum);
	}

	//recursive to find max sum
	//may appear on the left or right part, or across mid (from left to right)
	public int findMaxSub(int[] A, int left, int right, int maxSum){
		if(left > right) return Integer.MIN_VALUE;

		// get max sub sum from both left and right cases
		int mid = (left + right) /2;
		int leftMax = findMaxSub(A, left, mid -1, maxSum);
		int rightMax = findMaxSub(A, mid + 1, right, maxSum);
		maxSum = Math.max(maxSum, Math.max(leftMax, rightMax));

		//get max sum of this range (case: across mid)
		//so need to expend to both left and right using mid as center 
		//mid -> left
		int sum = 0, midLeftMax = 0;
		for(int i = mid -1; i >= left; i--){
			sum += A[i];
			if(sum > midLeftMax) midLeftMax = sum;
		}
		//mid -> right
		int midRightMax = 0; sum = 0;
		for(int i = mid + 1; i <= right; i++){ 
			sum += A[i];
			if(sum > midRightMax) midRightMax = sum;
		}

		//get the max value from the left, right and acrosss mid 
		maxSum = Math.max(maxSum, midLeftMax + midRightMax + A[mid]);

		return maxSum;
	}
}

//solution 3:
//Dynamic Programming Solution

//We should ignore the sum of the previous n-1 elements if nth element is greater than the sum.
// 一维DP，可省略掉dp数组 
public class Solution {
	public int maxSubArray(int[] A) {
		int max = A[0];
		int[] sum = new int[A.length];
		sum[0] = A[0];

		for(int i = 1; i < A.length; i++){
			// 要么不用前面的结果和当前的和，要么用当前值，求两者较大的
			sum[i] = Math.max(A[i], sum[i -1] + A[i]);
			max = Math.max(max, sum[i]);
		}
		return max;
	}
}

//memory优化版，不用数组存了
//http://blog.csdn.net/fightforyourdream/article/details/14515425
public class Solution {
	public int maxSubArray(int[] A) {
		int max = A[0];
		int maxEndHere = A[0];

		for(int i = 1; i < A.length; i++){
			maxEndHere = Math.max(A[i], maxEndHere + A[i]);
			max = Math.max(max, maxEndHere);
		}
		return max;
	}
}
