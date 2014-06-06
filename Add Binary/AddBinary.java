/**
Add Binary

 Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100". 

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