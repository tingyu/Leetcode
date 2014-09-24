/**
Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that 
adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

//my solution
//dfs的解法，采用经典的recursion的方法。注意要传入boolean[] exist = {false};以及只有在当前是叶子节点的时候判断是不是target
//这里没有看到remove之类的方法
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        boolean[] exist = {false};
        int calSum = 0;
        dfs(root, sum, calSum, exist);
        return exist[0];
    }
    
    private void dfs(TreeNode node, int sum, int calSum, boolean[] exist){
        if(node == null) return;
        calSum += node.val;
        if(node.left == null && node.right ==null){//注意要判断这个
        	//只要有一个true就好了
            if(calSum == sum){
                exist[0] = true;
                return;
            }
        }
        
        dfs(node.left, sum, calSum, exist);
        dfs(node.right, sum, calSum, exist);
    }
}
//可以加上，那么就可以减去拉
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null) return false;
        boolean[] res = new boolean[1];
        dfs(root, sum, res);
        return res[0];
    }
    
    private void dfs(TreeNode node, int sum, boolean[] res){
        if(node == null) return;

        sum -= node.val;
        
        if(node.left == null && node.right == null){
            if(sum == 0) res[0] = true;
            return;
        }
        dfs(node.left, sum, res);
        dfs(node.right, sum, res);
    }
}

//other solutions
//http://gongxuns.blogspot.com/2012/12/leetcodepath-sum.html

/*
Add all node to a queue and store sum value of each node to another queue. 
When it is a leaf node, check the stored sum value.

For example above, the queue would be: 5 - 4 - 8 - 11 - 13 - 4 - 7 - 2 - 1. It will check node 13, 7, 2 and 1.

This is a typical breadth first search(BFS) problem.
非recursion的解法
实现起来也是level order traversal
注意这种BFS的写法，不断的poll，不断的往里面加入新的left child和right child

BFS解法，使用两个数据结构nodes和accsums来记录所有的结点和累加和，这里是不断的加入叶子结点，然后不断poll出父节点。
这种方法不熟悉但是很好，可以记下
*/

public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return false;

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		LinkedList<Integer> accSums = new LinkedList<Integer>();

		nodes.add(root);
		nodes.add(root.val);

		while(!nodes.isEmpty()){
			TreeNode node = nodes.poll();
			int accSum = accSums.poll();
			if(node.left == null || node.right == null && accSums == sum){
				return true;
			}

			if(node.left != null){
				nodes.add(node.left);
				accSums.add(accSum + node.left.val);
			}

			if(node.right != null){
				nodes.add(node.right);
				accSums.add(accSum + node.right.val);
			}
		} 
		return false;
    }
}


//DFS non-recursion solution
//这里是DFS，preorder traversal。和前面BFS不同，仅仅由于采用了不同的数据结构。这里遍历的顺序也完全不同
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //DFS solution
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        Stack<Integer> accSums = new Stack<Integer>();
        
        nodes.add(root);
        accSums.add(root.val);
        
        while(!nodes.isEmpty()){
            TreeNode node = nodes.pop();
            int accSum = accSums.pop();
            
            if(node.left == null && node.right ==null && accSum == sum){
                return true;
            }
            
            if(node.right != null){
                nodes.add(node.right);
                accSums.add(accSum + node.right.val);
            }
            
            if(node.left != null){
                nodes.add(node.left);
                accSums.add(accSum + node.left.val);
            }
        }
        return false;
    }
}


/*
my dfs solution, why this is wrong??
你遍历这颗树的顺序是
5 > 4 -> 11 -> 7 -> 2> 8 -> 13 ->4 -> 1
那么accSum就会一直被累加
只要超过了target，那就错

你需要入站的时候同时记录一个当前的target value
那recursion的时候也没用管accSum，那会自动变回原来的？
因为是值传递。 int是原始类型，所以是值传递
所以一定要用两个stack来记录当前的。

本质上是函数的参数的传递方式
在java中，都是值传递
如果是原始类型，如int，double都是直接传递memory的原始值
如果遇到class object，那就会传递引用的指针
比如arraylist作为参数，就会传递它的指针，也是一个值
当函数对这个指针操作的时候，就会影响这个arraylist本身

me:  这跟要remove有什么关系。。
当然了啊，你之前add多了，但不是一个解，那就要remove，还原现场。不然别的函数调用还会在错误的结果上进行

java函数都是传值的。 只不过object参数是指针的值.引用其实是一回事情。
。
反正就是函数调用的时候，记录前面的状态值，他们只管int什么基本类型的，object之类的是引用的值，所以要自己remove


public class Solution {
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null) return false;
        int accSum = 0;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            accSum += cur.val;
            
            if(cur.left == null && cur.right == null){
                if(accSum == sum){
                    return true;
                }
            }
            
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
        return false;
    }
}*/