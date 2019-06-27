package com.Offer;

import java.util.*;

/**
 * Created by 王彦鑫 on 2019/3/8
 */
public class Day3_test {

}

class Bear implements Comparable<Bear>{
    int id;
    int fightVal;
    int hungerVal;

    public Bear(int id, int fightVal, int hungerVal) {
        this.id = id;
        this.fightVal = fightVal;
        this.hungerVal = hungerVal;
    }

    @Override
    //按武力值从小到大排序
    public int compareTo(Bear o) {
        return this.fightVal-o.fightVal;
    }
}

/**
 * 小熊吃糖---拼多多2018年秋招
 */
class BearEatSuger{
    public static final int nMax = 10,mMax = 100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int m = 0;
        if(sc.hasNext()){
            n = sc.nextInt();
            m = sc.nextInt();
        }
        int suger[] = new int[mMax];
        for (int i = 0; i < m; i++) {
            suger[i] = sc.nextInt();
        }
        Bear[] bears = new Bear[nMax];
        for (int i = 0; i < n; i++) {
            bears[i] = new Bear(i,sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(suger,0,m);
        Arrays.sort(bears,0,n);
        for (int i = n-1; i >= 0; i--) {
            boolean flag;
            do {
                flag = false;
                for (int j = m-1; j >= 0; j--) {
                    if(suger[j]!=-1 && suger[j] <= bears[i].hungerVal){
                        bears[i].hungerVal -= suger[j];
                        suger[j] = -1;
                        flag = true;
                        break;
                    }
                }
            } while(flag);
        }
        Arrays.sort(bears, 0, n, Comparator.comparingInt(bear -> bear.id));
        for (int i = 0; i < n; i++) {
            System.out.println(bears[i].hungerVal);
        }
    }
}

class SwordOffer {
    public static void main(String[] args) {
        //int[] array = {3,4,5,2,2};
        //System.out.println(minNumberInRotateArray(array));

        //System.out.println(CutRope(10));
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int[] array = new int[0];
        while(sc.hasNext()){
            n = sc.nextInt();
            array = new int[n];
            for(int i = 0; i < n; i++){
                array[i] = sc.nextInt();
            }
            int max = Maxproduct(array,n);
            System.out.println(max);
        }
    }

    /**
     *第11题
     *旋转数组的最小数字
     * @param array
     * @return
     */
    private static int minNumberInRotateArray(int[] array) {
        if(array.length == 0){
            return 0;
        }
        if(array[0] < array[array.length-1]){
            return array[0];
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

    /**
     *动态规划法
     *第14题剪绳子
     * 时间复杂度O(n*n)空间复杂度O(n)
     * @param length
     * @return
     */
    public static int CutRope(int length){
        if(length < 2)
            return 0;
        if(length == 2)
            return 1;
        if(length == 3)
            return 2;
        int[] products = new int[length+1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        int max = 0;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i/2; j++) {
                int product = products[j]*products[i-j];
                if(max < product)
                    max = product;
                products[i] = max;
            }
        }
        max = products[length];
        return max;
    }

    /**
     * 贪婪法剪绳子
     * 时间空间复杂度都为O(1)
     * @param len
     * @return
     */
    public static int CutRope2(int len){
        if(len<2)
            return 0;
        if(len==2)
            return 1;
        if(len==3)
            return 2;
        //啥也不管,先尽可能减去长度为3的段
        int timeOfThree=len/3;

        //当绳子最后剩下的长度为4时，不能再减去长度为3的绳子段，此时更好的方法是将绳子剪成长度为2的两段，2*2>1*3
        if(len-timeOfThree*3==1)
            timeOfThree-=1;
        int timeOfTwo=(len-timeOfThree*3)/2;

        return (int) ((Math.pow(3, timeOfThree))*(Math.pow(2, timeOfTwo)));
    }

    /**
     * 拼多多---最大乘积
     * 给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积使得乘积最大，要求时间复杂度O(n)空间复杂度O(1)
     * @param array
     * @param n
     * @return
     */
    public static int Maxproduct(int[] array,int n){
        if(n<3){
            return -1;
        }
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int min1 = 0;
        int min2 = 0;
        int maxProduct = 0;
        max1 = array[0]>array[1]? array[0]:array[1];
        max2 = array[0]>array[1]? array[1]:array[0];
        if(array[2]>max1){
            max3 = max2;
            max2 = max1;
            max1 = array[2];
        }else if(array[2]>max2){
            max3 = max2;
            max2 = array[2];
        }else{
            max3 = array[2];
        }
        min1 = max3;
        min2 = max2;
        for(int i = 3; i < n; i++){
            if(array[i] < min1){
                min2 = min1;
                min1 = array[i];
            }else if(array[i] < min2){
                min2 = array[i];
            }
            if(array[i] > max1){
                max3 = max2;
                max2 = max1;
                max1 = array[i];
            }else if(array[i] > max2){
                max3 = max2;
                max2 = array[i];
            }else if(array[i] > max3){
                max3 = array[i];
            }
        }
        maxProduct = max1*max2*max3 > max1*min1*min2 ? max1*max2*max3 : max1*min1*min2;
        return maxProduct;
    }
}

class pdd{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        while (sc.hasNext()){
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int j = 0; j < n; j++) {
                b[j] = sc.nextInt();
            }
            System.out.println(minS(a,b,n));
        }
    }

