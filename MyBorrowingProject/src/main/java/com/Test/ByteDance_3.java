package com.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 王彦鑫 on 2018/11/23
 */
public class ByteDance_3 {
}


class ListNode{
    int val;
    ListNode next = null;
    ListNode(int val){
        this.val = val;
    }
}

class printListFromTailToHead{
    private static ArrayList<Integer> arrayList=new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[0];
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            array = new int[n];
            for(int i = 0;i<n;i++){
                array[i] = scanner.nextInt();
            }
            ListNode listNode = changeArrayToListNode(array,0);
            ArrayList<Integer> arrayList = print(listNode);
            System.out.println(arrayList.size());
        }
    }
    public static ListNode changeArrayToListNode(int[] array,int index){
        ListNode listNode = null;
        if(index < array.length){
            int value = array[index];
            listNode = new ListNode(value);
            listNode.next = changeArrayToListNode(array,index+1);
            return listNode;
        }
        return listNode;
    }
    public static ArrayList<Integer> print(ListNode listNode) {

        if(listNode!=null){
            print(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}
