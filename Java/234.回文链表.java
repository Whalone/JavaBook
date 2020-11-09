import java.util.List;

/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode node = head;
        List<Integer> list = new ArrayList<Integer>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        int i = 0;
        int j = list.size()-1;
        while(i<j){
            if(!list.get(i).equals(list.get(j))){
                return false;
            }else{
                i++;
                j--;
            }
        }

        return true;

    }
}
// @lc code=end
