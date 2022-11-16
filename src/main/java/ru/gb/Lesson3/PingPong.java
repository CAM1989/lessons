package ru.gb.Lesson3;

public class PingPong {
    private static boolean flag;
    private static Object monitor = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    if (flag) {
                        System.out.println("Ping");
                        try {
                            Thread.sleep(1000);
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        monitor.notify();
                        flag = true;
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    if (!flag) {
                        System.out.println("Pong");
                        try {
                            Thread.sleep(1000);
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        monitor.notify();
                        flag = false;
                    }
                }
            }
        });
        thread2.start();
    }
}