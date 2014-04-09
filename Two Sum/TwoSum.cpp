/**
Two Sum
Total Accepted: 8509 Total Submissions: 39033

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2 
**/
#include<algorithm>
#include<vector>

/**
wrong way, we need the index of original array, not the sorted one. So we cannot do the sort first.
class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
        vector<int> result;
        double middle = double(target)/2.0;
        std::sort(numbers.begin(),numbers.end());
        int index1,index2,a,b;
        int size=numbers.size();
        
        for(int i=0; numbers[i]<=middle; i++)
        {
            a = numbers[i];
            b = target -numbers[i];
            for(int j=size; numbers[j]>middle;j--)
            {
                if(numbers[j]==b)
                {
                    index1 = i+1;
                    index2 = j+1;
                }
            }
        }
        result.push_back(index1);
        result.push_back(index2);
        
        return result;
        
    }
};
**/


class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
        vector<int> result;
        size = numbers.size();
        int index1, index2;
        
        for(int i = 0; i<size; i++)
        {
            for(int j= i; j<size; j++)
            {
                if(numbers[i] + numbers[j]==target)
                {
                    result.push_back(i+1);
                    result.push_back(j+1);
                    break;
                }
            }
        }
        return result;             
    }
};


class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
        unordered_map<int, int> mapping;
        vector<int> result;
        for(int i =0; i<numbers.size(); i++){
            mapping[numbers[i]]=i;
        }
        for(int i =0; i<numbers.size();i++){
            const int gap = target - numbers[i];
            if(mapping.find(gap)!=mapping.end()){
                result.push_back(i+1);
                result.push_back(mapping[gap]+1);
                break;
            }
        }
        return result;             
    }
};
