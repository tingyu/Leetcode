/**
Add Two Numbers
Total Accepted: 5553 Total Submissions: 24786

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order 
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
**/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        ListNode *p, *q;
        for(p=l1->next, q=l2->next; p!=NULL||q!=NULL; p=p->next,q=q->next){
            if(p!=NULL&&q!=NULL&&p->next!=NULL){
                p->val+=q->val;
                if(p->val>=10){
                    p->val=p->val-10;
                    p->next->val+=1;
                }
            }
            else()
        }
    }
};