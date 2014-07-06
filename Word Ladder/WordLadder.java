/**
Word Ladder

Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/*
http://www.programcreek.com/2012/12/leetcode-word-ladder/
This problem is a classic problem that has been asked frequently during interviews. The following are two Java solutions.

1. Naive Approach

In a simplest way, we can start from start word, change one character each time, 
if it is in the dictionary, we continue with the replaced word, until start == end.
下面这个解法是不对的 
*/

public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
    	int len = 0;
    	HashSet<String> visited = new HashSet<String>();

    	for(int i = 0; i < start.length(); i++){
    		char[] startArr = start.toCharArray();

    		//对于每一个位置的character,循环从a-z，如果当前character已经存在，那么不管。如果不一样的话，改变这个字符，生成新的String，
    		//看看是不是在dict里面。这样就可以看出来每次只改变一个。
    		for(char c = 'a'; c <= 'z'; c++){
    			if(c == start.toCharArray()[i]){
    				continue;
    			}

    			startArr[i] = c;
    			String temp = new String(startArr);
    			if(dict.contains(temp)){
    				len++;
    				start = temp;
    				if(temp.equals(end)){
    					return len;
    				}
    			}
    		}
    	}
    	return len;
    }
}

/*
Apparently, this is not good enough. The following example exactly shows the problem. 
It can not find optimal path. The output is 3, but it actually only takes 2.

Input:	"a", "c", ["a","b","c"]
Output:	3
Expected:	2
*/

/*
2. Breath First Search

So we quickly realize that this looks like a tree searching problem for which breath first guarantees the optimal solution.

Assuming we have all English words in the dictionary, and the start is "hit" as shown in the diagram below.

word-ladder

We can use two queues to traverse the tree, one stores the nodes, the other stores the step numbers. 
Before starting coding, we can visualize a tree in mind and come up with the following solution.

BFS

类似于图的遍历，将start一步内可以到达的单词加入到queue中，并将相应长度放入queue中

每次从queue中poll出一个单词，看是否是目标单词，如果是则返回相应长度

对每个单词的每一位进行变化('a'-'z'), 看是否在dict中，如在则说明该单词是转换路径中的一个

由于在找到一个转换路径后就返回，此返回值可以确保是最小值
*/

public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
    	if(dict.size() == 0) return 0;

    	LinkedList<String> wordQueue = new LinkedList<String>();
    	LinkedList<Integer> distanceQueue = new LinkedList<Integer>();

    	wordQueue.add(start);
    	distanceQueue.add(1);

    	while(!wordQueue.isEmpty()){
    		String currWord = wordQueue.pop();
    		Integer currDistance = distanceQueue.pop();

    		if(currWord.equals(end)){
    			return currDistance;
    		}

    		for(int i = 0; i < currWord.length(); i++){
    			char[] currCharArr = currWord.toCharArray();
    			for(char c = 'a'; c < 'z'; c++){
    				currCharArr[i] = c;

    				String newWord = new String(currCharArr);
    				if(dict.contains(newWord)){
    					wordQueue.add(newWord);
    					distanceQueue.add(currDistance + 1);
    					dict.remove(newWord);// if not remove tempStr, a loop may happened
    				}
    			}
    		}
    	}
    	return 0;
    }
}


/*
3. What learned from this problem?

    Use breath-first or depth-first search to solve problems
    Use two queues, one for words and another for counting

*/