/**
ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

*/

/*
http://blog.csdn.net/linhuanmars/article/details/21145039
这道题是cc150里面的题目了，其实比较简单，只要看出来他其实每个zigzag是2*m-1个字符就可以，这里m是结果的行的数量。
接下来就是对于每一行先把往下走的那一列的字符加进去，然后有往上走的字符再加进去即可。时间复杂度是O(n),空间复杂度是O(1),代码如下：

What we need to do is to observe the pattern of each row. It can be found that for each row, 
it has a period of 2∗nRows−2 , i.e., characters that are  2∗nRows−2 apart are in the same row. 
The first character in the first period is s[i] , where i is the row index. 
Except for the top and the bottom rows, there is (possibly) a second character in each period 
that is period−2∗i apart from the first one. With this pattern, we can work top-down and pick up 
characters at certain indices to make up the converted string. 
*/


public class Solution {
    public String convert(String s, int nRows) {
        if(s == null || s.length() ==0 || nRows <=0)
            return "";
        
        if(nRows == 1){
            return s;
        }
        
        StringBuilder res = new StringBuilder();
        int size = 2*nRows - 2;
        for(int i = 0; i < nRows; i++){
            for(int j = i; j < s.length(); j+=size){
                res.append(s.charAt(j));
                if(i != 0 && i != (nRows -1) && (j + size -2*i) <s.length()){
                    res.append(s.charAt(j + size -2*i));
                }
            }
        }
        return res.toString();
    }
}