import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javafx.print.Collation;

public class main2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution = new Solution46();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);

    }
}

class Solution46 {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(path);
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);
                // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

}



// 841
    
// 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

// 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

// 最初，除 0 号房间外的其余所有房间都被锁住。

// 你可以自由地在房间之间来回走动。

// 如果能进入每个房间返回 true，否则返回 false。

// 示例 1：

// 输入: [[1],[2],[3],[]]
// 输出: true
// 解释:  
// 我们从 0 号房间开始，拿到钥匙 1。
// 之后我们去 1 号房间，拿到钥匙 2。
// 然后我们去 2 号房间，拿到钥匙 3。
// 最后我们去了 3 号房间。
// 由于我们能够进入每个房间，我们返回 true。
// 示例 2：

// 输入：[[1,3],[3,0,1],[2],[0]]
// 输出：false
// 解释：我们不能进入 2 号房间。
// 提示：

// 1 <= rooms.length <= 1000
// 0 <= rooms[i].length <= 1000
// 所有房间中的钥匙数量总计不超过 3000。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/keys-and-rooms
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 使用Deque的stack dfs进行遍历
        Deque stack = new LinkedList<Integer>();
        List<Integer> firstRoom = new ArrayList<Integer>();
        boolean[] visit = new boolean[rooms.size()];
        int num = 0;

        for(int i : rooms.get(0)){
            stack.push(i);
        }
        visit[0] = true;
        num++;


        while(!stack.isEmpty()){
            int room = (int) stack.pop();
            if(!visit[room]){
                num++;
                visit[room] = true;
                for(int j : rooms.get(room)){         
                    stack.push(j);
                }
            }
        }

        return num == rooms.size();


    }
}

class CQueue {
    // stack1 只负责in的操作
    Deque<Integer> stack1;
    // stack2 只负责out的操作
    Deque<Integer> stack2;

    public CQueue() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        // 添加的时候直接添加进stack即可
        stack1.add(value);
    }

    public int deleteHead() {
        // 当进行out操作时候
        // 当stack2为空 把stack1从栈顶一个个pop出来poll进stack2 这样子能保证先进的元素可以先出
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // stack2不为空 直接pop就好
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            return -1;
        }
    }
}

class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;

        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {

            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 取最小
        // 刚开始我取0 但是发现不能解决区间<0的情况
        int end = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            // 目前的end小于遍历中的区间的end 有接触
            if (interval[0] < end) {
                // 需要去掉这个区间
                count++;
            } else {
                // 无接触 更新end
                end = interval[1];
            }
        }

        return count;

    }
}

// class TreeNode {
// int val;
// TreeNode left;
// TreeNode right;
// TreeNode(int x) { val = x; }
// }

// class Solution108 {
// public TreeNode sortedArrayToBST(int[] nums) {
// return DFS(nums,0,nums.length-1);

// }

// private TreeNode DFS(int[] nums, int low, int high) {
// if(low>high){
// return null;
// }
// // 每次都取中间节点为根节点
// int mid = low+(high-low)/2;
// TreeNode root = new TreeNode(nums[mid]);
// root.left = DFS(nums, low, mid-1);
// root.right = DFS(nums,mid+1,high);

// return root;
// }
// }

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution112_office {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }
}

class Solution112_me {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack1 = new LinkedList<TreeNode>();
        Deque<Integer> stack2 = new LinkedList<Integer>();

        stack1.push(root);
        stack2.push(root.val);

        while (!stack1.isEmpty()) {
            TreeNode now = stack1.pop();
            int temp = stack2.pop();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                } else {
                    continue;
                }
            }
            if (now.left != null) {
                stack1.push(now.left);
                stack2.push(temp + now.left.val);
            }
            if (now.right != null) {
                stack1.push(now.right);
                stack2.push(temp + now.right.val);
            }
        }

        return false;
    }
}

class Solution112_office2 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}

class Solution1611 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[] { shorter * k };
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = shorter * (k - i) + longer * i;
        }

        return result;
    }
}

// class Solution1713 {
// public int respace(String[] dictionary, String sentence) {

// }
// }

class Solution123 {
    /**
     * 自定义一个TrieNode类型。 这里不用建一个变量来存当前节点表示的字符， 因为只要该节点不为null，就说明存在这个字符
     */
    class TrieNode {
        TrieNode[] childs;
        boolean isWord;

        public TrieNode() {
            childs = new TrieNode[26];
            isWord = false;
        }
    }

    TrieNode root; // 空白的根节点，设为全局变量更醒目

    public int respace(String[] dictionary, String sentence) {
        root = new TrieNode();
        makeTrie(dictionary); // 创建字典树
        int n = sentence.length();
        int[] dp = new int[n + 1];
        // 这里从sentence最后一个字符开始
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = n - i; // 初始默认后面全不匹配
            TrieNode node = root;
            for (int j = i; j < n; j++) {
                int c = sentence.charAt(j) - 'a';
                if (node.childs[c] == null) {
                    // 例如"abcde",i=1,j=2 可找出长度关系
                    dp[i] = Math.min(dp[i], j - i + 1 + dp[j + 1]);
                    break;
                }
                if (node.childs[c].isWord) {
                    dp[i] = Math.min(dp[i], dp[j + 1]);
                } else {
                    dp[i] = Math.min(dp[i], j - i + 1 + dp[j + 1]);
                }
                node = node.childs[c];
            }
        }
        return dp[0];
    }

    public void makeTrie(String[] dictionary) {
        for (String str : dictionary) {
            TrieNode node = root;
            for (int k = 0; k < str.length(); k++) {
                int i = str.charAt(k) - 'a';
                if (node.childs[i] == null) {
                    node.childs[i] = new TrieNode();
                }
                node = node.childs[i];
            }
            node.isWord = true; // 单词的结尾
        }
    }
}

class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        ArrayList<Integer> result = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) != 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        // System.out.println(result);

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}

// 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

// 例如，给定三角形：
// [
// [2],
// [3,4],
// [6,5,7],
// [4,1,8,3]
// ]

class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

}

class Solution120_office {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }
}

