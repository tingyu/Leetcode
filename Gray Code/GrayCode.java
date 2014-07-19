/**
Gray Code
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

/**
============

Analysis:

Try one more example, n = 3:

000 - 0

001 - 1

011 - 3

010 - 2

110 - 6

111 - 7

101 - 5

100 - 4 

Comparing n = 2: [0,1,3,2] and n=3: [0,1,3,2,6,7,5,4], we found that the first four numbers in case n=3 
are the same as the the numbers in case n=4.  
Besides, [6,7,5,4] = [2+4,3+4,1+4,0+4].  Which means remaining numbers in case n=3 can also be calculated 
from the numbers in case n=4 in reversing order.  
Therefore, we decided to use recursive approach to form the resulting ArrayList.
*/

public class Solution {
    public ArrayList<Integer> grayCode(int n){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(n==0){
            res.add(0);
            return res;
        }
        
        ArrayList<Integer> preRes = grayCode(n-1);
        res.addAll(preRes);
        for(int i = preRes.size() -1; i >=0; i--){
            res.add(preRes.get(i) + (int)Math.pow(2, n-1));
        }
        return res;
    }
}


