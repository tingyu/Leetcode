/**
Restore IP Addresses 

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

/*
Submission Result: Wrong Answer
使用一个count记录recursion的次数，只允许recurse4次，也就是分别处理4个子substring。每次substring出当前的判断是不是有效，
如果有效就继续看下一段的substring是不是有效，看下一段时，把之前的去掉会比较方便。也就是s.substring(i)
另外加上“.”的时候，记住要加在后面tmp + substr + "."，我开始是tmp + “.” + substr错了，因为最初的时候tmp是空，就会出现以点开头的情形。

isValid中
if(substr.charAt(0) == '0') return substr.equals("0");判断非常重要，如果没有的话，就会出现很多0开头的无效字符

Input:	"010010"
Output:	["0.1.0.010","0.1.00.10","0.1.001.0","0.10.0.10","0.10.01.0","0.100.1.0","01.0.0.10","01.0.01.0","01.00.1.0","010.0.1.0"]
Expected:	["0.10.0.10","0.100.1.0"]


首先我们要明确传统IP 地址的规律是分4个Part，每个Part是从0-255的数字

0-255的数字，转换成字符，即每个Part 可能由一个字符组成，二个字符组成，或者是三个字符组成。那这又成为组合问题了，dfs便呼之欲出

所以我们写一个For循环每一层从1个字符开始取一直到3个字符，再加一个isValid的函数来验证取的字符是否是合法数字，如果是合法的数字，
我们再进行下一层递归，否则跳过。e
*/



public class Solution {
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        //if(s == null || s.length() == 0) return res;
        if(s.length() < 4 || s.length() > 12) return res;//tricky
        dfs(s,"", res, 0);
        return res;
    }
    
    public void dfs(String s, String tmp, ArrayList<String> res, int count){
        if(count == 3 && isValid(s)){
            res.add(tmp + s);
            return;
        }
        
        for(int i = 1; i < 4 && i < s.length(); i++){// tricky
            String substr = s.substring(0, i);
            if(isValid(substr)){
                dfs(s.substring(i), tmp + substr + ".", res, count+1);//tricky
            }
        }
    }
    
    public boolean isValid(String substr){
        if(substr.charAt(0) == '0') return substr.equals("0");//tricky
        int num = Integer.parseInt(substr);
        return num>=0 && num <=255;
    }
}


/*
http://blog.csdn.net/u011095253/article/details/9158449

 几点注意的地方：

1. 在验证字符串是否是数字的时候，要注意0的情况，001，010，03都是非法的。所以，如果第一位取出来是0，那么我们就判断字符串是否是"0"，不是的情况都是非法的
2. 取字符串的时候，注意位数不够的问题，不仅<4, 而且<s.length()
3. 注意substring的范围
4. 字符串转换成数字 Integer.parseInt(); 
5. 别忘了IP 地址里面的 "."
6. 到第4个Part的时候我们就可以整体验证剩下的所有字符串（因为第4个Part最后一定要取到结尾才是正确的字符串）
*/


//a wrong Solution
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12) return res;
        helper(s, res, "", 0);
        return res;
    }
    
    private void helper(String s, ArrayList<String> res, String tmp, int count){
        if(count == 4){
            res.add(tmp);    
            return;
        }
        
        for(int i = 1; i < 4 && i < s.length(); i++){
            String sub = s.substring(0, i);
            if(isValid(sub) == true){
                
                if(count < 3){
                    tmp = tmp + sub + ".";
                }else if(count == 3){
                    tmp = tmp + sub;
                }
                helper(s.substring(i), res, tmp, count+1); 
                //helper(s.substring(i), res, tmp + sub + ".", count+1);    
            }
        }
    }
    
    private boolean isValid(String sub){
        if(sub.charAt(0) == '0') return sub.equals("0");
        int num = Integer.parseInt(sub);
        return num>= 0 && num <= 255;
    }
}
/*
Submission Result: Wrong Answer

Input:  "0000"
Output: []
Expected:   ["0.0.0.0"]
如果把终止条件改成count == 3
Submission Result: Wrong Answer

Input:  "0000"
Output: ["0.0.0."]
Expected:   ["0.0.0.0"]

*/