/*
 * @lc app=leetcode.cn id=486 lang=java
 *
 * [486] 预测赢家
 */

// @lc code=start
class Solution {

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int i =0;i<n;i++){
            dp[i][i] = nums[i];
        }

        for(int i = n-2;i>=0;i--){
            for(int j=i+1;j<n;j++){
                dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
            }
        }

        return dp[0][n-1] >= 0;
    }

    

}
// @lc code=end
