package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test_0416 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String > list = new ArrayList<>();
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            if(sc.nextLine().equals("")){
                break;
            }
        }
        ArrayList<String> result = new ArrayList<>();
        result = get(list);

    }
    public static ArrayList<String> get(ArrayList<String> list){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);
            Pattern p = Pattern.compile("(\".*?),(.*?\")");
            Matcher m = p.matcher(temp);
            StringBuffer sb = new StringBuffer();
            while(m.find()){
                m.appendReplacement(sb,m.group().replace(",", ""));
            }
            String[] strs = sb.toString().split(",");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(strs[0]).append(",").append(strs[6]).append("years old, is from ").
                    append(strs[5]).append("and is interested in ").append(strs[3]);
            result.add(stringBuffer.toString());
        }
        return result;
    }
}

class HuaWei{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            get(array,n);
        }
    }
    public static void get(int[] array,int n){
        Set<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < n ; i++){
            set.add(array[i]);
        }
        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}

class HuaWei2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(get(str));
        }
    }
    public static String get(String str){
        str = str.substring(2);
        char[] chars = str.toCharArray();
        int result = 0;
        for(int i = chars.length-1; i >= 0; i--){
            int temp = 0;
            if(chars[i] == 'A'){
                temp = 10;
            }else if(chars[i] == 'B'){
                temp = 11;
            }else if(chars[i] == 'C'){
                temp = 12;
            }else if(chars[i] == 'D'){
                temp = 13;
            }else if(chars[i] == 'E'){
                temp = 14;
            }else if(chars[i] == 'F'){
                temp = 15;
            }else{
                temp = Character.getNumericValue(chars[i]);
            }
            result += temp * Math.pow(16,chars.length-1-i);
        }
        return String.valueOf(result);
    }
}

class MeiTuan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        if (n >= 1 && m <= 100000) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            cal(matrix, n, m);
        }
    }
    public static void cal(int[][] matrix, int n, int m) {
        int count = 0;
        boolean[][] booleans = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                booleans[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= 1) {
                    if (matrix[i][j] == matrix[i - 1][j] && booleans[i][j] == false && booleans[i - 1][j] == false) {
                        booleans[i - 1][j] = true;
                        booleans[i][j] = true;
                        count++;
                    }
                }
                if (i < n - 1) {
                    if (matrix[i][j] == matrix[i + 1][j] && booleans[i][j] == false && booleans[i + 1][j] == false) {
                        booleans[i + 1][j] = true;
                        booleans[i][j] = true;
                        count++;
                    }
                }
                if (j >= 1) {
                    if (matrix[i][j] == matrix[i][j - 1] && booleans[i][j] == false && booleans[i][j - 1] == false) {
                        booleans[i][j - 1] = true;
                        booleans[i][j] = true;
                        count++;
                    }
                }
                if (j < n - 1) {
                    if (matrix[i][j] == matrix[i][j + 1] && booleans[i][j] == false && booleans[i][j + 1] == false) {
                        booleans[i][j + 1] = true;
                        booleans[i][j] = true;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Hua_Wei_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(get(str));
        }
    }

    public static String get(String str){
        String result = "";
        int length = str.length();
        if(length % 2 != 0 ){
            return "false";
        }
        char[] chars = str.toCharArray();
        int left = 0;
        int right = length-1;
        while(chars[left++] == chars[right--] ){
            if(chars[left] != chars[right]){
                return "false";
            }
            if(left >= right){
                break;
            }
        }
        for (int i = 0; i < length; i = i+2) {
            if (chars[i] == chars[i+1]){
                result += chars[i];
            }else {
                result = "false";
            }
        }
        return result;
    }
}

class Hua_Wei_11{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(get(str));
            if(sc.nextLine().equals("")){
                break;
            }
        }
    }

    public static String get(String str){
        StringBuffer result = new StringBuffer();
        int length = str.length();
        if(length % 2 != 0 ){
            return "false";
        }
        char[] chars = str.toCharArray();
        int left = 0;
        int right = length-1;
        while(chars[left++] == chars[right--] ){
            if(chars[left] != chars[right]){
                return "false";
            }
            if(left >= right){
                break;
            }
        }
        for (int i = 0; i < length; i = i+2) {
            if (chars[i] == chars[i+1]){
                result.append(chars[i]);
            }else {
                return "false";
            }
        }
        return result.toString();
    }
}

