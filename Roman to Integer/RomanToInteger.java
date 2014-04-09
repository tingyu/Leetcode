/*
Roman to Integer Total Accepted: 6020 Total Submissions: 18666 My Submissions
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/
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