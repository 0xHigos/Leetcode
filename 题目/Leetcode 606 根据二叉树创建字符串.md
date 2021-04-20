# 						[Leetcode 606 根据二叉树创建字符串](https://leetcode-cn.com/problems/construct-string-from-binary-tree/)

### 数据结构定义：

````java
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

示例 1:

输入: 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

输出: "1(2(4))(3)"

解释: 原本将是“1(2(4)())(3())”，
在你省略所有不必要的空括号对之后，
它将是“1(2(4))(3)”。
示例 2:

输入: 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

输出: "1(2()(4))(3)"

解释: 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
````

### 解题思路：

+ 题意要求使用二叉树的前序遍历，所以在递归的情况下，需要判断左右子树来加上额外的括号，判断情况共有**4 **种
  - 1：若当前节点有两个子节点，则在递归时，两个孩子的结果外都需要加上一双括号
  - 2：若当前节点没有任何子节点，那么不需要加上任何括号
  - 3：若当前节点只有左子节点，则递归时，只需要在左子节点上加上一双括号
  - 4：若当前节点只有右子节点，则递归时，需要先加上一双空的括号，然后再对右子节点递归，并加上一双括号
+ 再根据以上的 4 种情况，可以将情况进行再整合

### 递归方式：

````java
class Solution {
    private StringBuilder sb = new StringBuilder();
    public String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        sb.append(t.val);
        //若 左子树不为空，或左子树为空但右子树不为空
        if(t.left != null || (t.left == null && t.right != null)){
           setParentheses(t.left);
        }
        if(t.right != null){
            setParentheses(t.right);
        }
        return sb.toString();
    }
    private void setParentheses(TreeNode node){
            sb.append("(");
            tree2str(node);
            sb.append(")");
    }
   
}
````

### 另一种递归方式：

````java
class Solution {
    public String tree2str(TreeNode t) {
        if(t == null)
            return "";
        if(t.left == null && t.right == null)
            return t.val + "";
        if(t.right == null)
            return t.val+ "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")("+tree2str(t.right) +")";
    }
}
````

### 迭代遍历方式：

````java
class Solution {
    public String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        stack.offer(t);
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(visited.contains(node)){
                sb.append(")");
                stack.poll();
            }else{
                visited.add(node);
                sb.append("(").append(node.val);
                if(node.left == null && node.right != null)
                    sb.append("()");
                if(node.right != null)
                    stack.addFirst(node.right);
                if(node.left != null)
                    stack.addFirst(node.left);
            }
        }
        return sb.substring(1,sb.length() - 1);
    }
}
````

