/**
Rotate Image
Total Accepted: 9443 Total Submissions: 30853

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/


//Naive Solution
public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix==null || matrix.length==0) return ;
        
        int n = matrix.length;
        int[][] result = new int[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j =0; j <n; j++){
                result[j][n-i-1] = matrix[i][j];
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j =0; j < n; j++){
                matrix[i][j]=result[i][j];
            }
        }
    }
}