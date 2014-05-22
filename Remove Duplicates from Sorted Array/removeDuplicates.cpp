/**

Remove Duplicates from Sorted Array
Total Accepted: 15265 Total Submissions: 48152

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2]. 
*/

class Solution {
public:
    int removeDuplicates(int A[], int n) {
    	//if n=0, null array, return 0
        if(n==0) return 0;
        
        int index =0;
        //如果都不重复，两个同等步骤的向前移动，如果相等，有重复的，那么略过这个i，看下一个是不是和已经存着的重复
        for(int i=1; i<n; i++){
            if(A[index]!=A[i])
                A[++index] = A[i];
        }
        return index+1;
    }
};