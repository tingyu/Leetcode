/**
Jump Game II 

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

/*
http://blog.csdn.net/worldwindjp/article/details/19009575
题意分析：给你一个非负数的数组，数组中的每个元素的值代表从当前位置能够跳的距离。Jump Game 1是判断能不能跳到最后一个元素。
本题是要求求出最小跳的次数。
解题报告：基本思路就是求出每个元素可能跳到的最大坐标，然后串行统计一共需要多少次可以跳到末尾。
以上面的输入为例，生成的每个元素可能跳到的最大坐标的数组为：2,4,4,4,8，统计跳的次数为：从0-2-4，一共需要跳2次。
虽然用到了2次循环，但最多遍历一次数组中的元素，所以时间复杂度为：O(n)


这个解法跟jump Game 1不同，没有采用DP。而是采用了贪心算法。
首先设了一个区间的start, end。这样只要end还没到结尾的时候，就jump_times++;
而每次要对这个[start, end]区间进行for循环，如果这个区间里面的某一个值可以跳的超过结尾if((A[i] + i) >= (A.length - 1))，
那么就返回jump_times，如果不行的话，就不断的找里面可以跳到的最远点max。
在这个区间循环完之后，不断的更新start和end的值，来对下一个区间进行循环
start = 上一个end +1
end = max
这样就保证了每个区间不重复，并且这个end可以覆盖到可以跳到的最远距离
*/

public class Solution {
    public int jump(int[] A) {
    	if(A == null || A.length == 0 || A.length == 1){
    		return 0;
    	}

    	int start = 0;
    	int end = 0;
    	int jump_times = 0;
    	int max = 0;

    	//循环一次, jump_times加1
    	while(end < A.length){
    		max = 0;
    		jump_times++;

    		//统计当前区间内能够跳到的最大距离
    		for(int i = start; i <= end; i++){
    			if((A[i] + i) >= (A.length - 1)){
    				return jump_times;
    			}

    			if((A[i] + i) > max){
    				max = A[i] + i;
    			}
    		}

    		//更新下次搜索区间
    		start = end + 1;
    		end = max;
    	}
    	return jump_times;
    }
}


/*
http://rleetcode.blogspot.com/2014/01/jump-game-ii-java.html

Solution: Greedy
At first, I try to solve this problem with DFS, but exceeded the time limitation, 
then I search the Internet find a very good solution for this question - Greedy Algorithm. 
the main idea is try to find the longest distance by each jump can reach and check if this distance 
can pass the total length of this array, of course we should have a variable to keep record of the current steps. 
if this distance cannot pass the total length of this array, then we should go through all the position within 
this distance to see if it can pass the array by jumping from there
*/

public class Solution {
    public int jump(int[] A) {
    	if(A == null || A.length == 0){
    		return -1;
    	}

    	if(A.length == 0){
    		return 0;
    	}

    	int minStep = 0;
    	int start = 0;
    	// current longest distance the jump can reach
    	int end = A[start];

    	// if current longest distance plus current postion passed the length of array, then return current minStep + 1;
    	if(start + end >= A.length -1){
    		return minStep + 1;
    	}

    	while(end < A.lenegth - 1){
    		minStep++;

    		// record farest position can be reached by jump from position within current farest position
    		int max = 0;

    		for(int i = start; i < end; i++){
    			int current = i + A[i];
    			//pass the total length, return minStep + 1

    			if(current >= A.length -1){
    				return minStep + 1;
    			}

    			max = Math.max(max, current);
    		}

    		//update start position (items in array are not negative, so end + 1 is exist)
    		start = end + 1;
    		end = max;
    	}
    	return minStep;i
   	}
}
