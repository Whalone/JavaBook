/*
 * @lc app=leetcode.cn id=1365 lang=java
 *
 * [1365] 有多少小于当前数字的数字
 */

// @lc code=start
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int curr = nums[i];
            int num = 0;
            for(int j =0;j<nums.length;j++){
                if(curr>nums[j]){
                    num++;
                }
            }
            result[i] = num;
        }
        return result;
    }

}
// @lc code=end

