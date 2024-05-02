package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * The AccountingSystem class manages financial records and the cash register's balance.
 * It logs all completed sales and updates the cash register with the payments received.
 */
public class AccountingSystem {

    private ArrayList<Sale> accountingBook;
    private Amount presentInRegister;

    /**
     * Constructs a new AccountingSystem with an empty list of sales and initializes the cash register to zero.
     */
    public AccountingSystem() {
        this.accountingBook = new ArrayList<>();
        this.presentInRegister = new Amount(0);
    }

    /**
     * Provides the total amount present in the register.
     * 
     * @return the total amount as Amount.
     */
    public Amount getPresenInRegister() {
        return presentInRegister;
    }

    /**
     * Provides all recorded sales in the AccountingSystem.
     * 
     * @return all sales as ArrayList
     */
    public ArrayList<Sale> getAccountingBook() {
        return accountingBook;
    }


    /**
     * Updates the accounting system by adding a completed sale to the accounting record and updating the cash balance.
     *
     * @param sale The completed Sale object to be added to the accounting records.
     * @param payment The payment received for the sale, which will be added to the cash register.
     */
    public void updateAccountingSystem(Sale sale, Amount payment) {
        accountingBook.add(sale);
        this.presentInRegister = this.presentInRegister.plus(payment);
    }
}
