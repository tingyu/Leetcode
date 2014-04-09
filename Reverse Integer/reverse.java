/*

Reverse Integer Total Accepted: 9975 Total Submissions: 24847 My Submissions
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

Throw an exception? Good, but what if throwing an exception is not an option? You would then have to re-design the function (ie, add an extra parameter).

*/

public class Solution {
    public int reverse(int x) {
        if(x==0) return x;
        boolean isNeg = false;
        if(x <0){
            isNeg = true;
            x = -x;
        }
        
        int result = 0;
        while(x>0){
            result = result*10 + x%10;//每次10取余然后加上之前的乘以10
            x = x/10;
        }

        //考虑溢出情况，溢出时result<0，此时返回-1
        if(result < 0){
             return -1;
        }
        
        if(isNeg){
            return -result;
        }else{
            return result;
        }
    }
}