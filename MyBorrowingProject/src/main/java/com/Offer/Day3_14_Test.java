package com.Offer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by 王彦鑫 on 2019/3/14
 */
public class Day3_14_Test {
}

class MinK{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        int[] result = new int[k];
        while (sc.hasNext()){
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            result = minK(array,k,result);
            for (int i = 0; i < k; i++) {
                System.out.println(result[i]);
            }
        }
    }

    public static int[] minK(int[] array,int k,int[] result){
        int length = array.length;
        if(length < 1 || k < 1 || k > length){
            return null;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < length; i++) {
            if(queue.size() != k){
                queue.offer(array[i]);
            }else if(queue.peek() > array[i]){
                queue.poll();
                queue.offer(array[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }
}

class niukeTest3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int[] aArray = new int[n+1];
        int[] bArray = new int[n+1];
        int ans = 0;
        while (sc.hasNextInt()) {
            for (int i = 1; i <= n; i++) {
                aArray[i] = sc.nextInt();
            }
            for (int i = 1; i <= n; i++) {
                bArray[i] = sc.nextInt();
            }
            ans = get(aArray,bArray,n);
            System.out.println(ans);
        }
    }

    public static int get(int[] aArray,int[] bArray,int n){
        Arrays.sort(aArray);
        Arrays.sort(bArray);
        if(aArray[n] <= bArray[1]){
            return -1;
        }
        int ans = 0;
        int i = n;
        int j = n;
        while(i > 0 || j > 0){
            if(aArray[i] > bArray[j]){
                ans += aArray[i--];
            }else{
                j--;
            }
            if(bArray[j] > aArray[i]){
                ans -= bArray[j--];
            }else {
                i--;
            }
        }
        return ans;
    }
}

class QQ{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int A = Integer.parseInt(arr[2]);
        int B = Integer.parseInt(arr[3]);
        System.out.println(process(a, b, A, B));
    }

    /**
     * 数字转换机
     * 输入2个数a，b，将a和b放入转换机，两个按钮，红色按钮按下两个数字同时加1；蓝色按钮按下两个数字同时乘2
     * 最后将a和b变成A和B，请问最少转换次数是多少次？不能转换输出-1
     * @param a
     * @param b
     * @param A
     * @param B
     * @return
     */
    private static int process(int a, int b, int A, int B) {  // 从a, b到A, B的次数
        if (a == A && b == B) return 0;
        if (a == A && b != B) return -1;
        if (a != A && b == B) return -1;
        if (a > A || b > B) return -1;
        if (A % 2 == 0 && B % 2 == 0) {
            int blue = process(a, b, A / 2, B / 2); // 上一次蓝色按钮
            if (blue == -1)
                return -1;
            else
                return blue + 1;
        } else {
            int red = process(a, b, A - 1, B - 1);
            if (red == -1) return -1;
            else return red + 1;
        }
    }
}

class Solution {

    public static int sum = 0;
    public static void main(String[] args) {
        int[] nums = {7,6,7,5,4,3,3,6};
        //int ans = InversePairs(nums);
        //System.out.println(ans);
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = temp ^nums[i];
        }
        int index = findOne(temp);
    }
    public static int findOne(int number)
    {
        int index = 0;
        while((number & 1) == 0)
        {
            index++;
            number = number >> 1;
        }
        return index;
    }
    public static int InversePairs(int [] nums) {
        if(nums == null){
            return 0;
        }
        int ans = mergesSort(nums,0,nums.length-1);
        return ans;
    }

    public static int mergesSort(int[] nums, int low, int high) {
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

    public static int merge(int[] nums,int low,int mid,int high){
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

class puke {
    public static void main(String[] args) {
        int[] numbers = {3,0,0,0,0};
        System.out.println(isContinuous(numbers));
    }
    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length > 5){
            return false;
        }
        Arrays.sort(numbers);
        int j = 0;
        for(int i = 0; i < numbers.length-1; i++){
            if(numbers[i] == 0){
                j = i+1;
                continue;
            }
            if(j >= 4)
                return true;
            if(numbers[i+1] == numbers[i]){
                return false;
            }else{
                j = j - (numbers[i+1] - numbers[i])+1;
            }
        }
        if(j == 0){
            return true;
        }else{
            return false;
        }
    }
}

