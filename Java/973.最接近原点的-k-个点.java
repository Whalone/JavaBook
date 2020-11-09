import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=973 lang=java
 *
 * [973] 最接近原点的 K 个点
 */

// @lc code=start
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }
}

// @lc code=end

