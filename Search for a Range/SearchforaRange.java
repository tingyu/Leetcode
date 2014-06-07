/**
Search for a Range 

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

*/

//my solution:
public class Solution { 
    public int[] searchRange(int[] A, int target) { 
         int[] res = new int[2]; 
         int index = searchRange(A, 0, A.length -1, target); 
         if(index == -1){ 
             res[0] = -1; 
             res[1] = -1; 
         }else{ 
             int i = index, j =index; 
             while(i>=1 && A[i-1] == target){
                 i--;
             }
             while(j<A.length-1&&A[j+1] == target){
                 j++;
             }
             res[0] = i; 
             res[1] = j; 
         } 
         return res; 
    } 
     
    public int searchRange(int[] A, int left, int right, int target){ 
        if(left > right) return -1;
        int mid = (left + right)/2; 
        if(A[mid] == target){ 
            return mid; 
        }else if(target > A[mid]){ 
            return searchRange(A, mid+1, right, target); 
        }else{ 
            return searchRange(A, left, mid-1, target);  
        } 
    } 
}



//another solution
/*
Solution: because of O(logn), so we should consider about binary search, so how to apply binary 
search to find the low bound and high bound? We can make target -0.5 for searching low 
bound and target+0. 5 for the high bound. Be careful the corner case
*/
public class Solution {
    public int[] searchRange(int[] A, int target) {  
        if (A==null) return null;
       
        int[] result={-1,-1};
      
        int low=binarySearch(A,target-0.5);
        // Be care for there , low>=A.length must be check first or 
        // there may be a out of boundary exception cause 
        // the binarySearch function in this question return low instead of null
        // if the target are not in the array
        
        if (low>=A.length||A[low]!=target){
            return result;
        }
        result[0]=low;
        result[1]=binarySearch(A,target+0.5)-1;
        
        return result;
           
    }
    public int binarySearch(int[] A, double t){
        int low = 0, high = A.length - 1;
        while(low <= high){
            int m = (low + high) / 2;
            if(A[m] == t) return m;
            if(A[m] > t) high = m - 1;
            else low = m + 1;
        }
        return low;
    }
}