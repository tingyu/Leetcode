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
//my solution
//a naive solution, LTE
public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null || height.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < height.length; i++){
            int minH = height[i];
            for(int j = i; j < height.length; j++){
                minH = Math.min(minH, height[j]);
                int area = minH*(j-i+1);
                max = Math.max(area, max);
            }
        }
        return max;
    }
}

//使用双指针向里面移动的也LTE了
public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null || height.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        
        int minH = getMinHeight(height, 0, height.length-1);
        for(int i = 0; i < height.length; i++){
            minH = Math.min(minH, height[i]);
        }
        int i = 0;
        int j = height.length-1;
        while(i <= j){
            int area = minH*(j - i + 1);
            max = Math.max(max, area);
            if(height[i] <= height[j]){
                if(height[i] == minH){
                    minH = getMinHeight(height, i, j);
                }
                    i++;
            }else{
                if(height[j] == minH){
                    minH = getMinHeight(height, i, j);
                }
                    j--;
            }
        }
        return max;
    }
    
    private int getMinHeight(int[] height, int start, int end){
        int min = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            min = Math.min(min, height[i]);
        }
        return min;
    }
}

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
    				i = k;//如果不加上这句就会超时
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
stack的解法，应该看这个更好
http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html

16行，给跪了。。。。
这个我不去debug下都特么不知道在干嘛。
那要不就debug下看看这段代码在做神马。例子就用题目中的[2,1,5,6,2,3]吧。
首先，如果栈是空的，那么索引i入栈。那么第一个i=0就进去吧。注意栈内保存的是索引，不是高度。然后i++。
然后继续，当i=1的时候，发现h[i]小于了栈内的元素，于是出栈。（由此可以想到，哦，看来stack里面只存放单调递增的索引）
这时候stack为空，所以面积的计算是h[t] * i.t是刚刚弹出的stack顶元素。也就是蓝色部分的面积。
继续。这时候stack为空了，继续入栈。注意到只要是连续递增的序列，我们都要keep pushing，直到我们遇到了i=4，h[i]=2小于了栈顶的元素。
这时候开始计算矩形面积。首先弹出栈顶元素，t=3。即下图绿色部分。
接下来注意到栈顶的（索引指向的）元素还是大于当前i指向的元素，于是出栈，并继续计算面积，桃红色部分。
最后，栈顶的（索引指向的）元素大于了当前i指向的元素，循环继续，入栈并推动i前进。直到我们再次遇到下降的元素，也就是我们最后人为添加的dummy元素0.
同理，我们计算栈内的面积。由于当前i是最小元素，所以所有的栈内元素都要被弹出并参与面积计算。

注意我们在计算面积的时候已经更新过了maxArea。

总结下，我们可以看到，stack中总是保持递增的元素的索引，然后当遇到较小的元素后，依次出栈并计算栈中bar能围成的面积，直到栈中元素小于当前元素。

可是为什么这个方法是正确的呢？ 我也没搞清楚。只是觉得不明觉厉了。
那h[t]无疑就是Stack.Peek和t之间那些上流社会的短板啦，而它们的跨越就是i - Stack.Peek - 1。

所以说，这个弹栈的过程也是维持程序不变量的方法啊：栈内元素一定是要比当前i指向的元素小的。
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }else{
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t]*(stack.isEmpty() ? i: i - stack.peek() - 1));
            }
        }
        return maxArea;
    }
}

/*
虽然上面的解法可以过大集合，但是不是最优的方法，下面介绍使用两个栈的优化解法。时间复杂度为O(n).
此解法的核心思想为：一次性计算连续递增的区间的最大面积，并且考虑完成这个区间之后，考虑其前、后区间的时候，不会受到任何影响。
也就是这个连续递增区间的最小高度大于等于其前、后区间。
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

// O(n) using one stack  
  // O(n) using one stack
  public int largestRectangleArea(int[] height) {
    // Start typing your Java solution below
    // DO NOT write main() function
    int area = 0;
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    for (int i = 0; i < height.length; i++) {
      if (stack.empty() || height[stack.peek()] < height[i]) {
        stack.push(i);
      } else {
        int start = stack.pop();
        int width = stack.empty() ? i : i - stack.peek() - 1;
        area = Math.max(area, height[start] * width);
        i--;
      }
    }
    while (!stack.empty()) {
      int start = stack.pop();
      int width = stack.empty() ? height.length : height.length - stack.peek() - 1;
      area = Math.max(area, height[start] * width);      
    }
    return area;
  }


