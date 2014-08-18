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

number[i-1]应该是一下走一步的情况，前面一个character有一个解码方式，下面的一个character单独的也只有一个解码方式
number[i-2]应该是一下子走两步的情况，所以如果这两个character里面第一个不是0的时候才算数。然后还要检查他们是不是在有效字母之间
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
			//检查当前字符是不是'0'  
			tmp = Integer.parseInt(s.substring(i - 1, i));
			if(tmp != 0){
				number[i] = number[i -1];
			}

			//检查当前字符和前一个字符组合在一起是否在1-26之间 
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

//another solution
//http://gongxuns.blogspot.com/2012/12/leetcodedecode-ways.html
//Time is O(n) and space is O(1). 
public class Solution {
    public int numDecodings(String s) {
    	if(s.length() == 0) return 0;
    	int[] hist = new int[2];
    	hist[0] = 1;
    	hist[1] = 1;

    	for(int i = 0; i < s.length(); i++){
    		int temp = 0;
    		if(s.charAt(i) != '0')
    			temp += hist[1];
    		if(i > 0){
    			int a = Integer.parseInt(s.substring(i - 1, i + 1));
    			if(s.charAt(i - 1)!= '0' && a <= 26 && a > 0)
    				temp+ = hist[0];
    		}
    		hist[0] = hits[1];
    		hist[1] = temp;
    	}
    	return hist[1];
    }
}


public class Solution {
    public int numDecodings(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int res = 1, prev=1;
        if(s.equals("") || s.length()==0||s.charAt(0)=='0') return 0;
        if(s.equals("1")) return 1;
        for(int i =1;i<s.length();i++){
            int temp = res;
            int num = (s.charAt(i-1)-'0') *10 + (s.charAt(i)-'0');
            if(s.charAt(i)=='0') res=0;
            if(num>=10 && num<=26)
                res +=prev;//climbing stair and fib problem
            prev = temp;
            //two zeros
            if(res ==0 && prev == 0)
                return 0; 
        }
        return res;
    }
}
//another solution
//http://rleetcode.blogspot.com/2014/01/decode-ways-java.html

