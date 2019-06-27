package com.Offer.SwordToOffer;

import java.util.*;

/**
 * Created by 王彦鑫 on 2019/3/23
 * 数组操作
 */
public class ArrayTest {
}

/**
 * 1、二维数组的查找
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
class Find {
    public boolean Find(int target, int [][] array) {
        int len = array.length-1;
        int i = 0;
        while((len >= 0)&& (i < array[0].length)){
            if(array[len][i] > target){
                len--;
            }else if(array[len][i] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }
}

/**
 * 2、旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
class minNumberInRotateArray {
    public static int minNumberInRotateArray(int[] array) {
        if(array.length == 0){
            return 0;
        }
        int start = 0;
        int end = array.length-1;
        int mid = start;
        while(array[start] >= array[end]){
            if(start+1 == end){
                return array[end];
            }
            mid = (start+end)/2;
            if(array[mid] > array[start]){
                start = mid;
            }else if(array[mid] < array[end]){
                end = mid;
            }else{
                start++;
            }
        }
        return array[start];
    }
}

/**
 * 3、调整数组顺序使奇数位于偶数前边
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
class reOrderArray {
    public void reOrderArray(int [] array) {
        int length = array.length;
        List<Integer> odd = new ArrayList<Integer>();
        List<Integer> even = new ArrayList<Integer>();
        for(int i = 0; i < length; i++){
            if(array[i] % 2 == 1){
                odd.add(array[i]);
            }else{
                even.add(array[i]);
            }
        }
        for(int i = 0; i < odd.size(); i++){
            array[i] = odd.get(i);
        }
        for(int i = 0; i < even.size(); i++){
            array[odd.size()+i] = even.get(i);
        }
    }
}

/**
 * 4、顺时针打印矩阵
 */
class printMatrix {
    public ArrayList<Integer> printMatrix(int [][] array) {
        ArrayList<Integer> result = new ArrayList<Integer> ();
        if(array.length == 0)
            return result;
        int n = array.length,m = array[0].length;
        if(m == 0)
            return result;
        int layers = (Math.min(n,m)-1)/2+1;//这个是层数
        for(int i=0;i<layers;i++){
            for(int k = i;k<m-i;k++) result.add(array[i][k]);//左至右
            for(int j=i+1;j<n-i;j++) result.add(array[j][m-i-1]);//右上至右下
            for(int k=m-i-2;(k>=i)&&(n-i-1!=i);k--) result.add(array[n-i-1][k]);//右至左
            for(int j=n-i-2;(j>i)&&(m-i-1!=i);j--) result.add(array[j][i]);//左下至左上
        }
        return result;
    }
}

/**
 * 5、数组中超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
class MoreThanHalfNum_Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int length = array.length;
        int ans = 0;
        for(int i = 0; i < length; i++){
            if(map.get(array[i]) == null){
                map.put(array[i],1);
            }else{
                map.put(array[i],map.get(array[i])+1);
            }
            if(map.get(array[i]) > length/2){
                ans = array[i];
                break;
            }
        }
        return ans;
    }
}

/**
 * 6、最小的k个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
class GetLeastNumbers_Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(k > input.length || k<=0)
            return resultList;
        //使用优先级队列建堆，优先级队列默认是最小堆，所以要重写比较器
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1,Integer o2){
                return o2.compareTo(o1);
            }
        });
        for(int i=0;i<input.length;i++)
        {
            if(i < k){//如果没有达到k个数，那么直接入堆
                maxHeap.add(input[i]);
            }else{
                if(maxHeap.peek() > input[i])
                {//堆顶的数比数组当前的数大，那么就堆顶出堆
                    maxHeap.poll();
                    maxHeap.add(input[i]);//把当前数加入堆中
                }
            }
        }
        while(maxHeap.isEmpty() != true)
            resultList.add(maxHeap.poll());//把堆中的数出堆添加到结果数组中
        return resultList;
    }
}

/**
 * 7、连续子数组的最大和
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和(子向量的长度至少是1)
 */
class FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null)
            return 0;
        int sum = array[0];
        int temp = array[0];
        for(int i = 1; i < array.length; i++) //从1开始 因为0的情况在初始化时完成了
        {
            temp = Math.max(temp+array[i],array[i]);
            sum = Math.max(sum,temp);
        }
        return sum;
    }
}

/**
 * 8、把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
class PrintMinNumber {
    public String PrintMinNumber(int [] numbers) {
        int length = numbers.length;
        String str = "";
        if(length == 0 || numbers == null){
            return str;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(numbers[i]);
        }
        Collections.sort(list,new Comparator<Integer>(){
            public int compare(Integer str1,Integer str2){
                String s1 = str1+""+str2;
                String s2 = str2+""+str1;
                return s1.compareTo(s2);
            }
        });
        for(Integer i:list){
            str += i;
        }
        return str;
    }
}

/**
 * 9、数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P
 */
class InversePairs {
    public static int sum = 0;
    public int InversePairs(int [] nums) {
        if(nums == null){
            return 0;
        }
        int ans = mergesSort(nums,0,nums.length-1);
        return ans;
    }

