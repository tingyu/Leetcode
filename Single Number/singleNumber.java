/**
Single Number Total Accepted: 13244 Total Submissions: 29525 My Submissions
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Have you been asked this question in an interview?  Yes
Discuss


*/

public class Solution {
    public int singleNumber(int[] A) {
        int x = A[0];
        for(int i = 1; i < A.length;i++){
            x ^= A[i];
        }
        
        return x;
    }
}