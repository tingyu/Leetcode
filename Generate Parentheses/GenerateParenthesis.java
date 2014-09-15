/**

Generate Parentheses
Total Accepted: 11441 Total Submissions: 37197

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Have you been asked this question in an interview?
*/

/*
summary
采用的是recursion的解法，采用一个helper函数，
helper(ArrayList<String> res, int left, int right, String tmp, int n){
left和right分别记录左右括号的数目。
如果right > left需要return，因为此时不合法
如果两个都是n，就加到res中。
如果Left是n, 那么只用递归的加上右括号
如果是其他情况，既递归的加上左括号，又递归的加上右括号
*/

public class Solution {
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<String>();
        rec(n, 0, 0, "", list);
        return list;
    }
    
    public void rec(int n, int left, int right, String s, ArrayList<String> list){
        if(left < right)
            return ;
        
        if(left ==n && right ==n){
            list.add(s);
        }
        
        if(left == n){
            rec(n, left, right+1, s + ")", list);
            return ;
        }
        
        rec(n, left+1, right, s + "(", list);
        rec(n, left, right +1, s + ")", list);
    }
}

//my solution
public class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        helper(n, 0, 0, "", res);
        return res;
    }
    
    private void helper(int n, int left, int right, String tmp, ArrayList<String> res){
        if(right > left) return;
        if(left == n && right == n){
            res.add(tmp);
        }
        
        if(left == n){
            helper(n, left, right + 1, tmp + ")", res);
        }else{
            helper(n, left + 1, right, tmp + "(", res);
            helper(n, left, right + 1, tmp + ")", res);
        }
        
    }
}