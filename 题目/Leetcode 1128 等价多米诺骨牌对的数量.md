### 题目定义：

````java
给你一个由一些多米诺骨牌组成的列表 dominoes。

如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

示例：
输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1
 
    
    
提示：
1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9

````



### 方式一(二元组表示+ 计数)：

````java
/*
* 思路： 直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。这样两个二元对「等价」当且仅当两个二元对完全相同。注意到二元对中的元素均不大于 99，因此我们可以将每一个二元对拼接成一个两位的正整数，即 (x, y) -→10x+y。这样就无需使用哈希表统计元素数量，而直接使用长度为 100100 的数组即可。
*/
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ans = 0; 
        for(int[] dominoe : dominoes){
            int val  = dominoe[0] > dominoe[1] ? dominoe[0] * 10 + dominoe[1] : dominoe[1] * 10 + dominoe[0];
            ans += num[val];
            num[val]++;
        }
        return ans;
    }
}
````



### 方式二(哈希表)：

````java

class Solution {

    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Pair, Integer> freq = new HashMap<>(100);
        for (int[] dominoe : dominoes) {
            Pair key = new Pair(dominoe[0], dominoe[1]);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        int count = 0;
        for (int f : freq.values()) {
            count += (f * (f - 1)) / 2;
        }
        return count;
    }

    private class Pair {

        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return key == pair.key && value == pair.value || key == pair.value && value == pair.key;
        }

        public int hashCode() {
            if (key > value) {
                return value * 10 + key;
            }
            return key * 10 + value;
        }
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-yjlz/

