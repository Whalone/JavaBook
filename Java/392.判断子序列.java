/*
 * @lc app=leetcode.cn id=392 lang=java
 *
 * [392] 判断子序列
 */

// @lc code=start
class Solution {

    private static void main(String args[]) {
        Sting s = "abc";
        String t = "ahbgdc";


    }


    public boolean isSubsequence(String s, String t) {

        int index1 = 0;
        int index2 = 0;
        while(index1<s.length()&&index2<t.length()){
            if(s.charAt(index1)==t.charAt(index2)){
                index1++;
                index2++;
            }else{
                index2++;
            }
        }

        return index1 == s.length();
    }
}
// @lc code=end

