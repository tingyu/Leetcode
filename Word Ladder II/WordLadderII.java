/**
Word Ladder II

Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
/*
http://codeganker.blogspot.com/2014/04/word-ladder-ii-leetcode.html
这道题是LeetCode中AC率最低的题目，确实是比较难。一方面是因为对时间有比较严格的要求（容易超时），另一方面是它有很多细节需要实现。
思路上和Word Ladder是比较类似的，但是因为是要求出所有路径，仅仅保存路径长度是不够的，而且这里还有更多的问题，
那就是为了得到所有路径，不是每个结点访问一次就可以标记为visited了，因为有些访问过的结点也会是别的路径上的结点，
所以访问的集合要进行回溯（也就是标记回未访问）。所以时间上不再是一次广度优先搜索的复杂度了，取决于结果路径的数量。
同样空间上也是相当高的复杂度，因为我们要保存过程中满足的中间路径到某个数据结构中，以便最后可以获取路径，这里我们维护一个HashMap，
把一个结点前驱结点都进行保存。

在LeetCode中用Java实现上述算法非常容易超时。为了提高算法效率，需要注意一下两点：
1）在替换String的某一位的字符时，先转换成char数组再操作；
2）如果按照正常的方法从start找end，然后根据这个来构造路径，代价会比较高，因为保存前驱结点容易，而保存后驱结点则比较困难。
所以我们在广度优先搜索时反过来先从end找start，最后再根据生成的前驱结点映射从start往end构造路径，这样算法效率会有明显提高。
*/

public class Solution {
	class StringWithLevel{
		String str;
		int level;
		public StringWithLevel(String str, int level){
			this.str = str;
			this.level = level;
		}
	}

    public ArrayList<ArrayList<String>> findLadders(String start, String end, Set<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        HashSet<String> unvisitedSet = new HashSet<String>();
        unvisitedSet.addAll(dict);
        unvisitedSet.add(start);
        unvisitedSet.remove(end);

        Map<String, List<String>> nextMap = new HashMap<String, List<String>>();
        for(String e: unvisitedSet){
        	nextMap.put(e, new ArrayList<String>());
        }

        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        boolean found = false;

        int finalLevel = Integer.MAX_VALUE;
        int curLevel = 0;
        int preLevel = 0;

        HashSet<String> visitedCurLevel = new HashSet<String>();
        while(!queue.isEmpty()){
        	StringWithLevel cur = queue.pop();
        	String curStr =  cur.str;
        	curLevel = cur.level;
        	if(found && curLevel > finalLevel){
        		break;
        	} 
        	if(curLevel > preLevel){
        		unvisitedSet.removeAll(visitedCurLevel);
        	}

        	preLevel = curLevel;
        	char[] curStrCharArray = curStr.toCharArray();
        	for(int i = 0; i < curStr.length(); i++){
        		char originalChar = curStrCharArray[i];
        		boolean foundCurCycle = false;
        		for(char c = 'a'; c <= 'z'; c++){
        			curStrCharArray[i] = c;
        			String newStr = new String(curStrCharArray);
        			if(c != originalChar && unvisitedSet.contains(newStr)){
        				nextMap.get(newStr).add(curStr);
        				if(newStr.equals(start)){
        					found = true;
        					finalLevel = curLevel;
        					foundCurCycle  = true;
        					break;
        				}
        				if(visitedCurLevel.add(newStr)){
        					queue.add(new StringWithLevel(newStr, curLevel + 1));
        				}
        			}
        		}
        		if(foundCurCycle){
        			break;
        		}
        		curStrCharArray[i] = originalChar;
        	}
         }
         if(found){
         	ArrayList<String> list = new ArrayList<String>();
         	list.add(start);
         	getPaths(start, end, list, finalLevel + 1, nextMap, res);
         }
         return res;
    }

    private void getPaths(String cur, String end, ArrayList<String> list, int level, Map<String, List<String>> nextMap, ArrayList<ArrayList<String>> res){
    	if(cur.equals(end)){
    		res.add(new ArrayList<String>(list));
    	}else if(level > 0){
    		List<String> parentSet = nextMap.get(cur);
    		for(String parent: parentSet){
    			list.add(parent);
    			getPaths(parent, end, list, level - 1, nextMap, res);
    			list.remove(list.size() - 1);
    		}
    	}
    }
}


/*
这道题目实现中有很多细节和技巧，个人觉得如果在面试中遇到很难做到完整，而且考核的算法思想也不是很精妙，更多的是繁琐的操作。
在面试中时间上花费太大，也不是很合适，大家主要还是了解一下思路哈。

这个题目应该算是leetcode上比较难的题目了。刚开始我采用了和Word Ladder相似的做法，只是用ArrayList记录了当前变换路径，在小数据的情况下可以Accept，但是大数据超时。究其原因，是由于为每个当前节点记录变换路径的时候，需要复制之前的ArrayList，这个时间开销较大。
其实，我们可以采用一个Map<String, HashSet<String>>结构，记录字典单词的每一个前驱，这样我们可以从end反向遍历，构造出转换路径。
同时，我利用了两个ArrayList，交替记录上一层和下一层的节点，如果下一层节点为空，则不存在路径，立即返回。如果下一层中出现了end，证明找到了所有的最短路径，停止搜索开始构造路径。
*/


