package Leetcode;

import java.util.ArrayList;
import java.util.List;

/*
    * 给定一个二维网格 board 和一个字典中的单词列表 words，
    * 找出所有同时在二维网格和字典中出现的单词。
    * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
    * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
    * 同一个单元格内的字母在一个单词中不允许被重复使用。
    * 示例:
    * 输入:
    * words = ["oath","pea","eat","rain"] and board =
    * [
        ['o','a','a','n'],
        ['e','t','a','e'],
        ['i','h','k','r'],
        ['i','f','l','v']
       ]
       输出: ["eat","oath"]
       说明:
       你可以假设所有输入都由小写字母 a-z 组成。
     提示:
        你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
        如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
        * 什么样的数据结构可以有效地执行这样的操作？
        * 散列表是否可行？为什么？ 前缀树如何？
        * 如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
        * */
class TrieN{
    TrieN[] children=new TrieN[26];
    String word; //存储最终的字符串
}
public class Leetcode_212 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieN node = buildTrieNode(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board,res,i,j,node);
            }
        }
        return res;
    }

    private void dfs(char[][] board, List<String> res, int i, int j, TrieN node) {
        char c =board[i][j];
        if(c=='#' || node.children[c-'a'] == null)
            return;
        node =node.children[c-'a'];
        if(node.word!=null){  // 找到了一个字符串
            res.add(node.word);
            node.word=null;  //去重
        }
        board[i][j] ='#';

        if(i>0) dfs(board, res, i - 1, j, node);
        if(j>0) dfs(board, res, i, j - 1, node);
        if(i<board.length-1) dfs(board, res, i + 1, j, node);
        if(j<board[0].length-1) dfs(board, res, i, j + 1, node);

        board[i][j] =c;
        /*
        char c =board[i][j];
        board[i][j] ='#';
        board[i][j] =c;
        是为了标记已经遍历的节点
        * */
    }


    //创建TireNode
    public TrieN buildTrieNode(String[] words) {
        TrieN root =new TrieN();
        for (String word : words) {
            TrieN ws =root;
            for (char c : word.toCharArray()) {
                if(ws.children[c-'a'] ==null)
                    ws.children[c-'a'] =new TrieN();
                ws=ws.children[c-'a'];
            }
            ws.word=word;
        }
        return root;
    }

    public static void main(String[] args) {
        Leetcode_212 leetcode_212 =new Leetcode_212();
        char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        System.out.println(leetcode_212.findWords(board, new String[]{"oath", "pea", "eat", "rain"}));
    }
}
