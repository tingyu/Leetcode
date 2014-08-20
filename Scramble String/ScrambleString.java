/**
Scramble String

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

/*
http://www.blogjava.net/sandy/archive/2013/05/22/399605.html
分析：

这个问题是google的面试题。由于一个字符串有很多种二叉表示法，貌似很难判断两个字符串是否可以做这样的变换。
对付复杂问题的方法是从简单的特例来思考，从而找出规律。
先考察简单情况：
字符串长度为1：很明显，两个字符串必须完全相同才可以。
字符串长度为2：当s1="ab", s2只有"ab"或者"ba"才可以。
对于任意长度的字符串，我们可以把字符串s1分为a1,b1两个部分，s2分为a2,b2两个部分，满足（(a1~a2) && (b1~b2)）或者 （(a1~b2) && (a1~b2)）

如此，我们找到了解决问题的思路。首先我们尝试用递归来写。


解法一（递归）

两个字符串的相似的必备条件是含有相同的字符集。简单的做法是把两个字符串的字符排序后，然后比较是否相同。
加上这个检查就可以大大的减少递归次数。
//为什么下面的方法考虑了就全了呢？？？？
Scramble的定义就是要不一样，要不swap了。所以两种递归
*/

public class Solution {
    public boolean isScramble(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        //前面先处理一系列的特殊情况来减少运算

        //如果两个长度不等，肯定false，不用往后看了
        if(l1 != l2){
          return false;
        }        

        //这里隐含了l1 = l2，如果l1 == 0那么返回true
        if(l1 == 0){
          return true;
        }

        //把string 变成charArray来看下排序完之后的两个序列是不是相等，如果不等，不用考虑其他的了，直接返回false
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        if(l1 == 1){
          return c1[0] == c2[0];
        }

        Arrays.sort(c1);
        Arrays.sort(c2);

        for(int i = 0; i < l1; i++){
          if(c1[i] != c2[i]){
            return false;
          }
        }

        //在去掉上面那些特殊情况之后，再进一步判断s2是不是由s1转换而来的。注意这里用的是s1, s2不是c1, c2，也就是没有排序的
        boolean result = false;
        for(int i = 1; i < l1 && !result; i++){//why !result??
          String s11 = s1.substrings(0, i);
          String s12 = s1.substrings(i);
          String s21 = s2.substrings(0, i);
          String s22 = s2.substrings(i);
          result = isScramble(s11, s21) && isScramble(s12, s22);
          //前面的若s1=great, s2=regeat，那么s11=g,s12=reat; s21=r, s22=egeat
          //前面的如果不成立，然后考虑swap之后的情况，这样可以减少循环。这里使用s11和s32比较，s12和s31比较。
          //s31=rgea, s32=t，防止是great在g后面切一刀变成reatg的情况
          if(!result){
            String s31 = s2.substrings(0, l1 - i);
            String s32 = s2.substrings(l1 - i);
            result = isScramble(s11, s32) && isScramble(s12, s31);
          }
        }
        return result;
    }
}



public class Solution {
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        if(len != s2.length()){
          return false;
        }

        if(len == 0){
          return true;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        boolean[][][] result = new boolean[len][len][len];
        //初始化，因为子串的长度是0，表示s1[i]是否可以由s2[j]变化得来。一个元素是否能由另外一个元素变化而来，就靠看他们是不是相等的了。
        for(int i = 0; i < len; i++){
          for(int j = 0; j < len; j++){
              result[0][i][j] = (c1[i] == c2[j]);
          }
        }

        for(int k = 2; k <= len; k++){// 子串的长度 
          for(int i = len - k; i >=0; i--){// s1[i...i+k]  
            for(int j = len - k; j >= 0; j--){ // s2[j...j+k]
                boolean r = false;
                for(int m = 1; m < k && !r; m++){// 尝试以m为长度分割子串
                  r = (result[m - 1][i][j] && result[k-m-1][i+m][j+m])// 前前后后匹配  
                      ||(result[m-1][i][j+k-m]&&result[k-m-1][i+m][j]);// 前后后前匹配 
                }
                result[k-1][i][j] = r;
            }
          }
        }
        return result[len -1][0][0];
    }
}


