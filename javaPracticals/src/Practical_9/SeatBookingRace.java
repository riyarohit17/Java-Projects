package Practical_9;
import java.util.concurrent.CountDownLatch;

public class SeatBookingRace {

    static class BookingSystem {
        int seatsLeft = 5;
        int successfulBookings = 0;
        boolean synchronizedMode;

        BookingSystem(boolean synchronizedMode) {
            this.synchronizedMode = synchronizedMode;
        }

        void book() {
            if (synchronizedMode) {
                synchronizedBook();
            } else {
                unsafeBook();
            }
        }

        void unsafeBook() {
            if (seatsLeft > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                seatsLeft--;
                successfulBookings++;
                System.out.println(
                        Thread.currentThread().getName()
                                + " booked successfully!"
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName()
                                + " failed! No seats available."
                );
            }
        }

        synchronized void synchronizedBook() {
            if (seatsLeft > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                seatsLeft--;
                successfulBookings++;
                System.out.println(
                        Thread.currentThread().getName()
                                + " booked successfully!"
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName()
                                + " failed! No seats available."
                );
            }
        }
    }

    static void runTest(boolean sync)
            throws InterruptedException {

        BookingSystem system = new BookingSystem(sync);
        Thread[] threads = new Thread[10];
        CountDownLatch start = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                try {
                    start.await();
                    system.book();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "User-" + (i + 1));

            threads[i].start();
        }
        start.countDown();

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Successful Bookings: "
                + system.successfulBookings);
        System.out.println("Seats Left: "
                + system.seatsLeft);
    }
    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("WITHOUT SYNCHRONIZATION");
        runTest(false);

        System.out.println("\nWITH SYNCHRONIZATION");
        runTest(true);
    }
}