class offer62 {
    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution(5,3));

    }
    public static int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            list.add(i);
        }
        int a = 0;
        while(list.size() > 1){
            a = (a+m-1)%list.size();
            list.remove(a);
        }
        if(list.size() == 1){
            return list.get(0);
        }
        return -1;
    }
}
class test{
    public static void main(String[] args) {
        int a = 2;
        int b = 4;
        Short aaa = 128;
        String str1 = "haha";
        String str2 = "kuku";
        change(str1);
        System.out.println(str1);
        char[] c = {'a','b','c','d'};
        String s = "abcd";
    }
    public static void change(String str){
        str += "+";
    }
}

class weizhong{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[7];
        while(sc.hasNext()){
            array[0] = 0;
            for (int i = 1; i < 7; i++) {
                array[i] = sc.nextInt();
            }
            int result = getMin(array);
            System.out.println(result);
        }
    }

    public static int getMin(int[] array){
        int length = array.length;
        int count = 0;
        int sum = 0;
        for (int i = length-1; i >= 1; i--) {
            if(array[i] >= 1){
                sum += i*i*array[i];
            }
        }
        for (int i = length-1; i >= 1; i--) {
            for (int j = 1; j <= length-1; j++) {
                while(sum >= 36 && i+j <=6){
                    count++;
                    sum -= 36;
                }
            }
        }
        return count;
    }
}

class wei{
    enum Direction{
        DOWN, RIGHT_UP, RIGHT, LEFT_DOWN
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        int _N;
        _N = Integer.parseInt(in.nextLine().trim());

        res = GetResult(_N);
        System.out.println(res);
    }

    static String GetResult(int N) {
        int row = 1, col = 1;
        Direction dir = Direction.RIGHT;
        int count = 1;
        while(count < N) {
            switch(dir) {
                case RIGHT:
                    col++;
                    count++;
                    dir = Direction.LEFT_DOWN;
                    break;
                case LEFT_DOWN:
                    while(col > 1) {
                        row++;
                        col--;
                        count++;
                    }
                    dir = Direction.DOWN;
                    break;
                case DOWN:
                    row++;
                    count++;
                    dir = Direction.RIGHT_UP;
                    break;
                case RIGHT_UP:
                    while(row > 1) {
                        row--;
                        col++;
                        count++;
                    }
                    dir = Direction.RIGHT;
                    break;
            }
        }
        return row+"/"+col;
    }
}
class Square implements Comparable<Square>{
    int x;
    int y;
    int length;
    public Square(int x,int y,int length){
        this.x = x;
        this.y = y;
        this.length = length;
    }
    public int getArea(){
        return length*length;
    }
    public int compareTo(Square o) {
        return Integer.compare(this.getArea(), o.getArea());
    }

}

class duye{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        Square[] squares = new Square[n];
        int overArea = 0;
        int allArea = 0;
        while (sc.hasNext()){
            for (int i = 0; i < n; i++) {
                squares[i] = new Square(sc.nextInt(),sc.nextInt(),sc.nextInt());
                allArea += squares[i].length*squares[i].length;
            }
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    overArea += getAreaOfTwoSquare(squares[i],squares[j]);
                }
            }
            int resultArea = allArea - overArea;
            System.out.println(resultArea);
        }
    }

    public static int getAreaOfTwoSquare(Square square1,Square square2){
        if (square1 == null || square2 == null) {
            return -1;
        }
        Integer p1_x = square1.x, p1_y = square1.y;
        Integer p2_x = p1_x + square1.length, p2_y = p1_y + square1.length;
        Integer p3_x = square2.x, p3_y = square2.y;
        Integer p4_x = p3_x + square2.length, p4_y = p3_y + square2.length;

        if (p1_x > p4_x || p2_x < p3_x || p1_y > p4_y || p2_y < p3_y) {
            return 0;
        }
        Integer Len = Math.min(p2_x, p4_x) - Math.max(p1_x, p3_x);
        Integer Wid = Math.min(p2_y, p4_y) - Math.max(p1_y, p3_y);
        return Len * Wid;
    }
}

class Treenode {
    String val;
    Treenode left = null;
    Treenode right = null;
    Treenode(String val) {
        this.val = val;
    }
}

