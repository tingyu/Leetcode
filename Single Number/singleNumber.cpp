/*
Single Number   Total Accepted: 11676 Total Submissions: 25841 My Submissions
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

class Solution {
public:
    int singleNumber(int A[], int n) {
        if(n<0) return 0;
        int x = A[0];
        for(int i = 1; i < n; i++){
            x^=A[i];
        }
        return x;
    }
};