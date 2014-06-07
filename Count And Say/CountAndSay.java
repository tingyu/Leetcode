/**
Count And Say

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

*/

/*
Thoughts:
This question is not hard. We can do a iteration through 1 to n. 
Each time we look back and examine the (n-1)th sequence, and count the number of different numbers appearing in it.
*/

public class Solution {
    public String countAndSay(int n) {
        String number = "1";
        for(int i = 2; i <=n; i++){
            String newNumber = new String();
            for(int j = 0; j < number.length();){
                char current = number.charAt(j);
                int count = 0;
                while(j< number.length() && current==number.charAt(j)){
                    count++;
                    j++;
                }
                newNumber += "" + count + current;
            }
            number = newNumber;
        }
        return number;
    }
}