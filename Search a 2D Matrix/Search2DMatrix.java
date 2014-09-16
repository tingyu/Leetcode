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
/*
binary search还是iterative的好，因为递归是有space开销的
 me:  stack多出logn空间？对。 binary search，左右各一半所以是logn
 */
//my solution
//1把二维数组展开称为一维数组，然后用二分查找
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int[] temp = new int[matrix.length* matrix[0].length];
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                temp[count++] = matrix[i][j];
            }
        }
        
        int l = 0;
        int r = temp.length -1;
        while(l <= r){
            int m = (l + r)/2;
            if(temp[m] == target) return true;
            if(target < temp[m]) r = m - 1;
            if(target > temp[m]) l = m + 1;
        }
        return false;
    }
}


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


//采用类似于数值运算的方法，reverse Integer的方法，来确定这个mid在二维数组中的具体位置
//            int midX = mid/n;
//            int midY = mid%n;
//二分法，不断的找mid在二维数组中的位置。
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

//my solution
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m*n - 1;
        
        while(left <= right){
            int mid = left + (right - left)/2;
            if(matrix[mid/n][mid%n] == target) return true;
            else if(matrix[mid/n][mid%n] > target) right = mid - 1;
            else left = mid + 1;
        }
        
        return false;
    }
}