/**

Search a 2D Matrix
Total Accepted: 10883 Total Submissions: 35685

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

Given target = 3, return true.
*/

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        
        if(target < matrix[0][0] || target > matrix[m-1][n-1])
            return false;
            
        for(int i = 0; i < m; i++){
        	if(i+1<m){
                if(target > matrix[i][n-1] && target <matrix[i+1][0])
                    return false;
        	}
            if(target == matrix[i][0] || target == matrix[i][n-1])
                return true;
            if(target > matrix[i][0] && target < matrix[i][n-1]) 
                row = i;
        }
        
        for(int j = 0; j < n; j++){
            if(target == matrix[row][j])
                return true;
        }
        
        return false;
    }
}



public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) { 
        
        if(matrix == null || matrix.length==0 || matrix[0].length==0)
            return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int start = 0;
        int end = m*n -1;
        
        while(start <= end){
            int mid = (start + end)/2;
            int midX = mid/n;
            int midY = mid%n;
            
            if(matrix[midX][midY] == target)
                return true;
            
            if(matrix[midX][midY] < target){
                start = mid +1;
            }else{
                end = mid -1;
            }
        }
        return false;
    }
}