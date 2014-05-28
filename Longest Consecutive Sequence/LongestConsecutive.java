/**

Longest Consecutive Sequence
Total Accepted: 11367 Total Submissions: 41784

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity. 
*/

/*
Thoughts

Because it requires O(n) complexity, we can not solve the problem by sorting the array first. Sorting takes at least O(nlogn) time.

Java Solution 1

We can use a HashSet to add and remove elements. HashSet is implemented by using a hash table. 
Elements are not ordered. The add, remove and contains methods have constant time complexity O(1).
*/
