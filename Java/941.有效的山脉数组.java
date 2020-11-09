/*
 * @lc app=leetcode.cn id=941 lang=java
 *
 * [941] 有效的山脉数组
 */

// @lc code=start
class Solution {
    public boolean validMountainArray(int[] A) {
        int left = 0;
        int right = A.length-1;

        for(int i=0;i<A.length;i++){
            if(A[i]>A[i+1]){
                left = i;
            }
        }

        for(int j = A.length-1;j>0;j--){
            if(A[j]>A[j-1]){
                right = j;
            }
        }

        return left == right;
        
    }
}
// @lc code=end