class kuaishou {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(",");
        Treenode root = createBinaryTreeByArray(strings,0);
        System.out.println(isBST(root));
    }
    public static Treenode createBinaryTreeByArray(String[] strings, int index){
        Treenode tn = null;
        if (index < strings.length) {
            String value = strings[index];
            if (value.equals("None")) {
                return null;
            }
            tn = new Treenode(value);
            tn.left = createBinaryTreeByArray(strings, 2*index+1);
            tn.right = createBinaryTreeByArray(strings, 2*index+2);
            return tn;
        }
        return tn;
    }
    public static boolean isBST(Treenode root) {
        if(root == null)
            return true;
        Stack<Treenode>stack = new Stack<>();
        Treenode cur = root;
        Treenode pre = null;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                if(pre != null && Integer.parseInt(cur.val) <= Integer.parseInt(pre.val))
                    return false;
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}

class kuaishou3{
    public static void main(String[] args) {
        System.out.println(NumberOf1(155000));
    }
    public static String NumberOf1(int n) {
        if(n == 0){
            return "0";
        }
        int count = 0;
        while((n&(n-1)) != 0){
            count++;
            n = n&(n-1);
        }
        count++;
        return String.valueOf(count);
    }
}


class kuaishou2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(movingCount(m,n,k));
        }
    }
    public static int movingCount(int rows, int cols,int threshold) {
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, flag, threshold);
    }

    private static int helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j)  > threshold || flag[i][j] == 1) return 0;
        flag[i][j] = 1;
        return helper(i - 1, j, rows, cols, flag, threshold)
                + helper(i + 1, j, rows, cols, flag, threshold)
                + helper(i, j - 1, rows, cols, flag, threshold)
                + helper(i, j + 1, rows, cols, flag, threshold)
                + 1;
    }
    private static int numSum(int i) {
        int sum = 0;
        do{
            sum += i%10;
        }while((i = i/10) > 0);
        return sum;
    }
}

class zidianxu{
    public static ArrayList<String> result = new ArrayList<>();
    public static TreeSet<String> set = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(printZi(str));
    }

    public static ArrayList<String> printZi(String str){
        char[] chars = str.toCharArray();
        printZi(chars,0);
        while(!set.isEmpty())
            result.add(set.pollFirst());
        return result;
    }

    public static void printZi(char[] chars,int index){
        if(index == chars.length-1){
            set.add(String.valueOf(chars));
        }else {
            for(int i = index; i < chars.length;i++){
                swap(chars,index,i);
                printZi(chars,index+1);
                swap(chars,i,index);
            }
        }
    }

    public static void swap(char[] chars,int i,int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}

class OfferSuger{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(",");
        int[] array = new int[strs.length];
        for(int i = 0; i < strs.length; i++){
            array[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(get(array));
    }
    public static int get(int[] array){
        int[] result = new int[array.length];
        int res = 0;
        for(int i = 0; i < result.length; i++){
            result[i] = 1;
        }
        for(int i = 1; i < array.length; i++){
            if(array[i] > array[i-1] && result[i] <= result[i-1]){
                result[i] = result[i-1]+1;
            }
        }
        for (int i = array.length-1; i > 0; i--) {
            if(array[i] < array[i-1] && result[i] >= result[i-1]){
                result[i-1] = result[i]+1;
            }
        }

        for(int i = 0; i < result.length;i++){
            res += result[i];
        }
        return res;
    }
}

class jiema{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int result = get(str);
        System.out.println(result);
    }
    public static int get(String str){
        int sum = 0;
        if(str == null){
            return 0;
        }
        if(str.length() == 1){
            return 1;
        }
        if(str.length() == 2 && Integer.parseInt(str) <= 26){
            return 2;
        }
        sum += get(str.substring(1));
        if(Integer.parseInt(str.substring(0,2)) <= 26){
            sum += get(str.substring(2));
        }
        return sum;
    }
}

/**
 * 招商银行小船载人
 */
class ship{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int max = sc.nextInt();
        String[] strs = str.split(" ");
        int[] array = new int[strs.length];
        for(int i = 0; i < strs.length; i++){
            array[i] = Integer.parseInt(strs[i]);
        }
        int result = get(array,max);
        System.out.println(result);
    }
    public static int get(int[] array,int max){
        Arrays.sort(array);
        int left = 0;
        int right = array.length-1;
        int shipNum = 0;
        while(left <= right){
            if(array[right] < max && array[left]+array[right] <= max){
                left++;
                right--;
            }else {
                right--;
            }
            shipNum++;
        }
        return shipNum;
    }
}

/**
 * 小昭喵跑步
 */
