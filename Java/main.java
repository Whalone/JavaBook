import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import javax.lang.model.element.Element;

import sun.security.util.Length;

class Test {

    public static void main(final String args[]) {
        int nums[] = { 2, 0, 1 };
        Solution9 solution9 = new Solution9();
        solution9.sortColors(nums);

        // System.out.println(result);

    }

}

class Solution11 {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        
        return 0;
    }
}

class Solution10 {
    public int findContentChildren(int[] g, int[] s) {
        // g是胃口 s是大小

        // 先排序
        Arrays.sort(g);
        Arrays.sort(s);

        int gi=0;
        int si=0;

        while(gi<g.length&&si<s.length){
            if(g[gi]<=s[si]){
                gi++;
            }
            si++;
        }

        return gi;
    }
}

class Solution33333 {
    public int findKthLargest(final int[] nums, final int k) {
        quickSort(nums, 0, nums.length - 1);
        // System.out.println(nums[nums.length-k]);
        return nums[nums.length - k];
    }

    public void quickSort(final int[] nums, final int left, final int right) {
        int i;
        int j;
        int temp1;
        int temp2;

        if (left >= right) {
            return;
        }

        i = left;
        j = right;
        temp1 = nums[left];

        while (i != j) {
            while (nums[j] >= temp1 && i < j) {
                j--;
            }

            while (nums[i] <= temp1 && i < j) {
                i++;
            }
            if (i < j) {
                temp2 = nums[i];
                nums[i] = nums[j];
                nums[j] = temp2;
            }

        }

        nums[left] = nums[i];
        nums[i] = temp1;

        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }
}

class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            // partition就是在做快速排序
            // j 是快速排序后 前后指针相遇的索引
            int j = partition(nums, l, h);
            if (j == k) {
                // j = k 是命中的情况，此时j之前的值都比nums[j]小，j之后的值都比nums[j]大
                break;
            } else if (j < k) {
                // j 在 k 之前，对后面进行排序
                l = j + 1;
            } else {
                // j 在 k 之后，对前面进行排序
                h = j - 1;
            }
        }
        // break 情况退出来：命中
        // l < h 情况退出来：已经全部排序完，是有序的了，直接返回nums[k]
        return nums[k];
    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h)
                ;
            while (a[--j] > a[l] && j > l)
                ;
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int val : nums) {
            queue.add(val);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}

class Solution4 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (Integer num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k; i > 0; i--) {
            result[i - 1] = heap.poll();
        }

        return result;

    }

}

class Solution5 {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));
        for (char temp : map.keySet()) {
            heap.add(temp);
        }
        String result = "";
        while (!heap.isEmpty()) {
            char c = heap.poll();
            for (int i = 0; i < map.get(c); i++) {
                result = result + c;
            }
        }

        return result;
    }
}

class Solution6 {
    public String frequencySort(String s) {
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for (char c : s.toCharArray())
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);

        // List数组做桶
        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (char c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if (frequencyBucket[f] == null) {
                frequencyBucket[f] = new ArrayList<>();
            }
            // 使用list的话，就算有相同出现频率的字母出现也不会覆盖
            frequencyBucket[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i >= 0; i--) {
            if (frequencyBucket[i] == null) {
                continue;
            }
            for (char c : frequencyBucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }

}

class Solution7 {
    public int minSubArrayLen(int s, int[] nums) {

        int start = 0;
        int end = 0;
        int sum = nums[0];
        int minLength = Integer.MAX_VALUE;

        while (start < nums.length && end < nums.length) {

            if (sum >= s && start <= end) {
                minLength = Math.min(minLength, end - start + 1);
                if (start < nums.length) {
                    sum -= nums[start];
                    if (start < end) {
                        start++;
                    } else {
                        end++;
                    }
                }
            } else {
                if (end < nums.length - 1) {
                    end++;
                    if (end < nums.length) {
                        sum += nums[end];
                    }
                }
            }

        }

        return minLength;
    }
}

class Solution8 {
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

class Solution9 {
    public void sortColors(int[] nums) {
        int point0 = 0; // 指向0的指针 肯定是在数组的最前面
        int point2 = nums.length - 1; // 指向2的指针 肯定是在数组的最前面
        int curr = 0; // 当前遍历的指针 从头开始
        while (curr <= point2) {
            if (nums[curr] == 1) {
                curr++;
            } else if (nums[curr] == 0) {
                nums[curr] = nums[point0];
                nums[point0] = 0;
                point0++;
                curr++;
            } else if (nums[curr] == 2) {
                nums[curr] = nums[point2];
                nums[point2] = 2;
                point2--;
            }

        }

    }
}
