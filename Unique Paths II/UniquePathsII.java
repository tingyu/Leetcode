/**
Unique Paths II

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/*
思路和unique Path很像，但是不同的是需要多考虑很多特殊情况，比如说如果该点有obstacle，
那么就把该点的dp[i][j]设为0，因为走不了，然后在第一列和第一行的时候出现了特殊情况，如果一个是0，那么它后面的也都是0.
*/

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        
        int[][] dp = new int[row][column];
        
        //左上角的点
        if(obstacleGrid[0][0] == 1){
            dp[0][0] = 0;
        }else{
            dp[0][0] = 1;
        }
        
        //第一列，如果有个点是有障碍，那么它后面的点都无法到达，就是0.用dp[i][0] = dp[i-1][0];描述会比较好
        for(int i = 1; i < row; i++){
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else{
                dp[i][0] = dp[i-1][0];
            }
        }
        
        //第一行，理由同上
        for(int j = 1; j < column; j++){
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }else{
                dp[0][j] = dp[0][j-1];
            }
        }

        //loop
        for(int i = 1; i < row; i++){
            for(int j = 1; j < column; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[row -1][column -1];
    }
}


//一个写的更短的解法，但是本质上是差不多的,不过没前面的方法好懂
//https://github.com/rffffffff007/leetcode/blob/master/Unique%20Paths%20II.java
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[][] grid = obstacleGrid;
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;
        int[][] sum = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    if(i == 0 && j == 0){
                        sum[i][j] = 1;
                    }else{
                        sum[i][j] = (i > 0 ? sum[i - 1][j] : 0) + (j > 0 ? sum[i][j - 1] : 0);
                    }
                }
            }
        }
        if(m == 0 || n == 0){
            return 0;
        }else{
            return sum[m - 1][n - 1];
        }
    }
    
}