class zhaohang{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int result = get(x);
        System.out.println(result);
    }
    public static int get(int x){
        x = Math.abs(x);
        int result = 0;
        if(x == 0){
            return 0;
        }
        if(x == 1){
            return 1;
        }
        if(x % 2 == 0){
            result = 1+get(x/2);
            return result;
        }else{
            result = Math.min(2+get((x-1)/2),2+get((x+1)/2));
            return result;
        }
    }
}

/**
 * 年会抢玩偶游戏
 */
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int result = get(n,m,k);
            System.out.println(result);
        }
    }
    public static int get(int n,int m,int k){
        if(n==0 || m==0 || k<=0 || k>n) {
            return 0;
        }
        for(int max = m; max > 0; max--){
            int sum = max;
            for(int i = 1; i <= k-1; i++){
                sum += (max-i)>0? (max-i):0;
            }
            for(int i = 1; i <= n-k; i++){
                sum += (max-i)>0? (max-i):0;
            }
            if(sum <= m){
                return max;
            }
        }
        return m;
    }
}

/**
 * 括号生成
 */
class kuohao{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> result = new ArrayList<>();
        backtrack(result,"",0,0,n);
        for(int i = 0; i < result.size()-1; i++){
            System.out.print(result.get(i)+",");
        }
        System.out.print(result.get(result.size()-1));
    }
    public static void backtrack(List<String> result,String str,int open,int close,int max){
        if(str.length() == max*2){
            result.add(str);
            return;
        }
        if(open < max){
            backtrack(result,str+"(",open+1,close,max);
        }
        if(close < open){
            backtrack(result,str+")",open,close+1,max);
        }
    }
}

/**
 *华为上机考试——蜜蜂采蜜
 */
class Location{
    private int x;
    private int y;
    Location(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
class mifeng{
    public static PriorityQueue<Integer> queue = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Location[] locations = new Location[5];
        while(sc.hasNext()){
            for (int i = 0; i < 5; i++) {
                locations[i] = new Location(sc.nextInt(),sc.nextInt());
            }
            get(locations,0);
            System.out.println(queue.peek());
        }
    }
    public static void get(Location[] locations,int index){
        int temp = count(locations);
        if(index == 5){
            queue.offer(temp);
        }
        for(int i = index; i < 5; i++){
            swap(locations,index,i);
            get(locations,index+1);
            swap(locations,i,index);
        }
    }
    public static void swap(Location[] locations,int i,int j){
        Location loc = locations[i];
        locations[i] = locations[j];
        locations[j] = loc;
    }
    public static int count(Location[] locations){
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += Math.sqrt(Math.pow(locations[i].getX()-locations[i+1].getX(),2)+
                    Math.pow(locations[i].getY()-locations[i+1].getY(),2));
        }
        sum += Math.sqrt(Math.pow(locations[0].getX(),2)+ Math.pow(locations[0].getY(),2));
        sum += Math.sqrt(Math.pow(locations[4].getX(),2)+ Math.pow(locations[4].getY(),2));
        return sum;
    }
}

/**
 * 腾讯2017暑期实习生——构造回文
 */
class tengxun1_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            char[] array = str.toCharArray();
            System.out.println(array.length-get(array,0,array.length-1));
        }
    }
    public static int get(char[] array,int left,int right){
        int max = 0;
        if(left == right){
            return 1;
        }
        if(array[left] == array[right] && left+1 == right){
            return 2;
        }

        if(array[left] == array[right]){
            max = get(array,left+1,right-1)+2;
        }else {
            max = get(array,left,right-1) > get(array,left+1,right) ?get(array,left,right-1):get(array,left+1,right);
        }
        return max;
    }
}

class tengxun1_2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = new StringBuilder(s1).reverse().toString();
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 1; i < dp.length; i ++ ) {
                for (int j = 1; j < dp[0].length; j ++ ) {
                    dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            System.out.println(s1.length() - dp[s1.length()][s2.length()]);
        }
    }
}

class tengxun2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            char[] chars = str.toCharArray();
            System.out.println(get(chars));
        }
    }
    public static String get(char[] chars){
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length-1-i; j++) {
                if((chars[j] >= 'A' && chars[j] <= 'Z') && (chars[j+1] < 'A' || chars[j+1] > 'Z')){
                    char temp = chars[j];
                    chars[j] = chars[j+1];
                    chars[j+1] = temp;
                }
            }
        }
        return String.valueOf(chars);
    }
}

