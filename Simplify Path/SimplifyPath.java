/**
Simplify Path

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

/*
http://blog.csdn.net/fightforyourdream/article/details/16917563

用栈来做，先把输入字符串以'/'为分隔符分来，如果遇到'.'或者空输入什么都不做。如果遇到'..'就弹栈。
然后每从栈里退出一个元素就用'/'连接起来，注意顺序。

发现Java里面的LinkedList实现了栈和队列的所有方法，而且还有重复的！值得注意的是，LinkedList中的pop()对应的是remove()或者removeHead()  
即从链表头移除，而不是removeLast()。所以在LinkedList中，进栈(push())出栈(pop())都是在链表头部进行，
进队列（add()）是从尾部进入，出队列是从头部被移除(remove())。

The simplification of an absolute path can be done by first separating the path by "/" and 
interpreting each segment as subdirectory, current directory, or its parent directory. 
For ease of going back to the parent directory, we can use a stack to which a segment 
is pushed as long as it represents a subdirectory. Finally, the stack is converted into an array, 
and the simplified path is the concatenation of the segments in the stack preceded by a "/".

*/

public class Solution {
    public String simplifyPath(String path) {
		if(path.length() == 0)
			return path;

		String[] splits = path.split("/");
		LinkedList<String> stack = new LinkedList<String>();

		for(String s: splits){
			if(s.length() == 0 || s.equals(".")){
				continue;
			}else if(s.equals("..")){// Go up to its parent
				if(!stack.isEmpty()){
					stack.pop();
				}
			}else{// Enter its subdirectory
				stack.push(s);
			}
		} 

		 // Construct simplified path
		if(stack.isEmpty()){// No subdirectory
			stack.push("");
		}        
		String ret = "";//有很多是加个/这里应该是先初始化
		while(!stack.isEmpty()){
			ret += "/" + stack.removeLast();
		}
		return ret;
    }
}
/*s.length() == 0 指的是""，而空格指的是" "，空格的话要push。""有可能出现在//这种情况
如果不要这个判断会出现如下问题
Submission Result: Wrong Answer

Input:	"/..."
Output:	"//..."
Expected:	"/..."
*/


//http://rleetcode.blogspot.com/2014/01/simplify-path-java.html
//先pop出来的都是sub-dir，所以要不断insert到0位置，这样就不断加到前面。前面那种方法通过stack.removeLast()就直接把栈底的根目录拿出来
public class Solution {
    public String simplifyPath(String path) {
       
       StringBuilder result=new StringBuilder();
        
       if (path==null ||path.length()==0){
           return result.toString();
       }
       
       String[] strs=path.split("/");
       
       Stack<String> stack=new Stack<String>();
 
       for (String s: strs){
           
           if (s.length()==0 ||s.equals(".")){
               
           }
           else if (s.equals("..")){
               if (!stack.isEmpty()){
                   stack.pop();
               }
           }
           else{
               stack.push(s);
           }
           
       }
       
       if (stack.isEmpty()){
           result.append('/');
           
       }
       else{
           
           while (!stack.isEmpty()){
               result.insert(0, stack.pop());
               result.insert(0, '/');
           }
       }
       
       return result.toString();
    }
}
