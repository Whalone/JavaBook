import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前 K 个高频元素
 */

// @lc code=start
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (Integer i : nums) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((n1, n2) -> countMap.get(n1) - countMap.get(n2));
        
        for(int n : countMap.keySet()){
            priorityQueue.add(n);
            if(priorityQueue.size()>k){
                priorityQueue.poll();
            }   
        }

        int[] result = new int[priorityQueue.size()];

        for(int i = priorityQueue.size()-1;i>=0;i--){
            result[i] = priorityQueue.poll();
        }

        return result;
    }
}
// @lc code=end
