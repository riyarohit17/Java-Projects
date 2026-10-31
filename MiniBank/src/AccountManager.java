
import model.Account;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AccountManager {

    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void displayAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void sortByBalance() {
        accounts.sort(Comparator.comparingLong(Account::getBalance));
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public int getAccountCount() {
        return accounts.size();
    }
}
