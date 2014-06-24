/**
Palindrome Partitioning II 

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/
/*
[Thoughts]
凡是求最优解的，一般都是走DP的路线。这一题也不例外。首先求dp函数，

定义函数
D[i,n] = 区间[i,n]之间最小的cut数，n为字符串长度

 a   b   a   b   b   b   a   b   b   a   b   a
                     i                                  n
如果现在求[i,n]之间的最优解？应该是多少？简单看一看，至少有下面一个解


 a   b   a   b   b   b   a   b   b   a   b   a
                     i                   j   j+1     n

此时  D[i,n] = min(D[i, j] + D[j+1,n])  i<=j <n。这是个二维的函数，实际写代码时维护比较麻烦。所以要转换成一维DP。如果每次，从i往右扫描，每找到一个回文就算一次DP的话，就可以转换为
D[i] = 区间[i,n]之间最小的cut数，n为字符串长度， 则,

D[i] = min(1+D[j+1] )    i<=j <n

有个转移函数之后，一个问题出现了，就是如何判断[i,j]是否是回文？每次都从i到j比较一遍？太浪费了，这里也是一个DP问题。
定义函数
P[i][j] = true if [i,j]为回文

那么
P[i][j] = str[i] == str[j] && P[i+1][j-1];


题意分析：对输入的字符串进行划分，要求划分后的所有的子字符串都是回文串。求最小划分的个数。
类似于：LeetCode Word Break， 也是利用动态规划。
定义状态数组：cut_num_array[s.length()+1]，其中：cut_num_array[i]代表：string[i..n]字符串从i开始到末尾的最小划分数。 
状态转移方程： cut_num_array[i] = Math.min(cut_num_array[i], cut_num_array[j+1]+1);  i<j<n
状态转移方程的意思是，string[i..j]是一个回文字符串，所以不用再划分。所以从i开始到末尾以j为划分点的最小划分数为： cut_num_array[j+1]+1 和 cut_num_array[i]中的最小值。
cut_num_array[i]的初值设为：s.length() - i; 也就是按照字符串中的每个字母都单独被划分来计算。
判断string[i..j]是一个回文串，用LeetCode Palindrome Partitioning中的方法
*/

public class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0)
            return 0;
        
        boolean[][] palindrome_map = new boolean[s.length()][s.length()];
        int[] cut_num_array = new int[s.length() + 1];
        
        for(int i = s.length() - 1; i >= 0; i--){
            cut_num_array[i] = s.length() - i;
            for(int j = i; j < s.length(); j++){
                if(s.charAt(i) == s.charAt(j)){
                    if((j - i) < 2|| palindrome_map[i+1][j-1] == true){
                        palindrome_map[i][j] = true;
                        cut_num_array[i] = Math.min(cut_num_array[i], cut_num_array[j+1] + 1);
                    }
                }
            }
        }
        return cut_num_array[0] -1;
    }
}