    /**
     * 拼多多---实习笔试---最小乘积
     * S=a[0]*b[0]+a[1]*b[1]+...+a[n-1]*b[n-1]
     * a数组可以改变数字顺序，求最小的S
     * @param a
     * @param b
     * @param n
     * @return
     */
    public static int minS(int[] a,int[] b,int n){
        if(n>50)
            return -1;
        Arrays.sort(a,0,n);
        int s = 0;
        for (int i = 0; i < n; i++) {
            int p = 0;
            int temp = b[i];
            for (int j = 0; j < n; j++) {
                if(temp > b[j]){
                    p++;
                }
            }
            s += a[n-1-p]*b[i];
        }
        return s;
    }


}

/**
 * 拼多多---实习笔试---字符串
 */
class pdd2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String lowStr = str.toLowerCase();
        String a = removeRepeatChar(lowStr);
        char[] cStr = a.toCharArray();
        Arrays.sort(cStr);
        System.out.println(cStr[0]);
    }

    public static String rmRepeated(String s){
        int len = s.length();
        int k = 0;
        int count = 0;
        String str = "";
        char[] c = new char[len];
        for(int i=0;i<len;i++){
            c[i] = s.charAt(i);
        }
        for(int i=0;i<len;i++){
            k=i+1;
            while(k<len-count){
                if(c[i]==c[k]){
                    for(int j=k;j<len-1;j++){
                        c[j] = c[j+1];//出现重复字母，从k位置开始将数组往前挪位
                    }
                    count++;//重复字母出现的次数
                    k--;
                }
                k++;
            }
        }
        for(int i=0;i<len-count;i++){
            str+=String.valueOf(c[i]);
        }
        return str;
    }

    public static String removeRepeatChar(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            sb.append(c);
            i++;
            while (i < len && s.charAt(i) == c) {//这个是如果这两个值相等，就让i+1取下一个元素
                i++;
            }
        }
        return sb.toString();
    }
}


class Bank implements Comparable<Bank>{
    int location;
    int money;
    public Bank(){

    }
    public Bank(int location,int money){
        this.location = location;
        this.money = money;
    }
    public int compareTo(Bank o) {
        return o.money-this.money;
    }
}

/**
 * 拼多多---实习笔试---两小偷偷银行
 * n家银行，两小偷所偷银行距离应不小于d，每个银行包括它的位置和钱数
 * 位置为数轴上的点
 */
class pdd3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        Bank[] banks = new Bank[n];
        while(sc.hasNext()){
            for (int i = 0; i < n; i++) {
                banks[i] = new Bank(sc.nextInt(),sc.nextInt());
            }
            System.out.println(get(banks,n,d));
        }
    }

    public static int get(Bank[] banks,int n,int d){
        Arrays.sort(banks,0,n);//按money从大到小排序
        Bank bank1 = new Bank();
        Bank bank2 = new Bank();
        int k = n;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            bank1 = banks[i];
            for (int j = i+1; j < n; j++) {
                if(Math.abs(bank1.location - banks[j].location)>=d){
                    bank2 = banks[j];
                    k = j;
                    break;
                }
            }
            sum = bank1.money+bank2.money > sum ? bank1.money+bank2.money : sum;
        }
        return sum;
    }
}

