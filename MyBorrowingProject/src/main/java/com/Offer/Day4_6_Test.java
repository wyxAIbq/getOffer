package com.Offer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 王彦鑫 on 2019/4/6
 */
public class Day4_6_Test {
}

/**
 * 2018秋招题目——买橘子
 */
class kugou{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(get(n));
    }
    public static int get(int n){
        if(n < 1 || n > 100){
            return -1;
        }
        int num8 = 0;
        int num6 = 0;
        if(n % 8 == 0){
            return n/8;
        }else {
            num8 += n/8;
            int yushu = n%8;
            if(yushu == 6){
                num6 = 1;
                return num6+num8;
            }else {
                while (num8 > 0){
                    num8--;
                    if((n - 8*num8) % 6 == 0){
                        num6 = (n - 8*num8) / 6;
                        return num6+num8;
                    }
                }
            }
        }
        return -1;
    }
}

/**
 * 数字三角形最大和
 * 时间复杂度O(n*n)
 */
class dp_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (sc.hasNext()){
            int[][] array = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    array[i][j] = sc.nextInt();
                }
            }
            System.out.println(get(array,n));
        }
    }
    //使用一维数组代替二维数组，节省空间，或者直接将数据存入array数组最后一行
    public static int get(int[][] array,int n){
        int[] dp = array[n];
        for (int i = n-1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                dp[j] = Math.max(dp[j],dp[j+1])+array[i][j];
            }
        }
        return dp[1];
    }
}

/**
 * 腾讯春招实习——怪兽
 */
class tengxun_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int[] power = new int[n];
            for (int i = 0; i < n; i++) {
                power[i] = sc.nextInt();
            }
            int[] coin = new int[n];
            for (int i = 0; i < n; i++) {
                coin[i] = sc.nextInt();
            }
            System.out.println(get(0,power,coin,0,n));
        }
    }
    public static int get(int CurPower,int[] power,int[] coin,int i,int n){
        if( i == n){
            return 0;
        }
        int CurCoin = 0;
        for (int j = i; j < n; j++) {
            if(power[j] > CurPower){
                CurCoin += coin[j];
                CurPower += power[j];
            }else if (power[j] <= CurPower){
                int a = get(CurPower+power[j],power,coin,j+1,n);
                int b = get(CurPower,power,coin,j+1,n);
                if((coin[j] + a) > b){
                    return CurCoin+b;
                }else {
                    return CurCoin+coin[j]+a;
                }
            }
        }
        return CurCoin;
    }

}

class baidu_1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(get(str));
        Stack<Character> stack = new Stack<>();

    }
    public static String get(String str){
        StringBuilder result = new StringBuilder();
        if(str == null || str.equals("")){
            return result.toString();
        }
        int num = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '['){
                num++;
            }else{
                num--;
            }
            if(num < 0){
                num++;
                result.append('[');
            }
        }
        result.append(str);
        for(int i = 0; i < num; i++){
            result.append(']');
        }
        return result.toString();
    }
}

/**
 * 求最长单调递增子序列，可以不连着(LIS)
 * 使用LCS解LIS问题
 * 原数组为A[5,6,7,1,2,8]
 * 排序后为B[1,2,5,6,7,8]
 * 原数组的子序列顺序保持不变，而且排序后数组是递增的，这样就能保证两序列的最长公共子序列的递增特性
 * 所以要求A的最长递增子序列，就是求A和B的最长公共子序列
 */
class LIS{
    static int nIndex = 0;
    public static void main(String[] args) {
        int[] array = {5,6,7,1,2,8};
        int size = array.length;
        int[] pre = new int[size];
        int maxLength = LIS(array,size,pre);
        int[] result = new int[maxLength];
        getLIS(array,pre,nIndex,result,maxLength);
    }
    public static int LIS(int[] array,int size,int[] pre){
        int[] longest = new int[size];//longest[i]记录了array[i]元素之前的最长子序列长度
        for (int i = 0; i < size; i++) {
            longest[i] = 1;
            pre[i] = -1; //pre[i]记录了array[i]元素的前驱元素位置
        }
        int nLIS = 1; //记录最长的子序列长度
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] <= array[i]){
                    if(longest[i] < longest[j]+1){
                        longest[i] = longest[j]+1;
                        pre[i] = j;
                    }
                }
            }
            if(nLIS < longest[i]){
                nLIS = longest[i];
                nIndex = i;//nIndex记录了最长子序列最后一个元素位置
            }
        }
        return nLIS;
    }
    public static void getLIS(int[] array, int[] pre, int nIndex, int[] result,int maxLength){
        while (nIndex >= 0){
            result[maxLength-1] = array[nIndex];
            nIndex = pre[nIndex];
            maxLength--;
        }
        System.out.println(Arrays.toString(result));
    }
}

/**
 * n个矩阵相乘，求最少计算次数
 */
