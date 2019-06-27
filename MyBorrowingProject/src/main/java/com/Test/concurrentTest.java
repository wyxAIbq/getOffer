package com.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 王彦鑫 on 2018/12/25
 */
public class concurrentTest {
    Object o = new Object();
    public synchronized void m1(){ // 重量级的访问操作。
        System.out.println("public synchronized void m1() start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("public synchronized void m1() end");
    }

    public void m3(){
        synchronized(o){
            System.out.println("public void m3() start");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("public void m3() end");
        }
    }

    public void m2(){
        System.out.println("public void m2() start");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("public void m2() end");
    }

    public static class MyThread01 implements Runnable{
        public MyThread01(int i, concurrentTest t){
            this.i = i;
            this.t = t;
        }
        int i ;
        concurrentTest t;
        public void run(){
            if(i == 0){
                t.m1();
            }else if (i > 0){
                t.m2();
            }else {
                t.m3();
            }
        }
    }

    public static void main(String[] args) {
        concurrentTest t = new concurrentTest();
        new Thread(new concurrentTest.MyThread01(0, t)).start();
        new Thread(new concurrentTest.MyThread01(1, t)).start();
        new Thread(new concurrentTest.MyThread01(-1, t)).start();
    }
}

class Test_05 {
    private double d = 0.0;
    boolean flag = false;
    public synchronized void m1(double d){
        if(!flag){
            try {
                // 相当于复杂的业务逻辑代码。
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.d = d;
        }
            flag = true;
    }

    public double m2(){
        if(flag){
            return this.d;
        }
        flag = false;
        return -1;
    }

    public static void main(String[] args) {
        final Test_05 t = new Test_05();

        new Thread(() -> t.m1(100)).start();

        System.out.println(t.m2());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.m2());
    }

}

class Test_06 {

    synchronized void m1(){ // 锁this
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }
    synchronized void m2(){ // 锁this
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {

        new Test_06().m1();

    }

}
/**
 * synchronized关键字
 * 同步方法 - 继承
 * 子类同步方法覆盖父类同步方法。可以指定调用父类的同步方法。
 * 相当于锁的重入。
 */
class Test_07 {

    synchronized void m(){
        System.out.println("Super Class m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Super Class m end");
    }

    public static void main(String[] args) {
        new Sub_Test_07().m();
    }

}

class Sub_Test_07 extends Test_07{
    synchronized void m(){
        System.out.println("Sub Class m start");
        super.m();
        System.out.println("Sub Class m end");
    }
}

/**
 * synchronized关键字
 * 同步方法 - 锁与异常
 * 当同步方法中发生异常的时候，自动释放锁资源。不会影响其他线程的执行。
 * 注意，同步业务逻辑中，如果发生异常如何处理。
 */
class Test_08 {
    int i = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + " - start");
        while(true){
            i++;
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(i == 5){
                i = 1/0;
            }
        }
    }

    public static void main(String[] args) {
        final Test_08 t = new Test_08();
        new Thread(t::m, "t1").start();

        new Thread(t::m, "t2").start();
    }

}

/**
 * volatile关键字
 * volatile的可见性
 * 通知OS操作系统底层，在CPU计算过程中，都要检查内存中数据的有效性。保证最新的内存数据被使用。
 *
 */
class Test_09 {

    volatile boolean b = true;

    void m(){
        System.out.println("start");
        while(b){}
        System.out.println("end");
    }

    public static void main(String[] args) {
        final Test_09 t = new Test_09();
        new Thread(t::m).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        t.b = false;
    }

}

/**
 * volatile关键字
 * volatile的非原子性问题
 * volatile， 只能保证可见性，不能保证原子性。
 * 不是枷锁问题，只是内存数据可见。
 */
class Test_10 {

    volatile int count = 0;
    synchronized void m(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }

    public static void main(String[] args) {
        final Test_10 t = new Test_10();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    t.m();
                }
            }));
        }
        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(t.count);
    }
}

/**
 * AtomicXxx
 * 同步类型
 * 原子操作类型。 其中的每个方法都是原子操作。可以保证线程安全。
 */
 class Test_11 {
    AtomicInteger count = new AtomicInteger(0);
    void m(){
        for(int i = 0; i < 10000; i++){
            /*if(count.get() < 1000)*/
            count.incrementAndGet();//相当于++i
        }
    }

    public static void main(String[] args) {
        final Test_11 t = new Test_11();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    t.m();
                }
            }));
        }
        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(t.count.intValue());
    }
}



