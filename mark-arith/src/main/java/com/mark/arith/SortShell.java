package com.mark.arith;

/**
 * @ClassName SortShell
 * @Description TODO
 * @Author mark1117
 * @Date 2020/12/14 - 9:52 上午
 */

/* 希尔排序: 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。所有距离为d1的倍数的记录放在同一个组中。
            现在各组内进行直接插入排序；取第二个增量d2
 */
public class SortShell {

    private static void shellSort(int[] a) {

        System.out.println("排序之前: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        // 希尔排序
        int d = a.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i = i + d) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }

            if (d == 1) {
                break;
            }

            System.out.println();
            System.out.println("排序之后: ");
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i] + " ");
            }
        }

    }

    public static void main(String[] args){
        int[] b = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        SortShell.shellSort(b);
    }
}
