/**
N-Queens II

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

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