/**
Edit Distance

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/*
http://www.programcreek.com/2013/12/edit-distance-in-java/
From Wiki:

In computer science, edit distance is a way of quantifying how dissimilar two strings 
(e.g., words) are to one another by counting the minimum number of operations required 
to transform one string into the other.

There are three operations permitted on a word: replace, delete, insert. For example, 
the edit distance between "a" and "b" is 1, the edit distance between "abc" and "def" is 3. 
This post analyzes how to calculate edit distance by using dynamic programming.

Key Analysis

Let dp[i][j] stands for the edit distance between two strings with length i and j, 
i.e., word1[0,...,i-1] and word2[0,...,j-1].
There is a relation between dp[i][j] and dp[i-1][j-1]. 
Let's say we transform from one string to another. 
The first string has length i and it's last character is "x"; 
the second string has length j and its last character is "y". The following diagram shows the relation.

edit-distance-dynamic-programming

if x == y, then dp[i][j] == dp[i-1][j-1]
if x != y, and we insert y for word1, then dp[i][j] = dp[i][j-1] + 1
if x != y, and we delete x for word1, then dp[i][j] = dp[i-1][j] + 1
if x != y, and we replace x with y for word1, then dp[i][j] = dp[i-1][j-1] + 1
When x!=y, dp[i][j] is the min of the three situations.
Initial condition:
dp[i][0] = i, dp[0][j] = j


http://blog.csdn.net/u010500263/article/details/19658011
Base case:

str1.length == 0 ; str2.length == 0; => operation=0;

str1.length == k ; str2.length == 0; => operation=k; (delete characters from str1)

str1.length == 0; str2.length == k; => operation=k; (insert characters to str1)


After we have the base case, when we are calculating Edit Distance(str1+char1, str2+char2), we will have cases below:

if (char1 == char2) Edit Distance(str1+char1, str2+char2) = Edit Distance(str1, str2) (no additional operation is needed)

if (char1 != char2){

3 ways to calculate Edit Distance(str1+char1, str2+char2):

Edit Distance(str1+char1, str2+char2) = Edit Distance(str1, str2) + 1 (+1 here means to replace char1 by char2)
Edit Distance(str1+char1, str2+char2) = Edit Distance(str1+char1, str2) + 1 (+1 here means to insert char2 to str2)
Edit Distance(str1+char1, str2+char2) = Edit Distance(str1, str2+char2) + 1 (+1 here means to delete char1 from str1)
}
*/

public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        
        for(int i=0; i<dp.length; i++) dp[i][0] = i; // insert to word1 operations only
        for(int i=0; i<dp[0].length; i++) dp[0][i] = i; // delete from word1 operations only
        
        for(int i=1; i<=word1.length(); i++){
            for(int j=1; j<=word2.length(); j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{
                    int replace=1+dp[i-1][j-1];
                    int insert=1+dp[i][j-1];
                    int delete=1+dp[i-1][j];
                    dp[i][j]=Math.min((Math.min(replace, insert)), delete);
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}