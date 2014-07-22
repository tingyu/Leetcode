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

//http://www.programcreek.com/2013/01/leetcode-rotate-image-java/
/*
Naive Solution

In the following solution, a new 2-dimension array is created to store the rotated matrix, 
and the result is assigned to the matrix at the end. This is WRONG! Why?
*/
public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length==0)
            return ;
 
        int m = matrix.length;
 
        int[][] result = new int[m][m];
 
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                result[j][m-1-i] = matrix[i][j];
            }
        } 
 
        matrix = result;
    }
}
/*
The problem is that Java is pass by value not by refrence! "matrix" is just a 
reference to a 2-dimension array. If "matrix" is assigned to a new 2-dimension array in the method, 
the original array does not change. Therefore, there should be another loop to assign 
each element to the array referenced by "matrix". Check out "Java pass by value."
*/
public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length==0)
            return ;
 
        int m = matrix.length;
 
        int[][] result = new int[m][m];
 
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                result[j][m-1-i] = matrix[i][j];
            }
        } 
 
       for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                matrix[i][j] = result[i][j];
            }
        } 
    }
}

//in-place solution
//from former: result[j][m-1-i] = matrix[i][j];
// rule: i = j'; j = n-1 - i';
// that is: i' = n-1 -j; j' = i;
        // loop through 1/4 of the matrix
        // a | b
        // c | d
        // four parts
        // Math.ceil : returns the smallest integer >= a number.
//注意上面这个转换公式，然后不断应用这个转换公式
public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return;
        
        int n = matrix.length;
        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < Math.ceil((double)(n/2.0)); j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
}
