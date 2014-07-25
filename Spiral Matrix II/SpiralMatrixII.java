/**
Spiral Matrix II

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,
You should return the following matrix:

[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

*/
/*
要是发现实在不好想的话考虑一下更新二维数组的两个坐标啊啊啊啊
*/
public class Solution {
    public int[][] generateMatrix(int n) {
    	if(n < 0) return null;
    	int[][] res = new int[n][n];

    	int start = 1, x = 0, y = 0;
    	for(int i = n; i > 0; i-=2){//i = i-2因为每次循环之后都减少了两行或者两列
    		if(i == 1){
    			res[x][y] == start;//处理最后一个值
    		} else{
    			for(int j = 0; j < i - 1; j++){//注意是i-1不是n-1
    				res[x][y++] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x++][y] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x][y--] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x--][y] = start++;
    			}
    			x++;//因为前面的x--多减去了，所以这里要加回来
    			y++;
    		}
    	}  
    	return res;
    }
}