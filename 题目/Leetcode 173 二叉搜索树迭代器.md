### 题目定义：

````java
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

````

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/25/bst-tree.png)

````java
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // 返回 3
iterator.next();    // 返回 7
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 9
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 15
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 20
iterator.hasNext(); // 返回 false
````



### 方式一(运用栈)：

````java
class BSTIterator {
    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                stack.add(new TreeNode(cur.val));
                cur = cur.right;
            }else{
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur){
                    pre = pre.right;
                }
                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }else{
                    pre.right = null;
                    stack.add(new TreeNode(cur.val));
                    cur = cur.right;
                }
            }
        }
    }

    public int next() {
        return stack.pop().val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

````



### 方式二()：

````java

````



### 方式三()：

````java

````



### 参考：

> 

