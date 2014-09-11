/**
Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/
/*
思路总结：
因为求一条线上最多的点数，首先想到如何固定一条线：固定一点，然后求斜率，通过同一点具有相同斜率线上的电都在同一条线上
此时有两个特殊情况
1. 在同一个位置的点，可以通过x,y都相等判断。在后面hashmap都记录完之后还是要再更新下（注意一定要更新，这是tricky容易错的地方）
2. 两个点x相同，但是y不同，此时斜率不存在，所以需要特殊处理
3. 计算斜率，通过斜率存放到hashMap中并更新数目
4. 最后还要考虑如果所有的点都是相同点的情况，第一种种的更新hashmap对结果没用了，因为hashmap都是空的
			//process all points are the same situation
			if(same + 1 > max){
				max = same + 1;
			}
另外，注意slope是double，所以第二种情况也需要特殊处理
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


//my solution
public class Solution {
    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0) return 0;
        int max = 0;
        for(Point o: points){
            int same = 0;
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            for(Point p: points){
                double slope = 0;
                if(o.x == p.x && o.y == p.y){
                    same++;
                }else if(o.x == p.x && o.y != p.y){
                    if(map.containsKey(Double.MAX_VALUE)){
                        map.put(Double.MAX_VALUE, map.get(Double.MAX_VALUE) + 1);
                    }else{
                        map.put(Double.MAX_VALUE, 2);
                    }   
                }else{
                    slope =((double)(p.y - o.y))/(p.x - o.x);
                    if(map.containsKey(slope)){
                        map.put(slope, map.get(slope) + 1);
                    }else{
                        map.put(slope, 2);
                    }
                }
                
            }
            
            if(same >1){
                for(double i: map.keySet()){
                    map.put(i, map.get(i) + same-1);
                }
            }
            
            for(int size: map.values()){
                if(size > max){
                    max = size;
                }
            }
            
            if(same > max){
                max = same;
            }
        }
        return max;
    }
}

