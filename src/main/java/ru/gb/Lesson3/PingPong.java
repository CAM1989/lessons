package ru.gb.Lesson3;

public class PingPong {
    private static boolean flag;
    private static Object monitor = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    while (flag) {
                        System.out.println("Ping");
                        try {
                            Thread.sleep(1000);
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    monitor.notify();
                    flag = true;
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    while (!flag) {
                        System.out.println("Pong");
                        try {
                            Thread.sleep(1000);
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    monitor.notify();
                    flag = false;
                }

            }
        });
        thread2.start();
    }
}