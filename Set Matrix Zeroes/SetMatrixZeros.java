/**
Set Matrix Zeroes


Set Matrix Zeroes
Total Accepted: 10035 Total Submissions: 33222

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.
Follow up:

Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

//My solution
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] setCol = new boolean[n];
        boolean[] setRow = new boolean[m];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    setRow[i] = true;
                    setCol[j] = true;
                }
            }
        }
        
        for(int j = 0; j < n; j++){
            if(setCol[j] == true){
                for(int i = 0; i < m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        
        for(int i = 0; i < m; i++){
            if(setRow[i] == true){
                for(int j = 0; j < n; j++){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

//another solution of mine
//和上面的算法相比减少了空间复杂度，时间复杂度还是O(mn)
/*
思路，因为要用in-place就想着用这个matrix本身来存储。想到的是把所有的0映射到第一行和第一列，但是要注意处理有些本身就在第一行和第一列的情况。
这两种情况要分开讨论
分析见下面这里描述
http://yucoding.blogspot.com/2013/04/leetcode-question-97-set-matrix-zeros.html
Analysis:
To reduce the space required, we can use the matrix itself to store the flags for each row 
and column if they need to set to 0. So we need 1 row and 1 column, the 1st row and 1st column 
then can be chosen to store the flag. But we need first check the two if they need to be 0. 
Then go the other rows and columns.

e.g.

1 0 2 3 4 5
2 0 2 3 4 5
3 1 2 3 4 5

First check 102345 and
1
2
3
use two flags storing the status.  fr0 = true, fc0=false;
then check the rest of matrix, use the 1st col and 1st row store the status.
1 0 2 3 4 5
0 0 2 3 4 5

1 1 2 3 4 5
Then set 0s to sub-matrix(excludes 1st row and 1st column), according to the values in 1st row and 1st column, 
and finally set 1st row and 1st column according to flags.

The new space used is O(1+1) = O(1).
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        for(int i = 0 ; i < m; i++){
            if(matrix[i][0] == 0){
                firstColZero = true;
            }
        }
        
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0){
                firstRowZero = true;
            }
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int j = 1; j < n; j++){
            if( matrix[0][j] == 0){
                for(int i = 0; i < m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        
        for(int i = 1; i < m; i++){
            if( matrix[i][0] == 0){
                for(int j = 0; j < n; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(firstRowZero == true){
            for(int j = 0; j < n; j++){
                matrix[0][j] = 0;
            }
        }
        
        if(firstColZero == true){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}

//Runtime: 492 ms
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        
        for(int i = 0; i < matrix.length; i++){
            if(row[i] == true){
                for(int j = 0; j <matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        
        for(int j = 0; j < matrix[0].length; j++){
            if(column[j] == true){
                for(int i = 0; i <matrix.length; i++){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}


/*
Thoughts about This Problem

This problem can solve by following 4 steps:

    check if first row and column are zero or not
    mark zeros on first row and column
    use mark to set elements
    set first column and row by using marks in step 1
*/
    //Runtime: 500 ms
public class Solution {
    public void setZeroes(int[][] matrix) {
    	boolean firstRowZero = false;
    	boolean firstColumnZero = false;

    	//set first row and column zero or not
    	for(int i = 0; i < matrix.length; i++){
    		if(matrix[i][0] == 0){
    			firstColumnZero = true;
    			break;
    		}
    	}

    	for(int i = 0; i < matrix[0].length; i++){
    		if(matrix[0][i] == 0){
    			firstRowZero = true;
    			break;
    		}
    	}

    	//mark zeros on first row and column
    	for(int i = 1; i< matrix.length; i++){
    		for(int j = 1; j < matrix[0].length; j++){
    			if(matrix[i][j] == 0){
    				matrix[i][0] = 0;
    				matrix[0][j] = 0;
    			}
    		}
    	}

    	//use mark to set elements
    	for(int i = 1; i < matrix.length; i++){
    		for(int j = 1; j < matrix.length; j++){
    			if(matrix[i][0] || matrix[0][j] == 0){
    				matrix[i][j] = 0;
    			}
    		}
    	}

    	//set first column and row
    	if(firstColumnZero){
    		for(int i = 0; i < matrix.length; i++){
    			matrix[i][0] = 0;
    		}
    	}

    	if(firstRowZero){
    		for(int j = 0; j < matrix[0].length; j++){
    			matrix[0][j] = 0;
    		}
    	}
    }
}