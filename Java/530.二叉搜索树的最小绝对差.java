/*
 * @lc app=leetcode.cn id=530 lang=java
 *
 * [530] 二叉搜索树的最小绝对差
 */

// @lc code=start

//   Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public int pre = -1;
    public int ans = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node){
        if(node==null){
            return;
        }

        dfs(node.left);
        if(pre == -1){
            pre = node.val;
        }else{
            ans = Math.min(ans, node.val-pre);
            pre = node.val;
        }
        dfs(node.right);
    }
}
// @lc code=end
