/*
 * @lc app=leetcode.cn id=617 lang=java
 *
 * [617] 合并二叉树
 */

// @lc code=start

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }
        if(t2==null){
            return t1;
        }

        TreeNode merge = new TreeNode(t1.val+t2.val);
        merge.left = mergeTrees(t1.left, t2.left);
        merge.right= mergeTrees(t1.right, t2.right);

        return merge;
            
        
    }

    
}
// @lc code=end
