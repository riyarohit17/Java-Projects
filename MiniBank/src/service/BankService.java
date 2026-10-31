package service;
import model.Account;
import model.SavingsAccount;
import util.GenericRepository;
import exception.BankException;
import exception.InvalidAmountException;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import java.util.List;

public class BankService {

    private final GenericRepository<Account> repository =
            new GenericRepository<>();

    public Account openAccount(String ownerName, long initialBalance)
            throws InvalidAmountException {

        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new InvalidAmountException("Owner name cannot be empty.");
        }

        if (initialBalance < 0) {
            throw new InvalidAmountException("Balance cannot be negative.");
        }

        Account account = new SavingsAccount(ownerName, initialBalance, 0);
        repository.save(account);
        return account;
    }

    private Account getAccount(String accountNumber)
            throws AccountNotFoundException {

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        return account;
    }

    public void deposit(String accountNumber, long amount)
            throws BankException {

        getAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, long amount)
            throws BankException {

        getAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String fromNumber, String toNumber, long amount)
            throws BankException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Transfer amount must be greater than zero.");
        }

        Account from = getAccount(fromNumber);
        Account to = getAccount(toNumber);

        if (from.equals(to)) {
            throw new InvalidAmountException(
                    "Cannot transfer to the same account.");
        }

        from.withdraw(amount);
        try {
            to.deposit(amount);
        } catch (BankException e) {
            from.deposit(amount);
            throw e;
        }
    }
    public String statement(String accountNumber)
            throws AccountNotFoundException {
        return getAccount(accountNumber).toString();
    }
    public List<Account> listAccounts() {
        return repository.findAll();
    }
}
