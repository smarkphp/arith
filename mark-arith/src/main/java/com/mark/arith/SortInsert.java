package com.mark.arith;

import java.util.Arrays;

/**
 * @ClassName SortInsert
 * @Description TODO
 * @Author mark1117
 * @Date 2020/12/14 - 8:22 上午
 */

public class SortInsert {

    /*
     * 插入排序:
     * 思想: 每步将一个待排序的记录，按其顺序码大小插入到前面已经排序好的字序列的合适位置，直到全部插入顺序完为止
     * 关键问题: 在前面已经排序好的序列中找到合适的位置
     * 方法{1、直接插入排序， 2、二分插入排序，3、希尔排序}
     *直接插入排序，基本思想: 每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的位置(从后向前找到合适的位置)，直到全部插入顺序完为止
    */
    private static void DirectInsertSort1(){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        System.out.println("排序之前：");
        for (int i=0; i < a.length; i++ ){
            System.out.print(a[i]+" ");
        }

        // 直接插入排序
        for (int i=0; i < a.length; i++ ) {
            // 待插入元素
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                // 将大于temp的往后移一位: {前面的每一位数 和 待插入的数据进行比较,如果前面的数>待插入的元素，则把前面的数放到后面}
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;  //????
        }
        System.out.print("\n");
        System.out.print("排序之后:\n");
        for(int i =0; i< a.length; i++){
            System.out.print(a[i]+" ");
        }

    }

    /*
        插入排序的第二种方法
     */
    private static void DirectInsertSort2(int[] a){
        int i, j ,insertNote; // 要插入的数据
        for (i = 1; i < a.length; i++) { // 从数组的第二个元素开始循环将数组中的元素插入
            insertNote = a[i]; // 设置数组中第二个元素为第一次循环要插入的元素
            j = i - 1;
            while (j>=0 && insertNote < a[j]) {
                a[j+1] = a[j];  //如果要插入的元素小于第j个元素，就将第j个元素向后移动
                j--;
            }
            a[j+1] = insertNote; // 直到插入的元素不小于第j个元素，将insertNote插入到数组中
        }
    }

    public static void main(String[] args){
        System.out.println("main function entry>>");
//        SortInsert.DirectInsertSort1();

        int a[] = {38,65,97,76,13,27,49};
        System.out.println(Arrays.toString(a));
        SortInsert.DirectInsertSort2(a);
        System.out.println(Arrays.toString(a));

    }

}
