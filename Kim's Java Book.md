

# Kim's Java Book

## 算法

### LeetCode

#### 双指针

#####  167. 两数之和 II - 输入有序数组

给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

**说明：**

- 返回的下标值（index1 和 index2）不是从零开始的。
- 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

**示例：**

```java
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```

使用双指针，一个指针指向值较小的元素，一个指针指向值较大的元素。指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。

- 如果两个指针指向元素的和 sum == target，那么得到要求的结果；
- 如果 sum > target，移动较大的元素，使 sum 变小一些；
- 如果 sum < target，移动较小的元素，使 sum 变大一些。

数组中的元素最多遍历一次，时间复杂度为 O(N)。只使用了两个额外变量，空间复杂度为 O(1)。

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int prev = 0;
        int back = numbers.length-1;
        int result[] = new int[2];
        result[0]=1;
        result[1]=2;

        while(prev<=back){
            if(numbers[prev] + numbers[back] == target){
                result[0]=prev+1;
                result[1]=back+1;
                return result;
            }else if (numbers[prev] + numbers[back]<target){
                prev++;
            }else{
                back--;
            }
        }
        return null;
    }
}
```



##### 633. 平方数之和

**说明：**

给定一个非负整数 `c` ，你要判断是否存在两个整数 `a` 和 `b`，使得 $ a^2+b^2=c $

**示例1：**

```java
输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5
```

**示例2：**

```java
输入: 3
输出: False
```

与167相同的思想，使用$ a*a+b*b<c$的逻辑进行判断。

刚开始我是直接使用int进行存放前后指针，但是当c为较大的数时，如999999999，那么$ b*b $就会超出长度。

所以可以先对c进行开方，实现剪枝，缩小搜索范围。

```java
class Solution{
    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = new Double(Math.sqrt(c)).intValue();
        while(a<=b){
            // b不进行开方b*b会超出int长度
            if(a*a+b*b==c){
                // System.out.println(a);
                // System.out.println(b);
                return true;
            }else if(a*a+b*b<c){
                a++;
            }else{
                b--;
            }
        }
        return false;
    }
}
```



##### 345. 反转字符串中的元音字母

**说明：**

编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

**示例1：**

```java
输入: "hello"
输出: "holle"
```

 **示例2：**

```java
输入: "hello"
输出: "holle"
```

元音字母不包含字母"y"。

利用双指针遍历，看当前字符是不是元音，如果是就++或者--，然后进行替换。

```java
class Solution {
    public String reverseVowels(String s) {
        char[] ss = s.toCharArray();
        int a = 0;
        int b = ss.length - 1;
        char tmp = ' ';

        while (a < b) {

            while (a < b) {
                if (Character.toLowerCase(ss[a]) != 'a' && Character.toLowerCase(ss[a]) != 'e' && Character.toLowerCase(ss[a]) != 'i' && Character.toLowerCase(ss[a]) != 'o' && Character.toLowerCase(ss[a]) != 'u') {
                    a++;
                } else {
                    break;
                }
            }

            while (a < b) {
                if (Character.toLowerCase(ss[b]) != 'a' && Character.toLowerCase(ss[b]) != 'e' && Character.toLowerCase(ss[b]) != 'i' && Character.toLowerCase(ss[b]) != 'o' && Character.toLowerCase(ss[b]) != 'u') {
                    b--;
                } else {
                    break;
                }
            }

            if (a < b) {
                tmp = ss[a];
                ss[a] = ss[b];
                ss[b] = tmp;
                a++;
                b--;
            }

        }
        return  new String(ss);
    }
}
```

大佬的解法跟我差不多，但是使用了hashset存放了元音字母，然后去查，确实比我的优雅很多。

```java
private final static HashSet<Character> vowels = new HashSet<>(
        Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

public String reverseVowels(String s) {
    if (s == null) return null;
    int i = 0, j = s.length() - 1;
    char[] result = new char[s.length()];
    while (i <= j) {
        char ci = s.charAt(i);
        char cj = s.charAt(j);
        if (!vowels.contains(ci)) {
            result[i++] = ci;
        } else if (!vowels.contains(cj)) {
            result[j--] = cj;
        } else {
            result[i++] = cj;
            result[j--] = ci;
        }
    }
    return new String(result);
}
```



##### 680. 验证回文字符串 Ⅱ

**说明：**

给定一个非空字符串 `s`，**最多**删除一个字符。判断是否能成为回文字符串。

**示例 1:**

```
输入: "aba"
输出: True
```

**示例 2:**

```
输入: "abca"
输出: True
解释: 你可以删除c字符。
```

**注意:**

1. 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。

   

所谓的回文字符串，是指具有左右对称特点的字符串，例如 "abcba" 就是一个回文字符串。

使用双指针可以很容易判断一个字符串是否是回文字符串：令一个指针从左到右遍历，一个指针从右到左遍历，这两个指针同时移动一个位置，每次都判断两个指针指向的字符是否相同，如果都相同，字符串才是具有左右对称性质的回文字符串。

![https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f64623566333061372d386266612d346563632d616235642d3734376337373831383936342e676966.gif]()

本题的关键是处理删除一个字符。在使用双指针遍历字符串时，如果出现两个指针指向的字符不相等的情况，我们就试着删除一个字符，再判断删除完之后的字符串是否是回文字符串。

在判断是否为回文字符串时，我们不需要判断整个字符串，因为左指针左边和右指针右边的字符之前已经判断过具有对称性质，所以只需要判断中间的子字符串即可。

在试着删除字符时，我们既可以删除左指针指向的字符，也可以删除右指针指向的字符。



```java
class Solution {

    public boolean validPalindrome(String s) {
        char chars[] = s.toCharArray();
        for(int i = 0, j=s.length()-1;i<=j;i++,j--){
            if(chars[i]!=chars[j]){
                return isPalindrome(chars, i+1, j)||isPalindrome(chars, i, j-1);
            }
        }
        return true;
    }

    public boolean isPalindrome(char chars[],int a,int b){
        for(int i = a,j=b;i<=j;i++,j--){
            if(chars[i]!=chars[j]){
                return false;
            }
        }
        return true;
    }  
}
```



##### 88.合并两个有序数组

给你两个有序整数数组 *nums1* 和 *nums2*，请你将 *nums2* 合并到 *nums1* 中*，*使 *nums1* 成为一个有序数组。

**说明:**

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

**示例：**

```java
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出:[1,2,2,3,5,6]
```



暴力偷懒解法

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i =m,j=0;j<n;j++,i++){
            nums1[i] = nums2[j];
        }
        // 还可以更偷懒
        // System.arraycopy(sum2,0,sum1,m,n);
        Arrays.sort(nums1);
    }
}
```

优化版本

从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。相当于把长的那个数组的最后几位空的当成tmp来存放数据。

因为是在num1上直接进行遍历，所以num2上的数据可能会被遗漏，如num2的数据全比num1小的时候。所以最后需要把num2上的数据复制到num1。

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m-1;
        int index2 = n-1;
        int p = m+n-1;

        while(index1>=0&&index2>=0){
            if(nums2[index2]>nums1[index1]){
                nums1[p] = nums2[index2];
                p--;
                index2--;
            }else{
                nums1[p] = nums1[index1];
                p--;
                index1--;
            }
        }
		
        System.arraycopy(nums2, 0, nums1, 0, index2 + 1);
        
    }
}
```



##### 66.判断链表是否存在环

**说明：**

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```



