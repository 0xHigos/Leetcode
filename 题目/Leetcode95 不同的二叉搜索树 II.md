

### 题目定义：

````java
给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。

 

示例：

输入：3
输出：
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释：
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

提示：

0 <= n <= 8


````



### 递归方式：

````java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)
            return new LinkedList<TreeNode>();
        return generateTrees(1,n);
    }
    private List<TreeNode> generateTrees(int start,int end){
        List<TreeNode> trees = new LinkedList<>();
        if(start > end){
            trees.add(null);
            return trees;
        }
        for(int i = start; i <= end; i++){
            List<TreeNode> leftTree = generateTrees(start,i-1);
            List<TreeNode> rightTree = generateTrees(i+1, end);
            for(TreeNode leftNode : leftTree)
                for(TreeNode rightNode : rightTree){
                    TreeNode cur = new TreeNode(i);
                    cur.left = leftNode;
                    cur.right = rightNode;
                    trees.add(cur);
                }
        }
        return trees;
    }
}
````



### 动态规划方式：

````java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList [n+1];
        dp[0] =new ArrayList<>();
         if(n == 0)
            return dp[0];
        dp[0].add(null);
        for(int i = 1; i <= n; i++){
            dp[i] = new ArrayList<>();
            for(int j = 1;j <= i; j++){
                int left = j - 1;
                int right = i - j;
                for(TreeNode leftNode : dp[left]){
                    for(TreeNode rightNode : dp[right]){
                        TreeNode cur = new TreeNode(j);
                        cur.left = leftNode;
                        cur.right = clone(rightNode,j);
                        dp[i].add(cur);
                    }
                }
            }
        }
        return dp[n];
    }
    private TreeNode clone(TreeNode root,int offset){
        if(root == null)
            return null;
        TreeNode cur = new TreeNode(root.val + offset);
        cur.left = clone(root.left,offset);
        cur.right = clone(root.right,offset);
        return cur;
    }
}
````



### 说明：

大多数递归都可以用动态规划的思想重写，这道也不例外。从底部往上走，参考[这里](https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31493/Java-Solution-with-DP)。

举个例子，n = 3

````java
数字个数是 0 的所有解
null
数字个数是 1 的所有解
1
2
3
数字个数是 2 的所有解，我们只需要考虑连续数字
[ 1 2 ]
  1  
   \    
    2
   2
  /
 1
    
[ 2 3 ]
  2  
   \    
    3
   3
  /
 2
如果求 3 个数字的所有情况。
[ 1 2 3 ]
利用解法二递归的思路，就是分别把每个数字作为根节点，然后考虑左子树和右子树的可能
1 作为根节点，左子树是 [] 的所有可能，右子树是 [ 2 3 ] 的所有可能，利用之前求出的结果进行组合。
    1
  /   \
null   2
        \
         3

    1
  /   \
null   3
      /
     2 
    
2 作为根节点，左子树是 [ 1 ] 的所有可能，右子树是  [ 3 ] 的所有可能，利用之前求出的结果进行组合。
    2
  /   \
 1     3

3 作为根节点，左子树是 [ 1 2 ] 的所有可能，右子树是 [] 的所有可能，利用之前求出的结果进行组合。
     3
   /   \
  1   null
   \
    2

      3
    /   \
   2   null 
  /
 1
````

然后利用上边的思路基本上可以写代码了，就是求出长度为 1 的所有可能，长度为 2 的所有可能 ... 直到 n。

但是我们注意到，求长度为 2 的所有可能的时候，我们需要求 [ 1 2 ] 的所有可能，[ 2 3 ] 的所有可能，这只是 n = 3 的情况。如果 n 等于 100，我们需要求的更多了 [ 1 2 ] ， [ 2 3 ] ， [ 3 4 ] ... [ 99 100 ] 太多了。能不能优化呢？

仔细观察，我们可以发现长度是为 2 的所有可能其实只有两种结构。

````java
  x  
 /    
y

y
 \
  x
````

看之前推导的 [ 1 2 ] 和 [ 2 3 ]，只是数字不一样，结构是完全一样的。

````java
[ 1 2 ]
  1  
   \    
    2
   2
  /
 1
    
[ 2 3 ]
  2  
   \    
    3
   3
  /
 2

````

所以我们 n = 100 的时候，求长度是 2 的所有情况的时候，我们没必要把 [ 1 2 ] ， [ 2 3 ] ， [ 3 4 ] ... [ 99 100 ] 所有的情况都求出来，只需要求出 [ 1 2 ] 的所有情况即可。

推广到任意长度 len，我们其实只需要求 [ 1 2 ... len ] 的所有情况就可以了。下一个问题随之而来，这些 [ 2 3 ] ， [ 3 4 ] ... [ 99 100 ] 没求的怎么办呢？

