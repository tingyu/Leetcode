/**
Container With Most Water 

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.

*/

/*这里跟自己想的有些不一样，这里不是找所有里面最短的，只需要找两个边界更短的一个就可以了
/*
The most strait forward approach is calculating all the possible areas and keep the max one as the result.  
This approach needs O(n*n) time complexity, which could not pass OJ (Time limit exceed).
*/

public class Solution {
    public int maxArea(int[] height) {
        if(height.length < 2) return 0;
        int max = 0;
        int area = 0;
        
        for(int i = 0; i < height.length-1; i++){
            for(int j = i + 1; j < height.length; j++){
                area = (j - i)*Math.min(height[i], height[j]);
                max = Math.max(area, max);
            }
        }
        return max;
    }
}

/*
After reviewing the available solution in Internet, there is so called "closing into the centre" approach.  
Idea of which is set two pointer at the start and end of the array (which I has thought about that), 
then move the shorter pointer each iteration.  The idea be hide this movement is that 
if we move the longer line, no matter how many steps we move, the new area would be smaller 
than the one before movement.  
This is because area = (end-start)*min(height[start], height[end]) <- after move, 
(end-start) decrease, min(height[start], height[end]) remains no change, still the "shorter line".
Remark: O(n). 
这题的关键就在于不断的比较两端比较小的高度，然后移动那个高度。因为如果移动高度比较高的，最终的高度还是取决于短板，所以面积一定是减小的。
*/


public class Solution {
    public int maxArea(int[] height) {
        if(height.length < 2) return 0;
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        
        while(end > start){
            int area = (end - start)*Math.min(height[end], height[start]);
            max = Math.max(max, area);
            
            if(height[end] < height[start]) end--;
            else start++;
        }
        return max;
    }
}