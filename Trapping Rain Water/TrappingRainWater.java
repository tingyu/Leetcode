/**

Trapping Rain Water
https://oj.leetcode.com/problems/trapping-rain-water/

Total Accepted: 9014 Total Submissions: 32041

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*/

//my solution
//首先想到的是找到每个的左右边界，先确定left，找到第一个高度不是0的位置，然后不断检查后面i的高度。如果后面的高度是比left小，
//计算当前这个区域的面积。如果后面的高度比left大，那么久把这些累加的area放到sum中，更新left的位置，然后讲area置为0.
//里面有个特殊情况，就是在left到达最高点之后，右边的不可能有值比left要高，所以一直到结束都会不停累加area的值，但是不会加到sum中。
//此时需要再从右边往左边检查。采用类似的方法，一直到left位置即可。将右边累加的area加到left中间。

/*
扫一遍的就是用两个指针，同时从左往右，然后从右往左。每次比较，移动比较小的那个
*/
public class Solution {
    public int trap(int[] A) {
        if(A == null || A.length == 0) return 0;
        int left = 0;
        int right = A.length -1;
        int area = 0;
        int sum = 0;
        
        while(left < A.length && A[left] == 0){//这个其实没必要，因为下面for循环中已经处理了这种情况
            left++;
        }
        for(int i = left + 1; i <= right; i++){
            if(A[i] < A[left]){
                area += A[left] - A[i];
            }else{
                sum += area;
                area = 0;
                left = i;
            }
        }
        
        area = 0;
        for(int i = right - 1; i >= left; i--){
            if(A[i] < A[right]){
                area += A[right] - A[i];
            }else{
                sum += area;
                area = 0;
                right = i;
            }
        }
        return sum;
    }
}
/*
Analysis:

Key point of this problem is to find out the logic to compute water trapped in each "container".  

In order to trap the water, there has to be a left bound, a right bound.  
My original idea was to calculate the amount of trapped water element by element from left bound of the container till the right bound reached.  
For example, trapped water between Array[3] -> Array[7] (example of the problem) is (2-1) + (2-0) + (2-1) = 4.  

From this example, we can also find that left bound of this "container" is the previous highest value (Array[3]=2); 
right bound of the container is the current highest value (Array[7]=3).  
In the next step, we can move the left bound to Array[7], and repeat this computation till the next right bound (i.e. next highest bound) reached.

However, this approach ignore the water trapped in Array[9].  
Because when the left bound reached the highest value (assume it is the only highest value) in the array, 
there would not be right bound anymore to construct a "container".  
Therefore, the approach above can only compute the water trapped from left most to the highest value.  

To complet the whole computation, we can simply repeat the approach one  more time from right most to the highest value.  
*/

public class Solution {  
    public int trap(int[] A) {
    	if(A.length <=2) return 0;

    	int singleSum = 0; //one hole
    	int totalSum = 0; //result

    	//from left to right
    	int left = 0;
    	for(int i = 1; i < A.length; i++){
    		if(A[left] > A[i]){
    			singleSum += A[left] - A[i];
    		} else{
    			totalSum += singleSum;
    			left = i;
    			singleSum = 0;
    		}
    	}

    	//from right to left
    	singleSum = 0; // reset sigle sum
    	int right = A.length - 1;
    	for(int i = right - 1; i >= left; i--){//if we only use i> left, there's errors
    		if(A[right] > A[i]){
    			singleSum += A[right] - A[i];
    		} else{
    			totalSum += singleSum;
    			right = i;
    			singleSum = 0;
    		}
    	}

    	return totalSum;
    }
}  

