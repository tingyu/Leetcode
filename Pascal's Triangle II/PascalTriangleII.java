/**
Pascal's Triangle II 

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

//my solution

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < rowIndex+1; i++){
            //res.clear(); this is wrong, return the previous row?
            res = new ArrayList<Integer>();
            res.add(1);
            if(i>0){
                for(int j = 0; j < tmp.get(i-1).size() -1; j++){
                    res.add(tmp.get(i-1).get(j) + tmp.get(i-1).get(j+1));
                }
                res.add(1);
            }
            tmp.add(res);
        }
        return res;
    }
}


/*
optimize your algorithm to use only O(k) extra space
*/
/*
Idea of using only O(k) extra space is reusing the same array list to form the current row of numbers.  
Iterations will be from 0th row ([1]) to kth row.  And for each iteration, 
we need to form the current base on the previous row.

For example, in 3th row, we have [1,3,3,1].  So in the iteration of 4th row, 
we need only add 1 at the beginning of this array list, overwritten the 2nd element 
to 1+3=4, 3rd element to 3+3=6, 4th element to 3+1=4, and keep the last element remain unchanged.  
Then we would have all the numbers of 4th row, i.e.[1,4,6,4,1].
这个解法很好，而且比较tricky。尽管想到了使用一个相同的arraylist然后不断更新，但是如果按照I里面的写法，就会发现更新之后
实际上求的是左边和上面的和。而不是所谓的上面两个值的和了。为了解决这种情况，需要在每一行0的位置插入一个1，这样使用
res.set(j, res.get(j) + res.get(j+1));即可，在结尾的时候也不用再add(1)了
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
 		ArrayList<Integer> res = new ArrayList<Integer>();

 		//special case
 		if(rowIndex == 0){
 			res.add(1);
 			return res;
 		}

 		res.add(1); //row 0

 		for(int i = 1; i <= rowIndex; i++){//rows
 			res.add(0, 1); // add first element
 			for(int j = 1; j <= i -1; j++){ //columns of ith row  
 				res.set(j, res.get(j) + res.get(j+1));
 			}
 		}
 		return res;
    }
}

