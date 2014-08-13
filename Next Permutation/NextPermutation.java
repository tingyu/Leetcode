/**
Next Permutation 

Total Accepted: 9098 Total Submissions: 36319 My Submissions
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/* 
Thoughts:
Ex. 1 7 4 5 3 2
1.From right to left, find the first number M less than previous number. It's 4 in this example.
2. From right to left, find the first number bigger than M. It's 5 in this example.
3. Swap them. 1 7 5 4 3 2.
4. Reverse numbers after position of M ( 5 now ). 
result is 1 7 5 2 3 4
*/
public class Solution {
    public void nextPermutation(int[] num) {
    	if(num == null || num.length == 0) return;
        int n = num.length -1;
        int m = -1;

        //from right to left, find the first number m less than the previous one
        for(int i = n -1; i >= 0; i--){
        	if(num[i] < num[i+1]){
        		m = i;
        		break;
        	}
        }

        if(m == -1){
        	reverse(num, 0, n);
        	return;
        }

        //find the first number bigger than m
        for(int i = n; i >=0; i--){
        	if(num[i] > num[m]){
        		//swap
        		int t = num[i];
        		num[i] = num[m];
        		num[m] = t;
        		reverse(num, m+1, n);
        		break;
        	}
        }
    }

    public void reverse(int[] num, int i, int j){
    	if(i == j) return;
    	int t;
    	while(i < j){
    		t = num[i];
    		num[i] = num[j];
    		num[j] = t;
    		i++;
    		j--;
    	}
    	return;
    }
}

//http://blog.csdn.net/xiaozhuaixifu/article/details/14122947
public class Solution {
    public void nextPermutation(int[] num) {
        if(num.length <= 1)  
            return ;  
        for(int i = num.length - 2; i >= 0; i--)  
        {  
            if(num[i] < num[i+1])  
            {  
                int j;  
                for(j = num.length - 1; j >= i; j--)  
                    if(num[i] < num[j])  
                        break;  
                // swap the two numbers.  
                num[i] = num[i] ^ num[j];  
                num[j] = num[i] ^ num[j];  
                num[i] = num[i] ^ num[j];  
                //sort the rest of arrays after the swap point.  
                Arrays.sort(num, i+1, num.length);  
                return ;  
            }  
        }  
        //reverse the arrays.  
        for(int i = 0; i < num.length / 2; i++)  
        {  
            int tmp = num[i];  
            num[i] = num[num.length - i - 1];  
            num[num.length - i - 1] = tmp;  
        }  
        return ;  
    }
}