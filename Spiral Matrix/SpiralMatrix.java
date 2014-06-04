/**
Spiral Matrix 

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

*/

//my solution
/*
Java Solution 1

If more than one row and column left, it can form a circle and we process the circle. 
Otherwise, if only one row or column left, we process that column or row ONLY.
*/
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        
        int x = 0, y = 0;
        while(m > 0 && n > 0){
        //for(int i = m; i > 0; i-=2){
        //    for(int j = n; j > 0; j-=2){
                //if(i == 1 && j == 1){
                //    res.add(matrix[x][y]);
                //}
        	//if one row/column left, no circle can be formed
                if(m==1){
                    for(int k = 0; k < n; k++){
                        res.add(matrix[x][y++]);    
                    }
                    break;
                }
                if(n==1){
                    for(int k = 0; k < m; k++){
                        res.add(matrix[x++][y]);    
                    }
                    break;
                }

                //below, process a circle
                
                //top - move right
                for(int k = 0; k < n -1; k++){
                    res.add(matrix[x][y++]);
                }
                
                //right - move down
                for(int k = 0; k < m - 1; k++){
                    res.add(matrix[x++][y]);
                }
                
                //bottom - move left
                for(int k = 0; k < n - 1; k++){
                    res.add(matrix[x][y--]);
                }
                
                //left - move up
                for(int k = 0; k < m - 1; k++){
                    res.add(matrix[x--][y]);
                }
                x++;
                y++;
                m-=2;
                n-=2;
           // }
        }
        return res;
    }
}


/*
http://www.programcreek.com/2013/01/leetcode-spiral-matrix-java/

We can also recursively solve this problem. The solution's performance is not better 
than Solution or as clear as Solution 1. Therefore, Solution 1 should be preferred.
*/
public class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        if(matrix==null || matrix.length==0) 
            return new ArrayList<Integer>();
 
        return spiralOrder(matrix,0,0,matrix.length,matrix[0].length);
    }
 
 
    public ArrayList<Integer> spiralOrder(int [][] matrix, int x, int y, int m, int n){
        ArrayList<Integer> result = new ArrayList<Integer>();
 
        if(m<=0||n<=0) 
            return result;
 
        //only one element left
        if(m==1&&n==1) {
            result.add(matrix[x][y]);
            return result;
        }
 
        //top - move right
        for(int i=0;i<n-1;i++){
            result.add(matrix[x][y++]);
        }
 
        //right - move down
        for(int i=0;i<m-1;i++){
            result.add(matrix[x++][y]);
        }
 
        //bottom - move left
        if(m>1){    
            for(int i=0;i<n-1;i++){
                result.add(matrix[x][y--]);
            }
        }
 
        //left - move up
        if(n>1){
            for(int i=0;i<m-1;i++){
                result.add(matrix[x--][y]);
            }
        }
 
        if(m==1||n==1)
            result.addAll(spiralOrder(matrix, x, y, 1, 1));
        else    
            result.addAll(spiralOrder(matrix, x+1, y+1, m-2, n-2));
 
        return result;
    }
}

