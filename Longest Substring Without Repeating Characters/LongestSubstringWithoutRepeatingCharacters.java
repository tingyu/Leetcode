/**
Longest Substring Without Repeating Characters 

Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/
//my solution
/*
关键就是从s中的每一个点开始找没有重复character的substring。i来找头，j来找尾巴。用个HashSet来记录有没有重复的character
里面注意有一个corner case就是else里面如果一直不走，说明整个都没有重复的，所以需要加个flag最后再确定下max
注意是j-i而不是j-i+1，因为此时已经是重复的了

Submission Result: Time Limit Exceeded
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        
        for(int i = 0; i < s.length(); i++){
            boolean flag = true;
            HashSet<Character> set = new HashSet<Character>();
            for(int j = i; j < s.length(); j++){
                char c = s.charAt(j);
                if(!set.contains(c)){
                    set.add(c);
                }else{
                    max = Math.max(max, j-i);
                    flag = false;
                    break;
                }
            }
            if(flag){
                max = Math.max(max, s.length() - i);
                //break;这里也可以加个break来减少复杂度
            }
        }
        return max;
    }
}


public class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] flag = new boolean[256];
        int res = 0, j = 0;
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(flag[c]){
                res = Math.max(i-j, res);
                for(int k = j; k < i; k++){
                    if(s.charAt(k) == c){
                        j = k + 1;
                        break;
                    }
                    flag[s.charAt(k)] =false;
                }
            }else{
                flag[c] = true;
            }
        }
        res = Math.max(s.length() - j, res);
        return res;
    }
}
/*
                for(int k = j; k < i; k++){
                    if(s.charAt(k) == c){
                        j = k + 1;
                        break;
                    }
                    flag[s.charAt(k)] =false;
是因为c重复了，因此找一下前面遍历过的里面，是哪个元素让c重复了。找到这个元素，然后j = k+1
*/
//http://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
    	boolean[] flag = new boolean[256];

    	int result = 0;
  		int j = 0;
  		char[] arr = s.toCharArray();

  		for(int i = 0; i < arr.length; i++){
  			char c = arr[i];
  			if(flag[c]){
  				result = Math.max(result, i - j);
  				for(int k =j; k < i; k++){
  					if(arr[k] == c){
  						j = k + 1;
  						break;
  					}
  					flag[arr[k]] == false;
  				}
  			}else{
  				flag[c] = true;
  			}
  		}

  		result = Math.max(arr.length -j, result);
  		return result;
  	}
}

//Submission Result: Time Limit Exceeded
public static int lengthOfLongestSubstring(String s) {
	char[] arr = s.toCharArray();
	int pre = 0;

	HashMap<Character, Integer> map = new HashMap<Character, Integer>();

	for(int i = 0; i < arr.length; i++){
		if(!map.containsKey(arr[i])){
			map.put(arr[i], i);
		}else{
			pre = pre > map.size()?pre: map.size();
			i = map.get(arr[i]);
			map.clear();
		}
	}
	return Math.max(pre, map.size());
}


