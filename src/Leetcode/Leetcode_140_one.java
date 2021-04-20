package Leetcode;

import java.util.*;

public class Leetcode_140_one {
    public  List<String> wordBreak1(String s, List<String> dict) {
        HashMap<String, List<String>> map = new HashMap<>();
        if(s==null || s.length() ==0 || dict ==null)
            return null;
        return DFS(s, dict, map);
    }

    private  List<String> DFS(String s, List<String> dict, HashMap<String, List<String>> map) {
        if(map.containsKey(s))
            return map.get(s);
        List<String> list = new ArrayList<>();
        int len =s.length();
        if(len ==0)
            list.add("");
        else {
            for (int i = 1; i <= len; i++) {
                String sub = s.substring(0, i);
                if (dict.contains(sub)) {
                    List<String> listRight = DFS(s.substring(i, len), dict, map);
                    for (String s1 : listRight) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(sub);
                        if (i != 0 && i != len) {
                            sb.append(" ");
                        }
                        sb.append(s1);
                        list.add(sb.toString());
                    }
                }
            }
        }
        map.put(s, list);
        return list;
    }

    public static void main(String[] args) {
        Leetcode_140_one leetcode_140_one =new Leetcode_140_one();
        /*Set<String> dict = new HashSet<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        System.out.println(wordBreak1("catsanddog", dict));*/
        List<String> dict = new ArrayList<>();
        dict.add("apple");
        dict.add("pen");
        dict.add("applepen");
        dict.add("pine");
        dict.add("pineapple");
        System.out.println(leetcode_140_one.wordBreak1("pineapplepenapple", dict));
        /*List<String> dict = new ArrayList<>();
        dict.add("cats");
        dict.add("dog");
        dict.add("sand");
        dict.add("and");
        dict.add("cat");
        System.out.println(leetcode_140_one.wordBreak1("catsandog", dict));*/
    }

}
