/**

Longest Consecutive Sequence
Total Accepted: 11367 Total Submissions: 41784

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity. 
*/

/*
Thoughts

Because it requires O(n) complexity, we can not solve the problem by sorting the array first. 
Sorting takes at least O(nlogn) time.

Java Solution 1

We can use a HashSet to add and remove elements. HashSet is implemented by using a hash table. 
Elements are not ordered. The add, remove and contains methods have constant time complexity O(1).
*/

public int longestConsecutive(int[] num) {
	Set<Integer> set = new HashSet<Integer>();
	int max = 1;

	for(int e: num){
		set.add(e);
	}

	for(int e: num){
		int left = e -1;
		int right = e + 1;
		int count = 1;

		while(set.contains(left)){
			count ++;
			set.remove(left);
			left--;
		}

		while(set.contains(right)){
			count++;
			set.remove(right);
			right++;
		}		

		max = Math.max(count, max);
	}

	return max;
}

//my sort Solution
//如果相等就continue，如果当前是前面加1，就len++，否则的话就len = 1，更新max。
//里面有两个特殊情况： 1，重复的元素也是算的但是不增加长度，要continue。
//2，如果一直++到结尾，那么就不会走else，所以需要在循环结束之后再判断下
//另外Arrays.sort()是merge sort, nlog(n)复杂度
public class Solution {
    public int longestConsecutive(int[] num) {
        Arrays.sort(num);
        
        int len = 1;
        int max = 1;
        for(int i = 1; i < num.length; i++){
            if(num[i-1] == num[i]) continue;
            if(num[i-1] + 1 == num[i]){
                len++;
            }else{
                max = Math.max(max, len);
                len = 1;
            }
        }
        max = Math.max(max, len);
        return max;
    }
}


//my soltion for O(n)
public class Solution {
    public int longestConsecutive(int[] num) {
        //HashSet<Integer> set = new HashSet<Integer>(Arrays.asList(new int[num.length](num))));//这种初始化咋弄
        HashSet<Integer> set = new HashSet<Integer>();
        for(int e: num){
            set.add(e);
        }
        
        int max = 0;
        for(int e: num){
            int count = 1;
            int left = e-1;
            int right = e+1;
            while(set.contains(left--)){
                count++;
                set.remove(left);
            }
            while(set.contains(right++)){
                count++;
                set.remove(right);
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
//for(int e: set){开始写成这样会报错。不要一边遍历，一边在set里面删除东西
//java.util.ConcurrentModificationException


//my solution, why it is wrong
public class Solution {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < num.length; i++){
            set.add(num[i]);
        }
        
        int max = 0;
        for(int i = 0; i < num.length; i++){
            int less = num[i];
            int more = num[i];
            while(set.contains(less - 1)){
                less--;
            }
            while(set.contains(more + 1)){
                more++;
            }
            max = Math.max(max, more - less + 1);
        }
        return max;
    }
}