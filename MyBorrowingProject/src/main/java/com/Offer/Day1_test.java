package com.Offer;

import java.util.*;
import java.util.concurrent.Delayed;

/**
 * Created by 王彦鑫 on 2019/2/26
 */
public class Day1_test {

    public static void main(String[] args) {
        int[] array = {2,3,1,0,2,5,3};
        duplcate(array);
    }

    /**
     * 找出数组中重复的数字
     * 时间复杂度O(n),空间复杂度O(1)
     * 用哈希表实现，时间复杂度O(n),空间复杂度O(n)
     * @param array
     */
    static void duplcate(int[] array){
        int length = array.length;
        if(length<=0){
            System.out.println("数组不能为空");
        }
        for (int i = 0; i < length; i++) {
            if(array[i]<0 || array[i]>length-1){
                System.out.println("数据错误");
            }
        }
        for (int i = 0; i < length; i++) {
            while (array[i]!=i){
                if(array[i] == array[array[i]]){
                    System.out.println("重复数字为"+array[i]);
                    break;
                }else {
                    int temp = array[i];
                    array[i] = array[temp];
                    array[temp] = temp;
                }
            }
        }
    }
}

class Day2_test{
    public static void main(String[] args) {
        //int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        //boolean flag = find(array,111);
        //System.out.println(flag);

        //StringBuffer str = new StringBuffer("we are happy");
        //String s = replace(str);
        //System.out.println(s);

        //int[] array = {1,2,3,4,5};
        //ListNode listNode = changeArrayToListNode(array,0);
        //ArrayList<Integer> arrayList = printListFromTailToHead2(listNode);

        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode root = reConstructBinaryTree(pre,in);
    }

    /**
     * 1.变数组为ListNode
     * @param array
     * @param index
     * @return
     */
    public static ListNode changeArrayToListNode(int[] array, int index){
        ListNode listNode = null;
        if(index < array.length){
            int value = array[index];
            listNode = new ListNode(value);
            listNode.next = changeArrayToListNode(array,index+1);
            return listNode;
        }
        return listNode;
    }

    /**
     * 2.从尾到头打印  使用递归实现
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ListNode node = listNode;
        if(node != null){
            if(node.next != null)
                arrayList = printListFromTailToHead(node.next);
            arrayList.add(node.val);
            System.out.println(node.val);
        }
        return arrayList;
    }

    /**
     * 2.从尾到头打印  使用栈来实现
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            int value = stack.pop();
            System.out.println(value);
            arrayList.add(value);

        }
        return arrayList;
    }

    /**
     * 递归实现重建二叉树
     * 已知前序遍历和中序遍历
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre,int[] in){
        if(pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length){
            return null;
        }
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    public static TreeNode reConstructBinaryTree(int[] pre,int startPre,int endPre,int[] in,int startIn,int endIn){
        if(startPre > endPre || startIn > endIn){
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for(int i = startIn;i <= endIn;i++){
            if(in[i] == pre[startPre]){
                root.left = reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right = reConstructBinaryTree(pre,startPre+i-startIn+1,endPre,in,i+1,endIn);
                break;
            }
        }
        return root;
    }

    /**
     * 给定二叉树和某一结点，找出它中序序列的下一个节点
     * @param node
     * @return
     */
    public static ParentTreeNode nextNode(ParentTreeNode node){
        if(node.right!=null){  //如果有右子树，下一个节点为右子树的最左子节点
            node = node.right;
            while(node.left!=null){
                node = node.left;
            }
            return node;
        }
        while(node.parent!=null){   //如果没有右子树，则找第一个当前节点是父节点左孩子的节点
            if(node.parent.left == node){
                return node.parent;
            }
            node = node.parent;
        }
        return null;
    }

}

/**
 * 单链表数据结构
 */
class ListNode{
    int val;
    ListNode next = null;
    ListNode(int val){
        this.val = val;
    }
}

/**
 * 二叉树数据结构
 */
class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int val) {
        this.val = val;
    }
}

class ParentTreeNode{
    int val;
    ParentTreeNode left = null;
    ParentTreeNode right = null;
    ParentTreeNode parent = null;
    ParentTreeNode(int val){
        this.val = val;
    }
}

/**
 * 两个栈实现一个队列
 * 两个队列实现一个栈同理
 */
class StackToQueue{
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int val){
        stack1.push(val);
    }

    public int pop(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        return -1;
    }

    public static void main(String[] args) {
        StackToQueue stackToQueue = new StackToQueue();
        stackToQueue.push(1);
        stackToQueue.push(2);
        stackToQueue.push(3);
        stackToQueue.push(4);
        stackToQueue.push(5);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
    }
}

/**
 * 输出m到n之间的水仙花数
 */
class keDaXunFei{
    public static void main(String[] args) {
        int m,n;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            m = sc.nextInt();
            n = sc.nextInt();
            if(m<100 || n<100){
                System.out.println("输入错误");
            }
            for(int i = m;i<=n;i++){
                int a = i/100;
                int b = (i-a*100)/10;
                int c = i-a*100-b*10;
                if(i == a*a*a+b*b*b+c*c*c){
                    count++;
                    System.out.print("水仙花数为"+i+" ");
                }
            }
            if(count == 0){
                System.out.println("no");
            }
        }
    }
}

