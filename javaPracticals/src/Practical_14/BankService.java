package Practical_14;

import java.util.ArrayList;

public class BankService {
    ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        accounts.add(new Account(id, name));
    }

    public int getCount() {
        return accounts.size();
    }
}
