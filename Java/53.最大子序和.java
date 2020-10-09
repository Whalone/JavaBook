import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for(int num : nums){
            if(sum>0){
                sum+=num;
            }else{
                sum = num;
            }
            ans = Math.max(sum, ans);
        }

        return ans;
    }
}
// @lc code=end

