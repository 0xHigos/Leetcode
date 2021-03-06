package BigIntegerAlgorithms;

import java.util.ArrayList;
import java.util.Collections;

public class XiaoxueAlgorithm {
    /**
     * 大数相乘 - 模拟乘法手算累加
     */
    public static Integer[] bigNumberMultiply(int[] arr1, int[] arr2){
        ArrayList<Integer> result = new ArrayList<>();  //中间求和的结果

        //arr2 逐位与arr1相乘
        for(int i = arr2.length - 1; i >= 0; i--){
            int carry = 0;
            ArrayList<Integer> singleList = new ArrayList<>();

            //arr2 逐位单次乘法的结果
            for(int j = arr1.length - 1; j >= 0; j--){
                int r = arr2[i] * arr1[j] + carry;
                int digit = r % 10;
                carry = r / 10;

                singleList.add(digit);
            }
            if(carry != 0){
                singleList.add(carry);
            }

            int resultCarry = 0, count = 0;
            int k = 0;
            int l = 0;
            int offset = arr2.length - 1 - i;       //加法的偏移位
            ArrayList<Integer> middleResult = new ArrayList<>();

            //arr2每位乘法的结果与上一轮的求和结果相加，从右向左做加法并进位
            while (k < singleList.size() || l < result.size()) {
                int kv = 0, lv = 0;
                if (k < singleList.size() && count >= offset) {
                    kv = singleList.get(k++);
                }
                if (l < result.size()) {
                    lv = result.get(l++);
                }
                int sum = resultCarry + kv + lv;
                middleResult.add(sum % 10);     //相加结果从右向左（高位到低位）暂时存储，最后需要逆向输出
                resultCarry = sum / 10;
                count++;
            }
            if(resultCarry != 0){
                middleResult.add(resultCarry);
            }
            result.clear();
            result = middleResult;
        }

        Collections.reverse(result);    //逆向输出结果
        return result.toArray(new Integer[result.size()]);
    }

    public static String bigNumberMultiply2(int[] num1, int[] num2) {
        int[] result =new int[num1.length+num2.length];
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                result[i + j + 1] += num1[i] * num2[j];
            }
        }
        for (int i = result.length-1; i >0; i--) {
            if(result[i]>10){
                result[i-1] +=result[i]/10;
                result[i] %=10;
            }
        }
        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if(i==0 &&result[i] ==0)
                continue;
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumberMultiply2(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
    }

}
