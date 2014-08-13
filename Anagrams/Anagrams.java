/**
Anagrams 

Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
*/

/*
这是一道很经典的面试题了，在cc150里面也有，就是把一个数组按照易位构词分类。易位构词其实也很好理解，
就是两个单词所包含的字符和数量都是一样的，只是顺序不同。
这个题简单的版本是判断两个单词是不是anagram，一般来说有两种方法。第一种方法是用hashmap，key是字符，value是出现的次数，
如果两个单词构成的hashmap相同，那么就是anagram。实现起来就是用一个构建hashmap，然后另一个在前面的hashmap中逐个去除，
最后如果hashmap为空，即返回true。这个方法时间复杂度是O(m+n)，m，n分别是两个单词的长度。而空间复杂度是O(字符集的大小)。
第二种方法是将两个单词排序，如果排序之后结果相同，就说明两个单词是anagram。这种方法的时间复杂度取决于排序算法，一般排序算法是O(nlogn)，
如果字符集够小，也可以用线性的排序算法。不过总体来说，如果是判断两个单词的，第一种方法要直接简单一些。
接下来我们看看这道题，是在很多字符串里面按照anagram分类，如果用hashmap的方法，然后两两匹配，在分组会比较麻烦。
而如果用排序的方法则有一个很大的优势，就是排序后的字符串可以作为一个key，也就是某一个class的id，如此只要对每一个字符串排序，
然后建立一个hashmap，key是排序后的串，而value是所有属于这个key类的字符串，这样就可以比较简单的进行分类。
假设我们有n个字符串，字符串最大长度是k，那么该算法的时间复杂度是O(nklogk)，其中O(klogk)是对每一个字符串排序
（如果用线性算法也可以提高）。空间复杂度则是O(nk)，即hashmap的大小。实现代码如下：
*/
//my solution
/*
基本思路：
原来是想用HashSet来存排序后的String，然后对每个String先变成CharArray，排序后转化为排序后的String，然后判断在不在set里，
如果在Set里面就加到res中，最后返回res。
不过这种解法错了，少了最初一个正确的加入HashSet的情况
然后修正：
用一个HashMap来存排序后的String和相应原来的ArrayList<String>，这样就每次都加入。只是如果是同一个key，那么就加入到同一个ArrayList<String中>
最后遍历一下这个HashMap里面的value，把ArrayList<String>中长度大于1的String全部都加入到res中
*/
public class Solution {
    public List<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> tmp = new ArrayList<String>();
        if(strs == null || strs.length == 0) return null;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for(int i = 0; i < strs.length; i++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String convertedStr = new String(arr);
            if(map.containsKey(convertedStr)){
                tmp = map.get(convertedStr);
                tmp.add(strs[i]);
                map.put(convertedStr, tmp);//这个是可以省略的
            }else{
                tmp = new ArrayList<String>();
                tmp.add(strs[i]);
                map.put(convertedStr, tmp);
            }
        }
        
        for(ArrayList<String> m : map.values()){
            if(m.size() > 1){
                for(String s: m){
                    res.add(s);
                }
            }
        }
        return res;
    }
}


//下面是别人的写法。思路是一样的。不过写的更简略些。首先少了个tmp.只有在else里面才声明，另外没有
import java.util.*;
public class Solution {
    public ArrayList<String> anagrams(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<String> result = new ArrayList<String>();
        
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        
        for(String str : strs){
            char[] tempstr= str.toCharArray();
            Arrays.sort(tempstr);
            String sortedstr = new String(tempstr);
            if(map.containsKey(sortedstr)){
                map.get(sortedstr).add(str);
            }else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                map.put(sortedstr,list);
            }
        }
        
        for(ArrayList<String> list:map.values())
            if(list.size()>1)
                for(String str : list)
                    result.add(str);
                    
        return result;
    }
}