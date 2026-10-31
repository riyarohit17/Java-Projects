
import model.Account;

public class BankingTask extends Thread {

    private Account account;
    private long amount;

    public BankingTask(Account account, long amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (account) {
            System.out.println(
                    Thread.currentThread().getName()
                            + " is depositing " + amount
            );

            try {
                account.deposit(amount);
                System.out.println(
                        "Balance after deposit: "
                                + account.getBalance()
                );
            } catch (exception.InvalidAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
