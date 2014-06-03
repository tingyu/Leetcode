/**
Spiral Matrix II

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,
You should return the following matrix:

[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

*/

public class Solution {
    public int[][] generateMatrix(int n) {
    	if(n < 0) return null;
    	int[][] res = new int[n][n];

    	int start = 1, x = 0, y = 0;
    	for(int i = n; i > 0; i-=2){
    		if(i == 1){
    			res[x][y] == start;
    		} else{
    			for(int j = 0; j < i - 1; j++){
    				res[x][y++] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x++][y] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x][y--] = start++;
    			}

    			for(int j = 0; j < i - 1; j++){
    				res[x--][y] = start++;
    			}
    			x++;
    			y++;
    		}
    	}  
    	return res;
    }
}