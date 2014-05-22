/**

Median of Two Sorted Arrays
Total Accepted: 11789 Total Submissions: 69143

There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/
/*
Keys to solve this problem

This problem can be converted to the problem of finding kth element, k is (A's length + B' Length)/2.

If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
If k == 0, the kth element is the first element of A or B.

For normal cases(all other cases), we need to move the pointer at the pace of half of an array length.

*/

public static double findMedianSortedArrays(int A[], int B[]) {
	int m = A.length;
	int n = B.length;

	if((m + n) % 2 != 0) //odd
		return (double) findKth(A, B, (m + n)/2, 0, m -1, 0, n -1);
	else{
		return (findKth(A, B, (m + n)/2, 0, m -1, 0, n -1) +
			findKth(A, B, (m + n)/2 -1, 0, m -1, 0, n -1))*0.5;
	}
}

public static int findKth(int A[], int B[], int k, int aStart, int aEnd, int bStart, int bEnd){
	int aLen = aEnd - aStart + 1;
	int bLen = bEnd - bStart + 1;

	//handle special cases
	if(aLen == 0)
		return B[bStart + k];
	if(bLen == 0)
		return A[aStart + k];
	if(k == 0)
		return A[aStart] < B[bStart]? A[aStart] : B[bStart];

	int aMid = aLen * k /(aLen + bLen); //a's middle count ??????
	int bMid = k - aMid - 1; //b's middle count?????????

	//make aMid and bMid to be array index
	aMid = aMid + aStart;
	bMid = bMid + bStart;

	if(A[aMid] > B[bMid]){
		k = k - (bMid -bStart + 1);
		aEnd = aMid;
		bStart = bMid +1;
	} else{
		k = k - (aMid - aStart + 1);
		bEnd = bMid;
		aStart = aMid + 1;
	}

	return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
}