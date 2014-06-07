/**
Jump Game 

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/
//http://rleetcode.blogspot.com/2014/02/jump-game-java.html
// DP Solution: use int[] checker to record the max length current position can reach, if the length
// passed the length of given array return true

public class Solution {
    public boolean canJump(int[] A) {
        if(A==null || A.length == 0){
            return false;
        }
        
        if(A.length ==1){
            return true;
        }
        
        int[] checker = new int[A.length];
        checker[0] = A[0];
        
        for(int i = 1; i < checker.length; i++){
            if(i <= checker[i-1]){//this position is reachable
                checker[i] = Math.max(A[i] + i, checker[i-1]);
                
                if(checker[i] > A.length-1){
                    return true;
                }
            }else{
                //if current i cannot be reached by previous jump then return false
                return false;
            }
        }
        return true;
    }
}

/*
This greedy solution below failed at test case {2,4,2,1,0,4}, there should be more test cases make
 
this solution fail but for some reason this solution can pass OJ. 
 
Thank you very much for Guangsen Wang point it out!
 */
// Greedy ALgorithm solution
// depend on the question description, the num in the given array are non-negative number
// so we can keep jump until to the end of the array if the num is not 0





