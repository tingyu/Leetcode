/**

Gas Station
Total Accepted: 11691 Total Submissions: 48258

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique. 
*/

/*
https://oj.leetcode.com/discuss/1043/this-question-needs-more-clarification
    I do not understand your question actually. You may misunderstand the cost[i]. 
    The cost[i] is the cost of gas, not money. From station i to station i+1, 
    you can fuel the car with gas[i] unit of gas, then it will cost cost[i] unit of gas to station i+1.
    costs is always positive.
    You can solve the problem as sufficient as you can. For example, if you implement in a O(N^2) solution, 
    you get time limit exceed, you might think about a better solution. I think when you having interview, 
    the best solution may come out iteratively. If interviewee tell you the test case scale, 
    it maybe a cheat to come up the best solution.
*/

/*
http://rleetcode.blogspot.com/2014/01/gas-station-java.html
Solution:
 
This question is prrety like the longest consecutive sequence problem.
 
To check if car can go through the gas stations, we have to check two points,
 
first, if total gas is enough for total cost, this point can be easily checked by sum all
gas[i]-cost[i].

second point, what is the start point? it is a little bit harder to finish in O (n).
however, we can consider this problem in another angel which is if the gas[i]-cost[i]<0 which
mean the i can not been an start point.
 
So we assume start point at 0, and we also declare two varibles which are sum and total, sum is used
to record the total from gas[i]-cost[i], 0<=i<=n, unitl i position . if sum <0 which mean before i can not be start
point, then we increase start pont one position and sum changed back to 0.
 
However, at the end , if the final start point is what we want? maybe, it is also decided by the
total valuable. total is the sum of all gas[i]-cost[i]. our final start point is
an valid one only when total>= 0, or we should return -1;
 */

public class Solution {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if(gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length)
			return -1;

		int sum = 0;
		int start = 0;
		int total = 0;

		for(int i = 0; i < gas.length; i++){
			sum += gas[i] - cost[i];
			total += sum;

			if(sum < 0){
				start = i + 1;
				sum = 0;
			}
		}

		if(total < 0){
			return -1;
		}
		return start;
	}
}


//why the solution is wrong
/*
Submission Result: Wrong Answer

Input:	[2,4], [3,4]
Output:	1
Expected:	-1

*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = -1;
        int remain = 0;
        for(int i = 0; i < gas.length; i++){
            int sum = 0;
            if(gas[i] - cost[i] >= 0){
                start = i;
            }
            for(int j = start; j < gas.length; j++){
                sum = sum + gas[i] - cost[i];
                if(sum < 0){
                    break;
                }
            }
            for(int j = 0; j < start; j++){
                sum = sum + gas[i] - cost[i];
                if(sum < 0){
                    break;
                }               
            }
        }
        return start;
    }
}