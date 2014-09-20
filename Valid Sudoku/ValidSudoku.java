/**
Valid Sudoku

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
*/

/*
首先要知道Sudoku的规则
http://sudoku.com.au/TheRules.aspx
There are just 3 rules to Sudoku.
Each row must have the numbers 1-9 occuring just once.
Each column must have the numbers 1-9 occuring just once.
And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.

基本上是根据规则来进行判断，先判断每一行，再每一列，再3*3的小grid。基本上考的时array的操作。
有些tricky的是3*3的，还有如何判断有效的，选择合适的数据结构

1. 我的解法是把1~9的数字放到hashSet中，然后如果有的话就remove，如果找不到
就false,如果是'.'就continue
HashSet<Character> set = new HashSet<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
2. use HashSet.add to determine (if duplicate value added, method would return false)
3. use boolean array[265] (representing all the ASCII characters) to determine.  
Set boolean value true when new number (character) read.  
If the value has already true, then a duplicate value read.
*/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = new HashSet<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            set = new HashSet<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
            for(int j = 0; j < n; j++){
                if(isValid(board[i][j], set) == false){
                    return false;
                }
            }
        }
        
        for(int j = 0; j < n; j++){
            set = new HashSet<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
            for(int i = 0; i < m; i++){
                if(isValid(board[i][j], set) == false){
                    return false;
                }
            }
        }
        
        for(int x = 0; x < m/3; x++){
            for(int y = 0; y < n/3; y++){
                set = new HashSet<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
                for(int i = 3*x; i < 3*x+3; i++){
                    for(int j = 3*y; j < 3*y+3; j++){
                        if(isValid(board[i][j], set) == false){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char c, HashSet<Character> set){
        if(c == '.') return true;
        else if(set.contains(c)){
            set.remove(c);
        }else{
            return false;
        }
        return true;
    }
}

/*
http://blog.csdn.net/u010500263/article/details/18905027
This problem is strait forward, we need only implementing all the 3 rules of Sudoku.

The only thing we need to concern about is how to determine if numbers (in each rule)  are duplicated.  
Accordingly, there are 2 approaches:

1. use HashSet.add to determine (if duplicate value added, method would return false)

2. use boolean array[265] (representing all the ASCII characters) to determine.  
Set boolean value true when new number (character) read.  
If the value has already true, then a duplicate value read.
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

//my another Solution
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //check row
        for(int i = 0; i < board.length; i++){
            HashSet<Character> set = new HashSet<Character>();
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] >= '1' && board[i][j] <= '9'){
                    if(set.add(board[i][j])) continue;
                    else return false;
                }
            }
        }
        
        //check col
        for(int j = 0; j < board[0].length; j++){
            HashSet<Character> set = new HashSet<Character>();
            for(int i = 0; i < board.length; i++){
                if(board[i][j] >= '1' && board[i][j] <= '9'){
                    if(set.add(board[i][j])) continue;
                    else return false;
                }
            }
        }
        
        //check 3*3
        for(int i = 0; i < board.length; i += 3){
            for(int j = 0; j < board[0].length; j += 3){
                HashSet<Character> set = new HashSet<Character>();
                for(int k = i; k < i + 3; k++){
                    for(int l = j; l < j + 3; l++){
                        if(board[k][l] >= '1' && board[k][l] <= '9'){
                            if(set.add(board[k][l])) continue;
                            else return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}