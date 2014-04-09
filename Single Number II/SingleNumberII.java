/*
Single Number II Total Accepted: 10154 Total Submissions: 31727 My Submissions
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

//java code : O(n)时间复杂度， O(n)空间复杂度 ： 哈希

public class Solution{
	public int singleNumber(int[] A){
		 // Note: The Solution object is instantiated only once and is reused by each test case. 
		if(A.length == 0) return 0;
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

		for(int a: A){
			if(hash.containsKey(a)){
				hash.put(a, hash.get(a) + 1);
			}
			else hash.put(a, 1);
		}
		for(int key : hash.keySet()){
			if(hash.get(key)==1)
				return key;
		}
		return 0;
	}
}


//O(n) 时间复杂度，O(1)空间复杂度

public class Solution{
	public int singleNumber(int[] A){
		// Note: The Solution object is instantiated only once and is reused by each test case.  
		if(A.length == 0)
			return 0;
		int[] cnt = new int[32];
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < 32; j++){
				if((A[i]>>j & 1)==1){
					cnt[j] = (cnt[j] +1)%3;
				}
			}
		}
		int res = 0;
		for(int i = 0; i < 32; i++){
			res += (cnt[i] << i);
		}
		cnt = null;
		return res;
	}
}