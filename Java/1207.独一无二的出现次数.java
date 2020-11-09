/*
 * @lc app=leetcode.cn id=1207 lang=java
 *
 * [1207] 独一无二的出现次数
 */

// @lc code=start
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> count = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        // 记录数组每个数出现过的次数
        for(int a:arr){
            count.put(a, count.getOrDefault(a, 0)+1);
        }

        for(Integer key :count.keySet()){
            if(!list.contains(count.get(key))){
                list.add(count.get(key));
            }else{
                return false;
            }
        }

        return true;


    }
}
// @lc code=end

