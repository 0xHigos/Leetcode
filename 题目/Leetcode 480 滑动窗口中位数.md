### 题目定义：

````java
中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；
此时中位数是最中间的两个数的平均数。

例如：

[2,3,4]，中位数是 3
[2,3]，中位数是 (2 + 3) / 2 = 2.5
给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。
窗口中有 k 个数，每次窗口向右移动 1 位。
你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，
并输出由它们组成的数组。

 

示例：

给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

    
窗口位置                      中位数
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7      -1
 1  3 [-1  -3  5] 3  6  7      -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。

````



### 方式一(依次遍历)：

````java
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> res = new ArrayList<>();
        int[] temp = new int[k];
        for(int i = k - 1; i < nums.length; i++){
            for(int j = k - 1,s = i;j >= 0; j --,s --){
                temp[j] = nums[s];
            }
            res.add(getResult(temp));
        }
        return res.stream().mapToDouble(Double::valueOf).toArray();
    }
    double getResult(int[] nums){
        Arrays.sort(nums);
        double result;
        if(nums.length % 2 == 0)
            result = (Double.valueOf(nums[nums.length / 2 - 1]) + Double.valueOf(nums[nums.length / 2])) / 2;
        else
            result =nums[nums.length / 2];
        return result;
    }
}
````



### 方式二(双优先队列 + 延迟删除)：

````java
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; i++) {
            dh.insert(nums[i]);
            dh.erase(nums[i-k]);
            ans[i -k + 1] = dh.getMedian();
        }
        return ans;
    }
}

class DualHeap {
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
	// 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;
	// small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private Map<Integer, Integer> delayed;

    private int k;
	// small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public DualHeap(int k) {
        this.small = new PriorityQueue<>(Comparator.reverseOrder());
        this.large = new PriorityQueue<>(Comparator.naturalOrder());
        this.delayed = new HashMap<>();
        this.smallSize = 0;
        this.largeSize = 0;
        this.k = k;
    }

    public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void insert(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }
        makeBalance();
    }

    public void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (!small.isEmpty() && num <= small.peek()) {
            smallSize--;
            if (num == small.peek())
                prune(small);
        } else {
            largeSize--;
            if (!large.isEmpty() && num == large.peek())
                prune(large);
        }
        makeBalance();
    }

    private void makeBalance() {
        if (smallSize > largeSize + 1 && !small.isEmpty()) {
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize && !large.isEmpty()) {
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            prune(large);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0)
                    delayed.remove(num);
                heap.poll();
            } else {
                break;
            }
        }
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/sliding-window-median/solution/hua-dong-chuang-kou-zhong-wei-shu-by-lee-7ai6/

