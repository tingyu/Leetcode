/**

Plus One
Total Accepted: 10850 Total Submissions: 35412

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list
*/
//下面这种解法很巧妙。先加上1然后判断digits[i]>=10。然后这里的carry只有一个，就是多出一位的情况。
public class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 0, i = digits.length -1;
        digits[i]+=1;
        
        while(digits[i]>=10){
            digits[i--]-=10;
            if(i>=0){
                digits[i]+=1;
            }else{
                carry =1;
                break;
            }
        }
        
        if(carry==0) return digits;
        int[] res = new int[digits.length +1];
        for(i = 0; i < digits.length; i++){
            res[i+1] = digits[i];
        }
        res[0] =1;
        return res;
    }
}

//my solution
public class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length -1; i >= 0; i--){
            digits[i] = digits[i] + carry;
            if(digits[i] < 10){
                carry = 0;
                continue;
            }else{
                carry = digits[i]/10;
                digits[i] = digits[i]%10;                
            }
        }
        
        if(carry == 1){
            int[] res = new int[digits.length + 1];
            res[0] = carry;
            for(int i = 1; i < res.length; i++){
                res[i] = digits[i -1];
            }
            return res;
        }
        return digits;
    }
}

//my Solution
//我的解法是分情况讨论的，当digits[i] < 9，carry =0，此时具体的分两种情况，最后一位的话digits[i] += 1;其他位的话digits[i] += carry;
//当digits[i] == 9，此时也分情况，只有是最后一位，或者carry == 1的时候才会carry = 1,然后当前位置为0.否则的话不变化、
//然后又有一种特殊情况。如果一直进位。到最后比原来的digits多了一位。这样就根据最后的carry来进行判断。
//如果carry == 0, 返回digits
//否则的话new一个n+1的数组，依次copy
//这种分情况讨论的很容易弄错。比较难做到一次bug free
public class Solution {
    public int[] plusOne(int[] digits) {
       int len = digits.length - 1;
       int carry = 0;
       for(int i = len; i >= 0; i--){
            if(digits[i] < 9){
                if(i == len){
                    digits[i] += 1;   
                }else{
                    digits[i] += carry;
                }
                carry = 0;
            }else if(digits[i] == 9){
                if(i == len || carry == 1){
                    carry = 1;
                    digits[i] = 0;   
                }
            }    
       }
       if(carry == 0) return digits;
       
       int[] res = new int[digits.length+1];
       res[0] = 1;
       for(int i = 1; i< res.length; i++){
            res[i] = digits[i-1];
        }
    return res;
    
    }
}