![](https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/circularlinkedlist.png)

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

![](https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/circularlinkedlist_test2.png)

**示例 3：**

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

![](https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/circularlinkedlist_test3.png)

可以使用快慢指针，如果存在环，那么快慢指针必定相遇。

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null||head.next==null){
            return false;
        }
        ListNode listNode1 = head;
        ListNode listNode2 = head.next;
        while(listNode1!=listNode2){
            // 这里只需要判断快指针
            // 而且要判断listNode2.next 
            // 不然listNode2.next.next会报空指针异常
            if(listNode2!=null&&listNode2.next!=null){
                    listNode1 = listNode1.next;
                    listNode2 = listNode2.next.next;
            }else{
                return false;
            }
        }
        return true;
    }
}
```



#####  524. 通过删除字母匹配到字典里最长单词

**说明：**

给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

**示例1：**

```
输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

输出: 
"apple"
```

**示例 2:**

```
输入:
s = "abpcplea", d = ["a","b","c"]

输出: 
"a"
```

**补充：**

1. 所有输入的字符串只包含小写字母。

2. 字典的大小不会超过 1000。

3. 所有输入的字符串长度不会超过 1000。

   

使用双指针，一个索引是目标字符串s，一个索引是要查的list里面的字符串str[i]。

相当于查看str[i]中的字符按顺序遍历，是否都能在s中找到。找到一个，s的索引就+1，str[i]的索引就+1。

str[i]遍历完，index2=str[i]的长度，证明str[i]所有的字符都是能在s中找到的。

接下来只要比较字典顺序即可。字典顺序可以用compareTo方法进行比较。

```java
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String str[] = d.toArray(new String[d.size()]);
        String maxString = "";
        for(int i = 0;i<str.length;i++){
            if(checkLongestWord(s, str[i], i)){
                if(str[i].length()>maxString.length()){
                    maxString = str[i];
                }else if(str[i].length()==maxString.length()&& str[i].compareTo(maxString) < 0) {
                    maxString = str[i];
                }else{
                    continue;
                }
            }
            
        }

        return  maxString;
        
    }

    public boolean checkLongestWord(String s,String str,int num){
        int index1 =0;
        int index2 =0;
        while(index1<s.length()&&index2<str.length()){
            if(str.charAt(index2)!=s.charAt(index1)){
                index1++;
            }else{
                index1++;
                index2++;
            }
        }
        if(index2==str.length()){
            return true;
        }else{
            return false;
        }

    }
}
```



#### 排序

##### 215. 数组中的第K个最大元素

**说明：**

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**示例1：**

```
输入: [3,2,1,5,6,4] 和 k = 2