//oNEthrEEfoursixNiNENiEN
class Hua_Wei2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(get(str));
    }
    public static String get(String str){
        StringBuffer result = new StringBuffer();
        str = str.toLowerCase();
        char[] strs = str.toCharArray();
        int[] num = new int[10];
        int[] chars_num = new int[26];
        for (int i = 0; i < strs.length; i++) {
            int index = strs[i]-'a';
            chars_num[index]++;
        }
        for (int j = 0; j < 26; j++) {
            if(chars_num['z'-'a'] > 0){
                int temp = chars_num['z'-'a'];
                num[0] = temp;
                chars_num['e'-'a'] -= temp;
                chars_num['r'-'a'] -= temp;
                chars_num['o'-'a'] -= temp;
                chars_num['z'-'a'] -= temp;
            }else if(chars_num['w'-'a'] > 0){
                int temp = chars_num['w'-'a'];
                num[2] = temp;
                chars_num['t'-'a'] -= temp;
                chars_num['o'-'a'] -= temp;
                chars_num['w'-'a'] -= temp;
            }else if(chars_num['u'-'a'] > 0){
                int temp = chars_num['u'-'a'];
                num[4] = temp;
                chars_num['f'-'a'] -= temp;
                chars_num['o'-'a'] -= temp;
                chars_num['u'-'a'] -= temp;
                chars_num['r'-'a'] -= temp;
            }else if(chars_num['x' - 'a'] > 0){
                int temp = 0;
                temp = chars_num['x'-'a'];
                num[6] = temp;
                chars_num['s'-'a'] -= temp;
                chars_num['i'-'a'] -= temp;
                chars_num['x'-'a'] -= temp;
            }else if(chars_num['g'-'a'] > 0){
                int temp = 0;
                temp = chars_num['g'-'a'];
                num[8] = temp;
                chars_num['e'-'a'] -= temp;
                chars_num['i'-'a'] -= temp;
                chars_num['g'-'a'] -= temp;
                chars_num['h'-'a'] -= temp;
                chars_num['t'-'a'] -= temp;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(chars_num['o'-'a'] > 0){
                int temp = chars_num['o'-'a'];
                num[1] = temp;
                chars_num['o'-'a'] -= temp;
                chars_num['e'-'a'] -= temp;
                chars_num['n'-'a'] -= temp;
            }else if(chars_num['t'-'a'] > 0){
                int temp = chars_num['t'-'a'];
                num[3] = temp;
                chars_num['t'-'a'] -= temp;
                chars_num['h'-'a'] -= temp;
                chars_num['r'-'a'] -= temp;
                chars_num['e'-'a'] -= 2*temp;
            }else if(chars_num['f'-'a'] > 0){
                int temp = chars_num['f'-'a'];
                num[5] = temp;
                chars_num['f'-'a'] -= temp;
                chars_num['i'-'a'] -= temp;
                chars_num['v'-'a'] -= temp;
                chars_num['e'-'a'] -= temp;
            }else if(chars_num['s'-'a'] > 0){
                int temp = chars_num['s'-'a'];
                num[7] = temp;
                chars_num['s'-'a'] -= temp;
                chars_num['n'-'a'] -= temp;
                chars_num['v'-'a'] -= temp;
                chars_num['e'-'a'] -= 2*temp;
            }
        }
        if(chars_num['i'-'a'] > 0){
            num[9] = chars_num['i'-'a'];
        }
        for (int i = 0; i < 10; i++) {
            if(num[i] > 0){
                for (int j = 0; j < num[i]; j++) {
                    result.append(i);
                }
            }
        }
        return result.toString();
    }
}

/**
 * leetcode 6、z字形变换
 */
class leetcode_6 {
    public static void main(String[] args) {
        String str = "LEETCODEISHIRING";
        int row = 3;
        System.out.println(convert(str,row));
    }
    public static String convert(String str,int row){
        int length = str.length();
        char[][] chars = new char[row][length];
        String flag = "DOWN";
        StringBuffer result = new StringBuffer();
        StringBuffer[] result1 = new StringBuffer[row];
        int r = 0;
        int c = 0;
        int i = 0;
        while(i < length){
            switch (flag){
                case "DOWN":
                    while (r < row){
                        if(i < length){
                            chars[r][c] = str.charAt(i++);
                        }
                        r++;
                    }
                    r--;
                    flag = "RIGHTUP";
                    break;
                case "RIGHTUP":
                    while (r > 0){
                        r--;
                        c++;
                        if(i < length){
                            chars[r][c] = str.charAt(i++);
                        }
                    }
                    r++;
                    flag = "DOWN";
                    break;
            }
        }
        for (int k = 0; k < row; k++) {
            for (int j = 0; j < length; j++) {
                if(chars[k][j] != 0 ){
                    result.append(chars[k][j]);
                }
            }
        }
        return result.toString();
    }
}


