package Leetcode;

/*
* Add Binary
* */
public class Leetcode_67 {
    public String addBinary(String a, String b) {
        StringBuilder sb =new StringBuilder();
        int i=a.length()-1,j =b.length()-1,carry=0;
        while (i>=0 || j>=0){
            int sum =carry;
            if(i>=0) sum+=a.charAt(i--)-'0';
            if(j>=0) sum+=b.charAt(j--)-'0';
            sb.append(sum  % 2);
            carry =sum >>>1;
        }
        if(carry !=0)
            sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Leetcode_67 leetcode =new Leetcode_67();
        String s = leetcode.addBinary("11", "1");
        System.out.println(s);
    }
}
