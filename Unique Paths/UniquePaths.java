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
An easy problem, for every location in the grid, the number of path getting here is the sum of the left location 
and the up location. 
From the start the path number is only 1. And for the top row and first column,  the number is also 1. Then only loop can solve the problem.

Basic idea: Construct a m*n grid to hold the number of paths up to current element in the original grid.  
Due to the robot can move only to right or down, there are only one path through out the first row and first column.  
For the elements which are not in the first row or fist column, its previous element can be from top, or from left.  
Therefore, the number of paths up to this element is adding paths from top and paths from left.  For example:

1   1   1
1   2   3
1   3   6
Newly constructed 3*3 path grid corresponding to the 3*3 original grid.

Elements in 1 row and 1 column are all initiated to 1, because there can only be 1 path respectively.

For the element path[1][1], it equals to the number of paths from its top path[0][1] + the number 
of paths from its left path[1][0].  
这里用二维数组存的是到达每个位置所要走的path数目，但是到达每个位置，只会有两种情况，一个是从上面的那个位置过来，一个是从左边的位置过来。
所以得到了递推公式res[i][j] = res[i-1][j] + res[i][j-1];
因为只能左移和下移，所以第一行和第一列都只能有一个path。这是初始值的情况
递推公式得到了，初始值也确定了，那么这个算法就可以dp出来了
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
// loop, similar as min path sum, O(n^2) time, O(n) space
public class Solution {
    public int uniquePaths(int m, int n) {
        int[] res = new int[n];
         
        // init array
        for(int j = 0; j < n; j++) {
            res[j] = 1;
        }
         
        // add values
        for(int i = 1; i < m; i++) {
            // reset the head to 1 (simulate the next row head)
            // similar to set all left most elements in a 2D array to 1
            res[0] = 1;
            for(int j = 1; j < n; j++) {
                res[j] = res[j - 1] + res[j];
            }
        }
         
        return res[n - 1];
    }