输出: 5
```

**示例2：**

```java
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4

输出: 4
```

**补充：**

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。



**超暴力解法**

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);  
        return nums[nums.length-k];
    }
}
```

**快速排序**

快速排序是对整个数组进行排序，选择基准值，前后指针遍历，直到指针相遇，再在指针相遇的索引的值与基准值交换（**基准值我一般取第一位**），这样能保证前后指针相遇的点，前面的值肯定都是小于基准值的，后面的值肯定都是大于基准值。然后把前半部分和后半部分继续迭代，就能得到有序数组。

```javascript
public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length-1);
        //System.out.println(nums[nums.length-k]);
        return nums[nums.length-k];
    }

    public void quickSort(int[] nums, int left, int right) {
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
```



**快速排序的优化：快速选择排序**

纠结很久，其实就是快速排序的优化，多了**选择**这一步。

我们假设在快慢指针相遇的索引为k，判断目前索引是否是我们需要的，譬如我们要找第3大的值，总共有6个数，我们进行升序排序，则第3大的值就是索引为`6 - 3 = 3 `的值。

若进行排序后，`k=3`，那么证明k=3之前所有的值都是比nums[k]大的，之后的值都是比nums[k]小的。此时num[k]就是我们想要的答案。

若进行排序后，`k>3 `  , 答案索引在我们交换基准值的后面，我们就对前面的部分进行排序。

若进行排序后，`k<3 `  , 答案索引在我们交换基准值的前面，我们就对后面的部分进行排序。

```java
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
```



**堆排序**

利用`PriorityQueue`特点，会将插入的值自己进行排序，将最小值放在头部，`peek()`方法可以得到头部元素。

保持`PriorityQueue`只有k个值，可以保证`PriorityQueue`的头部就是第k大的值。

```java
class Solution3{
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int val:nums){
            queue.add(val);
            if(queue.size()>k){
                queue.poll();
            }
        } 
        return queue.peek();
    }
}
```



##### 347. 前 K 个高频元素

**说明：**

给定一个非空的整数数组，返回其中出现频率前 **k** 高的元素。

**示例 1：**

```
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```

**示例 2：**

```
输入: nums = [1], k = 1
输出: [1]
```

**提示：**

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
你可以按任意顺序返回答案。



```java
public List<Integer> topKFrequent2(int[] nums, int k) {
	HashMap<Integer, Integer> count = new HashMap<>();
	for(Integer num : nums ){
        // getOrDefault 有值取值 无值取0
		count.put(num, count.getOrDefault(num, 0)+1);
	}

    // 这个我真的记不清 记住n1-n2是升序就好
    // PriorityQueue 默认就是升序
    // 这里指定了Comparator是为了让他以value值来排序
	PriorityQueue<Integer> heap = 
        new PriorityQueue<>((n1,n2) -> count.get(n1)-count.get(n2));

    for(int n : count.keySet()){
        heap.add(n);
		if (heap.size()>k){
			heap.poll();
		}
	}

	List<Integer> result = new ArrayList<>();
        
	while(!heap.isEmpty()){
		result.add(heap.poll());
	}

    // 因为PriorityQueue头部为最小值 poll取出后的值是[2,1]
    // 使用Colletions工具类的reverse方法进行反转（升序会转降序）
	Collections.reverse(result);

	return result;       
}
```



##### 451. 根据字符出现频率排序

**说明：**

给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

**示例 1:**

```
输入:
"tree"

输出:
"eert"

解释:
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
```

**示例 2:**

```
输入:
"cccaaa"

输出:
"cccaaa"

解释:
'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。
```

**示例 3:**

```
输入:
"Aabb"

输出:
"bbAa"

解释:
此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。
```



我参照上一题写法使用了堆排序，但是效率极低。

```java
class Solution5 {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<chars.length;i++){
            map.put(chars[i], map.getOrDefault(chars[i], 0)+1);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((n1,n2)->map.get(n2)-map.get(n1));
        for (char temp : map.keySet()){
            heap.add(temp);
        }
        String result = "";
        while(!heap.isEmpty()){
            char c = heap.poll();
            for(int i = 0; i < map.get(c);i++){
                result = result + c;
            }
        }

        return result;
    }
}
```



使用桶排序比堆排序要效率高出不少，同时更稳定。

```java
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
```



##### 荷兰国旗问题

荷兰国旗包含三种颜色：红、白、蓝。

有三种颜色的球，算法的目标是将这三种球按颜色顺序正确地排列。它其实是三向切分快速排序的一种变种，在三向切分快速排序中，每次切分都将数组分成三个区间：小于切分元素、等于切分元素、大于切分元素，而该算法是将数组分成三个区间：等于红色、等于白色、等于蓝色。



