package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Created by 王彦鑫 on 2019/6/18
 *
 * 动态规划
 */
public class LeftGod_8 {
}

/**
 * 求n！
 */
class Code_01_Factorial {
    //递归
    private static long getFactorial1(int n) {
        if (n == 1) {
            return 1L;
        }
        return (long) n * getFactorial1(n - 1);
    }

    //非递归
    private static long getFactorial2(int n) {
        long result = 1L;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(getFactorial1(n));
        System.out.println(getFactorial2(n));
    }

}


/**
 * 汉诺塔问题
 * 时间复杂度T(n) = T(n-1)+1+T(n-1) = 2^n - 1   >>>O(2^n)
 */
class Code_02_Hanoi {

    private static void hanoi(int n) {
        if (n > 0) {
            func(n, n, "left", "mid", "right");
        }
    }

    private static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            func(rest - 1, down - 1, from, to, help);
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    //N代表1-N个碟子
    private static void process(int N, String from, String help, String to ){
        if (N == 1){
            System.out.println("Move 1 from " + from + " to " + to);
        }else {
            process(N-1,from,to,help);
            System.out.println("Move " + N + " from " + from + " to " +to);
            process(N-1,help,from,to);
        }
    }

    //如果想不到递归方法，就分开
    private static void moveLeftToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        moveLeftToMid(N - 1);
        System.out.println("move " + N + " from left to right");
        moveMidToRight(N - 1);
    }

    private static void moveRightToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        moveRightToMid(N - 1);
        System.out.println("move " + N + " from right to left");
        moveMidToLeft(N - 1);
    }

    private static void moveLeftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        moveLeftToRight(N - 1);
        System.out.println("move " + N + " from left to mid");
        moveRightToMid(N - 1);
    }

    private static void moveMidToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        moveMidToRight(N - 1);
        System.out.println("move " + N + " from mid to left");
        moveRightToLeft(N - 1);
    }

    private static void moveRightToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        moveRightToLeft(N - 1);
        System.out.println("move " + N + " from right to mid");
        moveLeftToMid(N - 1);
    }

    private static void moveMidToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        moveMidToLeft(N - 1);
        System.out.println("move " + N + " from mid to right");
        moveLeftToRight(N - 1);
    }

    public static void main(String[] args) {
        //int n = 3;
        //hanoi(n);
        process(3,"左","中","右");
        moveLeftToRight(3);
    }

}

/**
 * 打印字符串全部子序列
 */
class Code_03_Print_All_SubSequences {

    //打印全部子序列，包括空格
    private static void printAllSubSequence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    private static void process(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process(chs, i + 1);
        chs[i] = tmp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    private static void process(char[] chs, int i, List<Character> res) {
        if(i == chs.length) {
            printList(res);
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(chs, i+1, resNoInclude);
    }

    private static void printList(List<Character> res) {
        // ...;
    }

    private static List<Character> copyList(List<Character> list){
        return null;
    }

    //打印全部子序列，包括空格
    private static void printAllSub(char[] chars, int i, String res){
        if (i == chars.length){
            System.out.println(res);
            return;
        }
        printAllSub(chars,i+1,res+String.valueOf(chars[i]));
        printAllSub(chars,i+1,res+" ");
    }

    public static void main(String[] args) {
        String test = "abc";
        printAllSub(test.toCharArray(),0,"");
    }

}

/**
 * 全排列问题
 *
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
class Code_04_Print_All_Permutations {

    private static void printAllPermutations1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }

    private static void process1(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        for (int j = i; j < chs.length; j++) {
            swap(chs, i, j);
            process1(chs, i + 1);
            swap(chs, i, j);
        }
    }

    private static void printAllPermutations2(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0);
    }

    private static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process2(chs, i + 1);
                swap(chs, i, j);
            }
        }
    }

    private static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        printAllPermutations1(test2);
        System.out.println("======");
        printAllPermutations2(test2);
        System.out.println("======");
    }

}

/**
 * 阿里巴巴原题！！！
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只 母牛，假设不会死。求N年后，母牛的数量。
 */
class Code_05_Cow {

    private static int cowNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }

    private static int cowNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    //假设每头牛的寿命为10年，求N年后母牛总数
    private static int cowNumber3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber3(n - 1) + cowNumber3(n - 3) - cowNumber3(n - 10);
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
        System.out.println(cowNumber3(n));
    }

}

