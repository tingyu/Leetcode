/**
Subsets II

 Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:

    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.

For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution {
    //public List<List<Integer>> subsetsWithDup(int[] num) {
      public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        if(num ==null || num.length ==0) return null;
        
        Arrays.sort(num);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();
        
        for(int i = num.length -1; i >= 0; i--){
            //get existing sets
            //if(i == num.length -1 || num[i] !=num[i+1] || prev.size() == 0){
            if(i == num.length -1 || num[i] !=num[i+1]){
                prev = new ArrayList<ArrayList<Integer>>();
                for(int j = 0; j < result.size(); j++){
                    prev.add(new ArrayList<Integer>(result.get(j)));
                }
            }
            
            //add current number to each element of set
            for(ArrayList<Integer> temp: prev){
                temp.add(0, num[i]);
            }
            
            //add each single number as a set, only if current element is different with previous
            if(i == num.length - 1|| num[i] !=num[i+1]){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(num[i]);
                prev.add(temp);
            }
            
            //add all set created in this iteration
            
            for(ArrayList<Integer> temp: prev){
                result.add(new ArrayList<Integer>(temp));
            }
            //result.addAll(prev); this is wrong
        }
        result.add(new ArrayList<Integer>());
        return result;
    }
}

//wrong solution
public class Solution {
    //public List<List<Integer>> subsetsWithDup(int[] num) {
      public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
          if(num == null) return null;
          
          Arrays.sort(num);
          
          ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
          
          for(int i = 0; i < num.length; i++){
              ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
               if(i==0 || (i > 0 && num[i] != num[i-1])){
                   temp = new ArrayList<ArrayList<Integer>>();
                  //copy all arraylist in result to temp 
                  for(ArrayList<Integer> a: result){
                      temp.add(new ArrayList<Integer>(a));
                  }
               }
              
              //append s[i] to the arraylist of temp
              for(ArrayList<Integer> a: temp){
                  a.add(num[i]);
              }

              //append s[i] to the arraylist of temp
              for(ArrayList<Integer> a: temp){
                  a.add(num[i]);
              }
              
              if(i==0 || ( i > 0 && num[i-1]!=num[i])){
                  //add single num[i] as arraylist
                  ArrayList<Integer> single = new ArrayList<Integer>();
                  single.add(num[i]);
                  temp.add(single);
              }
              
              result.addAll(temp);
          }
          
          result.add(new ArrayList<Integer>());
          return result;
      }
}