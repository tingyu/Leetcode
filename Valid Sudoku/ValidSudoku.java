/**
Valid Sudoku

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
*/

/*
http://blog.csdn.net/u010500263/article/details/18905027
This problem is strait forward, we need only implementing all the 3 rules of Sudoku.

The only thing we need to concern about is how to determine if numbers (in each rule)  are duplicated.  Accordingly, there are 2 approaches:

1. use HashSet.add to determine (if duplicate value added, method would return false)

2. use boolean array[265] (representing all the ASCII characters) to determine.  Set boolean value true when new number (character) read.  If the value has already true, then a duplicate value read.
*/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //rule 1, column
        for(int i = 0; i < board[0].length; i++){
        	HashSet<Character> test = new HashSet<Character>();
        	for(int j = 0; j < board.length; j++){
        		if(board[j][i] != '.' && !test.add(board[j][i])) return false;
        	}
        }

        //rule 2, row
        for(int i = 0; i < board.length; i++){
        	HashSet<Character> test = new HashSet<Character>();
        	for(int j = 0; j < board[0].length; j++){
        		if(board[i][j] != '.' && !test.add(board[i][j])) return false;
        	}
        }

        //rule 3, sub-box
        for(int i = 0; i < 3; i++){
        	for(int j = 0; j < 3; j++){ //for each sub box
        		HashSet<Character> test = new HashSet<Character>();
        		for(int m = i*3; m < i*3 + 3; m++){ //row
        			for(int n = j*3; n < j*3 +3; n++){ // column
        				if(board[m][n] !='.' && !test.add(board[m][n])) return false;
        			}
        		}
        	}
        }

        return true;
    }
}


// Approach2:
public class Solution {
    public boolean isValidSudoku(char[][] board) {
 		//rule 1, column
 		for(int i = 0; i < board[0].length; i++){
 			boolean[] dupCheck = new boolean[256];
 			for(int j = 0; j < board.length; j++){
 				if(board[j][i] != '.'){
 					if(dupCheck[board[j][i]] == true) return false;
 					else dupCheck[board[j][i]] = true;
 				}
 			}
 		}

 		//rule 2, row
 		for(int i = 0; i < board.length; i++){
 			boolean[] dupCheck = new boolean[256];
 			for(int j = 0; j < board[0].length; j++){
 				if(board[i][j] != '.'){
 					if(dupCheck[board[i][j]] == true) return false;
 					else dupCheck[board[i][j]] = true;
 				}
 			}
 		}

 		//rule 3, sub-box
 		for(int i = 0; i < 3; i++){
 			for(int j = 0; j < 3; j++){
 				boolean[] dupCheck = new boolean[256];
 				for(int m = i*3; m < i*3 + 3; m++){
 					for(int n = j*3; n < j*3 + 3; n++){
 						if(board[m][n] != '.'){
 							if(dupCheck[board[m][n]] == true) return false;
 							else dupCheck[board[m][n]] = true;
 						}
 					}
 				}
  			}
 		}

 		return true;
    }
}