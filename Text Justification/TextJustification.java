/**
Text Justification

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/

//https://github.com/rffffffff007/leetcode/blob/master/Text%20Justification.java
/*
对单词的长度进行遍历。每次记录目前为止单词的长度
在每一行放不下单词或着是到达结尾的时候进行集中处理。每一行new一个StringBuilder存每一行的数据。同时用spaceCount记录下当前行需要的space的数目
spaceSlots记录下需要的间隔的数目。
1. 然后处理特殊情况，当一行只有一个单词，或者是到达结尾最后一行的时候。这时的space的放置跟一般情况不同。
一行只有一个单词的：append单词之后，把剩下的长度都作为space append进去
最后一行的：先append单词和空格，此时的空格是一个空格，最后把剩下的长度都作为space append进去
2. 不是特殊情况时处理一般情况
此时要注意计算单词和单词之间需要放的空格的数目。通过计算最少可以均分的空格数目和额外的空格数目
然后开始遍历这一行放得下的单词，appedn单词和相应的空格。里面有个特殊情况是空格只有在没到达最后一个单词的时候才进行处理

每一行处理完之后放到result中。更新lastI就是下一行的第一个单词的index和curLen用来记录下一行单词的长度

在遍历所有单词的for循环中注意每次都要根据遍历过得单词更新curLen
*/
public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        int wordCount = words.length;
        ArrayList<String> result = new ArrayList<String>();
        int lastI = 0;
        int curLen = 0;
        for(int i = 0; i <= wordCount; i++){
            if(i == wordCount || curLen + words[i].length() + i - lastI > L){
                StringBuilder buf = new StringBuilder();
                int spaceCount = L - curLen;
                int spaceSlots = i - lastI - 1;
                if(spaceSlots == 0 || i == wordCount){
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)
                            appendSpace(buf, 1);
                    }
                    appendSpace(buf, L - buf.length());
                }else{
                    int spaceEach = spaceCount/spaceSlots;
                    int spaceExtra = spaceCount%spaceSlots;
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)
                            appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1: 0));
                    }
                }
                result.add(buf.toString());
                curLen = 0;
                lastI = i;
            }
            if(i < wordCount){
                curLen += words[i].length();
            }
        }
        return result;
    }
    
    private void appendSpace(StringBuilder buf, int count){
        for(int i = 0; i < count; i++){
            buf.append(" ");
        }
    }
}


public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        int wordsCount = words.length;
        ArrayList<String> result = new ArrayList<String>();
        int curLen = 0;
        int lastI = 0;
        for (int i = 0; i <= wordsCount; i++) {
            //到达结尾 || 之前的words的总长度+当前word的长度+单词间距的长度>L的时候，处理这一行
            if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                StringBuffer buf = new StringBuffer();
                int spaceCount = L - curLen;//L -放得下的words的长度等于总共需要的space的数目
                int spaceSlots = i - lastI - 1;//放得下的间距。因为i的是不会用的，lastI代表的是这一行第一个单词的index
                //如果paceSlots == 0 ||是结尾.这里对应最后一行的特殊情况和倒数第二行只有一个单词的情况
                if (spaceSlots == 0 || i == wordsCount) {
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)//如果还有单词的话就在buf后面加上一个space。这时最后一行的情况
                            appendSpace(buf, 1);
                    }
                    appendSpace(buf, L - buf.length());//单词全部放完之后再加上剩下长度的空格
                } else {//如果是前面的一般情况
                    int spaceEach = spaceCount / spaceSlots;//每块空格平均至少分的空格数
                    int spaceExtra = spaceCount % spaceSlots;//剩下多余的要放在前面的空格数
                    for (int j = lastI; j < i; j++) {//从每行的第一个单词开始遍历到最后一个
                        buf.append(words[j]);
                        if (j != i - 1)//不是最后一个单词的时候加上空格
                            appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));//extra space没用完的时候每个+1，用完了不加
                    }
                }
                result.add(buf.toString());//每行结束加到result中
                lastI = i;//更新为下一行的第一个单词的Index
                curLen = 0;//reset
            }
            if (i < wordsCount)//没到结束的时候，为下一行做准备，加上下一行的第一个单词
                curLen += words[i].length();
        }
        return result;
    }

    private void appendSpace(StringBuffer sb, int count) {
        for (int i = 0; i < count; i++)
            sb.append(' ');
    }
}


//http://blog.csdn.net/linhuanmars/article/details/24063271
/*
这道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。主要难点在于空格的安排，首先每个单词之间必须有空格隔开，
而当当前行放不下更多的单词并且字符又不能填满长度L时，我们要把空格均匀的填充在单词之间。如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，
否则还必须把多的一个空格放到前面的间隔里面。实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，
然后将当行字符串构造出来。最后一个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。
时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。
而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)）。代码如下： 
count 记录的是单词的长度
i 记录当前的word的index

*/
public ArrayList<String> fullJustify(String[] words, int L) {
    ArrayList<String> res = new ArrayList<String>();
    if(words==null || words.length==0)
        return res;
    int count = 0;
    int last = 0;
    for(int i=0;i<words.length;i++)
    {
        if(count+words[i].length()+(i-last)>L)
        {
            int spaceNum = 0;
            int extraNum = 0;
            if(i-last-1>0)
            {
                spaceNum = (L-count)/(i-last-1);
                extraNum = (L-count)%(i-last-1);
            }
            StringBuilder str = new StringBuilder();
            for(int j=last;j<i;j++)
            {
                str.append(words[j]);
                if(j<i-1)
                {
                    for(int k=0;k<spaceNum;k++)
                    {
                        str.append(" ");
                    }
                    if(extraNum>0)
                    {
                        str.append(" ");
                    }
                    extraNum--;//tricky
                }
            }
            for(int j=str.length();j<L;j++)
            {
                str.append(" ");
            }       
            res.add(str.toString());
            count=0;
            last=i;
        }
        count += words[i].length();
    }
    StringBuilder str = new StringBuilder();
    for(int i=last;i<words.length;i++)
    {
        str.append(words[i]);
        if(str.length()<L)
            str.append(" ");
    }
    for(int i=str.length();i<L;i++)
    {
        str.append(" ");
    }
    res.add(str.toString());
    return res;
}