//http://blog.csdn.net/muscler/article/details/22906533
public class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if (start == null || end == null) return res;
        ArrayList<String> tmparray = new ArrayList<String>();
        
        //如果start与end相等，直接返回
        if (start.equals(end)) {
        	tmparray.add(start);
        	tmparray.add(end);
        	res.add(tmparray);
        	return res;
        }
        
        //新建一个hashmap，保存每个节点的所有前驱。
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        map.put(end, new ArrayList<String>());
        map.put(start, new ArrayList<String>());
        for (String s : dict) {
        	map.put(s, new ArrayList<String>());
        }
        
        //利用bfs 层序遍历 如果队列中有end 那么结束遍历（到最短的一层就结束）
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        ArrayList<String> currentlevel = new ArrayList<String>(); 
        while (!queue.isEmpty()) {
        	int level = queue.size();
        	currentlevel.clear();
        	for (int i = 0; i < level; i++) {
        		String top = queue.poll();
        		if (dict.contains(top)) dict.remove(top);
        		currentlevel.add(top);
        	}
        	
        	//循环每个String的每个char 从a到z，在dict里面找是否有
        	for (String curs : currentlevel) {
        		for (int i = 0; i < curs.length(); ++i) {
        			for (char j = 'a'; j <= 'z'; ++j) {
        				char[] tmpchar = curs.toCharArray();
        				tmpchar[i] = j;
        				String tmps = new String(tmpchar);
        				if (!curs.equals(start) && tmps.equals(end)) {
        					map.get(end).add(curs);
        					queue.offer(tmps);
        				}
        				else if (!tmps.equals(curs) && dict.contains(tmps)) {
        					if (!queue.contains(tmps)) queue.offer(tmps);
        					map.get(tmps).add(curs);
        				}
        			}
        		}
        	}
        	if (queue.contains(end)) 
        		break;
        }
        tmparray.add(end);
        buildpath(start, end, map, tmparray, res);
        return res;
    }
	
	//根据节点的前驱 生成路径
	public void buildpath(String start, String end,
			HashMap<String, ArrayList<String>> map, ArrayList<String> tmparray,
			ArrayList<ArrayList<String>> res) {
		ArrayList<String> pre = map.get(end);
 		if (end.equals(start)) {
 			ArrayList<String> tmparray2 = new ArrayList<String>(tmparray);
 			Collections.reverse(tmparray2);
 			res.add(tmparray2);
 			return;
 		}
 		for (String s: pre) {
 			tmparray.add(s);
 			buildpath(start, s, map, tmparray, res);
 			tmparray.remove(tmparray.size() - 1);
 		}
			
	}

}



//http://www.aichengxu.com/article/Java/9837_2.html
/*
 优化方法：先BFS生成找到end时的生成树，标记出每个单词所在的层数。然后从目标用DFS往回找，过了大数据。


 */
public class Solution {
    //记录每个单词所在的层数
    HashMap<String,Integer> path = new HashMap<String,Integer>();
    //bfs生成path
    void bfs(String start, String end, HashSet<String> dict) {
        Queue queue = new LinkedList<String>();
        queue.add(start);
        path.put(start,0);
        String current;
        while(!queue.isEmpty()) {
            current = (String)queue.poll();
            if(current==end) {
                continue;
            }
            for(int i=0;i<current.length();i++) {
                char[] strCharArr = current.toCharArray();
                for(char ch='a';ch<='z';ch++) {
                    if(strCharArr[i]==ch) {
                        continue;
                    }
                    strCharArr[i] = ch;
                    String newWord = new String(strCharArr);
                    if(newWord.equals(end)==true||dict.contains(newWord)) {
                        //每个单词在path中只能出现一次，也就是每个单词只能出现在一层中，这样就很巧妙的解决了环的问题。
			if(path.get(newWord)==null) {
                            int depth = (int)path.get(current);
                            path.put(newWord,depth + 1);
                            queue.add(newWord);
                        }
                    }
                }
            }
        }
    }
    //从目标单词往回找开始单词，记录所有路径
    void dfs(String start, String end, HashSet<String> dict, ArrayList<String> pathArray,ArrayList<ArrayList<String>> result) {
        //找到了，需要reverse加入的所有单词
	if(start.equals(end)==true) {
            pathArray.add(start);
            Collections.reverse(pathArray);
            result.add(pathArray);
            return;
        }
        if(path.get(start)==null) {
            return;
        }
        pathArray.add(start);
        int nextDepth = (int)path.get(start) - 1;
        for(int i=0;i<start.length();i++) {
            char[] strCharArr = start.toCharArray();
            for(char ch='a';ch<='z';ch++) {
                if(strCharArr[i]==ch) {
                    continue;
                }
                strCharArr[i] = ch;
                String newWord = new String(strCharArr);
		//只相差一个字母同时这个单词所在的层数也是当前单词的上一层
                if(path.get(newWord)!=null&&(path.get(newWord)==nextDepth)) {
                    ArrayList<String> newPathArray = new ArrayList<String>(pathArray);
                    dfs(newWord,end,dict,newPathArray,result);
                }
            }
        }
    }
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> path = new ArrayList<String>();
        if(start==null||end==null||start.length()!=end.length()) {
            return result;
        }
        bfs(start, end, dict);
        dfs(end,start, dict, path, result);
        return result;
    }
}


