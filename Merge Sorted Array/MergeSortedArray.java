/*
Merge Sorted Array
Total Accepted: 9454 Total Submissions: 29936 My Submissions
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
*/

//这题的关键在于从后往前比较，然后把大的填到A的末尾
//和merge sorted list不同。因为linkedlist插入删除会很简单。而array的插入要移动所有后面的
public class Solution{
	public void merge(int A[], int m, int B[], int n){
	int i = m - 1;
	int j = n - 1;
	int k = m + n - 1;
 
	while (k >= 0) {
		if (j < 0 || (i >= 0 && A[i] > B[j]))//j<0指的是B里面的已经全部填到A里面了,后面指的是i还有然后进行比较
			A[k--] = A[i--];
		else
			A[k--] = B[j--];
	}
}
}
