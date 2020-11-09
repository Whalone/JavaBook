/*
 * @lc app=leetcode.cn id=129 lang=java
 *
 * [129] 求根到叶子节点数字之和
 */

// @lc code=start

//Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    int sum =0;
    public int sumNumbers(TreeNode root) {
        int curr = 0;
        preOrder(root,curr);
        return sum;
    }

    public void preOrder(TreeNode node,int curr) {
        if (root == null) {
            
            return;
        }
        curr += curr*10+root.val;
        sum+=curr;
        preOrder(root.left, curr);
        preOrder(root.right, curr);
    }
}
// @lc code=end
