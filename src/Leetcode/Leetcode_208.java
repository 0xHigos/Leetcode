package Leetcode;

class TrieNode{
    public boolean isWord;
    public TrieNode[] children =new TrieNode[26];
    public TrieNode(){
    }

}
public class Leetcode_208 {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Leetcode_208() {
        root =new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word ==null || word.length() ==0){
            return;
        }
        for(int i=0;i<word.length();i++){
            char c =word.charAt(i);
            if(root.children[c-'a'] ==null)
                root.children[c-'a'] =new TrieNode();
            root =root.children[c-'a'];
        }
        root.isWord=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word.length() ==0)
            return false;
        for(int i=0;i<word.length();i++){
            char c =word.charAt(i);
            if(root.children[c-'a'] ==null)
                return false;
            root =root.children[c-'a'];
        }
        return root.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix.length() ==0)
            return false;
        for(int i=0;i<prefix.length();i++){
            char c =prefix.charAt(i);
            if(root.children[c-'a'] ==null)
                return false;
            root =root.children[c-'a'];
        }
        return true;
    }
    public TrieNode delete(String word){
        if(!search(word))
            return null;
        return delete(root,word,0);
    }
    private TrieNode delete(TrieNode root,String word,int i){
        if(i==word.length())
            root.isWord=false;
        else{
            char c =word.charAt(i);
            root.children[c-'a'] =delete(root.children[c-'a'],word,i+1);
        }
        if(root.isWord)
            return root;
        for(TrieNode node:root.children)
            if(node !=null)
                return root;
        return null;
    }

    public static void main(String[] args) {
        Leetcode_208 leetcode = new Leetcode_208();
        leetcode.insert("apple");
        leetcode.search("apple");   // 返回 true
        leetcode.search("app");     // 返回 false
        leetcode.startsWith("app"); // 返回 true
        leetcode.insert("app");
        leetcode.search("app");     // 返回 true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
