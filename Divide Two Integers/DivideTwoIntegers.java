/**
Divide Two Integers 

Divide two integers without using multiplication, division and mod operator.

*/

//http://blog.csdn.net/myself9711/article/details/12269699
/*
Solution:

1st solution: use the add/subtraction method, but which can not pass the big OJ, such as the input is :2147483647, 1

running time: O(n).


*/
public class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        
        if(dividend < 0){
            sign *= -1;
        }
        
        if(divisor < 0){
            sign *= -1;
        }
        
        long tmpDividend = Math.abs(dividend);
        long tmpDivisor = Math.abs(divisor);
        int count = 0;
        
        while(tmpDividend >= tmpDivisor){
            count++;
            tmpDividend = tmpDividend - tmpDivisor;
        }
        
        return count* sign;
    }
}

/*
2nd solution:

optimize the 1st solution, double the divisor each time.

Running Time: O(lg(n));

note: there's two while loop here, if the temp_divisor bigger than the current dividend, 
set the temp_divisor to the initial value and start the loop again.
下面的双层循环写的太好了
*/

public class Solution {
    public int divide(int dividend, int divisor) {
    	int sign = 1;
    	if(dividend < 0){
    		sign *= -1;
    	}
    	if(divisor < 0){
    		sign *= -1;
    	}

    	long a =dividend;
    	long b = divisor;

    	//must cast to long here for dealing with the Integer.MIN_VALUE
    	//because Math.abs(-2147483648) > Integer.MAX_VALUE  

    	a = Math.abs(a);
    	b = Math.abs(b);

    	int count = 0;
    	//这个循环挺新颖的
    	while(a >= b){
    		long temp = b;
    		int multi = 1;
    		while(a >= temp){
    			count += multi;
    			a -= temp;
    			temp += temp;
    			multi += multi;
    		}
    	}
    	return count*sign;
    }
}


//http://rleetcode.blogspot.com/2014/01/divide-two-integers.html
//3rd solution: use the bit manipulation, similar to the 2nd solution. To be continued...

running time: O(lg(n)).
public class Solution {
    public int divide(int dividend, int divisor) {
    	if(divisor ==0){
    		return Integer.MAX_VALUE;
    	}
/*
    	Integer.MIN_VALUE is -2147483648, but the highest value a 32 bit integer can contain is +2147483647. 
 Attempting to represent +2147483648 in a 32 bit int will effectively "roll over" to -2147483648. 
 This is because, when using signed integers, the two's complement binary representations of
 +2147483648 and -2147483648 are identical. This is not a problem, however, as +2147483648 
 is considered out of range.
 */
 
 // conver the diviedend and divisor to long before apply Math.abs()*/
 		long a = Math.abs((long)dividend);
 		long b = Math.abs((long)divisor);
 		long result = 0;

 		while(a >= b){
 			long temp = b;
 			int i = 0;
 			while(a >= temp){
 				a = a -temp;
 				temp = temp<<1;
 				result += 1<<i;
 				i++;
 			}
 		}

 		if(dividend<0 && divisor>0 ||dividend>0 && divisor<0){
 			result = -result;
 		}
 		return (int)result;
    }
}

/*

http://blog.csdn.net/linhuanmars/article/details/20024907
这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题。
对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0。
这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。

那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，
即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，
我们先让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,
然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂知道超过结果，所以时间复杂度为O(logn)。代码如下：
*/

//这个解法没看懂
public int divide(int dividend, int divisor) {
    if(divisor==0)
        return Integer.MAX_VALUE;
    
    int res = 0;
    if(dividend==Integer.MIN_VALUE)
    {
        res = 1;
        dividend += Math.abs(divisor);
    }
    if(divisor==Integer.MIN_VALUE)
        return res;
    boolean isNeg = ((dividend^divisor)>>>31==1)?true:false;
    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);
    int digit = 0;
    while(divisor<=(dividend>>1))
    {
        divisor <<= 1;
        digit++;
    }
    while(digit>=0)
    {
        if(dividend>=divisor)
        {
            dividend -= divisor;
            res += 1<<digit;
        }
        divisor >>= 1;
        digit--;
    }
    return isNeg?-res:res;
}


//4th solution: math formular: exp(log(a) - log(b)); To be continued...

