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

/*
http://rleetcode.blogspot.com/2014/01/single-number-ii.html
Cause of the constrain of linear time complexity, so one loop may be work
also beacause of the the hint of without using extra memory, so a 32 length int array should be a
good choice. 
 
use this array to count the appear times of 1 at each bit, use %3 to ignore the number appear 3 times
then use | to build left number, because only one number appear onece, so the left number is the number is which
we want.

这里用32位，因为int是32位的。[00.....000]32位。比如3就是11，就是把这个数组里面两位置为1。
*/
public class Solution {
 
    public int singleNumber(int[] A) {
        if (A==null||A.length==0){
            return -1;
        }
        
        int[] bits=new int[32];
        int result=0;
        for (int i=0; i<32; i++){
            for(int j=0; j<A.length; j++){
                bits[i]+=A[j]>>i&1;
            }
            bits[i]%=3;
            result|=(bits[i]<<i);
        }
        
        return result;
            
   }
        
}

/*
http://gongxuns.blogspot.com/2013/10/leetcode-single-number-ii.html
Idea: if we look all the last bit of the numbers (assuming all are 32-bit) in the array, 
there must be 3k+1 or 3k `1's in total depending whether the single number's last bit is one or zero. 
This observation holds for all the rest 31 bits as well. Hence, if we sum all the numbers only 
at certain bit and mod by 3, we can get the corresponding bit the single number. 
Do this for all 32-bit, we can get all bits of that number. This generalizes the solution of LeetCode: 
Single Number I, where xor all the numbers is essentially trying to add all bits and then mod by 2...
*/
class Solution {
public:
    int singleNumber(int A[], int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int single_number = 0;
        for (int i = 0; i < 32; ++i) {// assume dealing with int32.
            int bit = 0;
            for (int j = 0; j < n; ++j) {
                bit = (bit + ((A[j] >> i) & 1)) % 3;
            }
            single_number += (bit << i);
        }
        return single_number;
    }
};
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