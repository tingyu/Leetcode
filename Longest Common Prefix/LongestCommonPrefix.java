/**
Longest Common Prefix 

Write a function to find the longest common prefix string amongst an array of strings.

*/

//my solution:
//Runtime: 496 ms
/*
使用一个String[] strs同样长度的array来保存同样位置的char的值，不断的更新
先遍历这个String的array找到最短的那个string，把它的长度设为len,这样最长的common prefix string一定不会超过这个值
然后遍历String[]中所有的String，将array存满
然后通过Arrays.sort(array);来得到排序后的，排序后我们只用考虑第一个元素和最后一个。如果他们相等，那么这些值一定都相等，如果不相等那么就返回

这个复杂度是多少？？
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String res="";
        if(strs == null || strs.length == 0) return res;
        char[] array = new char[strs.length];
        int len = Integer.MAX_VALUE;
        //find smallest length
        for(int i = 0; i < strs.length; i++){
            len = Math.min(strs[i].length(), len);
        }
        
        for(int i = 0; i < len; i++){
            for(int j = 0; j < strs.length; j++){
                array[j] = strs[j].charAt(i);
            }
            Arrays.sort(array);
            if(array[0] == array[strs.length -1]){
                res = res + array[0];
            }else{
                return res;
            }
        }
        return res;
    }
}


/*
http://gongxuns.blogspot.com/2012/12/leetcodelongest-common-prefix.html

A Java solution by checking the common prefix char-by-char:

Starting from the first char of the first string, and check if all other strings have it in the same position. 
Loop over each char. 
这个方法看着更为简单
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = strs.length;
        if(n==0) return "";
        int i=0;
        for(i=0;i<strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(i>=strs[j].length() || strs[j].charAt(i)!=c) return strs[0].substring(0,i);
            }
        }
        return strs[0].substring(0,i);
    }
}
