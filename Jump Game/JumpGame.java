/**
Jump Game 

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/

//my solution
public class Solution {
    public boolean canJump(int[] A) {
        if(A == null || A.length == 0) return false;
        int[] checker = new int[A.length];
        checker[0] = A[0];
        for(int i = 1; i < A.length; i++){
            if(i <= checker[i-1]){//在dp[i-1]可以到达当前位置i的时候，不断更新dp[i]
                checker[i] = Math.max(checker[i-1], i + A[i]);
                if(checker[i] >= A.length - 1) return true;//这里面可以简化复杂度，就是加上个判断，如果对于某一个dp[i]如果到达最后一个位置
            }else{//另外如果dp[i-1]连当前位置都到达不了，肯定到达不了后面的。所以要return false
                return false;
            }
        }
        return true;
    }
}

//http://rleetcode.blogspot.com/2014/02/jump-game-java.html
// DP Solution: use int[] checker to record the max length current position can reach, if the length
// passed the length of given array return true
/*
下面给出一种O（n）的算法：
我们用maxlength 维护一个从开始位置能到达的最远距离，然后判断在当前位置是否能够到底最后一个位置和当前位置是否可达，
s如果两个条件都满足，那么返回true，如果当前位置是0，并且最远距离不能超过当前位置，那么只能返回false 了，更新最远距离
*/

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
http://blog.csdn.net/worldwindjp/article/details/18990777
解题报告：数组中的每个数字代表可以跳的步数，看看能不能跳到最后。
解法：动态规划，状态转移数组state[i]代表当前从当前节点可跳到的最大节点，如果state[i]<=i&&i!=0，返回false。
注意state[i]里面存的是可以跳到的最大节点，而不是跳的长度
state[i]<=i指的是怎么跳都跳不过当前位置
*/
public class Solution {
    public boolean canJump(int[] A) {
        int[] state = new int[A.length];
        state[0] = A[0];
        //第一个是0，并且长度不止1，这样就说明没有跳到最后，如果第一个是0，长度也是1，那么就已经达到了最后一个
        if(state[0] == 0 && A.length > 1){
            return false;
        }

        for(int i = 1; i < A.length; i++){
            state[i] = Math.max(state[i-1], A[i] + i); //前一个状态能跳到的最远，和当前状态能跳到的最远距离选一个当做当前能跳到的最远距离
            if(state[i] <= i && i!= A.length -1){ //不在最后一个，但是state始终走不出位置i
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
//this cannot work, why???
/*
public class Solution {
    public boolean canJump(int[] A) {
 		if(A==null ||A.length ==0){
 			return false;
 		}

 		int current = 0;

 		while(current < A.length){
 			if(current == A.length-1){
 				return true;
 			}

 			if(A[current] == 0){
 				return false;
 			}

 			current +=A[current];
 		}
 		return true;
    }
}*/