##### 75. 颜色分类

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

**注意:**
不能使用代码库中的排序函数来解决这道题。

**示例:**

```
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
```

**进阶：**

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？



```java
class Solution {
    public void sortColors(int[] nums) {
        int point0 = 0; // 指向0的指针 肯定是在数组的最前面
        int point2 = nums.length-1; // 指向2的指针 肯定是在数组的最前面
        int curr = 0; //当前遍历的指针 从头开始
        while(curr<=point2){
            // 如果是1 就先不管 可以理解为curr就是为了找0 和 1
            if(nums[curr]==1){
                curr++;
            }
            // curr找到0 就替换指向0的指针的值
            else if(nums[curr]==0){
                nums[curr] = nums[point0];
                nums[point0] = 0;
                point0++;
                curr++;
            }
             // curr找到2 就替换指向2的指针的值
            else if(nums[curr]==2){
                nums[curr] = nums[point2];
                nums[point2] = 2;
                point2--;
            }
            
        }
    }
}
```



#### 贪心思想

求局部最优解

##### 455. 分发饼干

**说明：**

假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

**注意：**

你可以假设胃口值为正。
一个小朋友最多只能拥有一块饼干。

**示例 1：

```
输入: [1,2,3], [1,1]

输出: 1

解释: 
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
```

**示例 2：**

```
输入: [1,2], [1,2,3]

输出: 2

解释: 
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
```



在以上的解法中，我们只在每次分配时饼干时选择一种看起来是当前最优的分配方法，但无法保证这种局部最优的分配方法最后能得到全局最优解。我们假设能得到全局最优解，并使用反证法进行证明，即假设存在一种比我们使用的贪心策略更优的最优策略。如果不存在这种最优策略，表示贪心策略就是最优策略，得到的解也就是全局最优解。

证明：假设在某次选择中，贪心策略选择给当前满足度最小的孩子分配第 m 个饼干，第 m 个饼干为可以满足该孩子的最小饼干。假设存在一种最优策略，可以给该孩子分配第 n 个饼干，并且 m < n。我们可以发现，经过这一轮分配，贪心策略分配后剩下的饼干一定有一个比最优策略来得大。因此在后续的分配中，贪心策略一定能满足更多的孩子。也就是说不存在比贪心策略更优的策略，即贪心策略就是最优策略。

```java
class Solution {
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
```

##### 435. 无重叠区间

给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

**注意：**

1. 可以认为区间的终点总是大于它的起点。
2. 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

**示例 1：**

```
输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。
```

**示例 2：**

```
输入: [ [1,2], [1,2], [1,2] ]

输出: 2

解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
```

**示例 3：**

```
输入: [ [1,2], [2,3] ]

输出: 0

解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
```



什么是贪心算法呢？贪心算法可以认为是动态规划算法的一个特例，相比动态规划，使用贪心算法需要满足更多的条件（贪心选择性质），但是效率比动态规划要高。

比如说一个算法问题使用暴力解法需要指数级时间，如果能使用动态规划消除重叠子问题，就可以降到多项式级别的时间，如果满足贪心选择性质，那么可以进一步降低时间复杂度，达到线性级别的。

什么是贪心选择性质呢，简单说就是：每一步都做出一个局部最优的选择，最终的结果就是全局最优。注意哦，这是一种特殊性质，其实只有一部分问题拥有这个性质。

比如你面前放着 100 张***，你只能拿十张，怎么才能拿最多的面额？显然每次选择剩下***中面值最大的一张，最后你的选择一定是最优的。

然而，大部分问题明显不具有贪心选择性质。比如打牌，对手出对儿三，按照贪心策略，你应该出尽可能小的牌刚好压制住对方，但现实情况我们甚至可能会出王炸。这种情况就不能用贪心算法，而得使用动态规划解决，参见前文动态规划解决博弈问题。

**一、问题概述**

言归正传，本文解决一个很经典的贪心算法问题 Interval Scheduling（区间调度问题）。给你很多形如 [start, end] 的闭区间，请你设计一个算法，算出这些区间中最多有几个互不相交的区间。

```java
int intervalSchedule(int[][] intvs) {}
```


举个例子，intvs = [[1,3], [2,4], [3,6]]，这些区间最多有 2 个区间互不相交，即 [[1,3], [3,6]]，你的算法应该返回 2。注意边界相同并不算相交。

这个问题在生活中的应用广泛，比如你今天有好几个活动，每个活动都可以用区间 [start, end] 表示开始和结束的时间，请问你今天最多能参加几个活动呢？显然你一个人不能同时参加两个活动，所以说这个问题就是求这些时间区间的最大不相交子集。

**二、贪心解法**

这个问题有许多看起来不错的贪心思路，却都不能得到正确答案。比如说：

