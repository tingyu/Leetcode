/**

3Sum
Total Accepted: 13505 Total Submissions: 82155

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
    The solution set must not contain duplicate triplets.

    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

/*
1. Naive Solution

Naive solution is 3 loops, and this gives time complexity O(n^3). Apparently this is not an acceptable solution, but a discussion can start from here.
*/

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    	//sort array
        Arrays.sort(num);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> each = new ArrayList<Integer>();
        for(int i =0; i < num.length; i++){
        	if(num[i] > 0) break;

            for(int j = i +1; j < num.length; j++){
            	if(num[i] + num[j] >0 && num[j] >0) break;

                for(int k =j +1; k < num.length; k++){
                    if(num[i] + num[j] + num[k] == 0){
                        each.add(num[i]);
                        each.add(num[j]);
                        each.add(num[k]);
                        result.add(each);
                        each.clear();
                    }
                }
            }
        }
        return result;
    }
}

/*
* The solution also does not handle duplicates. Therefore, it is not only time inefficient, but also incorrect.

Result:

Submission Result: Output Limit Exceeded
*/

/*
2. Better Solution

A better solution is using two pointers instead of one. This makes time complexity of O(n^2).

To avoid duplicate, we can take advantage of sorted arrays, i.e., move pointers by >1 to use same element only once.
*/


public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
	ArrayList<ArrayList<Integer> result = new ArrayList<ArrayList<Integer>>();

	if(num.length < 3) return result;

	//sort array
	Arrays.sort(num);

	for(int i = 0; i < num.length -2; i++){
		//avoid duplicate solution
		if(i ==0 || num[i] > num[i -1]){
			int negate = -num[i];

			int start = i + 1;
			int end = num.length -1;

			while(start < end){
				//case 1
				if(num[start] + num[end] == negate){
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(num[i]);
					tmp.add(num[start]);
					tmp.add(num[end]);

					result.add(tmp);
					start++;
					end--;

					//avoid duplicate solution
					while(start < end && num[end] == num[end + 1])
						end--;

					while(start < end && num[start] == num[start - 1])
						start++;
				//case 2
				} else if (num[start] + num[end] < negate){
					start++;
				} else{
					end--;
				}
			} 
		}
		return result;		
	}
}