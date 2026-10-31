package Practical_9;

class MyThread extends Thread {

    public void run() {

        System.out.println(Thread.currentThread().getName() + " is working");

        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            sum += i;

            System.out.println(Thread.currentThread().getName());
        }

        System.out.println(Thread.currentThread().getName());

        System.out.println(Thread.currentThread().getName()
                + " finished. Sum = " + sum);
    }
}

public class Main {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();

        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t3.setName("Thread 3");
        t4.setName("Thread 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}