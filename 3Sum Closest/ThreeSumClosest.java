/**

3Sum Closest
Total Accepted: 9546 Total Submissions: 35548

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
/*
Thoughts

This problem is similar with 3 Sum. This kind of problem can be solve by using similar approach, 
i.e., two pointers from both left and right. 
注意这种两个指针的解法实在是太多了。常常想不出解法的时候可以考虑往DP或者这方面想一下。设左右指针，然后在一定情况下移动其中某一个
*/

public class Solution {
	public int threeSumClosest(int[] num, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;

		Arrays.sort(num);

		for(int i = 0; i < num.length -2; i++){
			int j = i + 1;
			int k = num.length -1;

			while(j < k){
				int sum = num[i] + num[j] + num[k];
				int diff = Math.abs(sum - target);

				if(diff < min){
					min = diff;
					result = sum;
				} 

				if(sum <= target){//注意这里是<=，如果是<没有算上=的话就会出现time limited的错误
					j++;
				}else{
					k--;
				}
			}
		}
		return reslut;
	}
}
