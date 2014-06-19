/**
Longest Substring Without Repeating Characters 

Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/
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