也许我们可以每次选择可选区间中开始最早的那个？但是可能存在某些区间开始很早，但是很长，使得我们错误地错过了一些短的区间。或者我们每次选择可选区间中最短的那个？或者选择出现冲突最少的那个区间？这些方案都能很容易举出反例，不是正确的方案。

正确的思路其实很简单，可以分为以下三步：

1. 从区间集合 intvs 中选择一个区间 x，这个 x 是在当前所有区间中结束最早的（**end 最小**）。
2. 把所有与 x 区间相交的区间从区间集合 intvs 中删除。
3. 重复步骤 1 和 2，直到 intvs 为空为止。之前选出的那些 x 就是最大不相交子集。

把这个思路实现成算法的话，可以按每个区间的 end 数值升序排序，因为这样处理之后实现步骤 1 和步骤 2 都方便很多:

<img src="https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/678752f150168fc2e53a36d30e589b76ef81a95943c018b01bef6a548bfafeeb-file_1566313617208.gif" style="zoom: 50%;" />

现在来实现算法，对于步骤 1，由于我们预先按照 end 排了序，所以选择 x 是很容易的。关键在于，如何去除与 x 相交的区间，选择下一轮循环的 x 呢？

由于我们事先排了序，不难发现所有与 x 相交的区间必然会与 x 的 end 相交；如果一个区间不想与 x 的 end 相交，它的 start 必须要大于（或等于）x 的 end：

<img src="https://raw.githubusercontent.com/Whalone/JavaBook/main/Java/resources/964afe81a1f6023a4f5a337c143bf0b0ad4df9de9089d35af26c2a5974504336-file_1566313617197.jpg" style="zoom:33%;" />





作者：labuladong
链接：https://leetcode-cn.com/problems/non-overlapping-intervals/solution/tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



```java
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        
        if (intervals.length == 0){
            return 0;
        }

        Arrays.sort(intervals,new Comparator<int []>() {

			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];	}
        });

        // 取最小
        // 刚开始我取0 但是发现不能解决区间<0的情况
        int end = Integer.MIN_VALUE;

        for(int[] interval:intervals ){
            // 目前的end小于遍历中的区间的end 有接触
            if(interval[0]<end){
                // 需要去掉这个区间
                count++;
            }else{
                // 无接触 更新end
                end = interval[1];
            }
        }

        return count;

    }
}
```



##### 121.买卖股票的最佳时机

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```



**示例 2:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```



```java
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
```



##### 122.买卖股票的最佳时机 II

给定一个数组，它的第 *i* 个元素是一支给定股票第 *i* 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

**注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```

**示例 2:**

```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```

**示例 3:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

 

求每个上升区间，总值就是最大利益。

```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i]<prices[i+1]){
                max += prices[i+1]-prices[i];
            }
        }

        return max;
    }
}
```



##### 605. 种花问题

假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

**示例1：**

```
输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
```

**示例2：**

```
输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
```

**注意：**

1. 数组内已种好的花不会违反种植规则。
2. 输入的数组长度范围为 [1, 20000]。
3. **n** 是非负整数，且不会超过输入数组的大小。



处理好边界问题即可，我刚开始的想法是重新初始化一个数组，头尾都是0这样子就不会有边界超出的问题。

但是这样子会占空间又耗时，最好的做法是直接判断，越界问题只要加多一个或的判断即可。

```java
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }
}

```



##### 392. 判断子序列

给定字符串 **s** 和 **t** ，判断 **s** 是否为 **t** 的子序列。

你可以认为 **s** 和 **t** 中仅包含英文小写字母。字符串 **t** 可能会很长（长度 ~= 500,000），而 **s** 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，`"ace"`是`"abcde"`的一个子序列，而`"aec"`不是）。

**示例 1:**
**s** = `"abc"`, **t** = `"ahbgdc"`

返回 `true`.

**示例 2:**
**s** = `"axc"`, **t** = `"ahbgdc"`

返回 `false`.



我的解法：

```java
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
```



大佬的解法，巧妙运用indexOf:

```java
public boolean isSubsequence(String s, String t) {
    int index = -1;
    for (char c : s.toCharArray()) {
        index = t.indexOf(c, index + 1);
        if (index == -1) {
            return false;
        }
    }
    return true;
}
```



##### 538. 吧二叉搜索树转换为累加树

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

**例如：**

```
输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13
```

 

二叉搜索树是一棵空树，或者是具有下列性质的二叉树：

1. 若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；

2. 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；

3. 它的左、右子树也分别为二叉搜索树。

由这样的性质我们可以发现，二叉搜索树的中序遍历是一个单调递增的有序序列。如果我们反序地中序遍历该二叉搜索树，即可得到一个单调递减的有序序列。

**反序中序遍历**

本题中要求我们将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。这样我们只需要反序中序遍历该二叉搜索树，记录过程中的节点值之和，并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。

```java
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root!=null){
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }

        return root;  
    }
}
```



##### 53.最大子序和

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

**示例:**

```
输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

