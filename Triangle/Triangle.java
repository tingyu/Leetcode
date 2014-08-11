/**

Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle. 
*/

/*
 Solution: DP (met during Amazon interview)

Declare pathSum array with length of triangle size(). For triangle, the bottom row length is equal 
to the height of triangle, 
so use pathSum to hold the bottom row's value, then from bottom to up, find minimum path
一个算法是top-bottom的，那现在这个就是bottom-up了，从最后一层开始，看上面一层的相邻节点，上面一层的每个节点在下一层有两个相邻节点，选取小的加上。
加到最后，就是p[0],就是返回值了。复杂度比上一个小了好多。
*/
//Bottom-Up, actually start from the bottom of the triangle.
//http://www.programcreek.com/2013/01/leetcode-triangle-java/
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int l = triangle.size() - 1;

        for(int i = 0; i < triangle.get(l).size(); i++){
        	total[i] = triangle.get(l).get(i);
        }

        //iterate from last second row
        for(int i = triangle.size() - 2; i >=0; i--){
        	for(int j = 0; j < triangle.get(i+1).size() - 1; j++){
        		total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j+1]);
        	}
        }
        return total[0];
    }
}


//a in-place DP Solution
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
    	for(int i = triangle.size() - 2; i>=0; i--){
    		for(int j = 0; j < triangle.get(i+1).size() - 1; j++){
    			triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
    		}
    	}
    	return triangle.get(0).get(0);
  	}
}

//up to bottom
//http://gongxuns.blogspot.com/2012/12/leetcodetriangle.html
public class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(triangle==null || triangle.size()==0) return 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<triangle.size();i++){
            ArrayList<Integer> temp = triangle.get(i);
            res.add(0,i>0?temp.get(0)+res.get(0):temp.get(0));
            for(int j=1;j<res.size()-1;j++){
                res.set(j,Math.min(res.get(j),res.get(j+1))+temp.get(j));
            }
            if(res.size()>1)
                res.set(res.size()-1,res.get(res.size()-1)+temp.get(res.size()-1));
        }
        
        int min=Integer.MAX_VALUE;
        for(Integer temp:res){
            min=Math.min(temp,min);
        }
        return min;
    }
}