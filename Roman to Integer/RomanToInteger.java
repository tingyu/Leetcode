/*
Roman to Integer Total Accepted: 6020 Total Submissions: 18666 My Submissions
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
http://blog.csdn.net/wzy_1988/article/details/17057929
思路
首先，学习一下罗马数字，参考罗马数字

罗马数字是最古老的数字表示方式，比阿拉伯数组早2000多年，起源于罗马

罗马数字有如下符号：

基本字符	    I	V	X	L	C	D	M
对应阿拉伯数字	1	5	10	50	100	500	1000
计数规则：
相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
正常使用时，连续的数字重复不得超过三次
在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）

其次，罗马数字转阿拉伯数字规则（仅限于3999以内）：

w
*/

//my solution
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        
        char[] c = s.toCharArray();
        int total = map.get(c[0]);
        int pre, cur;
        for(int i = 1; i < c.length; i++){
            pre = map.get(c[i -1]);
            cur = map.get(c[i]);
            
            if(cur <= pre){
                total += cur;
            }else{
                total = total - pre*2 + cur;
            }
        }
        return total;
    }
}

import java.util.Scanner;

public class Solution {
    public int romanToInt(String s) {
        int i, total, pre, cur;

		total = charToInt(s.charAt(0));

		for(i = 1; i < s.length(); i++){
			pre = charToInt(s.charAt(i-1));
			cur = charToInt(s.charAt(i));

			if(cur <= pre){
				total += cur;
			}else{
				total = total - pre*2 + cur;//why???????????????
			}
		}

		return total;
    }
    
    	public  int charToInt(char c){
		int data = 0;

		switch(c){
			case 'I':
				data = 1;
				break;
			case 'V':
				data = 5;
				break;
			case 'X':
				data = 10;
				break;
			case 'L':
				data = 50;
				break;
			case 'C':
				data = 100;
				break;
			case 'D':
				data = 500;
				break;
			case 'M':
				data = 1000;
				break;
		}
		return data;
	}

}


//这个思路算法有些不好想

public class Solution{
	public int romanToInt(String s){
		Map<Character, Integer> romans = new HashMap<Character, Integer>();
		romans.put('I', 1);
		romans.put('V', 5);
		romans.put('X', 10);
		romans.put('L', 50);
		romans.put('C', 100);
		romans.put('D', 500);
		romans.put('M', 1000);

		char[] cs = s.toCharArray();

		int num = 0;
		int val;
		for(int i = 0; i < cs.length; i++){
			val = romans.get(cs[i]);
			if(i == cs.length -1 || romans.get(cs[i + 1]) <= val){
				num += val;
			}else{
				num -= val;
			}
		}
		return num;
	}
}


/*
The java.util.Arrays.asList(T... a) returns a fixed-size list backed by the specified array. 
(Changes to the returned list "write through" to the array.) This method acts as bridge between 
array-based and collection-based APIs,
http://www.tutorialspoint.com/java/util/arrays_aslist.htm

The java.util.ArrayList.indexOf(Object) method returns the index of the first occurrence of 
the specified element in this list, or -1 if this list does not contain the element.

This method returns the index of the first occurrence of the specified element in this list, 
or -1 if this list does not contain the element.

*/

public class Solution {
    public int romanToInt(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] num = {1,5,10,50,100,500,1000};
		List<Character> roman = Arrays.asList('I','V','X','L','C','D','M');

		int len = s.length();
		int sum = num[roman.indexOf(s.charAt(len-1))];

		for(int i = len-2; i>=0; i--){
			int ind1 = roman.indexOf(s.charAt(i));
			int ind2 = roman.indexOf(s.charAt(i+1));
			if(ind1>=ind2)
				sum += num[ind1];
			else
				sum -= num[ind1];			
		}

		return sum;
        
    }
}