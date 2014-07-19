/**
Sort Colors Total Accepted: 12929 Total Submissions: 41699 My Submissions
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

*/

// Solution #1: (two-pass counting sort as the follow up says) 
public class Solution {
    public void sortColors(int[] A) {
        int redNum = 0;
        int whiteNum = 0;
        int blueNum = 0;
        for(int i=0; i<A.length; i++){
            if(A[i]==0) redNum++;
            if(A[i]==1) whiteNum++;
            if(A[i]==2) blueNum++;
        }
        
        for(int i=0; i< A.length; i++){
            if(i<redNum) A[i] =0;
            else if(i<(redNum+whiteNum)) A[i]=1;
            else A[i]=2;
        }
    }
}

/**
已经有点浆糊了，其实很简单，用i记录0应该放的位置，j记录2应该放的位置。
cur从0到j扫描，
遇到0，放在i位置，i后移；
遇到2，放在j位置，j前移；
遇到1，cur后移。
扫描一遍得到排好序的数组。
时间O(n)且一次扫描，空间O(1)，满足要求。
这么做的前提是，拿到一个值，就知道它应该放在哪儿。（这点和快排的根据pivot交换元素有点像）



维护两个指针：redIdx，blueIdx，从头开始扫描数组直到blueIdx(包括blueIdx)

1.A[i]==0时，将A[i]与A[redIdx]交换，redIdx++，i++

2.A[i]==2时，将A[i]与A[blueIdx]交换，blueIdx--，

3.A[i]==1时，i++
复制代码

//自己写的时候一直忽视了对i > redIdx和 i< blueIdx这里面两个sub情况的讨论。然后总是写的出错。
//注意里面还是要分情况讨论的。在A[i] == 0的时候，要注意这时候if(i > redIdx)只有这种情况需要交换，然后只增加redIdx，不增加i.
//如果if(i <= redIdx)的时候就不需要交换了。因为已经是0了。所以只需要redIdx++, i++就好了。
*/
public class Solution{
    public void sortColors(int[] A){
           //one-pass solution
        if(A==null||A.length==0||A.length==1) return;
        int len = A.length;
        int i=0, redIdx = 0, blueIdx = len-1;
        
        while(i < blueIdx+1){
            if(A[i]==0){
                if(i>redIdx){
                    swap(A, i, redIdx++);
                }
                else{
                    i++;
                    redIdx++;
                }
            }
            else if(A[i]==2){
                if(i<blueIdx){
                    swap(A, i, blueIdx-);
                }
                else{
                    return;
                }
            }
            else{
                i++;
            }
        }
    }
    
    public void swap(int[] A, int i, int idx){
        int temp = A[i];
        A[i] = A[idx];
        A[idx] = temp;
    }
    }
}


//one-pass inline swap, a little faster than the former one
//Runtime: 380 ms
public class Solution {
    public void sortColors(int[] A) {
            //one-pass solution
        if(A==null||A.length==0||A.length==1) return;
        int len = A.length;
        int i=0, redIdx = 0, blueIdx = len-1;
        
       // stop looping when current >= blue
        while(i < blueIdx+1){
            // if color is red, move to the front
            if(A[i]==0){
                // when cur > red, switch
                if(i>redIdx){
                    //swap(A, i, redIdx++);
                    int tmp = A[i];
                    A[i] = A[redIdx];
                    A[redIdx] = tmp;
                    redIdx++;
                }
                // when cur <= red, no need to switch, just move both to next
                else{
                    i++;
                    redIdx++;
                }
            }
            // if color is blue, move to the end
            else if(A[i]==2){
                // when cur < blue, switch
                if(i<blueIdx){
                   // swap(A, i, blueIdx--);
                   int tmp = A[i];
                   A[i] = A[blueIdx];
                   A[blueIdx]= tmp;
                   blueIdx--;
                }
                // when cur >= blue, end the loop
                else{
                    return;
                }
            }
            // if color is white, skip
            else{
                i++;
            }
        }
    }
}


//my solution
//自己写的时候一直忽视了对i > redIdx和 i< blueIdx这里面两个sub情况的讨论。然后总是写的出错。
//注意里面还是要分情况讨论的。在A[i] == 0的时候，要注意这时候if(i > redIdx)只有这种情况需要交换，然后只增加redIdx，不增加i.
//如果if(i <= redIdx)的时候就不需要交换了。因为已经是0了。所以只需要redIdx++, i++就好了。
public class Solution {
    public void sortColors(int[] A) {
        if(A==null||A.length==0||A.length==1) return;
        int redIdx = 0;
        int blueIdx = A.length - 1;
        int i = 0;
        
        while(i< A.length){
            if(A[i] == 0){
                if(i > redIdx){
                    A[i] = A[redIdx];
                    A[redIdx] = 0;
                    redIdx++;
                }else{
                    redIdx++;
                    i++;   
                }
            }else if(A[i] == 2){
                if(i < blueIdx){
                    A[i] = A[blueIdx];
                    A[blueIdx] = 2;
                    blueIdx--;   
                }else{
                    return;
                }
            }else{
                i++;
            }                
        }
    }
}