/**
 * 从1打印到n位数的最大值，没说n有多大，故不应用int，改用字符串或数组
 */
class Print1ToN{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        get(n);
    }

    public static void get(int n){
        if (n<=0)
            return;
        char[] numbers = new char[n+1];
        numbers[n] = '\0';
        for (int i = 0; i < 10; i++) {
            numbers[0] = (char) (i+'0');
            print1ToN(numbers,n,0);
        }
    }

    public static void print1ToN(char[] numbers,int length,int index){
        if (index == length-1){
            print(numbers);
            return;
        }
        for (int i = 0; i < 10; i++) {
            numbers[index+1] = (char) (i+'0');
            print1ToN(numbers,length,index+1);
        }
    }

    public static void print(char[] numbers){
        int length = numbers.length;
        boolean isBegining0 = true;
        for (int i = 0; i < length; i++) {
            if (isBegining0 && numbers[i] != '0'){
                isBegining0 = false;
            }
            if (!isBegining0){
                System.out.print(numbers[i]);
            }
        }
        System.out.println("\t");
    }
}

/**
 * 神策数据实习笔试
 * 一个数组只有1和-1，求最长和为0字串
 */
class MaxLong{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int[] array = new int[n];
        while(sc.hasNext()){
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            System.out.println(getLong(array));
        }
    }

    /*
    普通方法
     */
    public static int get(int []array) {
        int max = 0;
        int sum = 0;
        int length = array.length;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(0,-1);
        for (int i = 0;i < length; i++){
            sum += array[i];
            if (hashMap.get(sum)== null){
                hashMap.put(sum,i);
            }else{
                int temp=i - hashMap.get(sum);
                if (max < temp){
                    max = temp;
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     * //dp[i][j]表示从array[i]一直加到array[j]的和
     * 递推式：dp[i][j]  = dp[i+1][j-1] + array[i] + array[j];
     * 判定：if(dp[i][j] == 0)
     *     max = max(max, j-1+1);
     * @param array
     * @return
     */
    public static int getLong(int[] array){
        int max = 0;
        int length = array.length;
        if(length <= 1){
            return 0;
        }
        int[][] dp = new int[length][length];
        for (int i = 1; i < length; i++) {
            dp[i-1][i] = array[i-1]+array[i];
            if(array[i-1] == -array[i])
                max = 2;
        }
        for (int k = 3; k < length; k = k+2) { //每次增量为2
            for (int i = 0,j; (j = i+k) < length; i++) {
                dp[i][j] = dp[i+1][j-1] + array[i] + array[j];
                if(dp[i][j] == 0){
                    max = Math.max(max,j-i+1);
                }
            }
        }
        return max;
    }
}

/**
 *神策数据实习笔试题
 * 有一个点（x，y），如果x>=m或者y>=m，则称（x，y）为m的一个很赞的点
 * 如果x和y都小于m，则求x和y的和sum，并把sum替换x或y，（sum，y）或（x，sum）
 * 请问至少需要几次变换能使该点变为m的很赞的点
 */
class shence{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(get(x,y,m));
    }

    public static int get(int x,int y,int m){
        int i = 1;
        if(x >= m || y >= m){
            return 0;
        }
        if(x+y<=x && x+y<=y){
            return -1;
        }
        int sum = x+y;
        if(x>=y) {
            return i+get(x, sum, m);
        }else{
            return i+get(sum,y,m);
        }
    }
}

class baiDu2018{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        System.out.println(strMatch(a,b));
    }
    public static int strMatch(String a,String b){
        int ans = 0;
        HashSet<String> set = new HashSet<>();
        int aLength = a.length();
        int bLength = b.length();
        char[] bChars = b.toCharArray();
        for (int i = 0; i < aLength - bLength + 1; i++) {
            int j = i+bLength;
            String temp = a.substring(i,j);
            set.add(temp);
        }
        for (String s:set) {
            boolean flag = true;
            char[] chars = s.toCharArray();
            for (int i = 0; i < bLength; i++) {
                if(bChars[i] != chars[i]){
                    if(bChars[i] == '?'){
                        continue;
                    }else {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag)
                ans++;
        }
        return ans;
    }
}




