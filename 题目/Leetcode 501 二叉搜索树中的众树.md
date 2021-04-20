# 						[Leetcode 501 二叉搜索树中的众树 ](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/)

### 数据结构定义：

````java
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

   1
    \
     2
    /
   2
返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
       
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
       

````

### 运用缓存(未考虑二叉搜索树的特性)：

````java
class Solution {
    private Map<Integer, Integer> cache = new HashMap<>();

    public int[] findMode(TreeNode root) {
        getAllCurrentTime(root);
        return getMaxValue();
    }

    /*
    * 获取所有节点的出现次数
    */
    private void getAllCurrentTime(TreeNode root) {
        if (root == null) {
            return;
        }
        cache.put(root.val, cache.getOrDefault(root.val, 0) + 1);
        getAllCurrentTime(root.left);
        getAllCurrentTime(root.right);
    }
    
    /*
    * 获取出现次数最大的节点，并返回
    */
    private int[] getMaxValue(){
        List<Integer> temp = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer,Integer> entry : cache.entrySet()) {
            if(entry.getValue() > max)
                max = entry.getValue();
        }
        for (Map.Entry<Integer,Integer> entry : cache.entrySet()) {
            if(entry.getValue() == max)
                temp.add(entry.getKey());
        }
        Integer[] array = temp.toArray(new Integer[temp.size()]);
        return  Arrays.stream(array).mapToInt(Integer::valueOf).toArray();
    }
}
````

### 中序遍历方式：

````java
class Solution {
    private Map<Integer, Integer> inOrderMap = new HashMap<>();

    /*
    * 获取最终的数组
    */
    public int[] findMode(TreeNode root) {
        int max = Integer.MIN_VALUE;
        List<Integer> tempList = new ArrayList<>();
        getInOrderTraveral(root);
        for (Map.Entry<Integer, Integer> entry : inOrderMap.entrySet()) {
            if(entry.getValue() > max)
                max = entry.getValue();
        }
        for (Map.Entry<Integer, Integer> entry : inOrderMap.entrySet()) {
            if(entry.getValue() == max)
                ((ArrayList) tempList).add(entry.getKey());
        }
        return tempList.stream().mapToInt(Integer::valueOf).toArray();

    }

    /*
    * 中序遍历获取所有出现次数
    */
    private void getInOrderTraveral(TreeNode root) {
        if (root == null)
            return;
        getInOrderTraveral(root.left);
        inOrderMap.put(root.val, inOrderMap.getOrDefault(root.val, 0) + 1);
        getInOrderTraveral(root.right);
    }
}

````

### 中序遍历的优化版：

````java
class Solution {
    /* 定义存储出现次数最多的临时list*/
    private List<Integer> tempList = new ArrayList<>();
    /*
    * 定义一次遍历时，存储当前状态的参数
    * currentNum: 记录当前的数字
    * count: 记录当前数字重复的次数
    * maxCount: 记录已经扫描过的数字中最大的出现次数
    */
    private int currentNum,count,maxCount;
    public int[] findMode(TreeNode root) {
        inOrderTraveral(root);
        return tempList.stream().mapToInt(Integer::valueOf).toArray();
    }
    private void inOrderTraveral(TreeNode root){
        if(root == null)
            return;
        inOrderTraveral(root.left);
        update(root);
        inOrderTraveral(root.right);
    }
    private void update(TreeNode root){
        //若当前的数字和上一次遍历的数字是一样的，则 count +1
        if(root.val == currentNum){
            count += 1;
        }else { //否则currentNum 记录当前的数字，count 重新记为 1
            count = 1;
            currentNum = root.val;
        }
        //若当前遍历出现的次数和已经扫描过的数字中最大的出现次数相同，则放入临时list中
        if(count == maxCount){
            tempList.add(root.val);
        }else if(count > maxCount){ //若当前出现的次数大于最大出现次数，则清空list放入当前的节点值
            tempList.clear();
            maxCount = count;
            tempList.add(root.val);
        }
    }

}

````

### Morris中序遍历：

````java
class Solution {
    /*
    * morris 可参考 https://www.cnblogs.com/CodingXu-jie/p/13531475.html
    */
    private List<Integer> tempList = new ArrayList<>();
    private int count,currentNum,maxCount;
    public int[] findMode(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        TreeNode cur = root,pre = null;
        while(cur != null){
            if(cur.left == null){
                update(cur);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while(pre.right != null && pre.right != cur)
                pre = pre.right;
            if(pre.right == null){
                pre.right = cur;
                cur = cur.left;
            }else{
                pre.right = null;
                update(cur);
                cur = cur.right;
            }
        }
        return tempList.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void update(TreeNode root){
        if(root.val == currentNum){
            count += 1;
        }else {
            count = 1;
            currentNum = root.val;
        }
        if(count == maxCount){
            tempList.add(root.val);
        }else if(count > maxCount){
            tempList.clear();
            maxCount = count;
            tempList.add(root.val);
        }
    }

}
````



### 参考：

> https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-zhong-shu-by-leetcode-/