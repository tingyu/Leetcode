/**
Surrounded Regions

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

/*
本题思路：从四条边上为'O'的点开始遍历(BFS/DFS)字符为‘O’的点，被遍历到的'O'点均为未被'X'包围的点，其余'O'点需要被替换为'X'。

但是使用DFS进行遍历的话，在测试时，会出现StackOverflow，只好采用BFS。
http://mattcb.blogspot.com/2013/02/surrounded-regions.html
*/


public class Solution {
    public void solve(char[][] board) {
    	if(board == null || board.length == 0) return;
    	int row = board.length - 1;
    	int col = board[0].length - 1;
    	for(int i = 0; i <= row; i++){
    		bfs(board, i, 0);//left
    		bfs(board, i, col);//right
    	}
    	for(int j = 1; j < col; i++){
    		bfs(board, 0, j);//top
    		bfs(board, row, j);//bottom
    	}

    	for(int i = 0; i <= row; i++){
    		for(int j = 0; j <= col; j++){
    			if(board[i][j]=='O') board[i][j] = 'X';
    			else if(board[i][j] == '+') board[i][j] = 'O';
    		}
    	}
    }

    public void dfs(char[][] board, int x, int y){
    	Queue<Integer[]> queue = new LinkedList<Integer[]>();
    	if(board[x][y]=='0')
    		queue.add(new Integer[]{x, y});
    	while(queue.size() > 0){
    		Integer[] curr = queue.poll();
    		x = curr[0]; 
    		y = curr[1];
    		board[x][y] = '+';
    		if(x - 1>= 0 && board[x-1][y] == '0')
    			queue.add(new Integer[]{x-1, y});
    		if(y - 1 >=0 && board[x][y-1] == '0')
    			queue.add(new Integer[]{x, y-1});
    		if(x + 1 < board.length && board[x+1][y] == '0')
    			queue.add(new Integer[]{x+1, y});
    		if(y + 1 < board.length && board[x][y+1] == '0')
    			queue.add(new Integer[]{x, y+1});
    	}
    } 
}

//another solution
public class Solution {
	class Pair{
		int i;
		int j;

		Pair(int i, int j){
			this.i = i;
			this.j = j;
		}

		private int M;
		private int N;
	}

    public void solve(char[][] board) {
    	if(board == null || board.length <= 0){
    		return;
    	}

    	this.M = board.length;
    	this.N = board[0].length;

    	Queue<Pair> queue = new LinkedList<Pair>();
    	for(int i = 0; i < M; i++){
    		if(board[i][0] == '0'){
    			queue.add(new Pair(i, 0));
    			bfs(queue, board);
    		}
    		if(board[i][N - 1] == '0'){
    			queue.add(new Pair(i, N - 1));
    			bfs(queue, board);
    		}
    	}

    	for(int j = 1; j < N - 1; ++j){
    		if(board[0][j] == '0'){
    			queue.add(new Pair(0, j));
    			bfs(queue, board);
    		}
    		if(board[M - 1][j] == '0'){
    			queue.add(new Pair(M - 1, j));
    			bfs(queue, board);
    		}
    	}

    	for(int i = 0; i < M; ++i){
    		for(int j = 0; j < N; ++j){
    			if(board[i][j] == '0'){
    				board[i][j] = 'X';
    			}else if(board[i][j] == '#'){
    				board[i][j] = '0';
    			}
    		}
    	}
    }

    private void bfs(Queue<Pair> queue, char[][] board){
    	while(!queue.isEmpty()){
    		Pair pair = queue.poll();
    		int i = pair.i;
    		int j = pair.j;

    		if(i < 0 || i >= M || j < 0 || j >= N || board[i][j] != '0'){
    			continue;
    		}

    		board[i][j] = '#';
    		queue.add(new Pair(i - 1, j));
    		queue.add(new Pair(i + 1, j));
    		queue.add(new Pair(i, j - 1));
    		queue.add(new Pair(i, j + 1));
    	}
    }
}


///run dfs on all four side(the first outer layer of the matrix) mark +
//scan whole matrix, if see O, mark X else if +, mark O
//dfs
//LTE


public class Solution {
    public void solve(char[][] board) {
		if(board == null || board.length == 0) return;
		int row = board.length - 1;
		int col = board[0].length - 1;
		for(int i = 0; i <= row; i++){
			if(board[i][0] == 'O')
				dfs(board, i, 0);//left
			if(board[i][col] == 'O')
				dfs(board, i, col);//right
		}

		for(int j = 1; j < col; j++){
			if(board[0][j] == 'O')
				dfs(board, 0, j);//top
			if(board[row][j] == 'O')
				dfs(board, row, j);//bottom
		}

		for(int i = 0; i <= row; i++){
			for(int j = 0; j <= col; j++){
				if(board[i][j] == '0') board[i][j] = 'X';
				else if(board[i][j] == '+') board[i][j] = 'O';
			}
		}
	}

	public void dfs(char[][] board, int x, int y){
		board[x][y] = '+';
		if(x-1>=0 && board[x-1][y] == 'O')
			dfs(board, x-1, y);
		if(y-1>=0 && board[x][y-1] == '0')
			dfs(board, x, y-1);
		if(x+1<=board.length && board[x+1][y] == '0')
			dfs(board, x+1, y);
		if(y+1<=board[0].length && board[x][y+1] == '0')
			dfs(board, x, y+1)；
	}
}
