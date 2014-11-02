/**
Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
		if(intervals == null || intervals.size() <= 1)
			return intervals;

		
		Collections.sort(intervals, new IntervalComparator());

		ArrayList<Interval> result = new ArrayList<Interval>();

		Interval prev = intervals.get(0);

		for(int i = 1; i < intervals.size(); i++){
			Interval curr = intervals.get(i);

			if(prev.end >= curr.start){
				//merge case
				Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
				prev = merged;
			}else{
				result.add(prev);
				prev = curr;
			}
		}        

		result.add(prev);//最后一个，存不存在重复的情况？？？因为ArrayList中最后一个interval在循环结束的时候没有被处理，所以这里需要补上
		return result;
    }

    class IntervalComparator implements Comparator<Interval>{
    	public int compare(Interval i1, Interval i2){
    		return i1.start - i2.start;
    	}
    }
}


//my solution
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0) return res;
        IntervalComparator cmp = new IntervalComparator();
        Collections.sort(intervals, cmp);
        Interval pre = null;
        for(Interval cur: intervals){
            if(pre == null){
                pre = cur;
            }else{
                if(pre.end < cur.start){
                    res.add(pre);
                    pre= cur;
                }else if(pre.end >= cur.start){
                    pre = new Interval(Math.min(pre.start, cur.start), Math.max(pre.end, cur.end));
                }
            }
        }
        res.add(pre);
        return res;
    }
    
    class IntervalComparator implements Comparator<Interval>{
        public int compare(Interval n1, Interval n2){
            if(n1.start > n2.start) return 1;
            else if(n1.start == n2.start) return 0;
            else return -1;
        }
    }
}