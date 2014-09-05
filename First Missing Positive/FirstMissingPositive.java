/**
First Missing Positive 

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

//my solution:
//注意可能有重复的数字，去掉各种可能的情况
public class Solution {
    public int firstMissingPositive(int[] A) {
        if(A == null || A.length ==0)
            return 1;
            
        Arrays.sort(A);
        
        //如果最小的都大于1，直接返回1就可以
        if(A[0] > 1){
            return 1;
        }

        //从1到结尾
        for(int i = 1; i < A.length; i++){
        	//如果两个相等或者两个相差为1，或者当前的都小于0，或者当前的是1，前一个是-1（此时差的是0）；这些情况下continue
            if(A[i] - A[i-1] == 1 || A[i] == A[i-1] || A[i] < 0 || (A[i] == 1 && A[i-1] == -1)){
                continue;
            }else if(A[i-1] <= -1 && A[i] > 0 && A[i] >1){//如果前一个比-1小，后一个大于1，返回1
                return 1;   
            }else if(A[i-1]>=0){//其他情况，当所有比较的都>=0的时候，如果相差大于1，那么返回小的+1
                return A[i-1] +1;
            }
        }
        //以上情况都不成立，则说明一直都是连续的直到最后一个
        return A[A.length -1] + 1;
    }
}
/*
other solutions:
http://blog.csdn.net/kenden23/article/details/17099987
 这条题目虽然简单，但是思路还是很多的，可以开拓一下思路。
下面三种思路都是O(n)时间复杂度，测试运行时间基本上都没区别：
1 排序之后查找
2 把出现的数值放到与下标一致的位置，再判断什么位置最先出现不连续的数值，就是答案了。
3 和2差不多，把出现过的数值的下标位置做好标识，如果没有出现过的数组的下标就没有标识，那么这个就是答案。
 第一个思路最简单了

 Another solution:
 http://blog.csdn.net/linhuanmars/article/details/20884585
 这道题要求用线性时间和常量空间，思想借鉴到了Counting sort中的方法，不了解的朋友可以参见Counting sort - Wikipedia。
 既然不能用额外空间，那就只有利用数组本身，跟Counting sort一样，利用数组的index来作为数字本身的索引，
 把正数按照递增顺序依次放到数组中。即让A[0]=1, A[1]=2, A[2]=3, ... , 这样一来，
 最后如果哪个数组元素违反了A[i]=i+1即说明i+1就是我们要求的第一个缺失的正数。对于那些不在范围内的数字，
 我们可以直接跳过，比如说负数，0，或者超过数组长度的正数，这些都不会是我们的答案。
 */


 public int firstMissingPositive(int[] A) { 
 	if(A == null || A.length == 0){
 		return 1;
 	}

 	for(int i = 0; i < A.length; i++){
 		if(A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]){
 			int temp = A[A[i] - 1];
 			A[A[i] - 1] = A[i];
 			A[i] = temp;
 			i--;
 		}
 	}
 	
 }
