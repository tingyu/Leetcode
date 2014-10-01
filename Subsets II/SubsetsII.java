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
//a right solution
/*
这题解法跟Subset很类似。采用的是iterative的解法
因为要按照递增的顺序排列，所以需要先把原来数组sort下，这样按顺序添加的每个子数组会是递增的
有Duplicates和没有Duplicate的最大的区别体现在两个方面
1， 单个元素的数组：只添加第一次出现，后面出现就不添加
2. 比如如果有[1, 2]后面有相同的时候就不要再加上[1, 2]了。
所以如果是重复的时候，如果给tmp加上res，那么里面有一部分就会重复。比如开始有
1）iteration1: 最后res = [[1]]
2）iteration2: 这次iteration, res全部拷贝给tmp = [[1]], tmp再加上当前的，tmp更新为[[1, 2],[2]]。
              这里的tmp加到原来的res中。最后res = [[1], [1, 2], [2]]
3) iteration3: 重复的2出现，如果这次跟上个iteration一样处理。那么就是tmp = [[1], [1, 2], [2]]，再分别加上2
              那么就会tmp =[[1, 2], [1, 2, 2], [2, 2]]这样和res中的[1, 2]重复了。
              所以这种有重复的需要特殊处理。
              不重复的时候，是把tmp全部new，然后全部复制res里面所有元素
              如果重复的话，那么就处理一下，不要重新copy，就在一次iteration的基础上叠加就行了。

记得在每次iteration之后，都要把tmp里面的元素都加到res里面。然后下一次iteration，根据是否重复来更新或者不变
如何判断是不是重复：if(i == 0 || num[i] != num[i-1])
最后记得要加上空的情况
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(num == null) return res;
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
        
        for(int i = 0; i < num.length; i++){
            //get existing sets       
            if(i == 0 || num[i] != num[i-1]){
                tmp = new ArrayList<ArrayList<Integer>>();
                for(ArrayList<Integer> a: res){
                    tmp.add(new ArrayList<Integer>(a));
                }
            }
            
            //add current number to each element of set
            for(ArrayList<Integer> a: tmp){
                a.add(num[i]);
            }
            
            if(i == 0 || num[i] != num[i-1]){
                ArrayList<Integer> t = new ArrayList<Integer>();
                t.add(num[i]);
                tmp.add(t);
            }
            
            //add all set created in this iteration
            for(ArrayList<Integer> t: tmp){
                res.add(new ArrayList<Integer>(t));
            }
        }
        res.add(new ArrayList<Integer>());
        return res;
    }
}

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

//another wrong solutionpublic 
class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) return res;
        Arrays.sort(num);
        
        //ArrayList<ArrayList<Integer>> prev = new
        int pre = num[0];
        
        for(int i = 0; i < num.length; i++){
            ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
            
            for(ArrayList<Integer> a: res){
                tmp.add(a);
            }
            
            for(ArrayList<Integer> a: tmp){
                if(pre != num[i] || a.size() == num.length - 1){
                    a.add(num[i]);      
                }
            }
            
            if(num[i] != pre){
                ArrayList<Integer> single = new ArrayList<Integer>();
                single.add(num[i]);
                tmp.add(single);                
            }
            pre = num[i];
            res.addAll(tmp);
        }
        res.add(new ArrayList<Integer>());
        return res;
    }
}