class Main{
    public static void main(String[] args) {
        String str = "((";
        System.out.println(isValid(str));
    }
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if(s == null || length == 0){
            return true;
        }
        if(length == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < length; i++){
            if(chars[i] == '(' || chars[i] == '[' || chars[i] == '{'){
                stack.push(chars[i]);
                continue;
            }
            if(!stack.isEmpty()){
                if(stack.peek() == '(' && chars[i] == ')'){
                    stack.pop();
                }else if(stack.peek() == '[' && chars[i] == ']'){
                    stack.pop();
                }else if(stack.peek() == '{' && chars[i] == '}'){
                    stack.pop();
                }else {
                    return false;
                }
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

}

class Solution {

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
    public static String countAndSay(int n) {
        String[] array = new String[31];
        array[1] = "1";
        if(n < 1){
            return null;
        }
        if(n == 1){
            return array[1];
        }
        for(int i = 2; i <= n;i++){
            array[i] = get(array[i-1]);
        }
        return array[n];
    }
    public static String get(String str){
        char[] chars = str.toCharArray();
        int i = 0;
        int j = 0;
        StringBuffer sb = new StringBuffer();
        while(i < chars.length){
            char temp = chars[i];
            i++;
            if(i < chars.length){
                while(i < chars.length && chars[i] == temp){
                    i++;
                }
                sb.append(String.valueOf(i-j)).append(temp);
                j = i;
            }else{
                sb.append(String.valueOf(1)).append(temp);
            }
        }
        return sb.toString();
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

class leetCode_111 {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            result += 1;
            for(int i = 0; i < result; i++){
                TreeNode tempNode = queue.poll();
                if(tempNode.left == null && tempNode.right == null){
                    return result;
                }
                if(tempNode.left != null){
                    queue.offer(tempNode.left);
                }
                if(tempNode.right != null){
                    queue.offer(tempNode.right);
                }
            }
        }
        return result;
    }
}

class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new leetCode_111().minDepth(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}

class leetCode_118 {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(numRows == 0){
            return result;
        }
        for(int i = 1; i <= numRows; i++){
            for(int j = 1; j <= i; j++){
                if(j == 1){
                    list.add(1);
                    continue;
                }
                if(j == i){
                    list.add(1);
                    continue;
                }
                list.add(result.get(i-2).get(j-2)+result.get(i-2).get(j-1));
            }
            result.add(new ArrayList<>(list));
            list.clear();
        }
        return result;
    }
}

class leetCode_119 {
    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if(rowIndex == 0){
            return result;
        }
        for(int i = 1; i <= rowIndex; i++){
            for(int j = 1; j <= i; j++){
                if(j == 1){
                    temp.add(1);
                    continue;
                }
                if(j == i){
                    temp.add(1);
                    continue;
                }
                temp.add(result.get(j-2)+result.get(j-1));
            }
            result.clear();
            result.addAll(new ArrayList<Integer>(temp));
            temp.clear();
        }
        return result;
    }
}

class leetCode_171 {
    public static void main(String[] args) {
        System.out.println(titleToNumber("B"));
    }
    public static int titleToNumber(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] chars ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int i = 0; i < chars.length; i++){
            map.put(chars[i],i+1);
        }
        int l = s.length()-1;
        int pow = 0;
        int result = 0;
        while(l >= 0){
            result += map.get(s.charAt(l)) * Math.pow(26,pow);
            pow++;
            l--;
        }
        return result;
    }
}

class leetCode_189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums,3);
    }
    public static void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return;
        }
        k = k % nums.length;
        reverse(nums,0,nums.length-k-1);
        reverse(nums,nums.length-k,nums.length-1);
        reverse(nums,0,nums.length-1);
    }
    public static void reverse(int[] nums,int left,int right){
        int temp = 0;
        while(left < right){
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}

class leetCode_191 {
    public static void main(String[] args) {
        int n = 11;
        System.out.println(hammingWeight(n));
    }
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int result = 0;
        while(n != 0){
            n -= n&1;
            n = n>>1;
            result++;
        }
        return result;
    }
}

class leetCode_198 {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(rob(nums));
    }
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length + 2];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i < nums.length + 2; i++){
            dp[i] = Math.max(dp[i-2]+nums[i-2],dp[i-1]);
        }
        return dp[nums.length+1];
    }
}

class leetCode_4 {
    public static void main(String[] args) {
        int[] nums1 = {3};
        int[] nums2 = {-2,-1};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
    public static  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null){
            return 0;
        }
        if(nums1 == null || nums1.length == 0){
            return get(nums2);
        }
        if(nums2 == null || nums2.length == 0){
            return get(nums1);
        }
        double medianNum1 = get(nums1);
        double medianNum2 = get(nums2);
        return (medianNum1+medianNum2)/2;
    }
    public static double get(int[] nums){
        if(nums.length % 2 == 0){
            return (double)(nums[nums.length/2 - 1] + nums[nums.length/2])/2;
        }else{
            return (double)nums[nums.length/2];
        }
    }
}

class Bound{
    int high;
    int low;
    Bound(int high,int low){
        this.high = high;
        this.low = low;
    }
}
class lengthOflongest{
    public static void main(String[] args) {
        int[] nums = {31,6,32,1,3,2,5,7,8};
        HashMap map;
        System.out.println(getLength(nums));

    }
    public static int getLength(int[] nums){
        Map<Integer,Bound> map = new HashMap<>();
        int local = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],new Bound(nums[i],nums[i]));
        }
        for (int i = 0; i < nums.length; i++) {
            local = nums[i];
            int low = local;
            int high = local;
            if(map.containsKey(local-1)){
                low = map.get(local-1).low;
            }
            if(map.containsKey(local+1)){
                high = map.get(local+1).high;
            }
            map.get(low).high = map.get(local).high = high;
            map.get(high).low = map.get(local).low = low;
            if(high - low + 1 > maxLength){
                maxLength = high-low+1;
            }
        }
        return maxLength;
    }
}