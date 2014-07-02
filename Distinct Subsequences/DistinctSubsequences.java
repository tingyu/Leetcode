/**
Distinct Subsequences 

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/*
http://blog.csdn.net/abcbc/article/details/8978146
遇到这种两个串的问题，很容易想到DP。但是这道题的递推关系不明显。
可以先尝试做一个二维的表int[][] dp，用来记录匹配子序列的个数（以S ="rabbbit",T = "rabbit"为例）：
    r a b b b i t
  1 1 1 1 1 1 1 1
r 0 1 1 1 1 1 1 1
a 0 0 1 1 1 1 1 1
b 0 0 0 1 2 3 3 3
b 0 0 0 0 1 3 3 3
i 0 0 0 0 0 0 3 3
t 0 0 0 0 0 0 0 3  
从这个表可以看出，无论T的字符与S的字符是否匹配，dp[i][j] = dp[i][j - 1].
就是说，假设S已经匹配了j - 1个字符，得到匹配个数为dp[i][j - 1].现在无论S[j]是不是和T[i]匹配，匹配的个数至少是dp[i][j - 1]。
除此之外，当S[j]和T[i]相等时，我们可以让S[j]和T[i]匹配，然后让S[j - 1]和T[i - 1]去匹配。所以递推关系为：
dp[0][0] = 1; // T和S都是空串.
dp[0][1 ... S.length() - 1] = 1; // T是空串，S只有一种子序列匹配。
dp[1 ... T.length() - 1][0] = 0; // S是空串，T不是空串，S没有子序列匹配。
dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0).1 <= i <= T.length(), 1 <= j <= S.length()


上面的那个图， Ti Sj
i表示选到第几个子串，相应的位置表示的是[0, i], [0, j] substring
那么第一行的时候就是[0, 0]的子串，表示子串是空的情况。子串是空的时候，不管S是什么，得到的匹配子串的个数都是1， 所以第一行所有的数都填上1
第一列就是S都是空的情况，如果S是空，除了子串是空的情况下，匹配个数是1；其他情况下S是空，但是子串不是空，所有的匹配个数都是0
下面一行子串是r的情况，不管S是r,还是ra,还是rab,还是rabb,还是rabbb,还是rabbbi,还是rabbbit，那么和子序列匹配的个数都是1，因为只有一个r
同理如果子串是ra,就是第三行的情况。如果S是空或者S是r的时候，都没办法匹配的，因为子串T比S还要长。如果S是ra,这时开始匹配，后面的rab, rabb,
rabbb, rabbbi,rabbbit的时候都是匹配的，不过只有最开始的ra是匹配的，所以是1
下面到了子串是rab的情况，首先S是空，r, ra的时候都没法匹配的。都是0. rab的时候正好匹配，这时写上1； rabb的时候发现有两个匹配，此时是2，rabbb
的时候有三个b和前面的匹配的，这时是3； 后面的rabbbit和rabbbi的时候都和前面的情况相同，因为后面it子串没有，还是靠前面的rabbb来进行匹配的。

然后子串是rabb的时候，S是空，或r, ra, rab的时候都没法匹配的，所以都是0.然后rabb的时候开始匹配，是1； 然后rabbb这时bbb和bb之间可以有3种匹配
然后后面rabbbi,rabbbit都是和rabbb情况一样，都是3

rabbi的时候，只有从rabbbi开始起来匹配，匹配的情况还是靠bb和bbb之间三种组合，所以是3，然后S是rabbbit的时候也是3

最后rabbit因为有t,所有只有到了rabbbit结尾t的时候才会匹配。不过由于里面的bb和bbb所以有三种情况
*/


public class Solution {
    public int numDistinct(String S, String T) {
		int[][] dp = new int[T.length() + 1][S.length() + 1];//dp一般都这样多加一个

		//初始化
		dp[0][0] = 1;        
		for(int i = 1; i <= T.length(); i++){
			dp[i][0] = 0;
		}

		for(int j = 1; j <= S.length(); j++){
			dp[0][j] = 1;
		}

		for(int i = 1; i <= T.length(); i++){
			for(int j = 1; j <= S.length(); j++){
				dp[i][j] = dp[i][j-1];
				if(T.charAt(i -1) == S.charAt(j -1)){
					dp[i][j] += dp[i-1][j-1];
				}
			}
		}
		return dp[T.length()][S.length()];//dp一般都是保存中间结果，避免重复计算，然后结尾的时候返回右下角推导得到的最后的数据
    }
}