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
