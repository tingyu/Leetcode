/**
Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/*
http://blog.csdn.net/worldwindjp/article/details/19938131

题意解析：给你一串数字，解码成英文字母。
类似爬楼梯问题，但要加很多限制条件。
定义数组number，number[i]意味着：字符串s[0..i-1]可以有number[i]种解码方法。
回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
但不同的是本题有多种限制：
第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。

1010，生成的number数组为：[1,1,1,1,1]
10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]
*/

public class Solution {
    public int numDecodings(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}       

		if(s.charAt(0) == '0'){
			return 0;
		} 

		int[] number = new int[s.length() + 1];
		number[0] = 1;
		number[1] = 1;
		int tmp;

		for(int i = 2; i <= s.length(); i++){
			tmp = Integer.parseInt(s.substring(i - 1, i));
			if(tmp != 0){
				number[i] = number[i -1];
			}

			if(s.charAt(i - 2) != '0'){
				tmp = Integer.parseInt(s.substring(i -2, i));
				if(tmp > 0 && tmp <= 26){
					number[i] += number[i -2];
				}
			}
		}
		return number[s.length()];
    }
}