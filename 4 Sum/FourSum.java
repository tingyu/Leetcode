/**
LeetCode – 4Sum (Java) 

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)

*/
/*
Thoughts

A typical k-sum problem. Time is N to the poser of (k-1).

经典的K-SUM问题，解题思路除了2Sum利用hashMap降维之外，其他的都用了先sort,
再左右指针往中间移动的方法。
比如3SUM就是一个for循环，4Sum就是两个for循环
这里用到了HashSet来判断有没有重复的
注意在相等情形之后还是要向中间移动指针，如果忽略了这么一点，就会造成死循环
*/

public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
	Arrays.sort(num);

	HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

	for(int i = 0; i < num.length; i++){
		for(int j = i + 1; j < num.length; j++){
			int k = j + 1;
			int l = num.length - 1;

			while(k < l){
				int sum = num[i] + num[j] + num[k] + num[l];

				if(sum > target){
					l--;
				} else if(sum < target){
					k++;
				} else if(sum == target) {
					ArrayList<Integer> temp = new ArrayList<Integer>();
					temp.add(num[i]);
					temp.add(num[j]);
					temp.add(num[k]);
					temp.add(num[l]);

					if(!hashSet.contains(temp)){
						hashSet.add(temp);
						result.add(temp);
					}

					k++;
					l--;
				}
			}
		}
	}
	return result;
}

/*
Here is the hashCode method of ArrayList. It makes sure that if all elements of two lists are the same, 
then the hash code of the two lists will be the same. 
Since each element in the ArrayList is Integer, same integer has same hash code.
*/

int hashCode = 1;
Iterator<E> i = list.iterator();
while (i.hasNext()) {
      E obj = i.next();
      hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
}