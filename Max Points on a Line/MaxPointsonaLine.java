/**
Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/*
http://www.aichengxu.com/article/Java/18179_2.html
固定一点，计算每一条经过这个店的直线斜率，用一个hashmap记录。记录下最多的斜率个数。对每个点做一次这样的搜索，这样复杂度就是O(n^2)
经过一点，然后还有相同的斜率，说明他们在同一条线上
要注意相同点的情况
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
		if(points.length == 0) return 0;
		if(points.length == 1) return 1;
		int max = 0;
		for(int i = 0; i < points.length; i++){
			HashMap<Float, Integer> hm = new HashMap<Float, Integer>();
			int same = 0;
			for(int j = 0; j < points.length; j++){
				if(i == j) continue;
				if(points[i].x == points[j].x && points[i].y == points[j].y){
					same++;
					continue;
				}

				float slope = (float)(points[i].y - points[j].y)/(points[i].x - points[j].x);
				if(hm.containsKey(slope)){
					hm.put(slope, hm.get(slope) + 1);
				}else{
					hm.put(slope, 2);
				}
			}

			for(float k: hm.keySet()){
				hm.put(k, hm.get(k) + same);
			}

			for(int v: hm.values()){
				if(v > max) max =v;
			}

			//process all points are the same situation
			if(same + 1 > max){
				max = same + 1;
			}
		} 
		return max;      
    }
}

/*
				float slope = (points[i].y - points[j].y)/(points[i].x - points[j].x);
Submission Result: Runtime Error

Runtime Error Message:	Line 25: java.lang.ArithmeticException: / by zero
Last executed input:	[(0,0),(0,1)]

*/

/*
if there is no same + 1 >max

Submission Result: Wrong Answer
Input: 	[(0,0),(0,0)]
Output: 	0
Expected: 	2
*/

/*
another similar solution:
http://rleetcode.blogspot.com/2014/01/max-points-on-linejava.html


others:
http://yucoding.blogspot.com/2013/12/leetcode-question-max-points-on-line.html
http://blog.csdn.net/muscler/article/details/23015277
*/


