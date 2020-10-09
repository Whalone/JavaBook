/*
 * @lc app=leetcode.cn id=501 lang=java
 *
 * [501] 二叉搜索树中的众数
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
    public int[] findMode(TreeNode root) {

        Map<Integer,Integer> map = new HashMap<>();
        // 使用栈 DFS
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            map.put(node.val, map.getOrDefault(node.val, 0)+1);
            if(root.left!=null){
                stack.push(root.left);
            }
            if(root.right!=null){
                stack.push(root.right);
            }
            
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((n1,n2)->map.get(n2)-map.get(n1));

        for(int n:map.keySet()){
            priorityQueue.add(n);
        }

        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        int max = priorityQueue.poll();
        maxCount = map.get(max);
        result.add(max);
        while(priorityQueue.size()!=0){
            max = priorityQueue.poll();
            if(maxCount == map.get(max)){
                result.add(max);
            }else{
                break;
            }
        }
        
        int[] resultt = new int[result.size()];
        for(int i = 0;i<result.size();i++){
            resultt[i] = result.get(i);
        }

        return resultt;
    }
}
// @lc code=end
