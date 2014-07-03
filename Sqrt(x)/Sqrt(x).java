/**
Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.
*/

//下面给出了几种二分法，这些二分法有的有优化，有的没有，有些可以算作二分法的特殊解法.在后续复习二分法的时候可以继续了解

/*
两分法。原因就是：要求x的平方根，在假定x为正整数的情况下，那么它的结果一定在0到x之间，而二分查找法一定不会漏掉。
以此为基础，每一次取中间值的平方作为和x的比较，如果太大，那么结果一定在左半部分，如果太小，那么结果一定在右半部分。
这个方法如果把long改成int就会出错，为什么？
*/

public class Solution {
    public int sqrt(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        long low = 0, high = x;
        long mid;
        while(low <= high){
            mid = (low+high)/2;
            if(mid*mid == x) return (int)mid;
            else if(mid*mid < x) low = mid+1;
            else high = mid-1;
        }
        mid = (low+high)/2;
        return (int)mid;
    }
}
/*
http://blog.csdn.net/linhuanmars/article/details/20089131
这是一道数值处理的题目，和Divide Two Integers不同，这道题一般采用数值中经常用的另一种方法：二分法。
基本思路是跟二分查找类似，要求是知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，知道左界和右界相遇。
算法的时间复杂度是O(logx)，空间复杂度是O(1)。代码如下：
*/

public class Solution {
    public int sqrt(int x) {
        if(x < 0) return -1;
        if(x == 0) return 0;

        int l = 1;
        int r = x/2 +1;

        while(l <= r){
        	int m = (l + r)/2;
        	if(m <= x/m && x/(m + 1)< m +1) // m*m <= x <= (m+1)*(m+1)，x正好在m*m和(m+1)(m+1)之间
        		return m;

        	if(x/m < m){ //x < m*m，表示m大了，所以需要减小
        		r = m -1;
        	}else{//否则的话m小了，需要增大
        		l = m +1;
        	}
        }
        return 0;
    }
}

/*
http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
A:

这里给出两种实现方法：一是二分搜索，二是牛顿迭代法。

1. 二分搜索
对于一个非负数n，它的平方根不会小于大于（n/2+1）。在[0, n/2+1]这个范围内可以进行二分搜索，求出n的平方根。
*/
int sqrt(int x){
    long long i = 0;
    long long j = x /2 + 1;

    while(i <= j){
        long long mid = (i + j)/2;
        long long sq = mid * mid;
        if(sq == x) return mid;
        else if(sq < x) i = mid +1
        else j = mid -1;
    }
    return j;
}
//注：在中间过程计算平方的时候可能出现溢出，所以用long long。

/*
二分法在数值计算中非常常见，还是得熟练掌握。其实这个题目还有另一种方法，称为牛顿法。
不过他更多的是一种数学方法，算法在这里没有太多体现，不过牛顿法是数值计算中非常重要的方法，所以我还是介绍一下。
不太了解牛顿法基本思想的朋友，可以参考一下牛顿法-维基百科。一般牛顿法是用数值方法来解一个f(y)=0的方程（
为什么变量在这里用y是因为我们要求的开方是x，避免歧义）。对于这个问题我们构造f(y)=y^2-x，其中x是我们要求平方根的数，
那么当f(y)=0时，即y^2-x=0,所以y=sqrt(x),即是我们要求的平方根。f(y)的导数f'(y)=2*y，
根据牛顿法的迭代公式我们可以得到y_(n+1)=y_n-f(n)/f'(n)=(y_n+x/y_n)/2。最后根据以上公式来迭代解以上方程。


2. 牛顿迭代法

   为了方便理解，就先以本题为例：

   计算x2 = n的解，令f(x)=x2-n，相当于求解f(x)=0的解，如左图所示。

   首先取x0，如果x0不是解，做一个经过(x0,f(x0))这个点的切线，与x轴的交点为x1。

   同样的道理，如果x1不是解，做一个经过(x1,f(x1))这个点的切线，与x轴的交点为x2。

   以此类推。

   以这样的方式得到的xi会无限趋近于f(x)=0的解。

   判断xi是否是f(x)=0的解有两种方法：

   一是直接计算f(xi)的值判断是否为0，二是判断前后两个解xi和xi-1是否无限接近。

 

经过(xi, f(xi))这个点的切线方程为f(x) = f(xi) + f’(xi)(x - xi)，其中f'(x)为f(x)的导数，本题中为2x。令切线方程等于0，即可求出xi+1=xi - f(xi) / f'(xi)。

继续化简，xi+1=xi - (xi2 - n) / (2xi) = xi - xi / 2 + n / (2xi) = xi / 2 + n / 2xi = (xi + n/xi) / 2。

有了迭代公式，程序就好写了。关于牛顿迭代法，可以参考wikipedia以及百度百科。
*/

public class Solution {
    public int sqrt(int x) {
    	if(x == 0) reutn 0;
    	double lastY = 0;
    	double y = 1;
    	while(y != lastY){
    		lastY = y;
    		y = (y + x/y)/2;
    	}
    	return (int)(y);
    }
}

/*
实际面试遇到的题目可能不是对一个整数开方，而是对一个实数。方法和整数其实是一致的，
只是结束条件换成左界和右界的差的绝对值小于某一个epsilon（极小值）即可。
这里注意一个小问题，就是在java中我们可以用==来判断两个double是否相等，
而在C++中我们则需要通过两个数的绝对值差小于某个极小值来判断两个double的相等性。
实际上两个double因为精度问题往往是不可能每一位完全相等的，java中只是帮我们做了这种判定。


比较典型的数值处理的题目还有Divide Two Integers，Pow(x,n)等，其实方法差不多，一般就是用二分法或者以2为基进行位处理的方法。
*/


/*
http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
牛顿迭代法也同样可以用于求解多次方程的解。

P.S. 本题是求解整数的平方根，并且返回值也是整型。在上述代码基础上稍微做修改，就可以同样适用于double（仅限方法2）。
*/

double sqrt(double x) {
    if (x == 0) return 0;
    double last = 0.0;
    double res = 1.0;
    while (res != last)
    {
        last = res;
        res = (res + x / res) / 2;
    }
    return res;
}




