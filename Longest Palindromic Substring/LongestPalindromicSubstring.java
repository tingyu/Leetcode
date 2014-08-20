/**
Longest Palindromic Substring

Given a string S, find the longest palindromic substring in S. You may assume that 
the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

/*
http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
Finding the longest palindromic substring is a classic problem of coding interview. 

1. Naive Approach

Naively, we can simply examine every substring and check if it is palindromic. T
he time complexity is O(n^3). If this is submitted to LeetCode onlinejudge, 
an error message will be returned - "Time Limit Exceeded". 
Therefore, this approach is just a start, we need better algorithm.
*/

public static String longestPalindrome1(String s) {
	int maxPalinLength = 0;
	String longestPalindrome = null;
	int length = s.length();

	//check all possible sub strings
	for(int i = 0; i < length; i++){
		for(int j = i + 1; j < length; j++){
			int len = j - i;
			String curr = s.substring(i, j+1);
			if(isPalindrome(curr)){
				if(len > maxPalinLength){
					longestPalindrome = curr;
					maxPalinLength = len;
				}
			}
		}
	}
	return longestPalindrome;
}

public static boolean isPalindrome(String s){
	for(int i = 0; i < s.length() - 1; i++){
		if(s.charAt(i) != s.charAt(s.length()-i-1)){//why -1?因为需要的是index 
			return false;
		}
	}
	return true;
}


2. Dynamic Programming
/*
Let s be the input string, i and j are two indices of the string.

Define a 2-dimension array "table" and let table[i][j] denote whether substring from i to j is palindrome.

Start condition:

table[i][i] == 1;
table[i][i+1] == 1  => s.charAt(i) == s.charAt(i+1) 
Changing condition:

table[i][j] == 1 => table[i+1][j-1] == 1 && s.charAt(i) == s.charAt(j)
Time O(n^2) Space O(n^2)
*/

public static String longestPalindrome2(String s) {
	if(s == null) return null;

	if(s.length() <= 1){
		return s;
	}

	int maxLen = 0;
	String longestStr= null;

	int length = s.length();

	int[][] table = new int[length][length];

	//every single letter is palindrome
	for(int i = 0; i < length; i++){
		table[i][i] = 1;
	}
	printTable(table);

	//e.g. bcba
	//twp consecutive same letter are palinedrome
	for(int i = 0; i < length - 2; i++){
		if(s.charAt(i) == s.charAt(i+1)){
			table[i][i+1] = 1;
			longestStr = s.substring(i, i+2);
		}
	}
	printTable(table);

	//condition for calculate the whole table
	for(int l = 3; l <= length; l++){
		for(int i = 0; i <= length -1; i++){
			int j = i + l - 1;
			if(s.charAt(i) == s.charAt(j)){
				table[i][j] = table[i+1][j-1]; //why?
				if(table[i][j] == 1 && l > maxLen)
					longestStr = s.substring(i, j+1);
			}else{
				table[i][j] = 0;
			}
			printTable(table);
		}
	}
	return longestStr;
}


public static void printTable(int[][] x){
	for(int[] y: x){
		for(int z: y){
			System.out.print(z + " ");
		}
		System.out.println();
	}
	System.out.println("-------");
}


Given an input, we can use printTable method to examine the table after each iteration. For example, if input string is "dabcba", the final matrix would be the following:

1 0 0 0 0 0 
0 1 0 0 0 1 
0 0 1 0 1 0 
0 0 0 1 0 0 
0 0 0 0 1 0 
0 0 0 0 0 1 
From the table, we can clear see that the longest string is in cell table[1][5].


3. Simple Algorithm

Time O(n^2), Space O(1)
遍历数组，分别以每个点为中心（分i 和i ,i +1）的情况，来得到相应的longest substring
public String longestPalindrome(String s) {
	if(s.isEmpty()){
		return null;
	}

	if(s.length == 1){
		return s;
	}

	String longest = s.substring(0, 1);
	for(int i = 0; i < s.length(); i++){
		//get longest palindrome with center of i 
		String tmp = helper(s, i ,i);
		if(tmp.length() > longest.length()){
			longest = tmp;
		}

		//get longest palindrome with center of i, i + 1
		tmp = helper(s, i, i+1);
		if(tmp.length() > longest.length()){
			longest = tmp;
		}
	}
	return longest;
}

//given a center, either one letter or two letter
//find longest palindrome
public String helper(String s, int begin, int end){
	while(begin >= 0 && end <= s.length() -1 && s.charAt(begin) == s.charAt(end)){
		begin--;
		end++;
	}
	return s.substring(begin+1, end);//because we have one more loop for begin-- and end++, it will restore to last one here
}



4. Manacher's Algorithm

Manacher's algorithm is much more complicated to figure out, even though it will bring benefit of time complexity of O(n).

Since it is not typical, there is no need to waste time on that.



//my solution, why it is wrong??
public class Solution {
    public String longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        String res = "";
        for(int i = 0; i < s.length(); i++){
            dp[i][i] = 1;
        }
        
        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j < s.length(); j++){
                if(j == i + 1 && s.charAt(j) == s.charAt(i)){
                    dp[i][j] = 1;
                }
            }
        }
        
        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j < s.length(); j++){
                if(dp[i+1][j-1] == 1 && s.charAt(j) == s.charAt(i)){
                    dp[i][j] = 1;
                }
            }
        }
        
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < s.length(); j++){
                if(dp[i][j] == 1){
                    if(max < j - i + 1){
                        max = j - i + 1;
                        res = s.substring(i, j + 1);   
                    }
                }
            }
        }
        return res;
    }
}