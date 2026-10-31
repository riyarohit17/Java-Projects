package Practical_8;

import java.util.Scanner;

class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    int stock;

    Warehouse(int stock) {
        this.stock = stock;
    }

    void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0"
            );
        }

        if (qty > stock) {
            int shortfall = qty - stock;
            throw new OutOfStockException(shortfall);
        }

        stock = stock - qty;

        System.out.println(
                item + " issued successfully!"
        );
        System.out.println(
                "Remaining stock = " + stock
        );
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter initial stock: ");
        int stock = sc.nextInt();

        Warehouse w = new Warehouse(stock);

        System.out.print("Enter number of requests: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nRequest " + i);

            System.out.print("Enter item name: ");
            String item = sc.next();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            try {

                w.issue(item, qty);

            } catch (OutOfStockException e) {

                System.out.println(
                        "Out of stock! Shortfall = " + e.shortfall
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                        "Invalid quantity: " + e.getMessage()
                );
            }
        }

        System.out.println("\nAll requests processed.");

        sc.close();
    }
}