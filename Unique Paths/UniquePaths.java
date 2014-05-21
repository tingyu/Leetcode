/**

Unique Paths
Total Accepted: 10947 Total Submissions: 35829

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/

/**

Analysis:
An easy problem, for every location in the grid, the number of path getting here is the sum of the left location and the up location. 
From the start the path number is only 1. And for the top row and first column,  the number is also 1. Then only loop can solve the problem.
*/

// Solution #2: loop, O(n^2) space & time
// loop, similar as min path sum, O(n^2) time & space
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        //init left
        for(int i = 0; i < m; i++)
            res[i][0] = 1;
        
        //init top
        for(int j = 0; j < n; j++)
            res[0][j] = 1;
            
        for(int i = 1; i <m; i++){
            for(int j = 1; j < n; j++){
                res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        
        return res[m-1][n-1];
    }
}


// Solution #3: loop, O(n^2) time, O(n) space
// loop, similar as min path sum, O(n^2) time, O(n) space
