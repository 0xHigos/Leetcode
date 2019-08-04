package Leetcode.Trie;
/*
Leetcode 208
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // 返回 true
    trie.search("app");     // 返回 false
    trie.startsWith("app"); // 返回 true
    trie.insert("app");
    trie.search("app");     // 返回 true

说明:
    你可以假设所有的输入都是由小写字母 a-z 构成的。
    保证所有输入均为非空字符串。
* */

import java.util.HashMap;

class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];

    public TrieNode() {
    }
}

public class Tries {
    private TrieNode root;

    public Tries() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null)
                return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null)
                return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }

    public void delete(String word) {
        if (!search(word)) {
            return;
        }
        deleteWord(root, word, 0);
    }

    private TrieNode deleteWord(TrieNode root, String word, int i) {
        if(i ==word.length()){
            root =null;
            root.isWord =false;
            return null;
        }else{
            int index = word.charAt(i);
            root.children[index]= deleteWord(root.children[index], word, i + 1);
        }
        //判断前缀是否为一个单词
        if(root.isWord)
            return root;
        // 判断是否还有子树
        for (TrieNode child : root.children) {
            if (child != null) {
                return root;
            }
        }
        return null;
    }

}
