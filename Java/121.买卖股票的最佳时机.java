/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i : prices){
            if(i<min){
                min = i;
            }else{
                max = Math.max(max, i-min);
            }
        }
        return max;
    }
    
}
// @lc code=end

