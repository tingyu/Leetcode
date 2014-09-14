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
        //注意交换条件：1.A[i]要不大于A.length，不然会越界 2，A[i] > 0同理 3.A[A[i] - 1] != A[i]不然会死循环
 		if(A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]){
 			int temp = A[A[i] - 1];
 			A[A[i] - 1] = A[i];
 			A[i] = temp;
 			i--;
 		}
 	}
 	
    for(int i = 0; i < A.length; i++){
        if(A[i] != i+1){
            return i+1;
        }
    }

    return A.length + 1;
 }


 /*
 实现中还需要注意一个细节，就是如果当前的数字所对应的下标已经是对应数字了，那么我们也需要跳过，
 因为那个位置的数字已经满足要求了，否则会出现一直来回交换的死循环。这样一来我们只需要扫描数组两遍，时间复杂度是O(2*n)=O(n)，
 而且利用数组本身空间，只需要一个额外变量，所以空间复杂度是O(1)。
这道题个人还是比较喜欢的，既有一点算法思想，在实现中又有一些注意细节，而且整体来说模型比较简单，很适合在面试中出现。
*/

//my solution
//代码有些乱七八糟的，还改了半天才对。还是有些边界条件没考虑好
public class Solution {
    public int firstMissingPositive(int[] A) {
        int i = 0;
        int right = A.length-1;
        while(i <= right){
            if(A[i] > 0 && A[i] <= A.length){
                if(A[i]-1 != i && A[A[i] -1] != A[i]){
                    swap(A, i, A[i]-1);   
                }else{
                    i++;   
                }
            }else{
                swap(A, i, right);
                right--;
            }
        }
        
        for(i = 0; i < A.length; i++){
            if(A[i] -1 != i){
                return i+1;
            }
        }
        return A.length +1;
    }
    
    private void swap(int[] A, int a, int b){
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
