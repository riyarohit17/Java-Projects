
import exception.InvalidAmountException;
import exception.InsufficientFundsException;
import exception.BankException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;

import model.Account;
import model.Customer;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import service.WithdrawRule;
import service.BankService;
import util.Command;
import util.CommandParser;
import util.StatementFormatter;
import util.Validator;
import util.GenericRepository;

public class MiniBank {

    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");
        BankService bankService = new BankService();

        System.out.println("---------------------------------");
        System.out.println("Bank Name: " + bank.name());
        System.out.println("Branch: " + bank.branch());
        System.out.println("---------------------------------");

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n----- MiniBank Menu -----");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter owner name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter initial balance: ");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid amount.");
                            sc.nextLine();
                            break;
                        }

                        long initialBalance = sc.nextLong();
                        sc.nextLine();

                        Account newAccount =
                                bankService.openAccount(name, initialBalance);

                        System.out.println("Account opened successfully!");
                        System.out.println(newAccount);
                        break;

                    case 2:
                        System.out.print("Enter account number: ");
                        String depositAccount = sc.nextLine();

                        System.out.print("Enter deposit amount: ");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid amount.");
                            sc.nextLine();
                            break;
                        }

                        long depositAmount = sc.nextLong();
                        sc.nextLine();

                        bankService.deposit(depositAccount, depositAmount);

                        System.out.println("Money deposited successfully!");
                        System.out.println(
                                bankService.statement(depositAccount));
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        String withdrawAccount = sc.nextLine();

                        System.out.print("Enter withdrawal amount: ");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid amount.");
                            sc.nextLine();
                            break;
                        }

                        long withdrawAmount = sc.nextLong();
                        sc.nextLine();

                        bankService.withdraw(withdrawAccount, withdrawAmount);

                        System.out.println("Money withdrawn successfully!");
                        System.out.println(
                                bankService.statement(withdrawAccount));
                        break;

                    case 4:
                        System.out.print("Enter sender account number: ");
                        String fromAccount = sc.nextLine();

                        System.out.print("Enter receiver account number: ");
                        String toAccount = sc.nextLine();

                        System.out.print("Enter transfer amount: ");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid amount.");
                            sc.nextLine();
                            break;
                        }

                        long transferAmount = sc.nextLong();
                        sc.nextLine();

                        bankService.transfer(
                                fromAccount, toAccount, transferAmount);

                        System.out.println("Transfer successful!");
                        System.out.println(
                                bankService.statement(fromAccount));
                        System.out.println(
                                bankService.statement(toAccount));
                        break;

                    case 5:
                        System.out.println(
                                "Thank you for using MiniBank!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }
            } catch (BankException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            }
        }

        System.out.println("\n----- Account Testing -----");

        Customer customer = new Customer(
                "Riya", "riya@gmail.com", "9876543210");

        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Customer Name: " + customer.getName());

        Account[] accounts = {
                new SavingsAccount("Riya", 5000, 1000),
                new CurrentAccount("Aarav", 3000, 2000),
                new FixedDepositAccount("Priya", 5000)
        };

        try {
            accounts[0].deposit(2000);
            System.out.println(
                    "After Deposit: " + accounts[0].getBalance());

            boolean success = accounts[0].withdraw(3000);
            System.out.println("Withdraw 3000: " + success);
            System.out.println("Balance: " + accounts[0].getBalance());

            accounts[0].withdraw(10000);
            System.out.println("Withdraw 10000 successful");

        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());

        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());

        } finally {
            System.out.println("Transaction test completed.");
        }

        System.out.println("Withdrawal test finished.");
        System.out.println("Balance: " + accounts[0].getBalance());

        System.out.println("\nAll Accounts:");

        for (Account a : accounts) {
            System.out.println(a.getAccountNumber() + " | "
                    + a.getOwnerName() + " | " + a.getBalance());
        }

        System.out.println("\n----- Object Testing -----");

        Account a1 = accounts[0];
        Account a2 = accounts[1];

        System.out.println("Account Details: " + a1);
        System.out.println("Same Account: " + a1.equals(a1));
        System.out.println("Different Accounts: " + a1.equals(a2));
        System.out.println("Instanceof Account: " + (a1 instanceof Account));

        Customer.Address address =
                new Customer.Address("Main Road", "Anand", "388001");

        customer.setAddress(address);
        Customer clonedCustomer = customer.clone();

        System.out.println("Customer City: "
                + customer.getAddress().getCity());
        System.out.println("Cloned Customer: " + clonedCustomer.getName());

        System.out.println("\n----- Validation Testing -----");

        System.out.println("Valid Mobile: "
                + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid Mobile: "
                + Validator.isValidMobile("12345"));
        System.out.println("Valid Email: "
                + Validator.isValidEmail("abc@gmail.com"));
        System.out.println("Invalid Email: "
                + Validator.isValidEmail("abcgmail.com"));
        System.out.println("Valid PAN: "
                + Validator.isValidPan("ABCDE1234F"));
        System.out.println("Invalid PAN: "
                + Validator.isValidPan("ABC123"));
        System.out.println("Valid IFSC: "
                + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("Invalid IFSC: "
                + Validator.isValidIfsc("12345"));

        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");

        System.out.println("Type: " + cmd.type());
        System.out.println("Account Number: " + cmd.accountNumber());
        System.out.println("Amount: " + cmd.amount());

        System.out.println(StatementFormatter.buildStatement(accounts[0]));

        System.out.println("\n----- Polymorphism Testing -----");

        for (Account a : accounts) {
            System.out.println(a.getClass().getSimpleName()
                    + " | Interest Rate: " + a.interestRate() + "%");
        }

        System.out.println("\nWithdrawal Rules:");

        for (Account a : accounts) {
            System.out.println(a.getClass().getSimpleName()
                    + " | Can Withdraw 500: " + a.canWithdraw(500));
        }

        if (accounts[0] instanceof SavingsAccount) {
            System.out.println(
                    "Savings account identified using instanceof");
        }

        System.out.println("\n----- Interface Testing -----");

        WithdrawRule anonymousRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount > 0 && account.canWithdraw(amount);
            }
        };

        WithdrawRule lambdaRule =
                (account, amount) -> amount > 0
                        && account.canWithdraw(amount);

        System.out.println("Anonymous Class: "
                + anonymousRule.allow(accounts[0], 500));
        System.out.println("Lambda Expression: "
                + lambdaRule.allow(accounts[0], 500));
        System.out.println("Yearly Interest: "
                + accounts[0].yearlyInterest());

        System.out.println("\n Annotation Validation");

        Account testAccount = new SavingsAccount("Test", -100, 0);

        String[] errors = util.AnnotationValidator.validate(testAccount);

        for (String error : errors) {
            System.out.println(error);
        }

        System.out.println("\n Multithreading");

        Account threadAccount = new SavingsAccount("ThreadTest", 1000, 0);

        BankingTask t1 = new BankingTask(threadAccount, 500);
        BankingTask t2 = new BankingTask(threadAccount, 700);

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Balance: " + threadAccount.getBalance());

        System.out.println("\n Transaction Processing");

        TransactionProcessor.runDemo();

        System.out.println(
                "Deadlock Prevention: Use a consistent lock order.");

        System.out.println("\n Saving and Reporting");

        Path dataFile = Path.of("data", "accounts.dat");
        Path logFile = Path.of("logs", "transactions.log");
        Path reportFile = Path.of("data", "daily-report.txt");

        try {
            util.TransactionLog.append(logFile, "DEPOSIT,2000");
            util.TransactionLog.append(logFile, "WITHDRAW,500");

            util.StatePersister.save(accounts, dataFile);
            System.out.println("Accounts saved successfully.");

            Account[] loadedAccounts = util.StatePersister.load(dataFile);

            System.out.println("Loaded accounts:");

            for (Account account : loadedAccounts) {
                System.out.println(account);
            }

            util.ReportGenerator.generate(Path.of("logs"), reportFile);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }

        System.out.println("\n Collections and Sorting");

        AccountManager manager = new AccountManager();

        for (Account account : accounts) {
            manager.addAccount(account);
        }

        System.out.println("Accounts before sorting:");
        manager.displayAccounts();

        manager.sortByBalance();

        System.out.println("Accounts sorted by balance:");
        manager.displayAccounts();

        System.out.println("Total accounts: " + manager.getAccountCount());

        System.out.println("\n Generic Repository");

        GenericRepository<Account> repository = new GenericRepository<>();

        for (Account account : accounts) {
            repository.save(account);
        }

        System.out.println("Repository count: " + repository.count());

        Account found = repository.findById(
                accounts[0].getAccountNumber());

        if (found != null) {
            System.out.println("Found account: " + found);
        } else {
            System.out.println("Account not found.");
        }

        sc.close();
    }
}
