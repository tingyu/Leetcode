/**
Permutation Sequence

The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/*
Best Solution

http://blog.csdn.net/u013027996/article/details/18405735

3、数学规律。
其实学过数学，知道排列组合，无重复数据的话，n个数，从1到n就有n!种组合。
那么如果我们知道第一位数字是多少，就能算出后面的(n-1)位数的组合，也就是(n-1)!种组合。
这个分析很明显了，n可以分成n组，每组有(n-1)!个数，
比如n = 6，那么以1,2,3,4,5,6开头的组合必然是各有(n-1)! = 5! = 120中组合。
我们认为组数应该从0开始，那么k要-1;
注意此时K = 299，那么我们先要求解这个k在第几组，k/(n-1)! = 299/120 = 2,也就是说k应该在第
3组(注意组号从0开始)，第三组的首个数字应该是3。这样第一个数字就确定了。
确定第2个数字的时候，注意这个时候，k应该等于k % 120 = 59,为什么要这么算呢，因为每个组有120个数字，
而且k在第三组，那么前两组加起来是240，k在第二次循环的时候，应该是从(5-1)!中确定是属于哪个组，其实
就是确定k在第三组中是属于哪个位置。这个时候59/24 = 2,确定应该是属于第三组，
因为在上一步中，3已经用过了，所以这个时候的5个数字是1,2,4,5,6，
所以第三组的首个数字是4，依次类推，一直到n个数字全部算完为止。
答案就出来了。

Thoughts: it's more like a math problem. We can calculate every digit directly. 
d1 = k1/(n-1)!
k2 = k1%(n-1)!
d2 = k2/(n-2)!
...
and so on.
*/



public class Solution {
    public String getPermutation(int n, int k) {
    	//numList是用来存从1~n的数字，因为后面要从里面找到对应的并且添加进去
    	//sum就是用来记录公式里面的分母，(n-1)!, (n-2)!之类的
    	//下面是得到初始值，讲1~n全部添加进去，sum = n!
    	List<Integer> numList = new ArrayList<Integer>();
    	numList.add(1);
    	int sum = 1;
    	for(int i = 2; i <= n; i++){
    		sum *= i;
    		numList.add(i);
    	}

    	//开始准备循环，准备找到第一个数字
    	sum /= n;//sum = (n-1)!
    	k--; //因为第几个的话在numList中存入的是k-1的位置

    	StringBuffer sb = new StringBuffer();
    	//因为有n个数，找的时候从第一个位置1开始找到第n个位置n，这样循环
    	for(int i = 1; i <= n; i++){
    		//确定第几个元素，然后去numList中找到该元素，append到StringBuffer中。然后原来的numList中remove这个元素
    		int currNum = k /sum;
    		sb.append(numList.get(currNum));
    		numList.remove(currNum);
    		//如果已经第n个元素了，在numList中找到之后，就直接break，后面的继续更新就没有必要了
    		if(i == n){
    			break;
    		}
    		//更新k的值，准备找下一个位置
    		k %= sum;
    		//更新sum的值，准备下次currNum = k /sum; 
    		sum /= (n - i);
    	}
    	return sb.toString();
    }
}


/*
http://www.programcreek.com/2013/02/leetcode-permutation-sequence-java/
Cannot understand this solution
*/
public class Solution {
    public String getPermutation(int n, int k) {
    	boolean[] output = new boolean[n];
    	StringBuilder buf = new StringBuilder("");

    	int[] res = new int[n];
    	res[0] = 1;

    	for(int i = 1; i < n; i++){
    		res[i] = res[i - 1] * i;
    	} 

    	for(int i = n -1; i >= 0; i--){
    		int s = 1;

    		while(k > res[i]){
    			s++;
    			k = k -res[i];
    		}

    		for(int j = 0; j < n; j++){
    			if(j + 1 <= s && output[j]){
    				s++;
    			}
    		}

    		output[s - 1] = true;
    		buf.append(Integer.toString(s));
    	}
    	return buf.toString();
    }
}



/*
http://blog.csdn.net/u013027996/article/details/18405735

1、第一种思路，我一眼看到这种，就想到DFS，暴力搜索嘛，搜到所有的答案之后，放到list中，
题目不是要求第K个组合是多少么，从list中get就得到了。超时。
*/

public class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1; i <= n; i++){
        	StringBuffer sb = new StringBuffer(i);
        	int visit[] = new int[n + 1];
        	visit[i] = 1;
        	sb.append(i);
        	int num = 1;
        	dfs(list, visit, sb, n, num);
        }
        return list.get(k - 1);
    }

    public void dfs(ArrayList<String> list, int visit[], StringBuffer sb, int n, int num){
    	if(num == n){
    		list.add(sb.toString());
    	}
    	for(int i = 1; i <= n; i++){
    		if(visit[i] == 0){
    			StringBuffer tempSb = new StringBuffer(sb);
    			tempSb.append(i);
    			visit[i] = 1;
    			dfs(list, visit, tempSb, n, num + 1);
    			visit[i] = 0;
    		}
    	}
    }
}


/*
2、优化第一种，还是DFS，我不罗列所有的了，每次得到一个答案，就判断当前个数是不是和k相等，
如果相等的话，就把得到的结果return回去。还是超时了，不过好像比第一种多过了几个case。
*/
public class Solution {
    public int count ;
    public String result;
    public String getPermutation(int n, int k) {
        count = 0;
        result = "";
        for(int i = 1; i <= n; i++){
            StringBuffer sb = new StringBuffer(i);
            int visit[] = new int[n+1];
            visit[i] = 1;
            sb.append(i);
            int num = 1;
            dfs(visit,sb,n,num,k);
        }
        return result;
    }
    public void dfs(int visit[], StringBuffer sb, int n, int num, int k){
        if(num == n){
            count ++;
            if(count == k){
                result = sb.toString();
                return;
            }
        }
        for(int i = 1; i <= n; i++){
            if(visit[i] == 0){
                StringBuffer tempSb = new StringBuffer(sb);
                tempSb.append(i);
                visit[i] = 1;
                dfs(visit,tempSb,n,num+1,k);
                visit[i] = 0;
            }
        }
    }
}