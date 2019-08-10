package test;

import java.util.LinkedList;
import java.util.Queue;
public class SortedArrayToBalancedBST  {

    public static Node generateTree(int[] sortArr) {
        if(sortArr ==null)
            return null;
        return generateTree(sortArr, 0, sortArr.length - 1);
    }
/*
*                    5
*                  /   \
*                 2    7
*                / \   /\
*               1  3  6 8
*                   \    \
*                   4     9
* */
    private static Node generateTree(int[] sortArr, int start, int end) {
        if(start >end)
            return null;
        int mid=(start +end)>>>1;
        Node head = new Node(sortArr[mid]);
        head.left = generateTree(sortArr, start, mid - 1);
        head.right = generateTree(sortArr, mid + 1, end);
        return head;
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node node =queue.poll();
            System.out.print(node.data);
            if(node.left!=null)
                queue.offer(node.left);
            if(node.right!=null)
                queue.offer(node.right);
        }
    }



    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        printTree(generateTree(arr));

    }

}
