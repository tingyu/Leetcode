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
*/

public class Solution {
    public String simplifyPath(String path) {
		if(path.length() == 0)
			return path;

		String[] splits = path.split("/");
		LinkedList<String> stack = new LinkedList<String>();

		for(String s: splits){
			if(s.length() == 0 || s.quals(".")){
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
		String ret = "";
		while(!stack.isEmpty()){
			ret += "/" + stack.removeLast();
		}
		return ret;
    }
}



//http://rleetcode.blogspot.com/2014/01/simplify-path-java.html
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
