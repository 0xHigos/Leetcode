package Leetcode;

public class Leetcode_88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int length=m+n-1,nums1Length=m-1,nums2Length=n-1;
        while(nums1Length >=0 && nums2Length >=0){
            if(nums1[nums1Length]>=nums2[nums2Length])
                nums1[length--]=nums1[nums1Length--];
            else
                nums1[length--]=nums2[nums2Length--];
        }
        while(nums2Length >=0)
            nums1[length--]=nums2[nums2Length--];
    }

    public static void main(String[] args) {
         int[] n1={
                6,7,8,0,0,0
        };
         int[] n2={
                1,2,4
        };
        merge(n1, 3, n2, 3);
        for (int i : n1) {
            System.out.print(i+"   ");
        }
    }

    /*public  static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] numResult=new int[m+n];
        int i=0,j=0,k=0;
        while(i<m && j<n){
            if(nums1[i]<nums2[j])
                numResult[k++]=nums1[i++];
            else
                numResult[k++]=nums2[j++];

        }
        while(i<m){
            numResult[k++]=nums1[i++];
        }
        while(j<n){
            numResult[k++]=nums1[j++];
        }
        nums1 =numResult;
    }*/
}
