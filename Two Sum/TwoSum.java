/**
Two Sum 

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/*
http://www.programcreek.com/2012/12/leetcode-solution-of-two-sum-in-java/
Naive Approach

This problem is pretty straightforward. We can simply examine every possible pair of numbers in this integer array.

Time complexity in worst case: O(n^2).
*/

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        
        for(int i = 0; i < numbers.length; i++){
            for(int j = i+1; j < numbers.length; j++){
                if(numbers[i] + numbers[j] == target){
                    res[0] = i + 1;
                    res[1] = j + 1;
                    break;
                }
            }
        }
        return res;
    }
}

/*
思路：

先把原数组复制一遍，然后进行排序。在排序后的数组中找这两个数。最后再在原数组中找这两个数字的index即可。

时间复杂度O(nlogn)+O(n)+O(n) = O(nlogn)

注意的是结果有可能是两个数是相同的，比如 0 3 4 0, 0要返回1和4，不要返回成1和1或者4和4.
*/
	public int[] twoSum(int[] numbers, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		
		//Copy the original array and sort it
		int N = numbers.length;
		int[] sorted = new int[N];
		System.arraycopy(numbers, 0, sorted, 0, N);
        Arrays.sort(sorted);
        //find the two numbers using the sorted arrays
        int first = 0;
        int second = sorted.length - 1;
        while(first < second){
        	if(sorted[first] + sorted[second] < target){
        		first++;
        		continue;
        	}
        	else if(sorted[first] + sorted[second] > target){
        		second--;
        		continue;
        	}
        	else break;
        }
        int number1 = sorted[first];
        int number2 = sorted[second];
        //Find the two indexes in the original array
        int index1 = -1, index2 = -1;
        for(int i = 0; i < N; i++){
        	if((numbers[i] == number1) || (numbers[i] == number2)){
        		 if(index1 == -1)
        			 index1 = i + 1;
        		 else
        			 index2 = i + 1;
        	}
        		
        }
        int [] result = new int[]{index1, index2};
        Arrays.sort(result);
		return result;
    }

/*
Better Solution

Use HashMap to store the target value.

Time complexity depends on the put and get operations of HashMap which is normally O(1).

Time complexity of this solution: O(n).

*/

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int[] res = new int[2];

    	for(int i = 0; i < numbers.length; i++){
    		if(map.containsKey(target - numbers[i])){
    			res[0] = map.get(target - numbers[i]) + 1;
    			res[1] = i + 1;
    			break;
    		}else{
    			map.put(numbers[i], i);
    		}
    	}
    	return res;
    }
}

