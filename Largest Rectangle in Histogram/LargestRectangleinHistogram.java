/**
Largest Rectangle in Histogram

https://oj.leetcode.com/problems/largest-rectangle-in-histogram/
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
*/

/*
http://blog.csdn.net/abcbc/article/details/8943485
解法一是穷举法，对于直方图的每一个右边界，穷举所有的左边界。将面积最大的那个值记录下来。时间复杂度为O(n^2). 
单纯的穷举在LeetCode上面过大集合时会超时。可以通过选择合适的右边界，做一个剪枝(Pruning)。
观察发现当height[k] >= height[k - 1]时，无论左边界是什么值，选择height[k]总会比选择height[k - 1]所形成的面积大。
因此，在选择右边界的时候，首先找到一个height[k] < height[k - 1]的k，然后取k - 1作为右边界，穷举所有左边界，找最大面积。
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
    	int area = 0; 
    	for(int i = 0; i < height.length; i++){//i指的是右边界
    		for(int k = i + 1; k < height.length; k++){
    			if(height[k] < height[k - 1]){//经典判断和解法
    				i = k - 1;
    				break;
    			}else{
    				i = k;
    			}
    		}
    		int lowest = height[i];
    		for(int j = i; j>=0; j--){//j来确定左边界，这里是固定右边界之后往左边找
    			if(height[j] < lowest){//这里是固定右边界往左找找到高度最小的
    				lowest =height[j];
    			}
    			int currArea = (i - j + 1) * lowest;
    			if(currArea > area){
    				area = currArea;
    			}
    		}
    	}
   	}
   	return area;
}

/*
虽然上面的解法可以过大集合，但是不是最优的方法，下面介绍使用两个栈的优化解法。时间复杂度为O(n).
此解法的核心思想为：一次性计算连续递增的区间的最大面积，并且考虑完成这个区间之后，考虑其前、后区间的时候，不会受到任何影响。也就是这个连续递增区间的最小高度大于等于其前、后区间。
这个方法非常巧妙，最好通过一个图来理解：
*/

public int largestRectangleArea(int[] height){
	int area = 0;
	Stack<Integer> heightStack = new Stack<Integer>();
	Stack<Integer> indexStack = new Stack<Integer>();

	for(int i = 0; i < height.length; i++){
		if(heightStack.empty() || heightStack.peek() <= height[i]){
			heightStack.push(height[i]);
			indexStack.push(i);
		}else if(heightStack.peek() > height[i]){
			int j = 0;
			while(!heightStack.empty() && heightStack.peek() > height[i]){
				j = indexStack.pop();
				int currArea = (i - j)*heightStack.pop();
				if(currArea > area){
					area = currArea;
				}
			}
			heightStack.push(height[i]);
			indexStack.push(j);
		}
	}
	while(! heightStack.empty()){
		int currArea = (height.length - indexStack.pop())*heightStack.pop();
		if(currArea > area){
			area = currArea;
		}
	}
	return area;
}

/*
更新：
在网上发现另外一个使用一个栈的O(n)解法，代码非常简洁，栈内存储的是高度递增的下标。
对于每一个直方图高度，分两种情况。1：当栈空或者当前高度大于栈顶下标所指示的高度时，当前下标入栈。
否则，2：当前栈顶出栈，并且用这个下标所指示的高度计算面积。而这个方法为什么只需要一个栈呢？
因为当第二种情况时，for循环的循环下标回退，也就让下一次for循环比较当前高度与新的栈顶下标所指示的高度，注意此时的栈顶已经改变由于之前的出栈。
*/