    public int mergesSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergesSort(nums, low, mid);
            // 右边
            mergesSort(nums, mid + 1, high);
            // 左右归并
            sum += merge(nums, low, mid, high);
        }
        return sum;
    }

    public int merge(int[] nums,int low,int mid,int high){
        int[] temp = new int[high-low+1];
        int i = mid;
        int j = high;
        int k = high-low;
        int sum = 0;
        while(i >= low && j >= mid+1){
            if(nums[i] <= nums[j]){
                temp[k--] = nums[j--];
            }else{
                sum += j - mid;
                temp[k--] = nums[i--];
            }
        }
        while(i >= low){
            temp[k--] = nums[i--];
        }
        while(j >= mid+1){
            temp[k--] = nums[j--];
        }
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
        return sum;
    }
}

/**
 * 10、数字在排序数组中出现的次数
 */
class GetNumberOfK {
    public int GetNumberOfK(int [] array , int k) {
        if(array == null){
            return 0;
        }
        int length = array.length;
        int left = 0;
        int right = length - 1;
        int toLeft = -1;
        int toRight = -1;
        int mid = 0;
        int sum = 0;
        while(left <= right){
            mid = (left + right)/2;
            if(k < array[mid]){
                right = mid - 1;
            }else if(k == array[mid]){
                toLeft = mid;
                toRight = mid + 1;
                break;
            }else{
                left = mid + 1;
            }
        }
        if(toLeft == -1 || toRight == -1){
            return 0;
        }
        for(int i = toLeft; i >= left; i--){
            if(k == array[i]){
                sum++;
            }
        }
        for(int i = toRight; i <= right; i++){
            if(k == array[i]){
                sum++;
            }
        }
        return sum;
    }
}

/**
 * 11、数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 */
class FindNumsAppearOnce {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        //先进行一次异或，所得值即为两个出现一次的数字异或的结果
        int temp = 0;
        for(int i = 0; i < array.length; i++){
            temp ^= array[i];
        }
        int index = getIndex(temp);
        for(int i = 0; i < array.length; i++){
            if(isBit1(array[i],index)){
                num1[0] ^= array[i];
            }else{
                num2[0] ^= array[i];
            }
        }
    }

    public int getIndex(int temp){
        int index = 0;
        while((temp & 1) == 0){
            index++;
            temp = temp >> 1;
        }
        return index;
    }

    public boolean isBit1(int num,int index){
        num = num >> index;
        if((num & 1) == 1){
            return true;
        }else{
            return false;
        }
    }
}

/**
 * 12、和为S的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
class FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = array.length;
        if(array == null){
            return result;
        }
        int i = 0;
        int j = length-1;
        int k = 0;
        while(i < j){
            if(array[i] + array[j] == sum){
                result.add(array[i]);
                result.add(array[j]);
                return result;
            }else if(array[i] + array[j] < sum){
                i++;
            }else{
                j--;
            }
        }
        return result;
    }
}

/**
 * 13、扑克牌顺子
 * 一副扑克牌有两张大王，两张小王，王可以看成任意数字，抽5张牌（输入数组），看能否组成顺子
 */
class isContinuous {
    public boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length > 5 || numbers.length < 1){
            return false;
        }
        Arrays.sort(numbers);
        int j = 0;
        for(int i = 0; i < numbers.length-1; i++){
            if(numbers[i] == 0){
                j = i+1;
                continue;
            }
            if(numbers[i+1] == numbers[i]){
                return false;
            }else{
                j = j - (numbers[i+1] - numbers[i])+1;
            }
        }
        if(j >= 0){
            return true;
        }else{
            return false;
        }
    }
}

/**
 * 14、数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
class duplicate {
    public boolean duplicate(int array[],int length,int [] duplication) {
        if ( array==null ) return false;

        // 判断数组是否合法（每个数都在0~n-1之间）
        for ( int i=0; i<length; i++ ) {
            if ( array[i]<0 || array[i]>length-1 ) {
                return false;
            }
        }

        // key step
        int[] hash = new int[length];
        for( int i=0; i<length; i++ ){
            hash[array[i]]++;
        }
        for(int i=0; i<length; i++){
            if ( hash[i]>1 ) {
                duplication[0] = i;
                return true;
            }
        }
        return false;
    }
}

/**
 * 15、构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 */
class multiply {
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if(length != 0 ){
            B[0] = 1;
            //计算下三角连乘
            for(int i = 1; i < length; i++){
                B[i] = B[i-1] * A[i-1];
            }
            int temp = 1;
            //计算上三角
            for(int j = length-2; j >= 0; j--){
                temp *= A[j+1];
                B[j] *= temp;
            }
        }
        return B;
    }
}

/**
 * 16、滑动窗口的最大值
 * 如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}
 */
class maxInWindows {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if(size == 0) return res;
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < num.length; i++){
            begin = i - size + 1;
            if(q.isEmpty())
                q.add(i);
            else if(begin > q.peekFirst())
                q.pollFirst();

            while((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            q.add(i);
            if(begin >= 0)
                res.add(num[q.peekFirst()]);
        }
        return res;
    }
}

/**
 * 17、矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 * 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
class hasPath {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }
}


