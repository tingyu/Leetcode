/**
Remove Duplicates from Sorted Array II Total Accepted: 6837 Total Submissions: 22974 My Submissions
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

Have you been asked this question in an interview? 
*/

class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if(n==0) return 0;
        
        //注意这里要设置为1，而不是0。index在下面的1,2之间不停的改变
        int occur = 1;
        int index =0;
        for(int i=1; i < n; i++){
            if(A[index] == A[i]){
            	if(occur < 2){
            		A[++index] = A[i];
            		occur++;
            	}
            }else{
            	A[++index] = A[i];
            	occur = 1;
            }
        }
        return index+1;
    }
};