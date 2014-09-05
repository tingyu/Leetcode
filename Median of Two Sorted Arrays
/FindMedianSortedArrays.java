/**

Median of Two Sorted Arrays
Total Accepted: 11789 Total Submissions: 69143

There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. 
The overall run time complexity should be O(log (m+n)).
*/
/*
Keys to solve this problem

This problem can be converted to the problem of finding kth element, k is (A's length + B' Length)/2.

If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
If k == 0, the kth element is the first element of A or B.

For normal cases(all other cases), we need to move the pointer at the pace of half of an array length.


//http://blog.csdn.net/likecool21/article/details/10833175
具体的分析见上链接，复制下来：
     先说临界情况

    A为空或者B为空
    直接在非空数组中找第k大的数即可。O(1)
    找最小的数，k==0的情况，也简单，比较两个数组最开头的元素，谁小就是谁

    然后就是比较复杂的情况，假设寻找目标target是下标为k的数。
    那么意味着在排好的数组中，在目标数之前，一共有k个比目标更小的数。
    将k分成两份，一份在A的前端，一份在B的前端。这里其实将k怎么分配是一个可以讨论的问题，但是平分k可以得到平均最快的效果。
    设k = ka + kb，（k是偶数简单，k是奇数的话，剩下那一个随便放在两个数组中哪一个中都可以）

    这里可以列出来我们想要的效果：
    k=1 —-> ka = 1, kb = 1
    k=2 —-> ka = 1, kb = 1
    k=3 —-> ka = 1, kb = 1. [+1，表示还有一个元素，可以随意分配在ka或者kb中，只要不越界]
    k=4 —-> ka = 2, kb = 2
    k=5 —-> ka = 2, kb = 2. [+1]
    已经可以看出来规律了，这个造成了下面代码中比较复杂的部分，这些细节消耗的时间不少啊。

    然后就是主要逻辑：
    如果A[ka-1] >= B[kb-1]
    说明在B前端的kb个数中，不可能出现我们要寻找的目标。
    为什么呢？
    假如A一共有m个数，B一共有n个数。
    那么target(下标是k)后面有且只有n + m – 1 – k个数；
    但是B[kb-1]在B中已经排在n – kb个数之前，加上A中的m – ka + 1(A[ka-a])，也就是说在排序后的整体数组中排在B[kb-1]之后的数，至少有n – kb + m – ka + 1 = n + m – k + 1个。
    由于n + m – k + 1 > n + m – k – 1，所以B前端kb个数都不可能是target。
    所以此时可以将问题转化为，在A[0,...,m-1]和B[kb,...,n-1]中，寻找下标为k – kb的数。
    否则，A[ka-1] < B[kb-1]
    同上，可以剔除A前端的ka个数。

这样循环下去，就能以二分的速度找到目标。

这个问题不仅要找到第k大的数，当C是偶数的时候，还要找到第k+1个数，取两者均值。

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

public static int findKth(int A[], int B[], int k, aint aStart, int aEnd, int bStart, int bEnd){
	int aLen = aEnd - aStart + 1;
	int bLen = bEnd - bStart + 1;

	//handle special cases
	if(aLen == 0)
		return B[bStart + k];
	if(bLen == 0)
		return A[aStart + k];
	if(k == 0)
		return A[aStart] < B[bStart]? A[aStart] : B[bStart];

	int aMid = aLen * k /(aLen + bLen); //a's middle count 
	int bMid = k - aMid - 1; //b's middle count???why -1

	//make aMid and bMid to be array index
	aMid = aMid + aStart;
	bMid = bMid + bStart;

	if(A[aMid] > B[bMid]){
		k = k - (bMid -bStart + 1);//如果b比较小。k变成原来的k减去b的一半????ma
		aEnd = aMid;
		bStart = bMid +1;
	} else{
		k = k - (aMid - aStart + 1);//如果a比较小，k变成原来的k减去a的一半
		bEnd = bMid;
		aStart = aMid + 1;
	}

	return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
}


//另外一种写法：
/*
http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
[解题思路]
O(n)的解法比较直观，直接merge两个数组，然后求中间值。而对于O(log(m+n))显然是用二分搜索了， 相当于“Kth element in 2 sorted array”的变形。
如果(m+n)为奇数，那么找到“(m+n)/2+1 th element in 2 sorted array”即可。
如果（m+n）为偶数，需要找到(m+n)/2 th 及(m+n)/2+1 th，然后求平均。

而对于“Kth element in 2 sorted array”， 如下图，两个中位数 A[m/2] 和 B[n/2]， 可以将数组划分为四个部分。
而丢弃哪一个部分取决于两个条件：1， (m/2 + n/2)?k；2，A[m/2] ? B[n/2];


如果 (m/2 + n/2) > k，那么意味着，当前中位数取高了，正确的中位数要么在 Section 1或者Section3中。
如果A[m/2] > B[n/2], 意味着中位数肯定不可能在Section 2里面，那么新的搜索可以丢弃这个区间段了。同理可以推断出余下三种情况，如下所示：

If (m/2+n/2+1) > k && am/2 > bn/2 , drop Section 2
If (m/2+n/2+1) > k && am/2 < bn/2 , drop Section 4
If (m/2+n/2+1) < k && am/2 > bn/2 ,  drop Section 3
If (m/2+n/2+1) < k && am/2 < bn/2 ,  drop Section 1


简单的说，就是或者丢弃最大中位数的右区间，或者丢弃最小中位数的左区间。

下面代码算法可以看看，代码通过不了
*/


double findMedianSortedArrays(int[] A, int m, int[] B, int n){
	if((m + n)%2 == 0){
		return (GetMedian(A, m, B, n, (m+n)/2) + GetMedian(A, m, B, n, (m + n)/2 + 1))/2.0;
	}else{
		return GetMedian(A, m, B, n, (m + n)/2 + 1);
	}
}

	int GetMedian(int a[], int m, int b[], int n, int k){
		assert(a && b);
		if(m <= 0) return b[k - 1];
		if(n <= 0) return a[k - 1];
		if(k <= 1) return Math.min(a[0], b[0]);
		if(b[n/2] >= a[m/2]){
			if((m/2 + 1 + n/2) >= k)
				return GetMedian(a, m, b, n/2, k);
			else
				return GetMedian(a + m/2 + 1, m - (m/2 + 1), b, n, k - (m/2 + 1));
		}else{
			if((m/2 + 1 + n/2) >= k)
				return GetMedian(a, m/2, b, n, k);
			else
				return GetMedian(a, m, b + n/2 + 1, n - (n/2 + 1), k - (n/2 + 1));
		}
}