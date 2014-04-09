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