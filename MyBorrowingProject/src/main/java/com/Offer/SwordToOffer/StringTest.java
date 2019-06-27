package com.Offer.SwordToOffer;

import java.util.*;

/**
 * Created by 王彦鑫 on 2019/3/23
 * 字符串操作题
 */
public class StringTest {
}

/**
 * 1、替换空格
 * 将一个字符串中的每个空格替换成“%20”，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy
 */
class replaceSpace {
    public String replaceSpace(StringBuffer str) {
        String string = str.toString();
        if(string.equals("")){
            return string;
        }
        char[] strArray = string.toCharArray();
        int i = 0;
        int spaceCount = 0;
        while(i < strArray.length){
            if(strArray[i] == ' ')
                spaceCount++;
            i++;
        }
        int newStrLength = strArray.length+spaceCount*2;
        char[] newStrArray = new char[newStrLength];
        int p1 = strArray.length-1;
        int p2 = newStrLength-1;
        while(p1 >= 0){
            if(strArray[p1] != ' '){
                newStrArray[p2--] = strArray[p1--];
            }else{
                newStrArray[p2--] = '0';
                newStrArray[p2--] = '2';
                newStrArray[p2--] = '%';
                p1--;
            }
        }
        return new String(newStrArray);
    }
}


/**
 * 2、字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
class Permutation {
    private ArrayList<String> result = new ArrayList<>();
    private TreeSet<String> set = new TreeSet<>();//使用一个TreeSet保证有序和无重复

    public ArrayList<String> Permutation(String str) {
        char [] strArray = str.toCharArray();
        Permutation(strArray,0);
        while(!set.isEmpty())
            result.add(set.pollFirst());
        return result;
    }

    public void Permutation(char [] strArray,int index)
    {
        if(index == strArray.length - 1)
        {//这里的话就是每当到了最后一个字符的时候，就添加一个字符串
            set.add(String.valueOf(strArray));
        }else{
            for(int i=index;i<strArray.length;i++)
            {
                swap(strArray,index,i);
                Permutation(strArray,index+1);
                swap(strArray,index,i);
            }
        }
    }
    public void swap(char [] strArray,int i,int j)
    {
        char ch = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = ch;
    }
}

/**
 * 3、第一个只出现一次的字符
 */
class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        if(str == null)
            return -1;
        char[] cArray = str.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int n = 0;
        for (char aCArray : cArray) {
            if (map.get(aCArray) == null) {
                map.put(aCArray, 1);
            } else {
                map.put(aCArray, map.get(aCArray) + 1);
            }
        }
        for(int i = 0; i < cArray.length; i++){
            if(map.get(cArray[i]) == 1)
                return i;
        }
        return -1;
    }
}

/**
 * 4、左旋字符串
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 */
class LeftRotateString {
    public String LeftRotateString(String str,int n) {
        String result = "";
        int length = str.length();
        if(length < 1){
            return result;
        }
        n = n%length;
        char[] cArray = str.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < length; i++){
            queue.offer(cArray[i]);
        }
        for(int i = 0; i < n; i++){
            char c = queue.poll();
            queue.offer(c);
        }
        for(int i = 0; i < length; i++){
            cArray[i] = queue.poll();
        }
        result = String.valueOf(cArray);
        return result;
    }
}

/**
 * 5、翻转单词顺序
 * “student. a am I”的句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 */
class ReverseSentence {
    public String ReverseSentence(String str) {
        if(str.trim().equals("")){
            return str;
        }
        String[] a = str.split(" ");
        StringBuilder o = new StringBuilder();
        int i;
        for (i = a.length; i >0;i--){
            o.append(a[i-1]);
            if(i > 1){
                o.append(" ");
            }
        }
        return o.toString();
    }
}

/**
 * 6、字符串转换成整数
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。数值为0或者字符串不是一个合法的数值则返回0。
 */
class StrToInt {
    public int StrToInt(String str) {
        if(str == null || str == "" || str.equals("+") || str.equals("-")){
            return 0;
        }
        int flag = 0;
        long sum = 0;
        char[] strArray = str.toCharArray();
        for(int i = 0; i < strArray.length; i++){
            if(strArray[0] == '-' && i == 0){
                flag = 1;
                continue;
            }
            if(strArray[0] == '+' && i == 0){
                continue;
            }
            if(!judge(strArray[i]))
                return 0;
            sum = sum*10+strArray[i] - '0';
        }
        if(flag == 1){
            sum = sum*(-1);
            if(sum < Integer.MIN_VALUE)
                return 0;
            return (int)sum;
        }
        if(sum > Integer.MAX_VALUE){
            return 0;
        }
        return (int)sum;
    }
    //利用asc码，不在这个范围内的asc码直接返回false
    private boolean judge(char ch){
        int number = ch - '0';
        if(number >= 0 && number <= 9){
            return true;
        }
        return false;
    }
}

/**
 * 7、字符流中第一个不重复的字符
 */
class FirstAppearingOnce {
    private int[] hashtable=new int[256];
    private StringBuffer s=new StringBuffer();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        s.append(ch);
        if(hashtable[ch]==0)
            hashtable[ch]=1;
        else hashtable[ch]+=1;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char[] str=s.toString().toCharArray();
        for(char c:str)
        {
            if(hashtable[c]==1)
                return c;
        }
        return '#';
    }
}



