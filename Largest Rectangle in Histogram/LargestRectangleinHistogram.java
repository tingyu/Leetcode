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
