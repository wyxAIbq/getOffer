package com.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 八大排序算法java实现
 * Created by 王彦鑫 on 2018/12/26
 */
public class SortTest {

    public static void main(String[] args) {
        int[] a = {73,22, 93, 43, 55, 14, 28, 65, 39, 81};
        //insertSort(a);
        bubbleSortBetter2(a);
        //selectSort(a);
        //radixSort(a,100);
//        int[] sortA = mergesSort(a,0,a.length-1);
//        print(sortA,sortA.length);
    }

    /**
     * 插入排序
     *
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     * @param numbers  待排序数组
     */
    static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0 ;
        int j =  0;

        for (int i = 1; i < size; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && temp < numbers[j-1]; j--) {
                numbers[j] = numbers[j-1];
            }
            numbers[j] = temp;
            print(numbers,size);
        }

    }

    /**希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值
     * 移到后面，最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强
     * 版的插入排序
     * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
     * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
     * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
     * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
     *实现数组从大到小排
     */
    static void shellSort(int[] data) {
        int j = 0;
        int temp = 0;
        int size = data.length;
        //每次将步长缩短为原来的一半
        for (int increment = size/2; increment > 0; increment/=2){
            for (int i = increment; i < size; i++) {
                temp = data[i];
                for (j = i;j >= increment && temp < data[j-increment] ;j-= increment){
                    data[j] = data[j-increment];
                }
                data[j] = temp;
            }
            print(data,size);
        }
    }

     /**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     * @param data
     */
    static void selectSort(int[] data) {
        int temp = 0;
        int size = data.length;
        for (int i = 0; i < size; i++) {
            int k = i;//k位置存最小的数，先假设i位置的数最小
            for (int j = size-1;j > i ;j--){
                if(data[j] < data[k]){
                    k = j;
                }
            }
            temp = data[i];
            data[i] = data[k];
            data[k] = temp;
            print(data,size);
        }
    }



    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param numbers 需要排序的整型数组
     */
    static void bubbleSort(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        //i控制趟数
        for(int i = 0 ; i < size-1; i ++) {
            for(int j = 0 ;j < size-1-i ; j++) {
                //交换两数位置
                if(numbers[j] > numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
            print(numbers,size);
        }
    }

    static void bubbleSortBetter(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        int i = size-1;  //初始时，最后位置保持不变
        while (i > 0){
            int pos = 0;  //每趟开始时，无记录交换
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j+1]){
                    pos = j;
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
            i = pos;
            print(numbers,size);
        }
    }

    /**
     * 双向冒泡排序，一次排序
     * @param numbers
     */
    static void bubbleSortBetter2(int[] numbers) {
        int low = 0;
        int high = numbers.length-1;
        int temp = 0;
        int j = 0;
        while (low < high){
            for (j = low; j < high; j++) {
                if(numbers[j] > numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
            --high;
            for (j = high; j > low; j--) {
                if(numbers[j] < numbers[j-1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j-1];
                    numbers[j-1] = temp;
                }
            }
            ++low;
            print(numbers,numbers.length);
        }
    }

    static int getMiddle(int[] data,int low,int high){
        int temp = data[low];
        while (low < high) {
            while (low < high && temp < data[high]) {
                high--;
            }
            data[low] = data[high];
            while (low < high && temp > data[low]) {
                low++;
            }
            data[high] = data[low];
        }
        data[low] = temp;
        return low;
    }

    static void quickSort(int[] data,int low,int high){
        if (low < high){
            int middle = getMiddle(data,low,high);
            quickSort(data,low,middle-1);
            quickSort(data,middle+1,high);
            print(data,data.length);
        }
    }

    /**
     * 归并排序
     * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。
     * 然后再把有序子序列合并为整体有序序列
     * 时间复杂度为O(nlogn)
     * 稳定排序方式
     * @param nums 待排序数组
     * @return 输出有序数组
     */
    static int[] mergesSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergesSort(nums, low, mid);
            // 右边
            mergesSort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    /**
     * 将数组中low到high位置的数进行排序
     * @param nums 待排序数组
     * @param low 待排的开始位置
     * @param mid 待排中间位置
     * @param high 待排结束位置
     */
    static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }

    /**
     * 基数排序，桶排序，LSD低位优先
     * @param array
     * @param d
     */
    static void radixSort(int[] array,int d) {
        int size = array.length;
        int n = 1; //代表位数对应的数，1,10,100...
        int[][] bucket = new int[10][size]; //排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[size]; //用于保存每个桶里有多少数字
        int k = 0; //保存每一位排序后的结果用于下一位的排序输入
        while (n < d){ //决定几趟
            //将array里的每个数字放在相应的桶中
            for (int nums:array) {
                int digit = (nums/n)%10;
                bucket[digit][order[digit]++] = nums;
            }
            //将前一个循环生成的桶中的数据覆盖到原数组中用于保存这一位的排序结果
            for (int i = 0; i < size; i++) {
                //这个桶里有数据，则从上到下遍历这个桶并将数据保存到原数组中
                if (order[i] != 0){
                    for (int j = 0; j < order[i]; j++){
                        array[k++] = bucket[i][j];
                    }
                }
                order[i] = 0; //桶里的计数器置零，用于下一次位排序
            }
            n*=10; //LSD，按个十百千位顺序，每次乘以10
            k = 0; //将k置0，用于下一轮保存位排序结果
            print(array,size);
        }
    }

    static void print(int[] a,int n){
        for (int j = 0; j < n; j++) {
            System.out.print(a[j]+" ");
        }
        System.out.println(" ");
    }

}

class HeapSort {
    public static void main(String[] args) {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64};
        int arrayLength=a.length;
        //循环建堆
        for(int i=0;i<arrayLength-1;i++){
            //建堆
            buildMaxHeap(a,arrayLength-1-i);
            //交换堆顶和最后一个元素
            swap(a,0,arrayLength-1-i);
            System.out.println(Arrays.toString(a));
        }
    }
    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex){
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex]<data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k]<data[biggerIndex]){
                    //交换他们
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                }else{
                    break;
                }
            }
        }
    }
    //交换
    private static void swap(int[] data, int i, int j) {
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
}

class HeapSort_2{
    public static void main(String[] args) {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64};
        int arrayLength=a.length;
        for(int j = 0;j < arrayLength; j++){
            heapInsert(a,j);
        }
        int heapSize = arrayLength;
        swap(a,0,--heapSize);
        System.out.println(Arrays.toString(a));
        //每次调整后，交换输出
        while (heapSize > 0){
            heapify(a,0,heapSize);
            swap(a,0,--heapSize);
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 构建大根堆
     * 时间复杂度为O（N）   O(log1)+O(log2)+...+O(log n-1) = O(n)
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * 一个值发生变化，往下沉使堆仍然保持最大堆
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapify(int[] arr,int index,int heapSize){
        int left = index*2+1;
        while (left < heapSize){
            int largest = left+1 < heapSize && arr[left+1] > arr[left]
                    ? left+1
                    : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index*2+1;
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
}


