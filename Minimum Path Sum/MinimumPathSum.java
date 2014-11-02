/**
 Leetcode - Minimum Path Sum

Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.
*/

// DP, O(n^2) time, O(n^2) space
public class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        
        int[][] res = new int[row][col];
        
        //init
        res[0][0] = grid[0][0];
        
        //left
        for(int i = 1; i < row; i++){
            res[i][0] = res[i-1][0] + grid[i][0];
        }
        
        //top
        for(int j = 1; j < col; j++){
            res[0][j] = res[0][j -1] + grid[0][j];
        }
        
        //rest elements
        for(int i = 1; i < row; i++){
            for(int j =1; j < col; j++){
                res[i][j] = grid[i][j] + Math.min(res[i-1][j], res[i][j-1]);
            }
        }
        
        return res[row-1][col-1];
    }
}


// DP, O(n^2) time, O(n) space
public class Solution {
    public int minPathSum(int[][] grid) {
        // DP, O(n^2) time, O(n) space
        
        int row = grid.length;
        int col = grid[0].length;
        
        int[] res = new int[col];
        
        //init
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        
        //res element
        for(int i =0; i < row; i++){
            res[0] = res[0] + grid[i][0];
            
            //loop through each element of each row
            for(int j = 1; j < col; j++){
                res[j] = grid[i][j] + Math.min(res[j], res[j-1]);
            } 
        }
        return res[col -1];
    }
}