/**
 * 原地逆序栈
 */
class Code_06_ReverseStackUsingRecursive {

    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    private static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

}

/**
 * 动态规划——数组最短路径
 *
 * 给你一个二维数组，二维数组中的每个数都是正数，要求从左上 角走到右下角，每一步只能向右或者向下。
 * 沿途经过的数字要累 加起来。返回最小的路径和
 */
class Code_07_MinPath {

    private static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    //从（i，j）出发，到达最左上角位置，最短路径和是多少
    private static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    private static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    // for test
    private static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}

/**
 * 动态规划——类似打印字符串所有子序列
 *
 * 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的 数字，能不能累加得到aim，返回true或者false
 */
class Code_08_Money_Problem {

    private static boolean money1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }

    private static boolean process1(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        // sum != aim
        if (i == arr.length) {
            return false;
        }
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    /**
     * dp[i][j]表示arr[i]之前已经相加得到的和为j，dp[i][j]依赖于(dp[i+1][j] || dp[i+1][j+arr[i]])
     * @param arr
     * @param aim
     * @return
     */
    private static boolean dp(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return false;
        }
        boolean[][] dp = new boolean[arr.length+1][aim+1];
        dp[arr.length][aim] = true;
        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                if(j + arr[i] <= aim){
                    dp[i][j] = dp[i+1][j] || dp[i + 1][j + arr[i]];
                }else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }
        return dp[0][0];
    }

    private static boolean money2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 8 , 9};
        int aim = 18;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));
        System.out.println(dp(arr, aim));
    }

}

/**
 * 动态规划
 *
 * 背包问题
 */
class Code_09_Knapsack {

    private static int maxValue1(int[] c, int[] p, int bag) {
        return process1(c, p, 0, 0, bag);
    }

    private static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }
        if (i == weights.length) {
            return 0;
        }
        return Math.max(

                process1(weights, values, i + 1, alreadyweight, bag),

                values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));
    }

    private static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] c = { 3, 2, 4, 7 };
        int[] p = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(c, p, bag));
        System.out.println(maxValue2(c, p, bag));
    }

}

/**
 * 动态规划
 *
 * 背包问题
 */
class Knapsack{
    public static class Node{
        int weight;
        int value;
        Node(int weight, int value){
            this.value = value;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int[] W = {3,2,4,7};
        int[] V = {5,6,3,19};
        int bag = 11;
        System.out.println(get(W,V,bag));
        System.out.println(MaxValue2(W,V,bag));
    }

    private static int get(int[] W,int[] V,int bag){
        return MaxValue(W,V,bag,0,0,0).value;
    }

    //递归版本
    private static Node MaxValue(int[] W,int[] V,int bag,int weights,int values,int i){
        if (i == W.length && weights <= 11){
            return new Node(weights,values);
        }
        if (weights <= bag){
            Node left = MaxValue(W,V,bag,weights+W[i],values+V[i],i+1);
            Node right = MaxValue(W,V,bag,weights,values,i+1);
            return left.value > right.value ? left : right;
        }
        return new Node(weights-W[i-1],values-V[i-1]);
    }

    private static int MaxValue2(int[] W,int[] V,int bag){
        int[][] dp = new int[W.length+1][bag+1];
        for (int i = 0; i <= bag; i++) {
            dp[W.length][i] = 0;
        }
        for (int i = W.length-1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                //dp[i][j] = dp[i+1][j];
                if (j + W[i] <= bag) {
                    dp[i][j] = Math.max(dp[i+1][j], V[i] + dp[i+1][j + W[i]]);
                }
            }
        }
        return dp[0][0];
    }

}