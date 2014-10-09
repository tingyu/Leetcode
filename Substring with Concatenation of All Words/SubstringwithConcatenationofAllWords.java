/**
Substring with Concatenation of All Words 

You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/*
想法一：假设L中的单位长度为n，依次从S中取长度为n的子串，如果在L中，就记下来。需要借助hash或map，如果整个L都匹配完了，就算是一个concatenation；
当匹配错误的时候，S右移一个位置。

想法二：事先把L的所有concatenation组合出来，放到hash或map里，然后遍历S的时候直接看。

下面的代码是实现的第一种想法，第二种想法当L中数据较多时由于组合数会剧增，效率较低。
http://blog.csdn.net/ojshilu/article/details/22212703

http://gongxuns.blogspot.com/2012/12/leetcode-substring-with-concatenation.html

一个长度为M*N的子串在S上从左到右平移，每个位置上，直接去判断是不是每一个L中的单词都出现了一次。


*/
import java.util.Map;
import java.util.HashMap;

{
    public List<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(S == null || S.length() == 0 || L.length == 0){
            return res;
        }
        int n = L.length, m = L[0].length(), l = S.length();
        HashMap<String, Integer> covered = new HashMap<String, Integer>();
        for(int i = 0; i < n; i++){
            if(covered.containsKey(L[i])){
                covered.put(L[i], covered.get(L[i]) + 1);
            }else{
                covered.put(L[i], 1);
            }
        }

        int i = 0;
        while(l - i >= m*n){
            Map<String, Integer> temp = new HashMap<String, Integer>(covered);
            for(int j = 0; j < n; j++){//对于每个开始i，检查接下来的j个m长的string在不在tmp中，如果在的话就count,不在就移动i
                String testStr = S.substring(i + j*m, i + (j+1)*m);
                if(temp.containsKey(testStr)){
                    if(temp.get(testStr) - 1 == 0){
                        temp.remove(testStr);
                    }else{
                        temp.put(testStr, temp.get(testStr)-1);
                    }
                }else{
                    break;
                }
            }
            if(temp.size() == 0) res.add(i);
            i++;
        }
        return res;
    }
}
/*
这道题看似比较复杂，其实思路和Longest Substring Without Repeating Characters差不多。
因为那些单词是定长的，所以本质上和单一个字符一样。和Longest Substring Without Repeating Characters的区别只在于我们需要维护一个字典，
然后保证目前的串包含字典里面的单词有且仅有一次。思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，
否则窗口左端可以跳到字符串下一个单词了。
假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。
做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串。
因为每次扫描的时间复杂度是O(2*n/l)(每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，
总共扫描l次（i=0, ..., l-1)，所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。空间复杂度是字典的大小，
即O(m*l)，其中m是字典的单词数量。代码如下：

这种移动窗口的方法在字符串处理的问题中非常常见，是一种可以把时间复杂度降低到线性的有效算法，大家可以熟悉一下。
还有非常类似的题目Minimum Window Substring，思路完全一样，只是移动窗口的规则稍微不同而已。

*/


