package com.Offer.SwordToOffer;

import java.util.ArrayList;

/**
 * Created by 王彦鑫 on 2019/3/23
 * 链表操作
 */
public class ListTest {
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 1、从尾到头打印链表
 */
class printListFromTailToHead {
    private ArrayList<Integer> arrayList=new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            this.printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}

/**
 * 2、链表中倒数第K个结点
 */
class FindKthToTail {
    public ListNode FindKthToTail(ListNode head,int k) {
        int length = 0;
        ListNode tempNode = head;
        while(tempNode != null){
            length++;
            tempNode = tempNode.next;
        }
        if(k > length || k <= 0 ){
            return null;
        }
        ListNode quickNode = head;
        ListNode slowNode = head;
        for(int i = 0; i < k-1; i++){
            quickNode = quickNode.next;
        }
        for(int i = k;i < length; i++){
            quickNode = quickNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }
}

/**
 * 3、反转链表
 * 输出新链表的表头
 */
class ReverseList {
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode currentNode = head.next;
        ListNode preNode = head;
        ListNode nextNode = currentNode.next;
        preNode.next = null;
        while(currentNode != null){
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
            if(currentNode != null){
                nextNode = currentNode.next;
            }
        }
        head = preNode;
        return head;
    }
}

/**
 * 4、合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则
 */
class Merge {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode list3 = new ListNode(0);
        ListNode tempHead = list3;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                tempHead.next = list1;
                list1 = list1.next;
            }else{
                tempHead.next = list2;
                list2 = list2.next;
            }
            tempHead = tempHead.next;
        }
        if(list1 == null){
            tempHead.next = list2;
        }else{
            tempHead.next = list1;
        }
        return list3.next;
    }
}


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
/**
 * 5、复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
class Clone {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return null;
        }
        RandomListNode current = pHead;
        while(current != null){
            RandomListNode cloneNode = new RandomListNode(current.label);
            RandomListNode next = current.next;
            current.next = cloneNode;
            cloneNode.next = next;
            current = next;
        }
        current = pHead;
        while(current != null){
            RandomListNode tempNode = current.random;
            current.next.random = tempNode == null?null:tempNode.next;
            current = current.next.next;
        }
        current = pHead;
        RandomListNode cloneNode = pHead.next;
        while(current != null){
            RandomListNode clone = current.next;
            current.next = clone.next;
            clone.next = clone.next == null? null:clone.next.next;
            current = current.next;
        }
        return cloneNode;
    }
}

/**
 * 6、两个链表的第一个公共结点
 */
class FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        int length1 = 0;
        int length2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1 != null){
            length1++;
            p1 = p1.next;
        }
        while(p2 != null){
            length2++;
            p2 = p2.next;
        }
        int cha = 0;
        if(length1 > length2)
        {//求长度差，长的先走长度差个单位
            cha = length1 - length2;
            p1 = pHead1;
            p2 = pHead2;
            for(int i=0;i<cha;i++)
                p1 = p1.next;
        }else{
            cha = length2 - length1;
            p2 = pHead2;
            p1 = pHead1;
            for(int i=0;i<cha;i++)
                p2 = p2.next;
        }
        while(p1 != p2)
        {//找第一个相同的节点
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}

/**
 * 7、链表中环的入口结点
 */
class EntryNodeOfLoop {

    public ListNode EntryNodeOfLoop(ListNode pHead){
        ListNode meetNode = hasLoop(pHead);
        if(meetNode == null){
            return null;
        }
        ListNode p1 = pHead;
        ListNode p2 = meetNode;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public ListNode hasLoop(ListNode pHead){
        if(pHead == null){
            return null;
        }
        ListNode slow = pHead;
        ListNode quick = pHead;
        while(slow != null && quick.next != null){
            slow = slow.next;
            quick = quick.next.next;
            if(slow == quick){
                return slow;
            }
        }
        return null;
    }
}

/**
 * 8、删除链表中重复的结点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
class deleteDuplication {
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode node = new ListNode(0);
        node.next = pHead;
        ListNode pre = node;
        ListNode current = pHead;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                int val = current.val;
                while(current != null && val == current.val){
                    current = current.next;
                }
                pre.next = current;
            }else{
                pre = current;
                current = current.next;
            }
        }
        return node.next;
    }
}


