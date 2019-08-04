package Leetcode;

public class Leetcode_14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length ==0)
            return  "";
        int minLength =strs[0].length();
        StringBuilder sb =new StringBuilder();
        for(String str:strs){
            if(str.length() <minLength)
                minLength =str.length();
        }
        for(int i=0;i<minLength;i++){
            char c =strs[0].charAt(i);
            for(int j =1;j<strs.length;j++){
                if(c != strs[j].charAt(i))
                    return sb.toString();
            }
            sb.append(c);
        }
        return sb.length()>0?sb.toString():"";
    }

    public static void main(String[] args) {
        Leetcode_14 leetcode =new Leetcode_14();
        System.out.println(leetcode.longestCommonPrefix(new String[]{
                "dog","racecar","car"
        }));
        System.out.println("".indexOf("d"));
    }

    public String longestCommonPrefixs(String[] strs) {
        if(strs.length ==0) return "";
        String pre =strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0)
                pre =pre.substring(0,pre.length()-1);
        }
        return pre;
    }
}
