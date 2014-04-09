/*
Merge Sorted Array
Total Accepted: 9454 Total Submissions: 29936 My Submissions
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
*/
public class Solution{
	public void merge(int A[], int m, int B[], int n){
	int i = m - 1;
	int j = n - 1;
	int k = m + n - 1;
 
	while (k >= 0) {
		if (j < 0 || (i >= 0 && A[i] > B[j]))
			A[k--] = A[i--];
		else
			A[k--] = B[j--];
	}
}
}
