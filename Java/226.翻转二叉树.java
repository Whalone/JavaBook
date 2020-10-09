/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
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
    public TreeNode invertTree(TreeNode root) {

        fanzhuan(root);

        return root;

    }

    public void fanzhuan(TreeNode root){
        if(root!=null){
            TreeNode oldLeft = root.left;
            TreeNode oldRight = root.right;

            root.left = oldRight;
            root.right = oldLeft;

            fanzhuan(root.left);
            fanzhuan(root.right);
        }
        
    }
}
// @lc code=end
