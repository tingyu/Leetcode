/*
Integer to Roman Total Accepted: 5700 Total Submissions: 17817 My Submissions
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
两种思路，一个是将相应的数字放到String数组中，然后通过计算num得到相应的数组的Index，最后合并为output
另一个是使用hashmap。只记录这些值，rivate static int[] bases = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
剩下的通过加减乘除来计算
*/
// A very intuitive way to solve the problem
public class Solution{
	public String intToRoman(int num) {
		String digit[] = new String[] {"","I","II","III","IV","V","VI","VII","VIII","IX"};
		String ten[] = new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
		String hundred[] = new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
		String thousand[] = new String[]{"","M","MM","MMM"};
		StringBuffer result = new StringBuffer();

		if(num>3999)
			num = 3999;
		if(num<0)
			num = 0;

		result.append(thousand[num/1000]);
		result.append(hundred[num/100%10]);
		result.append(ten[num/10%10]);
		result.append(digit[num%10]);

		return result.toString();
 	}
}

/*
或者下面这样写也是可以的
        res.append(thousands[num/1000]);
        res.append(hundreds[(num%1000)/100]);
        res.append(tens[(num%100)/10]);
        res.append(digits[(num%10)]);
        */


//you need to think more about this way 
public class Solution{
	private static int[] bases = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	private static HashMap<Integer, String> map = new HashMap<Integer, String>();

	private static void setup(){
		map.put(1, "I");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(40, "XL");
		map.put(50, "L");
		map.put(90, "XC");
		map.put(100, "C");
		map.put(400, "CD");
		map.put(500, "D");
		map.put(900, "CM");
		map.put(1000, "M");
	}

	public String intToRoman(int num){
		setup();
		String result = new String();
		for(int i: bases){
			while(num >= i){
				result += map.get(i);
				num -= i;
			}
		}
		return result;
	}
}