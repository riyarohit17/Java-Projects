import java.util.concurrent.*;

public class Task implements Runnable {

    int id;

    Task(int id) {
        this.id = id;
    }

    public void run() {

        System.out.println("Task " + id + " running by "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println("Task " + id + " completed by "
                + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            pool.submit(new Task(i));
        }

        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All tasks completed.");
    }
}