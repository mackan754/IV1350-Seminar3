package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/*
 * The AccountingSystem class manages financial records and the cash register's balance.
 * It logs all completed sales and updates the cash register with the payments received.
 */
public class AccountingSystem {

    private ArrayList<Sale> accountingBook; // A record of all completed sales.
    private Amount presentInRegister; // The current amount of money in the cash register.

    /*
     * Constructs a new AccountingSystem with an empty list of sales and initializes the cash register to zero.
     */
    public AccountingSystem() {
        this.accountingBook = new ArrayList<>();
        this.presentInRegister = new Amount(0); // Initializes the register with no money.
    }

    /*
     * Updates the accounting system by adding a completed sale to the accounting record and updating the cash balance.
     *
     * @param sale The completed Sale object to be added to the accounting records.
     * @param payment The payment received for the sale, which will be added to the cash register.
     */
    public void updateAccountingSystem(Sale sale, Amount payment) {
        accountingBook.add(sale); // Adds the sale to the accounting records.
        this.presentInRegister = this.presentInRegister.plus(payment); // Updates the cash register balance.
    }
}
