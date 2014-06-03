/**
String to Integer (atoi) 

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

/*
Thoughts for This Problem

The vague description give us space to consider different cases.

1. null or empty string
2. white spaces
3. +/- sign
4. calculate real value
5. handle min & max
*/

public class Solution {
    public int atoi(String str) {

 	    if (str == null || str.length() < 1)
		    return 0;
 
	    // trim white spaces
	    str = str.trim();
 
	    char flag = '+';
 
	    // check negative or positive
	    int i = 0;
	    if (str.charAt(0) == '-') {
		    flag = '-';
		    i++;
	    } else if (str.charAt(0) == '+') {
		    i++;
	    }

	    // use double to store result
	    double result = 0;
 
	    // calculate value
	    while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
		    result = result * 10 + (str.charAt(i) - '0');
		    i++;
	    }
 
	    if (flag == '-')
		    result = -result;
 
	    // handle max and min
	    if (result > Integer.MAX_VALUE)
		    return Integer.MAX_VALUE;
 
	    if (result < Integer.MIN_VALUE)
		    return Integer.MIN_VALUE;
 
	    return (int) result;
	}
}