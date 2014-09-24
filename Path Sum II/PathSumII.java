/**
Path Sum II 

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

//my solution: this solutio similar to Path Sum
public class Solution {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        int accSum = 0;
        helper(root, res, tmp, accSum, sum);
        return res;
    }
    
    private void helper(TreeNode node, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int accSum, int sum){
        if(node == null) return;
        
        accSum = accSum + node.val;
        tmp.add(node.val);
        if(node.left == null && node.right == null && accSum == sum){
            res.add(new ArrayList<Integer>(tmp));
        }
        
        helper(node.left, res, tmp, accSum, sum);
        helper(node.right, res, tmp, accSum, sum);
        tmp.remove(tmp.size() -1);
    }
}



/*
上面的解法，把最后部分改成
        if(node.left != null){
            helper(node.left, res, tmp, accSum, sum);
            tmp.remove(tmp.size() -1);
        }
        if(node.right != null){
            helper(node.right, res, tmp, accSum, sum);
            tmp.remove(tmp.size() -1);
        }
也是对的
改成
        if(node.left != null){
            helper(node.left, res, tmp, accSum, sum);
        }
        if(node.right != null){
            helper(node.right, res, tmp, accSum, sum);
        }
        
        tmp.remove(tmp.size() -1);
也是对的
*/

//my another solution
public class Solution {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        helper(root, sum, res, tmp);
        return res;
    }
    
    private void helper(TreeNode node, int sum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp){
        if(node == null) return;
        tmp.add(node.val);
        
        if(node.left == null && node.right == null){
            int tmpSum = 0;
            for(int a: tmp){
                tmpSum += a;
            }
            if(tmpSum == sum){
                res.add(new ArrayList<Integer>(tmp));
            }
        }
        
        helper(node.left, sum, res, tmp);
        helper(node.right, sum, res, tmp);
        tmp.remove(tmp.size() - 1);
    }
}
/*
if we         //tmp.remove(tmp.size() - 1);
Submission Result: Wrong Answer

Input:  {0,1,1}, 1
Output: [[0,1]]
Expected:   [[0,1],[0,1]]

if we                 //res.add(new ArrayList<Integer>(tmp));
                res.add(tmp);
Submission Result: Wrong Answer

Input:  {1}, 1
Output: [[]]
Expected:   [[1]]

*/

/*
Solution:  DFS

Apply DFS check every possible combination, record result if meet requirement
*/


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//下面这种解法和上面不同的是，上面dfs的时候用了个accSum来记录sum。这里是在叶子节点的时候遍历tmp来求和。思想上貌似更为直观些
public class Solution {
    //public List<List<Integer>> pathSum(TreeNode root, int sum) {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        dfs(root, sum, tmp, res);
        return res;
    }
    
    public void dfs(TreeNode node, int sum, ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> res){
        if(node == null) return;
        
        tmp.add(node.val);

        if(node.left == null && node.right == null){
            int calSum = 0;
            for(int a: tmp){
                calSum += a;
            } 

            if(calSum == sum){
                res.add(new ArrayList<Integer>(tmp));
                return;
            }
        }
        
        //这两个判断和两个remove一定要加上，不加上就出错了。
        if(node.left != null){
            dfs(node.left, sum, tmp, res);
            tmp.remove(tmp.size() -1);    
        }
        if(node.right != null){
            dfs(node.right, sum, tmp, res);
            tmp.remove(tmp.size() -1);           
        }
    }
}
/*
但是这里如果
        if(node.left != null){
            dfs(node.left, sum, tmp, res);
        }
        if(node.right != null){
            dfs(node.right, sum, tmp, res);
        }
        tmp.remove(tmp.size() -1); 

        或者

        dfs(node.left, sum, tmp, res);
        dfs(node.right, sum, tmp, res);
        tmp.remove(tmp.size() -1); 
就错了
*/

/*
以下是别人的写法，把sum不停的减掉当前值，直到==0
这种写法不需要每次都tmp.remove
*/
public class Solution {
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
		if (root==null){
			return result;
		}	
		ArrayList<Integer> current=new ArrayList<Integer>();
		buildResult(root, sum, current, result);
		return result;
	}
	
	private void buildResult(TreeNode root,int sum, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result){
		if (root==null){
			return;
		}
		
		int currentVal=root.val;
		current.add(currentVal);
		
		if (root.left==null && root.right==null){
			if (sum-currentVal==0){
				ArrayList<Integer> temp=new ArrayList<Integer> (current);
				result.add(temp);
			}
		}

		buildResult(root.left, sum-currentVal, current, result);
		buildResult(root.right, sum-currentVal, current, result);
		current.remove(current.size()-1);
	}
}


public class Solution {
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
	// Start typing your Java solution below
	// DO NOT write main() function
	ArrayList <Integer> current=new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
	pathSumHelper(root, sum, current, result);
	return result;
	}

	public void pathSumHelper(TreeNode root, int sum, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result)
	{
		if (root==null){
		return;
		}
		current.add(root.val);
		int current_sum=sum-root.val;
		if (current_sum==0&& root.left==null && root.right==null){
			ArrayList<Integer> temp=new ArrayList<Integer>();
			for(int i:current){
				temp.add(i);
			}
			result.add(temp);
		}
	pathSumHelper(root.left, current_sum, current, result);
	pathSumHelper(root.right, current_sum, current, result);
	current.remove(current.size()-1);
	}
}

/*
简单的recursive解法
！注意：因为java是pass by reference, 所以需要特别注意送到recursive function是原来的instance还是一个clone的instance
http://xpxu.net/blog/?p=22
*/
public class Solution {
    public ArrayList> pathSum(TreeNode root, int sum) {
        ArrayList> solution = new ArrayList>();
        if (root == null) return solution;
        findSum(root, sum, new ArrayList(), solution);
        return solution;
    }
    void findSum(TreeNode root, int remain, ArrayList current, ArrayList> solution){
        remain -= root.val;
       // Cannot use current here, coz java is pass by reference!!
        ArrayList cur = (ArrayList) current.clone();
        cur.add(root.val);
        //leaf node && sum == sum
        if (root.left == null && root.right == null && remain == 0) solution.add(cur);
        if (root.left != null) findSum(root.left, remain, cur, solution);
        if (root.right != null) findSum(root.right, remain, cur, solution);
    }
}

