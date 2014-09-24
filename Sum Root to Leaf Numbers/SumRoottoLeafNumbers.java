/**
Sum Root to Leaf Numbers 

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

//wrong solution
//Line 20: error: missing return value
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int sum = 0;
        sum = dfs(root, sum);
        return sum;
    }
    
    public int dfs(TreeNode node, int sum){
        if(node == null)
            return;
            
        sum = sum * 10 + node.val;
        
        dfs(node.left, sum);
        dfs(node.right, sum);
        return sum;
    }
}

//correct solution


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int[] sum = {0}; //tricky point!!
        int current = 0;
        dfs(root, sum, current);//tricky point!!
        return sum[0];//tricky point!!
    }
    
    public void dfs(TreeNode node, int[] sum, int current){
        if(node == null)
            return;
            
        current = current * 10 + node.val;
        
        if(node.left == null && node.right == null){
            sum[0] = sum[0] + current;
            return；        
        }
        
        dfs(node.left, sum, current);
        dfs(node.right, sum, current);
    }
}

public class Solution {
    public int sumNumbers(TreeNode root) {
        int[] sum = {0};
        helper(root, sum, 0);
        return sum[0];
    }
    
    private void helper(TreeNode node, int[] sum, int current){
        if(node == null) return;
        current = current*10 + node.val;
        
        if(node.left == null && node.right == null){
            sum[0] += current;
        }
        
        helper(node.left, sum, current);
        helper(node.right, sum, current);
    }
}

public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        ArrayList<String> res = new ArrayList<String>();
        StringBuilder tmp = new StringBuilder();
        helper(root, res, tmp);
        return convertToSum(res);
    }
    
    private void helper(TreeNode node, ArrayList<String> res, StringBuilder tmp){
        if(node == null) return;
        tmp.append(node.val);
        if(node.left == null && node.right == null){
            res.add(tmp.toString());
        }
        
        helper(node.left, res, tmp);
        helper(node.right, res, tmp);
        tmp.deleteCharAt(tmp.length()- 1);
    }
    
    private int convertToSum(ArrayList<String> res){
        int sum = 0;
        for(String s : res){
            sum += Integer.valueOf(s);
        }
        return sum;
    }
}

//一个比较麻烦，复杂度高的解法
public class Solution {
    public int sumNumbers(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        helper(root, list, tmp);
        return sumNumbers(list);
    }
    
    private void helper(TreeNode node, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tmp){
        if(node == null) return;
        tmp.add(node.val);
        if(node.left == null && node.right == null){
            list.add(new ArrayList<Integer>(tmp));
        }
        
        helper(node.left, list, tmp);
        helper(node.right, list, tmp);
        tmp.remove(tmp.size() -1);
    }
    
    private int sumNumbers(ArrayList<ArrayList<Integer>> list){
        int sum = 0;
        for(ArrayList<Integer> l: list){
            int number = 0;
            for(int a: l){
                number = number*10 + a;
            }
            sum += number;
        }
        return sum;
    }
}