/**
Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/


/*
Solution1: BFS
http://www.programcreek.com/2012/12/leetcode-clone-graph-java/
这题的解法应该算法经典。注意使用对应的遍历方法，数据结构来存储遍历结果，最终保证所有的点都被遍历到并且被copy
Key to Solve This Problem

A queue is used to do breath first traversal.
a map is used to store the visited nodes. It is the map between original node and copied node.
It would be helpful if you draw a diagram and visualize the problem.

图的遍历有两种方式，BFS和DFS
这里使用BFS来解本题，BFS需要使用queue来保存neighbors
但这里有个问题，在clone一个节点时我们需要clone它的neighbors，而邻居节点有的已经存在，有的未存在，如何进行区分？
这里我们使用Map来进行区分，Map的key值为原来的node，value为新clone的node，当发现一个node未在map中时说明这个node还未被clone，
将它clone后放入queue中处理neighbors。
使用Map的主要意义在于充当BFS中Visited数组，它也可以去环问题，例如A--B有条边，当处理完A的邻居node，然后处理B节点邻居node时发现A已经处理过了
处理就结束，不会出现死循环！
queue中放置的节点都是未处理neighbors的节点！！！

关键的一点就是在clone neighbors的时候，如果neighbors存在，就直接clone
如果不存在那么就先把neighbor放到map里面，然后再clone
*/


/**
 * Definition for undirected graph.
 * class UndirectedGraphNode{
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x){label = x; neighbors = new ArrayList<UndirectedGraphNode>();}
 * };
 */

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
        queue.add(node);
        map.put(node, newHead);
        
        while(!queue.isEmpty()){
            UndirectedGraphNode curr = queue.pop();
            //ArrayList<UndirectedGraphNode> currNeighbors = curr.neighbors;
            List<UndirectedGraphNode> currNeighbors = curr.neighbors;
            
            
            for(UndirectedGraphNode aNeighbor: currNeighbors){
                if(!map.containsKey(aNeighbor)){
                    UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbor.label);
                    map.put(aNeighbor, copy);
                    map.get(curr).neighbors.add(copy);
                    queue.add(aNeighbor);
                }else{
                    map.get(curr).neighbors.add(map.get(aNeighbor));
                }
            }
        }
        return newHead;
    }
}


/*
c Solution2: DFS
Solution: DFS traverse all nodes, meanwhile use HashMap to record the node which has been cloned. 
use label as key and the new created node as value

这里dfs，回到起始点就说明clone完了？？？
*/

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if(node == null) return null;

    	HashMap<Integer, UndirectedGraphNode> checker = new HashMap<Integer, UndirectedGraphNode>();
    	return clone(node, checker);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> checker){
    	if(checker.containsKey(node.label)){
    		return checker.get(node.label);
    	}

    	UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
    	checker.put(newNode.label, newNode);

    	for(UndirectedGraphNode tempNode: node.neighbors){
    		newNode.neighbors.add(clone(tempNode, checker));
    	}

    	return newNode;
    }
}