/*

http://blog.csdn.net/perfect8886/article/details/19645691

整个过程可以分成两个部分：先通过BFS从start找到end，在找的过程中需要记录前驱单词，再用DFS反向找回完整路径。

但是用Java实现上述过程会遇到TLE。为了能让用时尽可能短，有如下几点需要注意的地方：

1. 由于最后生成路径的时候，需要从end找到start构造ArrayList，即使用LinkList来协助构造，性能也不好。解决办法：不从start找end了，反过来从end找start，找到后，再从start往end构造路径，性能会有明显提升。

2. 在BFS过程中，需要替换String的每一位字符，先转换成char数组再操作，性能也会有明显提升。

3. 在BFS过程中，注意避免一些不必要的搜索，具体细节参考如下代码。

最终在LeetCode测试中，Java实现达到了800ms用时。

此外，双向BFS也可以用来提升性能，而且效果会十分显著，这里没有具体实现。
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
	class WordWithLevel {
		String word;
		int level;

		public WordWithLevel(String word, int level) {
			this.word = word;
			this.level = level;
		}
	}

	private String end;
	private ArrayList<ArrayList<String>> result;
	private Map<String, List<String>> nextMap;

	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		result = new ArrayList<ArrayList<String>>();
		this.end = end;

		// unvisited words set
		Set<String> unVisited = new HashSet<String>();
		unVisited.addAll(dict);
		unVisited.add(start);
		unVisited.remove(end);

		// used to record the map info of <word : the words of next level>
		nextMap = new HashMap<String, List<String>>();
		for (String e : unVisited) {
			nextMap.put(e, new ArrayList<String>());
		}

		// BFS to search from the end to start
		Queue<WordWithLevel> queue = new LinkedList<WordWithLevel>();
		queue.add(new WordWithLevel(end, 0));
		boolean found = false;
		int finalLevel = Integer.MAX_VALUE;
		int currentLevel = 0;
		int preLevel = 0;
		Set<String> visitedWordsInThisLevel = new HashSet<String>();
		while (!queue.isEmpty()) {
			WordWithLevel wordWithLevel = queue.poll();
			String word = wordWithLevel.word;
			currentLevel = wordWithLevel.level;
			if (found && currentLevel > finalLevel) {
				break;
			}
			if (currentLevel > preLevel) {
				unVisited.removeAll(visitedWordsInThisLevel);
			}
			preLevel = currentLevel;
			char[] wordCharArray = word.toCharArray();
			for (int i = 0; i < word.length(); ++i) {
				char originalChar = wordCharArray[i];
				boolean foundInThisCycle = false;
				for (char c = 'a'; c <= 'z'; ++c) {
					wordCharArray[i] = c;
					String newWord = new String(wordCharArray);
					if (c != originalChar && unVisited.contains(newWord)) {
						nextMap.get(newWord).add(word);
						if (newWord.equals(start)) {
							found = true;
							finalLevel = currentLevel;
							foundInThisCycle = true;
							break;
						}
						if (visitedWordsInThisLevel.add(newWord)) {
							queue.add(new WordWithLevel(newWord,
									currentLevel + 1));
						}
					}
				}
				if (foundInThisCycle) {
					break;
				}
				wordCharArray[i] = originalChar;
			}
		}

		if (found) {
			// dfs to get the paths
			ArrayList<String> list = new ArrayList<String>();
			list.add(start);
			getPaths(start, list, finalLevel + 1);
		}
		return result;
	}

	private void getPaths(String currentWord, ArrayList<String> list, int level) {
		if (currentWord.equals(end)) {
			result.add(new ArrayList<String>(list));
		} else if (level > 0) {
			List<String> parentsSet = nextMap.get(currentWord);
			for (String parent : parentsSet) {
				list.add(parent);
				getPaths(parent, list, level - 1);
				list.remove(list.size() - 1);
			}
		}
	}
}

//http://yucoding.blogspot.com/2013/08/leetcode-question-127-word-ladder.html
//http://yucoding.blogspot.com/2014/01/leetcode-question-word-ladder-ii.html
