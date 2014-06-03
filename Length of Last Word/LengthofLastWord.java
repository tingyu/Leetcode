/**
Length of Last Word 

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.

*/

//my solution 1:
//use String attributes and methords
//348 ms 
public class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.trim().split(" ");
        int len = words.length;
        String lastWord = words[len -1];
        
        return lastWord.length();
    }
}


//my solution 2
//convert string to characters
//316 ms
public class Solution {
    public int lengthOfLastWord(String s) {
        char[] chars = s.trim().toCharArray();
        int len = chars.length;
        int count = 0;
        
        for(int i = len -1; i >=0; i--){
            if(chars[i]!= ' '){
                count++;
            }else{
                break;
            }    
        }
        return count;
    }
}