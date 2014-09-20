/**
Remove Duplicates from Sorted Array II Total Accepted: 6837 Total Submissions: 22974 My Submissions
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

Have you been asked this question in an interview? 
*/
//my solution
//自己马上想到的是给一个count，来保存变量重复的次数
public class Solution {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0) return 0;
        int count = 1;
        int index = 0;
        for(int i = 1; i < A.length; i++){
            if(A[i] == A[i-1]){
                count++;
                if(count <= 2){
                    A[++index] = A[i];    
                }
            }else if(A[i] != A[i-1]){
                A[++index] = A[i];
                count = 1;
            }
        }
        return index + 1;
    }
}

/*
这种允许多个重复的，一般的解法都是用一个变量保存重复的次数，然后在某些情况下++，某些情况下重置，然后判断在某个阈值内赋值

当然这些是最基本的解法。然后考虑优化，比如说如果不要额外的空间，in-place怎么
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
 * f
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



//my solution
public class Solution {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0) return 0;
        int pre = A[0];
        int cur = 0;
        int index = 0;
        int count = 1;
        for(int i = 1; i< A.length; i++){
            cur = A[i];
            if(pre == cur){
                count++;
                if(count <= 2){
                    A[++index]  = A[i];
                }else{
                    continue;
                }
            }else{
                A[++index]  = A[i];
                pre = cur;
                count = 1;
            }
        }
        return index + 1;
    }
}
