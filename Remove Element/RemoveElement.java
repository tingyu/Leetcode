/*
Remove Element Total 
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
/*
自己想的时把等于elem的移到数组的最后面。此时做的有些像counting sort了
后来查看答案，发现有个更好的写法，直接覆盖读写原来的数组就好了。因为有重复的，所以写的数组的index永远比原来数组中正读到的部分要小
注意这种直接overwrite原来数组的写法，很好也比较常用
*/
public class Solution {
    public int removeElement(int[] A, int elem) {
        int num = 0;
        for(int i =0; i< A.length; i++){
            if(A[i]!=elem){
                A[num++] = A[i];
            }
        }
        return num;
    }
}


//another Solution
public class Solution {
    public int removeElement(int[] A, int elem) {
        int num = 0;
        int right = A.length - 1;
        int i = 0;
        while(i <= right){
            if(A[i] == elem){
                num++;
                int tmp = A[i];
                A[i] = A[right];
                A[right] = tmp;
                right--;
            }else{
                i++;
            }
        }
        return A.length - num;
    }
}