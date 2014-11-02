/**
Add Binary

 Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100". 

*/

/*
Summary:
应该用String表示二进制啊，这是你今天犯的错误啊啊啊啊啊啊
感觉涉及二进制的都涉及了String的操作
这题开始没想到思路，后来想到了，因为两个长度可能不一样，所以需要先用到
padding，然后从后往前一位位加，弄一个flag看看需不需要进位。此时分了3种情况
，其中每一种又根据flag又分两种情况，并且不断更新flag。最后全部扫完之后，如果
flag是true的。那么就继续再加上一个1.因为是String的处理所以比较简单

//first padding a or b to the longer length, then consider flag and 0, 1 situation
//you can use flag to indicate a carrier
// Don't try to convert the string into "int" or "long", the string can get too long
 to be converted.

2.因为直接从String转的话可能太长了，会溢出。但是如果只转一位的话就没问题
convert char to int to compute using a.charAt(lastA--) - '0':
StringBuilder能直接add integer，最后转化成string??
*/


//my solution
//first padding a or b to the longer length, then consider flag and 0, 1 situation
//you can use flag to indicate a carrier
// Don't try to convert the string into "int" or "long", the string can get too long to be converted.
public class Solution {
    public String addBinary(String a, String b) {
        if(a.length() < b.length()){
            String tmp = a;
            for(int i = 0; i < b.length() - a.length(); i++){
                tmp = '0' + tmp;
            }
            a = tmp;
        }else if(a.length() > b.length()){
            String tmp = b ;
            for(int i = 0; i < a.length() - b.length(); i++){
                tmp = '0' + tmp;
            }
            b = tmp;
        }
        
        String res = "";
        boolean flag = false;
        for(int i = a.length()-1; i>=0; i--){
            if(a.charAt(i) != b.charAt(i)){ // 0+1, 1+0
                if(flag == false){
                    res = '1' + res;
                }else{
                    flag = true;
                    res= '0' + res;
                }
            }else if(a.charAt(i) == '1'){ //1+1
                if(flag == false){
                    flag = true;
                    res = '0' + res;
                }else{
                    flag = true;
                    res = '1' + res;
                }
            }else{ //0 + 0
                if(flag == false){
                    res = '0' + res;
                }else{
                    flag =false;
                    res = '1' + res;
                }
            }
        }
        
        if(flag == true){
            res = '1' + res;
        }
        return res;
    }
}


//convert char to int to compute using a.charAt(lastA--) - '0':
public class Solution {
    public String addBinary(String a, String b) {
        if(a == null || a.length() == 0){
            return b;
        }
        
        if(b == null || b.length() == 0){
            return a;
        }
        
        StringBuilder sb = new StringBuilder();
        
        int lastA = a.length() - 1;
        int lastB = b.length() - 1;
        int carry = 0;
        
        while(lastA >= 0 || lastB >= 0 || carry >0){
            int num1 = lastA>=0? a.charAt(lastA--) - '0': 0;
            int num2 = lastB>=0? b.charAt(lastB--) - '0': 0;
            int current = (num1 + num2 + carry)%2;
            carry = (num1 + num2 + carry)/2;
            
            sb.insert(0, current);
        }
        return sb.toString();
    }
}