/**
 * 争吵问题
 */
class keDaXunFei_3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(quarrel(str));
    }

    static int quarrel(String str){
        int size = str.length();
        int m = -1;
        int n = -1;
        char[] charArray = str.toCharArray();
        for(int i = 0;i<size;i++){
            if(charArray[i] == 'R'){
                m = i;
                break;
            }
        }
        for (int j = size-1; j > m ; j--) {
            if(charArray[j] == 'L'){
                n = j;
                break;
            }
        }
        if(m == -1 || n == -1 || m>n ){
            return size;
        }
        return m-n+size;
    }
}

class Node {
    int data;
    Node pre;  //我们需要知道当前结点的前一个结点
    public Node(int data) {
        this.data = data;
    }
}

class Statckab {
    public Node head;
    public Node current;

    //方法：入栈操作
    public void push(int data) {
        if (head == null) {
            head = new Node(data);
            current = head;
        } else {
            Node node = new Node(data);
            node.pre = current;//current结点将作为当前结点的前驱结点
            current = node;  //让current结点永远指向新添加的那个结点
        }
    }

    public Node pop() {
        if (current != null) {
            Node node = current; // current结点是我们要出栈的结点
            current = current.pre;  //每出栈一个结点后，current后退一位
            return node;
        }
        return null;
    }

    public void delete(int data){
        Node node1 = pop();
        Node node2 = pop();
        push(node1.data);
    }

    public int query(){
        Node node = pop();
        push(node.data);
        return node.data;
    }

    static void go(String[] strings,int[] ints,int n){
        Statckab stack = new Statckab();
        for (int i = 0; i < n; i++) {
            if(strings[i].equals("push") && ints[i]>=1 && ints[i]<=5){
                stack.push(ints[i]);
            }else if(strings[i].equals("pop")){
                stack.pop();
            }else if(strings[i].equals("delete") && ints[i]>=1 && ints[i]<=5){
                stack.delete(ints[i]);
            }else if(strings[i].equals("query")){
                int q = stack.query();
                System.out.println(q);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strings = new String[n];
        int[] ints = new int[n];
        while(sc.hasNext()){
            for (int i = 0; i < n; i++) {
                String str = sc.next();
                if(str.equals("push") || str.equals("delete")){
                    strings[i] = str;
                    ints[i] = sc.nextInt();
                }else if(str.equals("pop") || str.equals("query")){
                    strings[i] = str;
                    ints[i] = 0;
                }
            }
            go(strings,ints,n);
        }
    }
}

/**
 * 密码解码问题
 * A=1,B=2,C=3、、、Z=26
 * 现输入一串字符串“11”，可以解码为AA，或者解码为K
 * 问输入的数字字符串共有几种解码方法
 */
class cvte{
    public static void main(String[] args) {
        String code = "3123112";
        System.out.println(get(code));
    }

    public static int get(String code){
        int n = 0;
        char[] strArray = code.toCharArray();
        int length = code.length();
        if(length<=1){
            return 1;
        }
        if(Integer.parseInt(String.valueOf(strArray[0]))<=2){
            if(Integer.parseInt(String.valueOf(strArray[1]))<=6){
                n = get(code.substring(1))+get(code.substring(2));
            }else{
                n = get(code.substring(1));
            }
        }else {
            n = get(code.substring(1));
        }
        return n;
    }
}

/**
 * 360笔试题
 * n个人，m张桌子，再输入n个数字，代表每个人的淘气值，两人同桌时淘气值之和越大越容易吵架，单独坐不会
 * 如何分配桌子，使得每张桌子的淘气值最小，求出最大的淘气值
 */
class qihu{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0,m = 0;
        int min = 0;
        int[] array = new int[0];
        if(sc.hasNext()){
            n = sc.nextInt();
            m = sc.nextInt();
        }
        if(n>m*2 || m>=n){
            System.out.println("输入错误");
        }else {
            array = new int[n];
        }
        while (sc.hasNext()) {
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            array = quickSort(array,0,n-1);
            min = get(array,n,m);
            System.out.println(min);
        }
    }
    static int get(int[] data,int n,int m){
        int two = n-m;
        int one = n-two*2;
        int[] data2 = new int[two*2];
        int[] sum = new int[two];
        data2 = Arrays.copyOf(data,two*2);
        int length = data2.length;
        for (int i = 0; i < two; i++) {
            sum[i] = data2[i]+data2[length-i-1];
        }
        if(sum.length == 1){
            return sum[0];
        }
        sum = quickSort(sum,0,sum.length-1);
        return sum[sum.length-1];
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

    static int[] quickSort(int[] data,int low,int high){
        if (low < high){
            int middle = getMiddle(data,low,high);
            quickSort(data,low,middle-1);
            quickSort(data,middle+1,high);
            return data;
        }
        return null;
    }

    static void print(int[] a,int n){
        for (int j = 0; j < n; j++) {
            System.out.print(a[j]+" ");
        }
        System.out.println(" ");
    }
}

