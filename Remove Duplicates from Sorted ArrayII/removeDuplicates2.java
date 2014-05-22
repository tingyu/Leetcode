/**
Remove Duplicates from Sorted Array II Total Accepted: 6837 Total Submissions: 22974 My Submissions
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

Have you been asked this question in an interview? 
*/

//Runtime: 396 ms
public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length ==0) return 0;
        int occurence=1;
        int index=0, last =A[0];
        
        for(int i = 1; i< A.length; i++){
            if(A[i] == last){
                occurence++;
            } else{
                occurence =1;
                last = A[i];
            }
            
            if(occurence <=2){
                A[++index] = A[i];
            }
        }
        return index + 1;
    }
}



/**
 * Solution:
 * Only difference is now we allow two duplicates. So we scan through the 
 * array and if we find the 3rd duplicate we just skip them.
 * We know it's 3rd duplicate by comparing the itor with second last elements in
 * final array (if A[itor] == A[len-2], then A[itor]==A[len-2]==A[len-1])
 */
//Runtime: 432 ms

public class Solution {
    public int removeDuplicates(int[] A) {
        
        if(A.length <= 2) return A.length;
        int index = 2;
        for(int i = 2; i<A.length; i++){
            if(A[i]!=A[index-2]){
                A[index++] = A[i];
            }
        }
        return index;
    }
}

/**
 * Solution:
 * Only difference is now we allow two duplicates. So we scan through the 
 * array and if we find the 3rd duplicate we just skip them.
 * We know it's 3rd duplicate by comparing the itor with second last elements in
 * final array (if A[itor] == A[len-2], then A[itor]==A[len-2]==A[len-1])
 */
int removeDuplicates(int A[], int n) {
    if (n <= 2) return n;       // no need to deal with n<=2 case.
    int len = 2, itor = 2;
    while (itor < n) {
        if (A[itor] != A[len-2]) 
            A[len++] = A[itor];
        itor++;
    }
    return len;
}
