/**

3Sum
Total Accepted: 13505 Total Submissions: 82155

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all 
unique triplets in the array which gives the sum of zero.

Note:

    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
    The solution set must not contain duplicate triplets.

    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

/*
summary:
The solution set must not contain duplicate triplets.

这题首先想到的是naive的解法，但是这种肯定是不可行的。
最优解是O(n^2)的，先遍历这个数组，通过固定一个i的值，然后来求j, k。固定了i
之后就得到了negate，就把三个数的和为0转化为了求两个数的和是一个固定值negate.
为了保证输出是递增的顺序，同时为了后面的好操作，首先把原来数组sort一下。
在求另外两个值的时候，用到了双指针的解法，一个从i+1开始，一个从最后开始向
中间找，看着很像binary search，但是又不一样。
里面一定要注意很tricky的地方是不能包含重复的，但是数组中有重复的。这两个重复的概念不同，前面的是res中不能重复。
上面的去除duplicates的操作是分别针对i, j, k的。就是对于i 或者 j, 或者k， 考虑其中
一种的时候不能要重复的。
*/

/*
1. Naive Solution

Naive solution is 3 loops, and this gives time complexity O(n^3). Apparently this is not an acceptable solution, 
but a discussion can start from here.
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < num.length; i++){
            for(int j = i + 1; j < num.length; j++){
                for(int k = j + 1; k < num.length; k++){
                    if(num[j] + num[k] + num[i] == 0){
                        ArrayList<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(num[i]);
                        tmp.add(num[j]);
                        tmp.add(num[k]);
                        res.add(new ArrayList<Integer>(tmp));
                    }
                }
            }
        }
        return res;
    }
}

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    	//sort array
        Arrays.sort(num);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> each = new ArrayList<Integer>();
        for(int i =0; i < num.length; i++){
        	if(num[i] > 0) break;

            for(int j = i +1; j < num.length; j++){
            	if(num[i] + num[j] >0 && num[j] >0) break;//?????

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
	Arrays.sort(num);//tricky，只有sort了才可以进行下面的操作

	for(int i = 0; i < num.length -2; i++){
		//avoid duplicate solution
		if(i ==0 || num[i] > num[i -1]){//只有在不等的时候才考虑，去除了duplicates
			int negate = -num[i];

			int start = i + 1;
			int end = num.length -1;

			while(start < end){
				//case 1
				if(num[start] + num[end] == negate){
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(num[i]);
					tmp.add(s[start]);
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

/*
上面的去除duplicates的操作是分别针对i, j, k的。就是对于i 或者 j, 或者k， 考虑其中一种的时候不能要重复的。
如果画个斜线图可以很好的看出来，如果有duplicate那么就是横线了，横线的话移动i或者start或者end就没有意义，因为改变不了什么
*/

//HashSet的解法。这种解法好写些，但是复杂度高
public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        
        for(int i = 0; i < num.length - 2; i++){
            int negate = -num[i];
            //if(i != 0 && num[i] == num[i-1]) continue;
            int j = i + 1;
            int k = num.length - 1;
            
            while(j < k){
                if(num[j] + num[k] == negate){
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(num[i]);
                    tmp.add(num[j]);
                    tmp.add(num[k]);
                    //res.add(tmp);
                    j++;
                    k--;
                    if(set.add(tmp)){
                        res.add(tmp);
                    }
                    /*
                    while(j < num.length && num[j] == num[j-1]){
                        j++;
                    }
                    while(k > 0 && num[k] == num[k+1]){
                        k--;
                    }*/
                }else if(num[j] + num[k] < negate){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return res;
    }
}