/**
 * 腾讯2018春招——翻转数列
 */
class tengxun3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(get(n,m));
        }
    }
    public static long get(int n ,int m){
        if(n%(2*m) != 0){
            return 0;
        }
        long sum = 0;
        boolean flag = false;
        for (int i = 1; i <= n; i = i+m) {
            if(!flag){
                for (int j = i; j < i+m; j++) {
                    sum -= j;
                }
                flag = true;
            }else {
                for (int j = i; j < i+m; j++) {
                    sum += j;
                }
                flag = false;
            }
        }
        return sum;
    }
}

/**
 * 腾讯2018春招——纸牌游戏
 */
class tengxun4{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            System.out.println(get(array,n));
        }
    }
    public static int get(int[] array,int n){
        Arrays.sort(array);
        int niu = 0;
        for (int i = n-1; i >= 0; i = i-2) {
            niu += array[i];
        }
        int yang = 0;
        for (int i = n-2; i >= 0; i = i-2) {
            yang += array[i];
        }
        return niu-yang;
    }
}

/**
 * 腾讯2018春招——贪吃的小Q
 */
class tengxun5{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int day = scan.nextInt();
        int number = scan.nextInt();
        System.out.print(fun(day,number));
    }

    //计算第一天吃s个巧克力一共需要多少个多少个巧克力
    public static int sum(int s, int n, int m){
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=s;
            s=(s + 1)/2;//向上取整
        }
        return sum;
    }
    //二分查找
    public static int fun(int n, int m){
        if(n==1) return m;
        int low=1;
        int high=m;//第一天的巧克力一定是大于等于1小于等于m的
        while(low<=high){
            int mid=(low+high + 1)/2;//向上取整
            if(sum(mid, n, m)==m) return mid;//如果第一天吃mid个巧克力，刚刚好吃完所有巧克力，那么直接返回
            else if(sum(mid, n, m)<m){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }
}

class tengxun6{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int k = sc.nextInt();
            int lengthA = sc.nextInt();
            int countA = sc.nextInt();
            int lengthB = sc.nextInt();
            int countB = sc.nextInt();
            System.out.println(get(k,lengthA,countA,lengthB,countB));
        }
    }
    public static int get(int k,int lengthA,int countA,int lengthB,int countB){
        int result = 0;
        int[][] dp = new int[countA+countB+1][k+1];
        int[] length = new int[countA+countB+1];
        for (int i = 1; i <=countA ; i++) {
            length[i] = lengthA;
        }
        for (int i = countA+1; i <= countA+countB; i++) {
            length[i] = lengthB;
        }
        for (int i = 1; i <= countA+countB; i++) {
            for (int j = 1; j <= k; j++) {
                if(j < length[i]){
                    dp[i][j] = dp[i-1][j] % 1000000007;
                }else {
                    dp[i][j] = (dp[i - 1][j] % 1000000007 + dp[i - 1][j - length[i]] % 1000000007) % 1000000007;
                }
            }
        }
        return 0;
    }
}

class tengxun7{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str0 = sc.nextLine();
        String str = sc.nextLine();
        System.out.println(getResult(str,n));
    }
    public static int getResult(String str,int n){
        if(str.equals("1") || str.equals("0")){
            return 1;
        }
        if(n <= 0){
            return 0;
        }
        if(n != str.length()){
            return 0;
        }
        int result = 0;
        int num0 = 0;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if(array[i] == '0'){
                num0++;
            }
        }
        int num1 = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == '1'){
                num1++;
            }
        }
        result = Math.abs(num0-num1);
        return result;
    }
}

class Monster{
    int power;
    int coin;
}
class tengxun8{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Monster[] monster = new Monster[n];
        while(sc.hasNext()){
            for (int i = 0; i < n; i++) {
                monster[i] = new Monster();
                monster[i].power = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                monster[i].coin = sc.nextInt();
            }
            System.out.println(get(monster,n));
        }
    }
    public static int get(Monster[] monsters,int n){
        int resultCoin = 0;
        resultCoin = monsters[0].coin;
        int power = monsters[0].power;
        for (int i = 1; i < n; i++) {
            if(monsters[i].power > power){
                power += monsters[i].power;
                resultCoin += monsters[i].coin;
            }
        }
        return resultCoin;
    }
}

