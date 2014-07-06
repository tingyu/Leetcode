/**
Maximal Rectangle

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.

*/

/*
http://blog.csdn.net/fightforyourdream/article/details/17711893
 Analysis:
This is not an easy problem and the idea is not straightforward.
Since the question requires the area of all ones region, first we can define the region (rectangle) in the matrix.
Any region in the matrix has several basic properties:   1 corner point,  width, and length.
Also which corner point it is decide the exact place of the region, here we consider the bottom-right corner point,
because we usually scan the matrix from (0,0) to right and down direction. 
For this problem, we also need to consider the "all ones" requirement, where all the regions are filled with 1s.

We can then have this data structure:
One 2D array(vector) ones[i][j],  where ones[i][j] stores the number of contiguous 1s ended at matrix[i][j], 
along ith row. e.g.  if matrix[i] = "1011101", then ones[i]=1,0,1,2,3,0,1. 
This array stores the length of the rectangle, for every possible bottom-right corner point.
ones存的是rectangle的宽度，第i行在j位置结尾的连续的i的数目

Having this two values, next is to find the height of the rectangle, as well as keep the "all ones" requirement.
Start with a corner point [i][j],  since it is the bottom right corner, the rectangle search direction is left and up.
For the left we already have the number of contiguous 1s ones[i][j]. 
How to get the height?  We need to scan all the values above ones[i][j], it is from i-1 to 0. 
If meets 0, then stop, else compute the possible rectangle area, and store the max. 
To compute the area, the minimum length of rectangle should be compared and stores every time.

搜索方向：从右下开始，一直到左上
*/


public class Solution {
    public int maximalRectangle(char[][] matrix) {
		int rows = matrix.length;
		if(rows == 0)
			return 0;

		int cols = matrix[0].length;
		//先计算全1矩阵的宽度
		int[][] hOnes = new int[rows][cols]; //horizontal ones

		int max = 0; //最大面积
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(matrix[i][j] == '1'){
					if(j == 0){//特殊处理左起第一个
						hOnes[i][j] = 1;
					}else{
						hOnes[i][j] = hOnes[i][j-1] + 1;
					}
				}
			}
		}

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(hOnes[i][j] != 0){
					int minI = i; //计算高度，minI不断向上走
					int minRowWidth = hOnes[i][j]; //随着向上走，宽度也要随着调整
					while(minI >= 0){
						minRowWidth = Math.min(minRowWidth, hOnes[minI][j]);
						int area = minRowWidth*(i - minI + 1);
						max = Math.max(max, area);
						minI--;
					}
				}
			}
		}
		return max;
    }
}

//another Solution
//http://blog.csdn.net/muscler/article/details/23641887
/*
如图所示，对于点A来说，向上连续为1的红箭头长度为4，H[4][1] = 4。这个红箭头向左右拓展为矩形，能移动到最左边的位置L[4][1] = 1, 
能移动到最右边的位置R[4][1] = 2(L包含在矩阵内，R不包含)。点A对应的矩形面积就是H * (R - L) = 4。

对于点B，H[3][2] = 3, L[3][2] = 1, R[3][2] = 4。对应的面积H * (R - L) = 9。遍历所有点，求最大的面积。
因为计算是只会用一次H, L, R的数据，所以用一维数组而不是二维数组来计算。
*/
public class Solution {
    public int maximalRectangle(char[][] matrix) {
    	if(matrix == null || matrix.length == 0)
    		return 0;

    	int m = matrix.length;
    	int n = matrix[0].length;
    	int[] H = new int[n];
    	int[] L = new int[n];
    	int[] R = new int[n];

    	for(int i = 0; i < n; i++){
    		L[i] = 0;
    		H[i] = 0;
    		R[i] = n;
    	}

    	int res = 0;
    	for(int i = 0; i < m; i++){
    		int left = 0;
    		int right = n;
    		for(int j = 0; j < n; j++){
    			if(matrix[i][j] == '1'){
    				H[j]++;
    				L[j] = Math.max(L[j], left);
    			}else{
    				H[j] = 0;
    				L[j] = 0;
    				R[j] = n;
    				left = j + 1;
    			}
    		}
    		for(int j = n -1; j >=0; --j){
    			if(matrix[i][j] == '1'){
    				R[j] = Math.min(R[j], right);
    				res = Math.max(res, H[j]*(R[j] - L[j]));
    			}else{
    				right = j;
    			}

    		}
    	}
    	return res;
    }
}


//另外一个很好的办法：http://www.cnblogs.com/lichen782/p/leetcode_maximal_rectangle.html
//这个里面解说很详细
//和 Largest Rectangle in Histogram方法类似
//和http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n + 1];
        //actually we know that height can just be a int[n+1], 
        //however, in that case, we have to write the 2 parts together in row traverse,
        //which, leetcode just doesn't make you pass big set
        //所以啊，leetcode是喜欢分开写循环的，即使时间复杂度一样，即使可以节约空间
        int maxArea = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0'){
                    height[i][j] = 0;
                }else {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }
        for(int i = 0; i < m; i++){
            int area = maxAreaInHist(height[i]);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
     }
     
     private int maxAreaInHist(int[] height){
         Stack<Integer> stack = new Stack<Integer>();
         int i = 0;
         int maxArea = 0;
         while(i < height.length){
             if(stack.isEmpty() || height[stack.peek()] <= height[i]){
                 stack.push(i++);
             }else {
                 int t = stack.pop();
                 maxArea = Math.max(maxArea, height[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
             }
         }
         return maxArea;
     }


//http://rleetcode.blogspot.com/2014/01/maximal-rectangle-java.html
