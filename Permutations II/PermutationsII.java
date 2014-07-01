/**
Permutations II 

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

/*
http://www.programcreek.com/2013/02/leetcode-permutations-ii-java/

Basic idea: For each number in the array, swap it with every element after it. To avoid duplicate, need to check it first.
如果有时间的话可以打印输出看下结果如何。
*/

public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();        
		permuteUnique(num, 0, res);
		return res;
    }

    private void permuteUnique(int[] num, int start, ArrayList<ArrayList<Integer>> res){
    	if(start >= num.length){
    		ArrayList<Integer> iterm = convertArrayToList(num);
    		res.add(iterm);
    	}

    	//依次比较当前的和start是否有重复的，如果重复返回false，不是重复的话才返回true
    	for(int j = start; j <=num.length - 1; j++){
    		if(containsDuplicate(num, start, j)){
    			swap(num, start, j); //swap
    			permuteUnique(num, start+1, res);
    			swap(num, start, j); //swap回去
    		}
    	}
    }

    private ArrayList<Integer> convertArrayToList(int[] num){
    	ArrayList<Integer> iterm = new ArrayList<Integer>();
    	for(int h = 0; h < num.length; h++){
    		iterm.add(num[h]);
    	}
    	return iterm;
    }

    private boolean containsDuplicate(int[] arr, int start, int end){
    	for(int i = start; i <= end -1; i++){
    		if(arr[i] == arr[end]){
    			return false;
    		}
    	}
    	return true;
    }

    private void swap(int[] a, int i, int j){
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
}

/*
another solution:
http://www.darrensunny.me/leetcode-permutations-ii/

This problem is almost identical to LeetCode - Permutations, except that numbers may 
be duplicated and that unique permutations are needed. So we can adopt a similar idea as 
in the earlier problem with minor modifications. For example, with the use of a boolean array, 
do not consider to add a duplicate number to the next recursion if its earlier appearance has 
not been considered yet. This will avoid duplicate permutations.
*/

package me.darrensunny.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LeetCode - Permutations II
 * Created by Darren on 14-4-16.
 */
public class PermutationsII {

    // 436ms for 30 test cases
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        if (num == null)
            return null;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length == 0)
            return result;
        Arrays.sort(num);       // Sort the array in non-descending order
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
    // otherwise, append each unused number to "current", and recursively try next unused number
    private void recursivePermute(int[] num, boolean[] used, ArrayList<Integer> current,
                                  ArrayList<ArrayList<Integer>> result) {
        if (current.size() == num.length) {     // "current" is already a permutation of "num"
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Append each unused number to "current", and recursively try next unused number
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && !used[i-1] && num[i]==num[i-1])
                // Do not consider a duplicate number if its earlier appearance has
                // not been considered yet
                continue;
            if (!used[i]) {
                // Append an unused number
                used[i] = true;
                current.add(num[i]);
                // Recursively append next unused number
                recursivePermute(num, used, current, result);
                // Get back to original state, get ready for appending another unused number
                current.remove(current.size()-1);
                used[i] = false;
            }
        }
    }
}