**进阶:**

如果你已经实现复杂度为 O(*n*) 的解法，尝试使用更为精妙的分治法求解。



```java
// 简单的贪心

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for(int num : nums){
            // 判断总和是否大于0
            if(sum>0){
                // 若大于0，则可继续累加
                sum+=num;
            }else{
                // 小于0，则证明前面加的都没有意义
                // 从当前值继续开始
                sum = num;
            }
            // 记录最大值
            ans = Math.max(sum, ans);
        }

        return ans;
    }
}
```



##### 665.非递减数列

给你一个长度为 `n` 的整数数组，请你判断在 **最多** 改变 `1` 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 `i` `(0 <= i <= n-2)`，总满足 `nums[i] <= nums[i + 1]`。

 

**示例 1:**

```
输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
```

**示例 2:**

```
输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
```

 

**说明：**

- `1 <= n <= 10 ^ 4`
- `- 10 ^ 5 <= nums[i] <= 10 ^ 5`



这里稍微难一点，贪心算法的局部最优解需要考虑两种情况才能决定最优解。

如果遍历到的这个数 num[i]，判断到他比前一个数 num[i-1] 小，那么有两种方式，一是让nums[i] = nums[i-1]，另一种就是让 nums[i-1] = nums[i] 。但是使用 nums[i] = nums[i-1] ，可能会使 num[i] 变大，从而可能nums[i+1] < nums[i] 。所以我们使用nums[i-1] = nums[i] 。 让nums[i-1] 变小。但是nums[i-1]变小也许要考虑一种情况，nums[i-1]变小以后，会不会小于nums[i-1-1]，如果出现nums[i-1] 小于nums[i-1-1]，那么就只能让nums[i] = nums[i-1]，让nums[i]变大。

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length && cnt < 2; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            cnt++;
            if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return cnt <= 1;
    }
}
```



### 每日一题

#### LeetCode

##### 29.长度最小的子数组

**说明：**

给定一个含有 **n** 个正整数的数组和一个正整数 **s** ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。

**示例：**

```
输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
```

**进阶:**

如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法



使用快慢指针，慢指针到快指针的总和大于**s**，则让慢指针+1，否则快指针+1

```java
class Solution {
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
```



##### 108. 将有序数组转换为二叉搜索树

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

**示例:**

```
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

```



```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return DFS(nums,0,nums.length-1);

    }

    private TreeNode DFS(int[] nums, int low, int high) {
        if(low>high){
            return null;
        }
        // 每次都取中间节点为根节点
        int mid = low+(high-low)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = DFS(nums, low, mid-1);
        root.right = DFS(nums,mid+1,high);

        return root;
    }
}
```



##### 112. 路经总和

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

**示例:** 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

返回 `true`, 因为存在目标和为 22 的根节点到叶子节点的路径 `5->4->11->2`。



**广度优先遍历**

首先我们可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。

这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。

```java
class Solution112_office {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }   
}
```



**深度优先遍历**

同广度优先遍历

```java
class Solution112_me{
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack1 = new LinkedList<TreeNode>();
        Deque<Integer> stack2 = new LinkedList<Integer>();

        stack1.push(root);
        stack2.push(root.val);

        while(!stack1.isEmpty()){
            TreeNode now = stack1.pop();
            int temp = stack2.pop();
            if(now.left==null&&now.right==null){
                if(temp==sum){
                    return true;
                }else{
                    continue;
                }
            }
            if(now.left!=null){
                stack1.push(now.left);
                stack2.push(temp+now.left.val);
            }
            if(now.right!=null){
                stack1.push(now.right);
                stack2.push(temp+now.right.val);
            }
        }

