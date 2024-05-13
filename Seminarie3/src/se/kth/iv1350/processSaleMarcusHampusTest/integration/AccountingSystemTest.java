package se.kth.iv1350.processSaleMarcusHampus.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AccountingSystemTest {

    private AccountingSystem accountingSystem;

    @Before
    public void setUp() {
        accountingSystem = new AccountingSystem();
    }

    @Test
    public void testInitialization() {
        assertTrue("Accounting book should be empty upon initialization", accountingSystem.getAccountingBook().isEmpty());
        assertEquals("Cash register should be initialized to 0", new Amount(0), accountingSystem.getPresenInRegister());
    }

    @Test
    public void testUpdateAccountingSystem() {
        Sale testSale = new Sale(); // Assuming there's a no-arg constructor
        Amount payment = new Amount(100); // Amount of 100 for simplicity
        accountingSystem.updateAccountingSystem(testSale, payment);

        assertEquals("The accounting book should have one sale recorded", 1, accountingSystem.getAccountingBook().size());
        assertTrue("The recorded sale should be the one we added", accountingSystem.getAccountingBook().contains(testSale));
        assertEquals("Cash register should be updated by the payment amount", new Amount(100), accountingSystem.getPresenInRegister());
    }

    @Test
    public void testGetAccountingBook() {
        Sale testSale = new Sale();
        accountingSystem.updateAccountingSystem(testSale, new Amount(50));
        ArrayList<Sale> retrievedBook = accountingSystem.getAccountingBook();

        assertTrue("The retrieved accounting book should contain the added sale", retrievedBook.contains(testSale));
    }
}
