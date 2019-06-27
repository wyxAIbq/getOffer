package com.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 自定义实现map的功能
 * Created by 王彦鑫 on 2018/12/11
 */
public class SxtMap {

    SxtEntry[] arr = new SxtEntry[990];
    int size;
    public void put(Object key,Object value){

        for (int i = 0; i < size; i++) {
            if(arr[i].key.equals(key)){
                arr[i].value = value;
                return;
            }
        }
        SxtEntry e = new SxtEntry(key,value);
        arr[size++] = e;
    }

    //这样查询效率低，需要改进
    public Object get(Object key){
        for (int i = 0; i < size; i++) {
            if(arr[i].key.equals(key)){
                return arr[i].value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SxtMap m = new SxtMap();
        m.put("wang","bin");
        System.out.println(m.get("wang"));
    }
}

class SxtEntry{
    Object key;
    Object value;

    SxtEntry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

class SxtMap002{
    private LinkedList[] arr = new LinkedList[990];  //Map的底层=数组+链表
    int size;

    public void put(Object key,Object value){
        SxtEntry e = new SxtEntry(key,value);

        int hash = key.hashCode();
        hash = hash<0?-hash:hash;
        int a =hash%arr.length;
        if(arr[a] == null){
            LinkedList list = new LinkedList();
            arr[a] = list;
            list.add(e);
        }else {
            LinkedList list = arr[a];
            for (int i = 0; i < list.size(); i++) {
                SxtEntry e2 = (SxtEntry) list.get(i);
                if(e2.key.equals(key)){
                    e2.value = value;  //键值重复直接覆盖,然后跳出
                    return;
                }
            }
            arr[a].add(e);
        }
    }

    public Object get(Object key){
        int hash = key.hashCode();
        hash = hash<0?-hash:hash;
        int a =hash%arr.length;
        if(arr[a] != null){
            LinkedList list = arr[a];
            for (int i = 0; i < list.size(); i++) {
                SxtEntry e = (SxtEntry) list.get(i);
                if(e.key.equals(key)){
                    return e.value;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SxtMap002 m = new SxtMap002();
        m.put("wang","bin");
        m.put("yan","qiong");
        m.put("wang","binqiong");
        System.out.println(m.get("wang"));

        Map map = new HashMap();
        map.put("wang","bin");
        map.put("yan","qiong");
        map.put("wang","binqiong");


    }
}