举个例子。n = 100，此时我们求把 98 作为根节点的所有情况，根据之前的推导，我们需要长度是 97 的 [ 1 2 ... 97 ] 的所有情况作为左子树，长度是 2 的 [ 99 100 ] 的所有情况作为右子树。

[ 1 2 ... 97 ] 的所有情况刚好是 [ 1 2 ... len ] ，已经求出来了。但 [ 99 100 ] 怎么办呢？我们只求了 [ 1 2 ] 的所有情况。答案很明显了，在 [ 1 2 ] 的所有情况每个数字加一个偏差 98，即加上根节点的值就可以了。

````java
[ 1 2 ]
  1  
   \    
    2
   2
  /
 1
    
[ 99 100 ]
  1 + 98
   \    
    2 + 98
   2 + 98
  /
 1 + 98

即
  99  
   \    
    100
   100
  /
 99

````

所以我们需要一个函数，实现树的复制并且加上偏差。

````java
private TreeNode clone(TreeNode n, int offset) {
    if (n == null) {
        return null;
    }
    TreeNode node = new TreeNode(n.val + offset);
    node.left = clone(n.left, offset);
    node.right = clone(n.right, offset);
    return node;
}
````


通过上边的所有分析，代码可以写了，总体思想就是求长度为 2 的所有情况，求长度为 3 的所有情况直到 n。而求长度为 len 的所有情况，我们只需要求出一个代表 [ 1 2 ... len ] 的所有情况，其他的话加上一个偏差，加上当前根节点即可。



### 动态规划方式2：

````java
public List<TreeNode> generateTrees(int n) {
    List<TreeNode> pre = new ArrayList<TreeNode>();
    if (n == 0) {
        return pre;
    }
    pre.add(null);
    //每次增加一个数字
    for (int i = 1; i <= n; i++) {
        List<TreeNode> cur = new ArrayList<TreeNode>();
        //遍历之前的所有解
        for (TreeNode root : pre) {
            //插入到根节点
            TreeNode insert = new TreeNode(i);
            insert.left = root;
            cur.add(insert);
            //插入到右孩子，右孩子的右孩子...最多找 n 次孩子
            for (int j = 0; j <= n; j++) {
                TreeNode root_copy = treeCopy(root); //复制当前的树
                TreeNode right = root_copy; //找到要插入右孩子的位置
                int k = 0;
                //遍历 j 次找右孩子
                for (; k < j; k++) {
                    if (right == null)
                        break;
                    right = right.right;
                }
                //到达 null 提前结束
                if (right == null)
                    break;
                //保存当前右孩子的位置的子树作为插入节点的左孩子
                TreeNode rightTree = right.right;
                insert = new TreeNode(i);
                right.right = insert; //右孩子是插入的节点
                insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                //加入结果中
                cur.add(root_copy);
            }
        }
        pre = cur;

    }
    return pre;
}


private TreeNode treeCopy(TreeNode root) {
    if (root == null) {
        return root;
    }
    TreeNode newRoot = new TreeNode(root.val);
    newRoot.left = treeCopy(root.left);
    newRoot.right = treeCopy(root.right);
    return newRoot;
}

````

### 说明：

解法二的动态规划完全是模仿了解法二递归的思想，这里再介绍另一种思想，会更好理解一些。

````java
 1
  \
   2

考虑 [ 1 2 3 ] 的所有解
    3
   /
  2
 /
1

   2
  / \
 1   3
    
     3
    /
   1
    \
     2
       
   1
     \
      3
     /
    2
    
  1
    \
     2
      \
       3

````

仔细分析，可以发现一个规律。首先我们每次新增加的数字大于之前的所有数字，所以新增加的数字出现的位置只可能是根节点或者是根节点的右孩子，右孩子的右孩子，右孩子的右孩子的右孩子等等，总之一定是右边。其次，新数字所在位置原来的子树，改为当前插入数字的左孩子即可，因为插入数字是最大的。

````java
对于下边的解 
  2
 /
1

然后增加 3
1.把 3 放到根节点
    3
   /
  2
 /
1

2. 把 3 放到根节点的右孩子
   2
  / \
 1   3
 
对于下边的解
 1
  \
   2

然后增加 3
1.把 3 放到根节点
     3
    /
   1
    \
     2
       
2. 把 3 放到根节点的右孩子，原来的子树作为 3 的左孩子       
      1
        \
         3
        /
      2

3. 把 3 放到根节点的右孩子的右孩子
  1
    \
     2
      \
       3

````

以上就是根据 [ 1 2 ] 推出 [ 1 2 3 ] 的所有过程，可以写代码了。由于求当前的所有解只需要上一次的解，所有我们只需要两个 list，pre 保存上一次的所有解， cur 计算当前的所有解。



### 参考：

> https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
>
> https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/