        return false;
    }
}
```



**递归**

```java
class Solution112_office2{
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}
```



##### 16.11 跳水版

你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为`shorter`，长度较长的木板长度为`longer`。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。

返回的长度需要从小到大排列。

**示例：**

```java
输入：
shorter = 1
longer = 2
k = 3
输出： {3,4,5,6}
```

**提示：**

- 0 < shorter <= longer
- 0 <= k <= 100000



```java
class Solution1611 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k==0){
            return new int[0];
        }
        if(shorter == longer){
            return new int[]{shorter * k};
        }
        int[] result = new int[k+1];
        for(int i = 0; i<=k;i++){
            result[i] = shorter * (k-i) + longer * i;
        }
        
        return result;
    }
}
```



##### 350. 两个数组的交集 II

给定两个数组，编写一个函数来计算它们的交集。

**示例 1:**

```java
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
```

**示例 2:**

```java
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
```

**说明：**

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？



这一题利用哈希表来求解。
为了降低空间复杂度，就用长度较短的数组生成map。
一开始就比较长度，若 num2 的长度比 num1 的短，就换个位置重新调用，这个方式真的很棒。

```java
class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            return intersect(nums2, nums1);
        }

        ArrayList<Integer> result = new ArrayList<>();

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums1){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        for(int num:nums2){
            if(map.containsKey(num)&&map.get(num)!=0){
                result.add(num);
                map.put(num, map.get(num)-1);
            }
        }

        //System.out.println(result);

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
```



##### 841. 钥匙和房间

有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i] [j]由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

最初，除 0 号房间外的其余所有房间都被锁住。

你可以自由地在房间之间来回走动。

如果能进入每个房间返回 true，否则返回 false。

示例 1：

输入: [[1],[2],[3],[]]
输出: true

解释:  
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。

示例 2：

输入：[[1,3],[3,0,1],[2],[0]]
输出：false
解释：我们不能进入 2 号房间。

提示：

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
所有房间中的钥匙数量总计不超过 3000。
通过次数24,583提交次数37,777



我使用了DFS进行遍历，说到DFS就会想到Stack，所以使用了Stack进行存放遍历。

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 使用Deque的stack dfs进行遍历
        Deque stack = new LinkedList<Integer>();
        List<Integer> firstRoom = new ArrayList<Integer>();
        boolean[] visit = new boolean[rooms.size()];
        int num = 0;

        for(int i : rooms.get(0)){
            stack.push(i);
        }
        visit[0] = true;
        num++;


        while(!stack.isEmpty()){
            int room = (int) stack.pop();
            if(!visit[room]){
                num++;
                visit[room] = true;
                for(int j : rooms.get(room)){         
                    stack.push(j);
                }
            }
        }

        return num == rooms.size();

    }
}
```

大佬也是使用的DFS，但是比我简洁的多，只使用了DFS的思想，没有局限于Stack

```java
class Solution {
    boolean[] vis;
    int num;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }
}

```



##### 486. 预测赢家

给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

 

示例 1：

```
输入：[1, 5, 2]
输出：False
解释：一开始，玩家1可以从1和2中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
因此，玩家 1 永远不会成为赢家，返回 False 。
```

示例 2：

```
输入：[1, 5, 233, 7]
输出：True
解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
     最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
```


提示：

1 <= 给定的数组长度 <= 20.
数组里所有分数都为非负数且不会大于 10000000 。
如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。



**动态规划**



![image-20200901145605759](C:\Users\WHL\AppData\Roaming\Typora\typora-user-images\image-20200901145605759.png)

![image-20200901145624343](C:\Users\WHL\AppData\Roaming\Typora\typora-user-images\image-20200901145624343.png)

```java
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
```



##### 94. 二叉树的中序遍历

给定一个二叉树，返回它的中序 遍历。

示例:

```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```


进阶: 递归算法很简单，你可以通过迭代算法完成吗？



```java
// 递归
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        
        inorder(root,list);

        return list;

    }

    public void inorder(TreeNode treeNode,List<Integer> list){
        if(treeNode==null){
            return;
        }

        inorder(treeNode.left,list);
        list.add(treeNode.val);
        inorder(treeNode.right, list);
    }
}
```



**进阶版：Morris中序遍历**

![image-20200914134644125](C:\Users\WHL\AppData\Roaming\Typora\typora-user-images\image-20200914134644125.png)

需要补充一点的是，确定predecessor的时候，除了需要是左子树的中序遍历最后一个节点，还需要确定他的右节点没有指向x节点。

Morris遍历第一次遍历会破坏树的结构，因为他会让predecessor节点指向x节点，再第二遍历的时候才会断开连接。

更为清楚的表达是，确认predecessor节点的时候，就是以原先的树来确定的，而不是改变后的树。

![image-20200914135022820](C:\Users\WHL\AppData\Roaming\Typora\typora-user-images\image-20200914135022820.png)



```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                // 这里predecessor.right != root 就是排除树的结构被破坏 对predecessor的判断造成干扰
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}

```



##### 617.合并二叉树

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则**不为** NULL 的节点将直接作为新二叉树的节点。

**示例 1:**

```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
```

**注意:** 合并必须从两个树的根节点开始。



```java
// 使用一个简单的递归，每次进来判断状态即可
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }
        if(t2==null){
            return t1;
        }

        TreeNode merge = new TreeNode(t1.val+t2.val);
        merge.left = mergeTrees(t1.left, t2.left);
        merge.right= mergeTrees(t1.right, t2.right);

        return merge;
            
        
    } 
}
```



##### 141.环形链表

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 `true` 。 否则，返回 `false` 。

 

**进阶：**

你能用 *O(1)*（即，常量）内存解决此问题吗？

 

**示例 1：**

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" alt="img" style="zoom: 67%;" />

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

**示例 2：**

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" alt="img" style="zoom: 67%;" />

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

**示例 3：**

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" alt="img" style="zoom: 67%;" />

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

 

**提示：**

- 链表中节点的数目范围是 `[0, 104]`
- `-105 <= Node.val <= 105`
- `pos` 为 `-1` 或者链表中的一个 **有效索引** 。



判断一个链表是否为环可以使用快慢指针，在一个环内，快慢指针总会相遇

公式大概是 x%y = 2x%y。

x是满节点，2x是快节点，快节点走的是慢节点的两倍。

当x=y，x%y=0，公式成立。

```java
public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

    	// 小细节 直接比对整个对象 而不是比对val
        while(slow!=fast){
            if(fast!=null&&fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }else{
                return false;
            }
        }

        return true;
}
```



##### 530. 二叉搜索树的最小绝对差

给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

 

**示例：**

```
输入：

   1
    \
     3
    /
   2

输出：
1

解释：
最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
```

 

**提示：**

- 树中至少有 2 个节点。
- 本题与 783 [https://leetcode-cn.com/problems/minimum-distance-](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/)



二叉搜索树的中序遍历是一个升序数组

中序遍历以后，找当前val值与前一val值的最小值即可

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public int pre = -1;
    public int ans = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node){
        if(node==null){
            return;
        }

        dfs(node.left);
        if(pre == -1){
            pre = node.val;
        }else{
            ans = Math.min(ans, node.val-pre);
            pre = node.val;
        }
        dfs(node.right);
    }
}
```





#### 剑指offer

##### 09. 用两个栈实现队列

**说明：**

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail `和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

