package com.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2019/1/3
 * 单词频率检测
 * 存储到Map中，“分拣”思路
 * 1.为所有的key创建容器，之后存放对应的value
 * 2.第一次创建容器并存放值，第二次直接使用容器存放值
 */
public class WordsCountTest {

    public static void main(String[] args) {
        String str = "this is a cat that is a mice and where is the food";
        //分割字符串
        Map<String,Letter> map = countWord1(str);
        for (Map.Entry<String,Letter> entry:map.entrySet()) {
            System.out.println(entry.getValue().getName()+":"+entry.getValue().getCount());
        }
    }

    /**
     * 方法二
     * @param str
     * @return
     */
    static Map<String, Letter> countWord2(String str){
        String[] strArray = str.split(" ");
        //存储到map中
        Map<String,Letter> map = new HashMap<String,Letter>();
        for (String temp:strArray) {
            if (!map.containsKey(temp)){
                map.put(temp,new Letter(temp,1));
            }else {
                Letter letter = map.get(temp);
                letter.setCount(letter.getCount()+1);
            }
        }
        return map;
    }

    /**
     * 方法一
     * @param str
     * @return
     */
    static Map<String, Letter> countWord1(String str){
        String[] strArray = str.split(" ");
        //存储到map中
        Map<String,Letter> map = new HashMap<String,Letter>();
        for (String temp:strArray) {
            if (!map.containsKey(temp)){
                map.put(temp,new Letter());
            }
            Letter letter = map.get(temp);
            letter.setName(temp);
            letter.setCount(letter.getCount()+1);
        }
        return map;
    }
}

class Letter{
    private String name;
    private int count;

    public Letter() {

    }

    public Letter(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
