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

//my another solution
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        int k = 0;
        for(k = 0; k < m/2 && k < n/2; k++){
            for(int j = k; j < n-1-k; j++){
                res.add(matrix[k][j]);
                count++;
            }
            
            for(int i = k; i <m-1-k; i++){
                res.add(matrix[i][n-1-k]);
                count++;
            }
            
            for(int j = n-1-k; j > k; j--){
                res.add(matrix[m-1-k][j]);
                count++;
            }
            
            for(int i = m-1-k; i > k; i--){
                res.add(matrix[i][k]);
                count++;
            }
        }
        
        if(count < m*n && k == m/2){
            for(int j = k; j <= n-1-k; j++){
                res.add(matrix[k][j]);
                count++;
            }
        }
        if(count < m*n && k == n/2){
            for(int i = k; i <= m-1-k; i++){
                res.add(matrix[i][k]);
                count++;
            }
        }
        return res;
    }
}
/*
如果最后面conner case的处理情况for(int j = k; j <= n-1-k; j++){ 中没有加上等号，而是j < n-1-k那么肯定是错的。
此时无法处理只有一行或者一列的情况。加上=，在有多行多列的时候处理是对的，因为count会终止保证不会取到后面的.
在只有一行或者一列的时候，会取到最后面
Submission Result: Wrong Answer

Input:  [[1]]
Output: []
Expected:   [1]

*/

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

