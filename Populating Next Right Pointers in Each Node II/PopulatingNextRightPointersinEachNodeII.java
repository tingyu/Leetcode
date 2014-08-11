/**
Populating Next Right Pointers in Each Node II 

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/*
my solution:
使用inorder traversal。为什么我的recursion的解法超时了？？？？
Submission Result: Time Limit Exceeded

Last executed input:	{69,73,68,18,20,18,39,7,-3,13,-1,42,5,93,70,63,17,#,91,-4,30,#,-1,64,-4,16,49,48,78,51,43,92,45,#,53,9,36,80,-6,58,78,#,#,41,81,89,67,71,#,25,#,82,54,28,14,61,57,35,5,83,9,18,#,-9,-9,50,92,93,#,0,80,62,1,28,29,27,89,21,#,85,-9,#,56,56,-9,#,#,43,#,29,97,-7,#,35,25,90,67,53,18,61,7,23,81,37,19,26,2,0,19,#,#,77,37,-2,#,49,39,28,1,37,11,87,83,68,55,53,33,-2,22,7,52,#,14,#,18,50,97,-8,-7,#,21,59,72,27,#,64,#,#,47,#,#,38,46,#,#,99,#,#,48,13,85,78,7,64,43,59,71,11,37,12,37,50,2,#,#,89,87,#,78,97,#,31,86,37,96,3
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        ArrayList<ArrayList<TreeLinkNode>> res = new ArrayList<ArrayList<TreeLinkNode>>();
        //if(root == null) return res;
        
        ArrayList<TreeLinkNode> tmp = new ArrayList<TreeLinkNode>();
        
        levelorder(root, tmp, res, 1);
        
        //connect(root, tmp, res, 1);
    }
    
    public void levelorder(TreeLinkNode node, ArrayList<TreeLinkNode> tmp, ArrayList<ArrayList<TreeLinkNode>> res, int height){
        if(node == null) return;
        
        if(res.size() < height) res.add(tmp);
        else tmp = res.get(height -1);
        
        if(tmp.size() > 1){
            tmp.get(tmp.size() -1).next = node;
            node.next = null;
        }
        tmp.add(node);
        
        levelorder(node.left, tmp, res, height + 1);
        levelorder(node.right, tmp, res, height + 1);
        
    }
    
    public void connect(TreeLinkNode node, ArrayList<TreeLinkNode> tmp, ArrayList<ArrayList<TreeLinkNode>> res, int height){
        for(int i = 0; i < res.size(); i++){
            tmp = res.get(height - 1);
            for(int j = 1; j < tmp.size(); j++){
                tmp.get(j-1).next = tmp.get(j);
            }
            tmp.get(tmp.size() -1).next = null;
        }
    }
}

//my second solution
//Runtime: 516 ms
/*
这个方法就是使用BFS，层序遍历，在遍历的时候不断的判断。
前面recursion的方法超时通过不了，但是这个方法是可以通过的
这个方法和Path Sum中的BFS方法类似。用两个LinkedList/ Queue来记录数据，一个是结点数据，一个是需要处理的相应问题的数据。
在Path Sum中，该数据是累加和，在这里这个数据是高度。

*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        Queue<TreeLinkNode> nodes = new LinkedList<TreeLinkNode>();
        Queue<Integer> heights = new LinkedList<Integer>();
        
        nodes.add(root);
        heights.add(1);
        
        while(!nodes.isEmpty()){
            //得到当前结点和相应高度
            TreeLinkNode node = nodes.poll();
            int height = heights.poll();
            
            //这里是判断下一个结点。因为是层序遍历，如果下一个结点存在，我们需要将pop出的结点指向下一个结点或者指向null
            if(!nodes.isEmpty()){
                //这里使用的是peek，不是pop，因为下一个循环需要pop出他们找他们的next
                int nextNodeHeight = heights.peek();
                TreeLinkNode nextNode = nodes.peek();
                //如果下一个结点和当前结点不在同一层，这里说明当前结点已经到了这一层的最后一个，直接将当前结点指向null
                if(nextNodeHeight != height){
                    node.next = null;
                }else{//下一个结点和当前结点在同一层，这里就讲当前结点指向下一个结点
                    node.next = nextNode;
                }
            }else{//这里是处理特殊情况，当下一个结点不存在。这里有两种情况，一个是root的时候，因为还没来得及把root.left和root.right加进去
                //还有种情况是所有层序遍历之后的最后一个结点。但是无论是哪种情况，当前结点都该指向null
                node.next = null;
            }

            //当左子结点存在时，加入queue中，以便于后续处理
            if(node.left !=null){
                nodes.add(node.left);
                heights.add(height+1);
            

            //当柚子节点存在时，加入queue中，以便于后续处理
            if(node.right != null){
                nodes.add(node.right);
                heights.add(height + 1);
            }
        }
    }
}

//a right solution
//use the method like Populating Next Right Pointers in Each Node
//but need to handle more situations.
//在遍历当前层时, 构建下一层。
//dfs
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        //如果右孩子不是空，那么左孩子的next指向右孩子；
        //否则的话，不断的p->next直到找到孩子不为空的孩子
        if(root.left != null){
            if(root.right != null){
                root.left.next = root.right;
            }else{
                TreeLinkNode p = root.next;
                while(p != null && p.left == null && p.right == null){
                    p = p.next;
                }
                if(p != null){
                    root.left.next = pleft == null? p.right: p.left;
                }
            }
        }
        
        //这里没有了左孩子指向右孩子的情况，只用处理上面的第二种情况
        ////右孩子的next 根节点至少有一个孩子不为空的next  
        if(root.right != null){
            TreeLinkNode p = root.next;
            while(p != null && p.left == null && p.right == null){
                p = p.next;
            }
            if(p != null){
                root.right.next = p.left == null? p.right: p.left;
            }
        }
        
        //为什么这里要先right再left?????先left再right就出错、？？？
        connect(root.right);
        connect(root.left);
    }
}



/*
http://blog.csdn.net/perfect8886/article/details/20874913
用层次遍历的方法可以比较直观的解决这题（解法1）。

由于题目给出了next指针这个辅助空间，我们可以借助这个指针省去层次遍历里的队列，从而实现O(1)空间复杂度的解法（解法2）。iqqn
*/

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;

        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        Queue<TreeLinkNode> nextQueue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        TreeLinkNode left = null;

        while(!queue.isEmpty()){
            TreeLinkNode node = queue.poll();
            if(left != null){
                left.next = node;
            }
            left = node;

            //左子结点
            if(node.left !=null){
                nextQueue.add(node.left);
            }

            if(node.right != null){
                nextQueue.add(node.right);
            }

            if(queue.isEmpty()){
                Queue<TreeLinkNode> temp = queue;
                queue = nextQueue;
                nextQueue = temp;
                left = null;
            }
        }

    }
}