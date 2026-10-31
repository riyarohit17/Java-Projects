import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransactionProcessor {

    private static final BlockingQueue<String> queue =
            new LinkedBlockingQueue<>();

    public static void runDemo() {
        Thread producer = new Thread(() -> {
            try {
                queue.put("Deposit 500");
                queue.put("Withdraw 200");
                queue.put("Transfer 100");
                queue.put("STOP");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String transaction = queue.take();

                    if (transaction.equals("STOP")) {
                        break;
                    }

                    System.out.println("Processing: " + transaction);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All transactions processed.");
    }
}
