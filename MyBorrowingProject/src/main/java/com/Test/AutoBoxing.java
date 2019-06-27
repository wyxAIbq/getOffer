package com.Test;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 王彦鑫 on 2018/12/5
 */
public class AutoBoxing {
    public static int race = 0;
    public static void increase(){
        synchronized (AutoBoxing.class){
            race++;
        }
    }

    private static final int count = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[count];
        for (int i = 0; i <count ; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(race);
    }
}

class LockTest {
    private static int count = 0;
    private static final int times = 10000;

    // 使用Lock实现，多线程的数据同步
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        long curTime = System.nanoTime();

        Thread decThread = new DecThread();
        decThread.start();

        System.out.println("Start thread: " + Thread.currentThread() + " i++");

        for (int i = 0; i < times; i++) {
            // 进行自加的操作
            try {
                lock.lock();
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        System.out.println("End thread: " + Thread.currentThread() + " i++");

        // 等待decThread结束
        while (decThread.isAlive())
            ;

        long duration = System.nanoTime() - curTime;
        System.out.println("Result: " + count);
        System.out.format("Duration: %.2fs\n", duration / 1.0e9);
    }

    private static class DecThread extends Thread {

        @Override
        public void run() {
            System.out.println("Start thread: " + Thread.currentThread()
                    + " i--");
            for (int i = 0; i < times; i++) {
                // 进行自减的操作
                try {
                    lock.lock();
                    count--;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("End thread: " + Thread.currentThread() + " i--");
        }
    }
}

class SynchronizedTest {
    private static int count = 0;
    private static final int times = 1000000;

    public static Vector<Integer> vector = new Vector<>();
    public static void main(String[] args) {

        long curTime = System.nanoTime();

        Thread decThread = new DecThread();
        decThread.start();

        System.out.println("Start thread: " + Thread.currentThread() + " i++");

        for (int i = 0; i < times; i++) {
            // 进行自加的操作
            synchronized (SynchronizedTest.class) {
                count++;
            }

        }

        System.out.println("End thread: " + Thread.currentThread() + " i++");

        // 等待decThread结束
        while (decThread.isAlive())
            ;

        long duration = System.nanoTime() - curTime;
        System.out.println("Result: " + count);
        System.out.format("Duration: %.2fs\n", duration / 1.0e9);
    }

    private static class DecThread extends Thread {

        @Override
        public void run() {
            System.out.println("Start thread: " + Thread.currentThread()
                    + " i--");
            for (int i = 0; i < times; i++) {
                // 进行自减的操作
                synchronized (SynchronizedTest.class) {
                    count--;
                }
            }
            System.out.println("End thread: " + Thread.currentThread() + " i--");
        }
    }
}

