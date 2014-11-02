/**
Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters
 in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/
/*
这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常
类似，同样是建立一个字典，然后维护一个窗口。
区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含
且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），
所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找
到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。
在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符
的数量，然后遇到字典中字符时就将对应字符的数量减一。
算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中
不会被访问多于两次。
空间复杂度则是O(字典的大小)，也就是代码中T的长度.

注意用一个count记录
*/

//http://mattcb.blogspot.com/2012/12/minimum-window-substring.html

public class Solution {
    public String minWindow(String S, String T) {
    	char[] Tset = new char[256];
    	char[] Sset = new char[256];
    	for(int i = 0; i < T.length(); i++){
    		Tset[T.charAt(i)]++;
    	}

    	int left = 0, count = 0, min = Integer.MAX_VALUE;
    	String res = "";
    	for(int i = 0; i < S.length(); i++){
    		if(Tset[S.charAt(i)] == 0) continue;
    		Sset[S.charAt(i)]++;
    		//不断的找
    		if(Sset[S.charAt(i)] <= Tset[S.charAt(i)]) count++;
    		if(count == T.length()){
    			while(Sset[S.charAt(left)] > Tset[S.charAt(left)]|| Tset[S.charAt(left)] == 0){
    				if(Sset[S.charAt(left)] > Tset[S.charAt(left)]){
    					Sset[S.charAt(left)]--;
    				}
    				left++;
    			}

    			if(min > i - left + 1){
    				min = i - left + 1;
    				res = S.substring(left, i + 1);
    			}
    		}
    	}
    	return res;
    }
}
/*
http://blog.csdn.net/linhuanmars/article/details/20343903
这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，然后维护一个窗口。
区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），
所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。
在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一。
算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。
空间复杂度则是O(字典的大小)，也就是代码中T的长度.

由于要求时间复杂度在O(n)，经测试，实际上是O(S.size())，只好用空间换时间来做。
算法实际上是维持着一个window，这个window应当覆盖了所有T中出现的字符。
随着游标在S上扫描，window逐渐向右移动。
当扫描到一个出现在T中的字符，而在window中整体出现次数还没有达到T中出现的次数时，将次数递增；
当已经达到了T中出现次数时，将window中最开始出现的此字符挤掉。
当window有效时（window中包含了所有T中出现的字符并且出现的次数是不小于T中该字符出现的次数的），就扫描一下，看这个window的长度是多少。
这样S扫描完之后，就得到了最短window。
算法除了扫描S的时间复杂度是O(S.size())之外，循环内部的所有操作都是常数次。
*/ 


public class Solution {
    public String minWindow(String S, String T) {
    	if(S == null || T ==null || S.length() == 0 || T.length() == 0)
    		return "";

    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	for(int i = 0; i < T.length(); i++){
    		if(map.containsKey(T.charAt(i))){
    			map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
    		}else{
    			map.put(T.charAt(i), 1);
    		}
    	}

    	int count = 0;
    	int pre = 0;
    	String res = "";
    	int minLen = S.length() + 1;

    	for(int i = 0; i < S.length(); i++){
    		if(map.containsKey(S.charAt(i))){
    			map.put(S.charAt(i), map.get(S.charAt(i)) - 1);
    			if(map.get(S.charAt(i))>=0)
    				count++;
    			while(count == T.length()){
    				if(map.containsKey(S.charAt(pre))){
    					map.put(S.charAt(pre), map.get(S.charAt(pre)) + 1);
    					if(map.get(S.charAt(pre))>0){//bba, ba的情况，如果负的话？？
    						if(minLen> i - pre + 1){
    							res = S.substring(pre, i + 1);
    							minLen = i - pre + 1;
    						}
    						count--;
    					}
    				}
    				pre++;
    			}
    		}
    	}
    	return res;
    }
}

//my Solution
/*
Submission Result: Time Limit Exceeded
*/
public class Solution {
    public String minWindow(String S, String T) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int min = Integer.MAX_VALUE;
        String res = "";
        for(int i = 0; i < T.length(); i++){
            char c = T.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c, 1);
            }
        }
        
        for(int i = 0; i < S.length(); i++){
            HashMap<Character, Integer> tmp = new HashMap<Character, Integer>(map);
            if(tmp.containsKey(S.charAt(i))){
                if(tmp.get(S.charAt(i)) == 1){
                    tmp.remove(S.charAt(i));
                }else{
                    tmp.put(S.charAt(i), tmp.get(S.charAt(i)) -1);
                }
            }else{
                continue;
            }
            for(int j = i+1; j < S.length(); j++){
                char c = S.charAt(j);
                if(tmp.containsKey(c)){
                    if(tmp.get(c) == 1){
                        tmp.remove(c);
                    }else{
                        tmp.put(c, tmp.get(c) -1);
                    } 
                }else{
                    continue;
                }
                
                if(tmp.isEmpty()){
                    if(j - i + 1 < min){
                        min = j - i + 1;
                        res = S.substring(i, j+1);
                    }
                    break;
                }
            }
        }
        return res;
    }
}