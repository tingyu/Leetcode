/**
Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.
*/

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
        	if(m <= x/m && x/(m + 1)< m +1)
        		return m;

        	if(x/m < m){
        		r = m -1;
        	}else{
        		l = m +1;
        	}
        }
        return 0;
    }
}

/*
二分法在数值计算中非常常见，还是得熟练掌握。其实这个题目还有另一种方法，称为牛顿法。
不过他更多的是一种数学方法，算法在这里没有太多体现，不过牛顿法是数值计算中非常重要的方法，所以我还是介绍一下。
不太了解牛顿法基本思想的朋友，可以参考一下牛顿法-维基百科。一般牛顿法是用数值方法来解一个f(y)=0的方程（
为什么变量在这里用y是因为我们要求的开方是x，避免歧义）。对于这个问题我们构造f(y)=y^2-x，其中x是我们要求平方根的数，
那么当f(y)=0时，即y^2-x=0,所以y=sqrt(x),即是我们要求的平方根。f(y)的导数f'(y)=2*y，
根据牛顿法的迭代公式我们可以得到y_(n+1)=y_n-f(n)/f'(n)=(y_n+x/y_n)/2。最后根据以上公式来迭代解以上方程。
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