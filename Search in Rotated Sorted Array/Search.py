''''
Search in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
''''

class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return an integer
    def search(self, A, target):
    	first = 0
    	end = len(A)-1
    	mid = (A[first] + A[end])/2

    	if mid==target:
    		return mid
    	if(mid < target):
    		return 

        

if __name__ == '__main__':
    print search_rotated_arr([4,5,6,7,0,1,2], 6, 0, 7)
    print search_rotated_arr([4,5,6,7,0,1,2], 0, 0, 7)
    print search_rotated_arr([4,5,6,7,0,1,2], 3, 0, 7)
    print
    print search_rotated_arr2([4,5,7,0,1,2,4,4], 7, 0, 8)
    print search_rotated_arr2([4,5,6,7,0,0,1,2], 0, 0, 8)
    print search_rotated_arr2([4,4,4,4,5,2], 3, 0, 6)