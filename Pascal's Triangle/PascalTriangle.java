/*
Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
//my new solution
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        for(int i = 0; i < numRows; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(1);
            if(i > 1){
                for(int j = 1; j < res.get(i-1).size(); j++){
                    tmp.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
            if(i > 0){
                tmp.add(1);
            }
            res.add(tmp);
        }
        return res;
    }
}

//my solution:
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        for(int i = 0; i < numRows; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for(int j = 0; j < i+1; j++){
                if(j == 0){
                    tmp.add(1);
                }
                if(j > 0 && j < i){
                    tmp.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
                if(i!= 0 && j == i){
                    tmp.add(1);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}


public class Solution{
	public ArrayList<ArrayList<Integer>> generate(int numRows){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numRows; i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(1);
			if(i > 0){
				for(int j = 0; j < res.get(i-1).size() - 1; j++){
					temp.add(res.get(i -1).get(j) + res.get(i-1).get(j+1));
				}
				temp.add(1);
			}
			res.add(temp);
		}
		return res;
	}
}