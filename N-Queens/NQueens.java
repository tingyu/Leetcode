/**
N-Queens

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

*/

/*
http://blog.csdn.net/linhuanmars/article/details/20667175
原题链接: http://oj.leetcode.com/problems/n-queens/ 
N皇后问题是非常经典的问题了，记得当时搞竞赛第一道递归的题目就是N皇后。因为这个问题是典型的NP问题，所以在时间复杂度上就不用纠结了，肯定是指数量级的。

下面我们来介绍这个题的基本思路。
主要思想就是一句话：用一个循环递归处理子问题。这个问题中，在每一层递归函数中，我们用一个循环把一个皇后填入对应行的某一列中，
如果当前棋盘合法，我们就递归处理先一行，找到正确的棋盘我们就存储到结果集里面。
这种题目都是使用这个套路，就是用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除，
这道题目中不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，
如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。

这道题最后一个细节就是怎么实现检查当前棋盘合法性的问题，因为除了刚加进来的那个皇后，前面都是合法的，我们只需要检查当前行和前面行是否冲突即可。
检查是否同列很简单，检查对角线就是行的差和列的差的绝对值不要相等就可以。代码如下： 
*/
public class Solution {
    public List<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        helper(n, 0, new int[n], res);
        return res;
    }

	//columnForRow store the columns for each row, 用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，
    private void helper(int n, int row, int[] columnForRow, ArrayList<String[]> res){
    	if(row == n){  //the nth row
    		String[] item = new String[n]; //store all row strings
    		for(int i = 0; i < n; i++){ //iterate through row
				StringBuilder strRow = new StringBuilder(); //store String in one row
				for(int j = 0; j < n; j++){ //iterate through columns in each row
					if(columnForRow[i] == j) //check the Q situated column in row i column j
						strRow.append('Q');
					else
						strRow.append('.');
				}
				item[i] = strRow.toString(); //convert each row to a string   			
    		}
    		res.add(item); //put string[] to arraylist, res store different solutions
    		return;
    	}
    	for(int i = 0; i < n; i++){ //for each row, iterate through each column
    		columnForRow[row] = i;
    		if(check(row, columnForRow)){ //given row and column, check whether it is valid
    			helper(n, row+1, columnForRow, res); //if it is valid, columnForRow[row] = i;is stored, we only need to go to anthoer row
    		}
    	}
    }

	//given row and column, check whether it is valid
    private boolean check(int row, int[] columnForRow){
    	for(int i = 0; i < row; i++){
    		//检查是否同列(iterate from top(0throw) to row, to check in each position)，检查对角线, 就是行的差和列的差的绝对值不要相等就可以
    		if(columnForRow[row] == columnForRow[i] || Math.abs(columnForRow[row] - columnForRow[i])== row - i)
    			return false;
    	}
    	return true;
    }
}


/*
http://blog.csdn.net/u011095253/article/details/9158473
首先我们弄清楚一下输出，最终输出是ArrayList<String[]>，每一个解是String[], 每一个解的每一行是String

我们当然可以采用上一题，Word Search里更新board内容的方法，不过更新来更新去比较麻烦，这一题我们换个思路

我们把这一题分成几个小问题

1. 传统的dfs递归

2. 验证放置Queen的地方是否合法

3. 输出Board结果

这么做的好处就是，一开始，我们不用建立一个庞大的Board，我们选用一个数组对应Board里面的每一行，数组每一个值对应这一行放置Queen的列号

比如： int[ ] {3,1,4,2} 代表放置的地点分别为[1,3], [2,1], [3,4], [2,4] 这么一来，我们用很简单的用数组表示了整个Board，而且在isValid函数里判断的时候会非常简洁，而且把输出Board单独隔离了出来
*/

public class Solution {  
    public ArrayList<String[]> solveNQueens(int n) { 
		ArrayList<String[]> res = new ArrayList<String[]>();
		int[] loc = new int[n];
		dfs(res, loc, 0, n);
		return res; 
    }

    public void dfs(ArrayList<String[]> res, int[] loc, int cur, int n){
    	if(cur = n)
    		printboard(res, loc, n);
    	else{
    		for(int i = 0; i < n; i++){
    			loc[cur] = i; //in row cur, put Queen in position i. Then check all the positions
    			if(isValid(loc, cur)){ //if is valid, go to next row
    				dfs(res, loc, cur+1, n);
    			}
    		}
    	}
    }

    public boolean isValid(int[] loc, int cur){
    	for(int i = 0; i < cur; i++){
    		if(loc[i] == loc[cur] || Math.abs(loc[i] - loc[cur]) == (cur - i))
    			return false;
    	}
    	return true;
    }

    public void printboard(ArrayList<String[]> res, int[] loc, int n){
    	String[] ans = new String[n];
    	for(int i = 0; i < n; i++){
    		String row = new String();
    		for(int j = 0; j < n; j++){
    			if(j == loc[i]) row+="Q";
    			else row +=".";
    		}
    		ans[i] = row;
    	}
    	res.add(ans);
    }
}

/*


来分析一下代码：

dfs的循环是指这一行里，从第一列到最后一列放置的所有可能，如果放置的地点通过isValid验证，通过cur+1进入下一行进行递归， 如果没通过验证，试下一个位置，如果所有位置都不Valid，跳回上一层

采用int[ ]的好处是，每一次我们只需改变一个数字就相当于改变了棋子的放置位置

isValid函数，首先int[ ]代表行，这样就避免了每一行出现重复的Queen （因为你不可能在一个int里面放2个值）这样简化了验证 接下来我们只需验证列和对角线

验证列的时候，要验证这一行之前的行有没有重复的（注意是验证之前的喔）

验证对角线，根据对角线性质，长 ＝ 宽 那么我们不难写出 Math.abs(loc[i] - loc[cur]) == (cur - i) 

最后loc［］里面记录的是解的信息（如果有解）我们把它转换成String, 输出Board即可
*/