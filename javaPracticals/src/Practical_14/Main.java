package Practical_14;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {
            System.out.println("\n1. Add Account");
            System.out.println("2. Show Count");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    try {
                        bank.addAccount(id, name);
                        System.out.println("Account Added!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Total Accounts: "
                            + bank.getCount());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
