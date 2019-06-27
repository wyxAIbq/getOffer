package com.Test;

import java.util.*;

/**
 * 自己实现一个ArrayList，更好的理解底层结构
 * Created by 王彦鑫 on 2018/12/10
 */
public class CollectionTest {
}

class SxtArrayList{
    private Object[] elementData;
    private int size;

    public int size(){
        return size;
    }

    public SxtArrayList(){
        this(10);
    }

    //初始化
    private SxtArrayList(int initialCapacity){
        if(initialCapacity < 0){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }

    //判断是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //得到某个位置的元素
    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    //index越界判断
    private void rangeCheck(int index){
        if(index >= size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //移除某个位置的元素
    public void remove(int index){
        rangeCheck(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    //移除和obj一样的对象
    public boolean remove(Object obj){
        if (obj == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (obj.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    //添加元素
    public void add(Object obj){
        //数组扩容和数据拷贝
        if(size == elementData.length){
            Object[] newArray = new Object[size*2+1];
            System.arraycopy(elementData,0,newArray,0,elementData.length);
            elementData = newArray;
        }
        elementData[size++] = obj;
    }

    //将某个位置的值改变
    public Object set(int index,Object obj){
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = obj;
        return oldValue;
    }

    public static void main(String[] args) {
        SxtArrayList list = new SxtArrayList(3);
        list.add(222);
        list.add(new Date());
        list.add("wang");
        list.add(111);
        list.add(333);
        System.out.println(list.size());
        list.set(0,123);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

//表示节点
class Node{
    Node previous;
    Object obj;
    Node next;

    public Node(){
    }

    public Node(Node previous, Object obj, Node next) {
        this.previous = previous;
        this.obj = obj;
        this.next = next;
    }
}

class SxtLinkedList{

    private Node first;
    private Node last;
    private int size;

    public void add(Object obj){
        Node n = new Node();
        if(first == null){
            n.previous = null;
            n.obj = obj;
            n.next = null;
            first = n;
            last = n;
        }else {
            //直接往last节点后增加新的节点
            n.previous = last;
            n.obj = obj;
            n.next = null;
            last.next = n;
            last = n;
        }
        size++;
    }

    private void rangeCheck(int index){
        if(index >= size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object get(int index){
        rangeCheck(index);
        Node temp = node(index);
        return temp != null ? temp.obj : null;
    }

    //索引靠前，从前往后找；索引靠后，从后往前找
    public Node node(int index){
        Node temp = null;
        if(first != null){
            if(index < (size >> 1)){
                temp = first;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
            }else {
                temp = last;
                for (int i = size - 1; i > index; i--) {
                    temp = temp.previous;
                }
            }

        }
        return temp;
    }

    public void remove(int index){
        rangeCheck(index);
        Node temp = node(index);
        if(temp != null){
            Node up = temp.previous;
            Node down = temp.next;
            if(up == null){
                first = down;
            }else {
                up.next = down;
                temp.previous = null;
            }
            if(down == null){
                last = up;
            }else {
                down.previous = up;
                temp.next = null;
            }
            temp.obj = null;
            size--;
        }
    }

    public void add(int index,Object obj){
        Node temp = node(index);
        Node newNode = new Node();
        newNode.obj = obj;
        if(temp != null){
            Node up = temp.previous;
            if(up == null){
                temp.previous = newNode;
                newNode.next = temp;
                newNode.previous = null;
                first = newNode;
            }
            up.next = newNode;
            newNode.previous = up;
            newNode.next = temp;
            temp.previous = newNode;
        }else {
            Node n = node(index-1);
            n.next = newNode;
            newNode.previous = n;
            newNode.next = null;
            last = newNode;
        }
        size++;
    }

    public static void main(String[] args) {
        SxtLinkedList list = new SxtLinkedList();
        list.add(111);
        list.add(222);
        list.add(333);
        list.add(3,"BBB");
        System.out.println(list.size);
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }
    }
}

//自定义HashSet
class SxtHashSet{
    HashMap  map;
    private static final Object PRESENT = new Object();

    public SxtHashSet(){
        map = new HashMap();
    }

    public void add(Object o){
        map.put(o,PRESENT);
    }

    public void remove(Object o){
        map.remove(o,PRESENT);
    }

    public int size() {
        return map.size();
    }

    public static void main(String[] args) {
        SxtHashSet set = new SxtHashSet();
        set.add("aaa");
        set.add("bbb");
        set.remove("aaa");
        System.out.println(set.size());
    }
}

class TestIterator{

    public static void main(String[] args) {

        Set set = new HashSet();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Map map = new HashMap();
        map.put("aaa","AAA");
        map.put("bbb","BBB");
        map.put("ccc","CCC");
    }

    public static <T> void test(T t){

    }
}

/**
 *实现一个简单的迭代器原理
 */
class MyArrayList{

    private String[] elem = {"a","b","c","d","e","f","g"};
    private int size = elem.length;

    private int cursor = -1;  //游标

    /**
     * 判断是否有下一个元素
     * @return
     */
    public boolean hasNext(){
        return cursor+1 < size;
    }

    /**
     * 获取下一个元素
     * @return
     */
    public String next(){
        cursor++;    //移动一次
        return elem[cursor];
    }

    /**
     * 删除元素
     */
    public void remove(){

    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        while (list.hasNext()){
            System.out.println(list.next());
        }
    }
}

/**
 * 简化迭代器原理加入接口提供方法
 */
class MyArrayList2 implements Iterable<String>{
    private String[] elem = {"a","b","c","d","e","f","g"};
    private int size = elem.length;

    //相当于包装了一下，这个类实现了Iterator接口,每次迭代完之后cursor发生变化，若再迭代需要重新新建MyArrayList容器，开销
    //增大，用内部类减少开销
    public Iterator<String> iterator(){
        return new Iterator<String>() {
            private int cursor = -1;  //游标

            /**
             * 判断是否有下一个元素
             *
             * @return
             */
            public boolean hasNext() {
                return cursor + 1 < size;
            }

            /**
             * 获取下一个元素
             *
             * @return
             */
            public String next() {
                cursor++;    //移动一次
                return elem[cursor];
            }

            /**
             * 删除元素
             */
            public void remove() {

            }
        };
    }

    public static void main(String[] args) {
        MyArrayList2 list = new MyArrayList2();
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        for (String str:list) {
            System.out.println(str);
        }
    }
}

