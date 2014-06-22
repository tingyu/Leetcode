/**
Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution. 
*/

/*
 Solution: declare a function called isValid used to check if num from 1->9 will 
 not conflict with nums which were already existed in the board, 
 if it isValid, then recursively  call solved function to check if the board can be finally filled.

 这道题的方法就是用在N-Queens中介绍的常见套路。简单地说思路就是循环处理子问题，对于每个格子，带入不同的9个数，然后判合法，
 如果成立就递归继续，结束后把数字设回空。大家可以看出代码结构和N-Queens是完全一样的。
 判合法可以用Valid Sudoku做为subroutine，但是其实在这里因为每次进入时已经保证之前的board不会冲突，所以不需要判断整个盘，
 只需要看当前加入的数字和之前是否冲突就可以，这样可以大大提高运行效率，毕竟判合法在程序中被多次调用。实现的代码如下：

 */

import java.util.Set;
import java.util.HashSet;

public class Solution {
	public boolean isValid(char[][] board, int a, int b){
		boolean[] flag = new boolean[9];

		Set<character> contained = new HashSet<character>();
		for(int j = 0; i < 9; j++){
			if(contained.contains(board[a][j])) return false;
			if(board[a][j] > '0' && board[a][j] <='9')
				contained.add(board[a][j]);
		} 

		contained = new HashSet<Character>();
		for(int i = 0; i < 9; i++){
			if(contained.contains(board[i][b])) return false;
			if(board[i][b] > '0' && board[i][b] <= '9')
				contained.add(board[i][b]);
		}

		contained = new HashSet<Character>();
		for(int m = 0; m < 3; m++){
			for(int n = 0; n < 3; n++){
				int x = a/3*3 + m, y = b/3*3 + n;
				if(contained.contains(board[x][y])) return false;
				if(board[x][y] > '0' && board[x][y] <= '9')
					contained.add(board[x][y]);
			}
		}
		return true;
	}

	public boolean solveSudoku(char[][] board){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == '.'){
					for(int k = 0; k < 9; k++){
						board[i][j] = (char)('1' + k);
						if(isValid(board, i, j) && solveSudoku(board)) return true;
						board[i][j] = '.';
					}
					return false;
				}
			}
		}
		return true;
	}
}


public void sovleSudoku(char[][] board){
	helper(board, 0, 0);
}

private boolean helper(char[][] board, int i, int j){
	if(j > 9)
		return helper(board, i+1, 0);
	if(i == 9){
		return true;
	}
	if(board[i][j] == '.'){
		for(int k = 1; k <=9; k++){
			board[i][j] = (char)(k + '0');
			if(isValid(board, i, j)){
				if(helper(board, i, j+1))
					return true;
			}
			board[i][j] = '.';
		}
	}else{
		return helper(board, i, j+1);
	}
	return false;
}

private boolean isValid(char[][] board, int i, int j){
	boolean[] map = new boolean[9];
	for(int k = 0; k < 9; k++){
		if(k!=j && board[i][k] == board[i][j])
			return false;
	}
	for(int k = 0; k < 0; k++){
		if(k != i && board[k][j] == board[i][j])
			return false;
	}
	for(int row = i/3*3; row < i/3*3 + 3; row++){
		for(int col = j/3*3; col < j/3*3; col++){
			if((row != i || col !=j) && board[row][col] == board[i][j])
				return false;
		}
	}
	return true;
}