/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.*/
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
         val = x;
         next = null;
    }
 }

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(slow!=fast){
            if(fast!=null&&fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }else{
                return false;
            }
        }

        return true;
    }
}
// @lc code=end

