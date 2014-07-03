/**
Pow(x, n) 

Implement pow(x, n).
*/

/*
这道题是一道数值计算的题目，因为指数是可以使结果变大的运算，所以要注意越界的问题。如同我在Sqrt(x)这道题中提到的，
一般来说数值计算的题目可以用两种方法来解，一种是以2为基进行位处理的方法，另一种是用二分法。
这道题这两种方法都可以解，下面我们分别介绍。
第一种方法在Divide Two Integers使用过，就是把n看成是以2为基的位构成的，因此每一位是对应x的一个幂数，然后迭代直到n到最高位。
比如说第一位对应x，第二位对应x*x,第三位对应x^4,...,第k位对应x^(2^(k-1)),可以看出后面一位对应的数等于前面一位对应数的平方，
所以可以进行迭代。因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)。代码如下：
*/


public double pow(double x, int n) {
    if(n==0)
        return 1.0;
    double res = 1.0;   
    if(n<0)
    {
        if(x>=1.0/Double.MAX_VALUE||x<=1.0/Double.MIN_VALUE)
            x = 1.0/x;
        else
            return Double.MAX_VALUE;
        if(n==Integer.MIN_VALUE)
        {
            res *= x;
            n++;
        }
    }
    n = Math.abs(n);
    boolean isNeg = false;
    if(n%2==1 && x<0)
    {
        isNeg = true;
    }
    x = Math.abs(x);
    while(n>0)
    {
        if((n&1) == 1)
        {
            if(res>Double.MAX_VALUE/x)
                return Double.MAX_VALUE;
            res *= x;
        }
        x *= x;
        n = n>>1;
    }
    return isNeg?-res:res;
}


/*
以上代码中处理了很多边界情况，这也是数值计算题目比较麻烦的地方。比如一开始为了能够求倒数，我们得判断倒数是否越界，
后面在求指数的过程中我们也得检查有没有越界。所以一般来说求的时候都先转换为正数，这样可以避免需要双向判断（就是根据符号做两种判断）。
接下来我们介绍二分法的解法，如同我们在Sqrt(x)的方法。不过这道题用递归来解比较容易理解，把x的n次方划分成两个x的n/2次方相乘，
然后递归求解子问题，结束条件是n为0返回1。因为是对n进行二分，算法复杂度和上面方法一样，也是O(logn)。代码如下：
*/

public class Solution {
    double pow(double x, int n) {
        if(n == 0) return 1.0;
        double half = pow(x, n/2);
        
        if(n % 2 == 0){
            return half*half;
        }else if(n >0){
            return half*half*x;
        }else{
            return half*half/x;
        }
    }
}

/*
以上代码比较简洁，不过这里有个问题是没有做越界的判断，因为这里没有统一符号，所以越界判断分的情况比较多，
不过具体也就是在做乘除法之前判断这些值会不会越界，有兴趣的朋友可以自己填充上哈，这里就不写太啰嗦的代码了。
不过实际应用中健壮性还是比较重要的，而且递归毕竟会占用递归栈的空间，所以我可能更推荐第一种解法。
*/

