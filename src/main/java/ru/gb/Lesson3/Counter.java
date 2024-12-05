package ru.gb.Lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private static final int ITERATIONS = 100;
    private static int value = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS / 2; i++) {
                lock.lock();
                value++;
                System.out.println("Count 1 - " + value);
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS / 2; i++) {
                lock.lock();
                value++;
                System.out.println("Count 2 - " + value);
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Counter: " + value);
    }
}