**示例1：**

```java
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例2：**

```java
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]

```

**提示：**

* 1 <= values <= 10000
* 最多会对 appendTail、deleteHead 进行 10000 次调用



Java中实际上提供了java.util.Stack来实现栈结构，但官方目前已不推荐使用,而是使用 **java.util.Deque **双端队列来实现队列与栈的各种需求。如下图所示 java.util.Deque 的实现子类有 **java.util.LinkedList** 和 **java.util.ArrayDeque** 。顾名思义前者是基于链表。后者基于数据实现的双端队列。两者都可以实现队列和栈。



**Deque与Queue相对应的接口：**

| Queue Method | Equivalent Deque Method | 说明                                 |
| ------------ | ----------------------- | ------------------------------------ |
| add(e)       | addLast(e)              | 向队尾插入元素，失败则抛出异常       |
| offer(e)     | offerLast(e)            | 向队尾插入元素，失败则返回false      |
| remove(e)    | remove(e)               | 获取并删除队首元素，失败则抛出异常   |
| poll()       | pollFirst()             | 获取并删除队首元素，失败则返回null   |
| element()    | getFirst()              | 获取但不删除队首元素，失败则抛出异常 |
| peek()       | peekFirst()             | 获取但不删除队首元素，失败则返回null |



**Deque与Stack相对应的接口：**

| Stack Method | Equivalent Stack Method | 说明                                 |
| ------------ | ----------------------- | ------------------------------------ |
| push(e)      | addFirst(e)             | 向栈顶插入元素，失败则抛出异常       |
| 无           | offerFirst(e)           | 向栈顶插入元素，失败则返回false      |
| pop()        | removeFirst()           | 获取并删除栈顶元素，失败则抛出异常   |
| 无           | pollFirst()             | 获取并删除栈顶元素，失败则返回null   |
| 无           | element()、getFirst()   | 获取但不删除栈顶元素，失败则抛出异常 |
| peek()       | peekFirst()             | 获取但不删除栈顶元素，失败则返回null |



```java
class CQueue {
    // stack1 只负责in的操作
    Deque<Integer> stack1;
    // stack2 只负责out的操作
    Deque<Integer> stack2; 

    public CQueue() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }
    
    public void appendTail(int value) {
        // 添加的时候直接添加进stack即可
        stack1.add(value);
    }
    
    public int deleteHead() {
        // 当进行out操作时候
        // 当stack2为空 把stack1从栈顶一个个pop出来poll进stack2 这样子能保证先进的元素可以先出
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        // stack2不为空 直接pop就好
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            return -1;
        }
    }
}
```



## Java 基础

### 数据类型

#### 基本类型

bit 就是位，也叫比特位，是计算机表示数据最小的单位。
byte 就是字节
1 byte = 8 bit

| 数据类型 | 位   | bit  | byte |
| -------- | ---- | ---- | ---- |
| byte     | 8    | 8    | 1    |
| char     | 16   | 16   | 2    |
| short    | 16   | 16   | 2    |
| int      | 32   | 32   | 4    |
| float    | 32   | 32   | 4    |
| double   | 64   | 64   | 8    |
| long     | 64   | 64   | 8    |
| boolean  | ~    | ~    | ~    |

boolean 只有两个值：true、 false，可以使用1 bit 来存储，但是具体大小没有明确规定。JVM 会在编译时期将 boolean 类型的数据转化为 int ，使用 1 来表示 true ，0 表示 false。 JVM支持 boolean 数组，但是是通过读写byte数组来实现的。



#### 基本类型

基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。

```java
Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
int y = x;         // 拆箱 调用了 X.intValue()
```