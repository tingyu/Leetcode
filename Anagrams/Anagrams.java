/**
Anagrams 

Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
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