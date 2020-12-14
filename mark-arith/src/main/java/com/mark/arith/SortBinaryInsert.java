package com.mark.arith;

/**
 * @ClassName BinaryInsert
 * @Description TODO
 * @Author mark1117
 * @Date 2020/12/14 - 9:30 上午
 */
public class SortBinaryInsert {

    /*
    二分法插入排序,基本思想: 只是找合适的插入位置不同，这里是按二分法找到合适的位置，可以减少比较的次数
     */
    private static void BinaryInsert1(int[] a){
        for (int i=0; i<a.length;i++){
            int temp = a[i];  // temp待比较的元素
            int left =0;
            int right = i -1;
            int mid = 0;
            while(left <= right)
            {
                mid = (left +right) /2;
                if (temp < a[mid])
                {
                    right = mid -1;
                }
                else
                {
                    left = mid +1;
                }
            }
            // ----
            for ( int j = i-1; j >= left; j--)
            {
                a[j+1] = a[j];
            }
            // ----
            if (left != i)
            {
                a[left] = temp;
            }
        }
    }
    public static void main(String[] args){
        int[] a = {49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        // 二分插入排序
        SortBinaryInsert.BinaryInsert1(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
}
