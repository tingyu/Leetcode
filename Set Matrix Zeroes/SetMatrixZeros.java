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