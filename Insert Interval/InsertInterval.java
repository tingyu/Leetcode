/**
Insert Interval 

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/

/*
http://www.programcreek.com/2012/12/leetcode-insert-interval/
Quickly summarize 3 cases. Whenever there is intersection, created a new interval.

*/
/**新区间是新输入的线段，对原有的线段ArrayList进行遍历。
考虑以下情况： 
如果当前区间的end小于新区间的start，把当前区间加入res,继续遍历找下一个区间。
如果新区间的end < 当前区间的start，把新区间加入res, 然后把当前区间赋值给新区建。
如果当前区间和新区间发生重合，则start取两者最小的start，end取两者最大的end，生成一个新的区间。
继续遍历。
遍历到结尾的时候，还有一个新区间没有加入res中，因此需要在循环外面处理。
此时同时处理了如果intervals是空的时候的特殊情况。

如果遍历一直到末尾也没发生区间重合，就把新区间插入到原来ArrayList的末尾。
里面用到了几个通过iterator操作ArrayList的函数：在遍历ArrayList的过程中想删除其中某个element、遍历的过程中插入某个element，有空慢慢总结一下。

下面解法是不断更新newInterval，直到最后一个等于newInterval然后加入
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();

        for(Interval interval: intervals){
        	//当前的interval比newInterval要小，并且没有交集；将当前interval加入到res即可，不用更新newInterval。因为是按照从小到大排序
        	if(interval.end < newInterval.start){
        		result.add(interval);
        	}else if(interval.start > newInterval.end){
        		//newInterval比当前interval要小，并且没有交集。因为要从小到大放入res,所以将newInterval加入res,此时同时要更新newInterval为当前的那个
        		result.add(newInterval);
        		newInterval = interval;
        	}else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
        		//当前的interval和newInterval有交集的情况。此时包含了几种情况。
        		//1，interval.end >= newInterval.start当前interval的后半段和newinterval的前半段有交集
        		//2.interval.start <= newInterval.end当前interval的前半段和newInterval的后半段有交集
        		//3. interval.start <= newInterval.start && interval.end >= newInterval.end newInterval完全包含在Interval里面
        		//4. interval.start >= newInterval.start && interval.end <= newInterval.end Interval完全包含在newInterval里面
        		//这些情况都可以使用interval.end >= newInterval.start || interval.start <= newInterval.end来概况，因为上面两种情况已经被排除了
        		//不管哪种情况，交集的话都是选择最小的作为左边，最大的作为右边
        		newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.val));
        	}
        }
        result.add(newInterval);
        return result;
    }
}

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        for(Interval interval: intervals){
            if(newInterval.end < interval.start){
                res.add(newInterval);
                newInterval = interval;
            }else if(newInterval.start > interval.end){
                res.add(interval);
            }else if(newInterval.start >= interval.start && newInterval.end <= interval.end
                    || newInterval.start >= interval.start && newInterval.end > interval.end
                    || newInterval.start < interval.start && newInterval.end <= interval.end){
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
            
        }
        res.add(newInterval);
        return res;
    }
}