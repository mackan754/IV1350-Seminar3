package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AccountingSystemTest {

    private AccountingSystem accountingSystem;
    private Sale sale;

    @Before
    public void setUp() {
        accountingSystem = new AccountingSystem();
        sale = new Sale();
    }

    @After
    public void tearDown() {
        accountingSystem = null;
        sale = null;
    }

    @Test
    public void testUpdateAccountingSystem() {
        Amount payment = new Amount(100); // Payment of 100 units
        Amount initialBalance = accountingSystem.getPresenInRegister(); // Get initial balance
        accountingSystem.updateAccountingSystem(sale, payment);
        assertEquals(1, accountingSystem.getAccountingBook().size()); // Assert that the sale is added to the accounting
                                                                      // records
        assertEquals(initialBalance.plus(payment).getAmount(), accountingSystem.getPresenInRegister().getAmount()); // Assert that the cash
                                                                                             // register balance is
                                                                                             // updated correctly
    }

}
