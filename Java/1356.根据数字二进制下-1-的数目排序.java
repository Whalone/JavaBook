import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/*
 * @lc app=leetcode.cn id=1356 lang=java
 *
 * [1356] 根据数字二进制下 1 的数目排序
 */

// @lc code=start
class Solution {
    public int[] sortByBits(int[] arr) {
        // 0 <= arr[i] <= 10^4
        int bit[] = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for(int a : arr){
            list.add(a);
            bit[a] = get(a);
        }
        // 利用collections工具类对list进行排序
        // 指定Comparator规则
        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer x,Integer y){
                if(bit[x]!=bit[y]){
                    return bit[x] - bit[y];
                }else{
                    return x - y;
                }
            }
        });

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int get(int a){
        int res = 0;
        while(a!=0){
            res += a%2;
            a /= 2;
        }
        return res;
    }
}
// @lc code=end

