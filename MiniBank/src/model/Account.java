
package model;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.Positive;

public abstract class Account implements Transactable, InterestBearing, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private final String accountNumber;

    private String ownerName;

    @Positive
    protected long balance;

    private boolean active;

    private static long accountCounter = 0;

    public Account(String ownerName, long balance) {
        accountCounter++;
        this.accountNumber = String.format("AC%04d", accountCounter);
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    @Override
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }

        balance += amount;
    }

    @Override
    public boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (!canWithdraw(amount)) {
            long shortfall = Math.max(0, amount - balance);

            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal.", shortfall);
        }

        balance -= amount;
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;

        Account other = (Account) obj;
        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);
}
