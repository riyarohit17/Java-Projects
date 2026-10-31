package Practical_14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    void testAddAccount() {
        BankService bank = new BankService();
        bank.addAccount(1, "Riya");
        assertEquals(1, bank.getCount());
    }

    @Test
    void testMultipleAccounts() {
        BankService bank = new BankService();
        bank.addAccount(1, "Riya");
        bank.addAccount(2, "Amit");
        assertEquals(2, bank.getCount());
    }

    @Test
    void testInvalidAccount() {
        BankService bank = new BankService();
        assertThrows(IllegalArgumentException.class,
                () -> bank.addAccount(-1, "Test"));
    }
}

