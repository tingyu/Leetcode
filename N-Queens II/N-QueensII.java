/**
N-Queens II

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

//my solution
public class Solution {
    int res;
    public int totalNQueens(int n) {
        //int res = 0;
        res = 0;
        int[] position = new int[n];
        helper(position, 0, n);
        return res;
    }
    
    private void helper(int[] position, int row, int n){
        if(row == n){
            res++;
            return;
        }
        
        for(int j = 0; j < n; j++){
            position[row] = j;
            if(check(position, row)){
                helper(position, row + 1, n);
            }
        }
    }
    
    private boolean check(int[] position, int row){
        for(int i = 0; i < row; i++){
            if(position[i] == position[row] || Math.abs(position[row] - position[i]) == (row -i)){
                return false;
            }
        }
        return true;
    }
}

//同样的思路，代码反而比Queen I 要简单，记得把res声明在totalNQueens外面

public class Solution {
    int res; // if I put the int res to totalNQueens and pass res, then always get 0 result, which is wrong
    public int totalNQueens(int n) {
        res = 0;
        if(n<=0) return res;  
        int[] loc = new int[n];
        dfs(loc, 0, n);
        return res;
    }
    
    public void dfs(int[] loc, int cur, int n){
        if(cur == n){
            res+=1;
            return;
        }else{
            for(int i = 0; i < n; i++){
                loc[cur] = i;
                if(isValid(cur, loc)){
                    dfs(loc, cur+1, n);
                }
            }
        }
    }
    
    public boolean isValid(int cur, int[] loc){
        for(int i = 0; i < cur; i++){
            if(loc[i] == loc[cur] || Math.abs(loc[i] - loc[cur]) == (cur - i))
                return false;
        }
        return true;
    }
}