class dp_2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                p[i][j] = sc.nextInt();
            }
        }
        System.out.println(get(p,n));
    }
    public static int get(int[][] p,int n){
        int[][] dp = new int[n][n];
        for (int col = 0; col < n; col++) {
            int i = 0;
            int j = col;
            while (i == j && j < n){
                dp[i][j] = 0;
                i++;
                j++;
            }
            while (j < n){
                dp[i][j] = dp[i][i] + dp[i+1][j] + p[i][0]*p[i+1][0]*p[j][1];
                for (int k = i+1; k < j; k++) {
                    int temp = dp[i][k]+dp[k+1][j]+p[i][0]*p[k+1][0]*p[j][1];
                    dp[i][j] = dp[i][j] < temp ? dp[i][j]:temp;
                }
                i++;
                j++;
            }
        }
        return dp[0][n-1];
    }
}

/**
 * 判断s3是否可以由s1和s2交错构成，不改变s1、s2钟各个字符的相对位置
 */
class dp_3{
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(get(s1,s2,s3));
    }
    public static boolean get(String s1,String s2,String s3){
        int lengthS1 = s1.length();
        int lengthS2 = s2.length();
        s1 = " "+s1;
        s2 = " "+s2;
        s3 = " "+s3;
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        char[] array3 = s3.toCharArray();
        boolean[][] dp = new boolean[lengthS1+1][lengthS2+1];
        for (int i = 0; i <= lengthS1; i++) {
            for (int j = 0; j <= lengthS2; j++) {
                if(array3[i+j] == ' '){
                    dp[i][j] = false;
                    continue;
                }
                if(i == 0){
                    dp[0][j] = array2[j] == array3[j];
                }else if(j == 0){
                    dp[i][0] = array1[i] == array3[i];
                }else {
                    if(array1[i] == array3[i+j] && dp[i-1][j]){
                        dp[i][j] = true;
                    }
                    if(array2[j] == array3[i+j] && dp[i][j-1]){
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[lengthS1][lengthS2];
    }
}

class zhaohang_1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(get(n));
    }
    public static int get(int n){
        int result = 0;
        if(n < 6){
            return 0;
        }
        if(n == 6){
            return 1;
        }
        int temp = 1;
        int sum = 1;
        result = 2;
        for(int i = n-2 ; i >= 6 ; i--){
            sum = sum + temp;
            temp = sum;
            result += sum;
        }
        return result%666666666;
    }
}

class zhaohang_2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for(int i = 0 ; i < n ; i++){
            array[i] = sc.nextInt();
        }

    }
    public static int get(int[] array){
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if(array[i-1] * 2 != array[i]){
                break;
            }
        }
        int middle = array.length/2;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = (array[middle]/array[i])/2;
        }
        return result;
    }
}

class Job implements Comparable<Job>{
    long hard;
    long pay;
    Job(long hard,long pay){
        this.hard = hard;
        this.pay = pay;
    }

    @Override
    public int compareTo(Job o) {
        return (int) (o.pay - this.pay);
    }
}
class wangyi_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Job[] jobs = new Job[n];
        long[] power = new long[m];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(sc.nextLong(),sc.nextLong());
        }
        for (int i = 0; i < n; i++) {
            power[i] = sc.nextLong();
        }
        long[] result = get(n,m,jobs,power);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
    public static long[] get(int n,int m,Job[] jobs,long[] power){
        long[] result = new long[m];
        Arrays.sort(jobs);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(power[i] >= jobs[j].hard){
                    result[i] = jobs[j].pay;
                    break;
                }
            }
        }
        return result;
    }
}


class wangyi_2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int left = sc.nextInt();
        int right = sc.nextInt();
        System.out.println(get(left,right));
    }
    public static int get(int left,int right){
        if(left < 1 || left > right){
            return 0;
        }
        int result = 0;
        for (int i = left; i <= right; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                str.append(j);
            }
            char[] chars = str.toString().toCharArray();
            int sum = 0;
            for (char aChar : chars) {
                sum += aChar - '0';
            }
            if(sum % 3 == 0){
                result++;
            }
        }
        return result;
    }
}


class byteDance_3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n+1];
        for (int i = 1; i <= n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(get(array,n));
    }
    public static int get(int[] array,int n){
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > max){
                max = array[i];
            }
            if(array[i] <= min){
                min = array[i];
            }
        }
        int result = 0;
        int[] dp = new int[array.length];
        for (int i = min; i <= max; i++) {
            dp[0] = i;
            for (int j = 1; j < array.length; j++) {
                dp[j] = 2*dp[j-1] - array[j];
                if(dp[j] < 0){
                    break;
                }
            }
            Arrays.sort(dp);
            if(dp[0] >= 0){
                result = i;
                break;
            }
        }
        return result;
    }
}

class hahh{
    public static void main(String[] args) {

    }

}
