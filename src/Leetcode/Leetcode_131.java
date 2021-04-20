package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res =new ArrayList<>();
        List<String> tmpList =new ArrayList<>();
        backTracking(res,tmpList,s,0);
        return res;
    }
    public void backTracking(List<List<String>> res,List<String> tmpList,String s,int pos){
        if(pos == s.length()){
            res.add(new ArrayList<>(tmpList));
            return;
        }
        for(int i=pos;i<s.length();i++){
            if(isPalindrome(s,pos,i)){
                tmpList.add(s.substring(pos,i+1));
                backTracking(res,tmpList,s,i+1);
                tmpList.remove(tmpList.size()-1);
            }
        }
    }
    public boolean isPalindrome(String str,int l,int r){
        while(l <r)
            if(str.charAt(l++) !=str.charAt(r--))
                return false;
        return true;
    }

    public static void main(String[] args) {
        Leetcode_131 leetcode =new Leetcode_131();
        System.out.println(leetcode.partition("aab"));
    }
}
