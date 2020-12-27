package com.mark.arith;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {

    public static int[] sortAll(String sortv, int[] arr){
        switch (sortv) {
            case "bubble": //从头开始、相邻元素比较; 每次都交换比较、将最大的数放到最后
                System.out.println("sort data as >>bubble");
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1 - i; j++) {
//                        System.out.println(i + "-" + j + ":" + arr[j]);
                        if (arr[j + 1] < arr[j]) {
                            int temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            case "selection": // 无序区、有序区；无序区和有序区、记录个数+1的有序区和记录个数-1的无序区
                System.out.println("sort data as >> selection");
                for(int i = 0; i < arr.length;i++){
                    int minIndex = i;
                    for (int j = i; j < arr.length; j++){
                        if (arr[j] < arr[minIndex]) //找到最小的数
                            minIndex = j;           //将最小数的索引保存
                    }
                    int temp = arr[minIndex];
                    arr[minIndex] = arr[i];
                    arr[i] = temp;
                }
                return arr;

            default:
                System.out.println("sort data as default");

        }
        return arr;
    }

    /**
     * 选择排序: 表现最稳定的排序算法之一、两个部分
     * @param arr
     * @return
     */
    public static int[] sortSelect2(int[] arr){
        if (arr.length == 0)
            return arr;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i ; j< arr.length; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /**
     * 插入排序:
     * @param arr
     * @return
     */
    public static int[] sortInsertion3(int[] arr){
        if (arr.length == 0 )
            return arr;

        int current;
        for (int i =0; i< arr.length-1; i++) {
            current = arr[i+1];
            int preIndex =i;
            while (preIndex >= 0 && current < arr[preIndex]){
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = current;
        }
        return arr;
    }


    /**
     * 希尔排序: 一种插入排序、简单插入排序经过改进之后的一个更高效的版本：优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * 是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序, 简单微调、无需大量移动操作即可完成整个数组排序
     * @param arr
     * @return
     */
    public static int[] sortShell4(int[] arr){
        int len = arr.length;
        int temp, gap = len/2;
        while(gap > 0){
            for ( int i = gap; i < len; i++){
                temp = arr[i];
                int preIndex = i - gap;

                while(preIndex >= 0 && arr[preIndex] > temp){
                    arr[preIndex+gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex+gap] = temp;
            }
            gap /= 2;
        }
        return arr;
    }


    /**
     * 归并排序: 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多。因为始终都是O(n log n)复杂度。代价是需要额外的存储空间
     * @param arr
     * @return
     */
    public static int[] sortMerge5(int[] arr){
        if (arr.length <=2 ) return  arr;
        int mid = arr.length/2;

        int[] left = Arrays.copyOfRange(arr, 0 ,mid);
        int[] right = Arrays.copyOfRange(arr,mid,arr.length);

        return merge(sortMerge5(left),sortMerge5(right));  // 递归调用
    }
    public static int[] merge(int[] left, int[] right){
        int[] result = new int[left.length+right.length];
        for(int index=0, i= 0, j=0; index < result.length; index++){
            if ( i >= left.length)
                result[index] = right[i++];
            else if ( j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
         }
        return result;
    }

    /**
     * 快速排序方法
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] sortQuick6(int[] arr, int start, int end){
        if (arr.length < 1  || start < 0 || end >= arr.length || start > end) return null;
        int smallIndex = partition(arr,start,end);
        if (smallIndex > start)
            sortQuick6(arr,start,smallIndex-1); // 递归调用
        if (smallIndex < end)
            sortQuick6(arr,smallIndex+1,end);
        return arr;
    }

    /**
     * 快速排序算法-partion
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] arr, int start, int end){
        int pivot = (int) (start+ Math.random()*(end-start+1));
        int smallIndex = start - 1;
        swap(arr,pivot,end);
        for (int i = start; i <= end; i++)
            if (arr[i] <= arr[end]){
                smallIndex++;
                if ( i > smallIndex)
                    swap(arr, i ,smallIndex);
            }
        return smallIndex;
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    static int len;
    /**
     * 堆排序算法
     * @param arr
     * @return
     */
    public static int[] sortHeap7(int[] arr){
        len = arr.length;
        if (len < 1) return arr;
        // 构建一个最大堆
        buildMaxHeap(arr);
        // 循环将首位（最大值）与末位交换，然后再重新调整最大堆
        while(len > 0){
            swap(arr,0,len-1);
            len --;
            adjustHeap(arr,0);
        }
        return arr;

    }

    /**
     * 建立最大堆
     * @param arr
     */
    public static void buildMaxHeap(int[] arr){
        // 从最后一个非叶子节点开始向上构造最大堆
        for (int i= (len/2-1); i >= 0; i--){
            adjustHeap(arr,i);
        }
    }

    /**
     * 调整使之成为最大堆
     * @param arr
     * @param i
     */
    public static void adjustHeap(int[] arr,int i){
        int maxIndex = i;
        // 如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i*2 < len && arr[i*2] > arr[maxIndex])
            maxIndex = i*2;
        // 如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i*2 + 1 < len &&  arr[i*2+1] > arr[maxIndex])
            maxIndex = i*2+1;
        // 如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换位置
        if (maxIndex != i){
            swap(arr,maxIndex,i);
            adjustHeap(arr,maxIndex);
        }
    }

    // ----需要开辟额外存储空间、非比较的方式
    /**
     * 计数排序：将输入的数据值转化为键存储在额外开辟的数组空间中
     * @param arr
     * @return
     */
    public static int[] sortCounting8(int[] arr){
        if (arr.length == 0) return arr;
        int bias,min=arr[0],max=arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max-min+1];
        Arrays.fill(bucket,0);
        for(int i=0; i< arr.length;i++){
            bucket[arr[i]+bias]++;
        }
        int index=0, i=0;
        while (index < arr.length){
            if(bucket[i] != 0){
                arr[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return arr;
    }

    /**
     * 捅排序: 计数排序的升级版
     * @param arr
     * @param bucketSize
     * @return
     */
    public static ArrayList<Integer> SortBucket9(ArrayList<Integer> arr, int bucketSize){
        if (arr == null || arr.size() == 2)
            return arr;
        int max = arr.get(0), min = arr.get(0);
        // 找到最大值和最小值
        for(int i =0; i < arr.size(); i++){
            if (arr.get(i) > max)
                max = arr.get(i);
            if (arr.get(i) < min)
                min = arr.get(i);
        }
        int bucketCount = (max-min)/bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for ( int i = 0; i < bucketCount; i++){
            bucketArr.add(new ArrayList<Integer>());
        }

        for (int i =0; i <  arr.size() ; i++){
            bucketArr.get((arr.get(i)-min)/bucketSize).add(arr.get(i));
        }

        for(int i = 0; i < bucketCount; i++){
            if ( bucketSize == 1){ // 如果带排序数组中有重复数字时
                for ( int j=0; j < bucketArr.size(); j++){
                    resultArr.add(bucketArr.get(i).get(j));
                }
            } else{
                if(bucketCount ==1 )
                    bucketSize--;
                ArrayList<Integer> temp = SortBucket9(bucketArr.get(i),bucketSize); // 递归
                for (int j=0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }

        return resultArr;
    }

    /**
     * 基数排序: 也是一种非比较排序的算法: 分别排序、分别收集
     * @param arr - radix{基数、根值}
     * @return
     */
    public static int[] sortRadix10(int[] arr){
        if (arr == null || arr.length <2)
            return arr;

        // 先找出最大位数
        int max = arr[0];
        for (int i=0; i< arr.length;i++){
            max = Math.max(max, arr[i]);
        }
        int maxDigit = 0 ;
        while(max != 0 ){
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div =1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10){
            for (int j = 10; j < arr.length; j++ ){
                int num = (arr[i] % mod) /div;
                bucketList.get(num).add(arr[j]);
            }
            int index = 0;

            for (int j = 0 ; j < bucketList.size(); j++){
                for (int k = 0; k < bucketList.size(); k++) {
                    arr[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
                }
            }
        }
        return  arr;
    }

    public static void main(String[] args) {

        //Selection sort: 排序规则
        String sort[]  = {"bubble", "selection"};
        //排序数据
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        // 选择排序
//        int[] sortArrv = sortSelect2(arr);
//        System.out.println("sortArrv: "+sortArrv.toString());



    }
}
