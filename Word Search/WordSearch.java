/**
Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

/*
这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串。
这里注意每次从一个元素出发时要重置访问标记（也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。
深度优先搜索的算法就不再重复解释了，不了解的朋友可以看看wiki - 深度优先搜索。
我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的
（因为一个顶点有固定上下左右四条边）。加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，
空间上就是要用一个数组来记录访问情况，所以是O(m*n)。

http://www.aichengxu.com/article/Java/21198_2.html
 Applied Helper table and DFS to solve it, for each char in board, check if it is matched in word, if so, recursively check the rest chars in word.
*/
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() ==0) 
            return true;
        if(board == null || board.length == 0 || board[0].length == 0)
            return false;
        
        boolean[][] used = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(search(board, word, 0, i, j, used)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used){
        if(index == word.length()){
            return true;
        }
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j] || board[i][j] != word.charAt(index)){
            return false;
        }
        used[i][j] = true;
        boolean res = search(board, word, index + 1, i -1, j, used)
                    || search(board, word, index + 1, i + 1, j, used)
                    || search(board, word, index + 1, i, j - 1, used)
                    || search(board, word, index + 1, i, j + 1, used);
        used[i][j] =false;
        return res;
    }
}

/*
这道题其实还可以变一变，比如字符可以重复使用。准备的时候多联想还是比较好的，因为面试中常常会做完一道题会变一下问问，
虽然经常不用重新写代码，但是想了解一下思路，有兴趣的朋友可以想想哈。
*/