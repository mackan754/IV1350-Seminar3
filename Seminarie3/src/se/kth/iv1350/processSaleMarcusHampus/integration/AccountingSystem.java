package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;

import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AccountingSystem {
    private ArrayList<Sale> accountingBook;
    private Amount presentInRegister;

    public AccountingSystem() {
        this.accountingBook = new ArrayList<>();
        this.presentInRegister = new Amount(0);
    }

    public void updateAccountingSystem(Sale sale, Amount payment) {
        accountingBook.add(sale);
        this.presentInRegister = this.presentInRegister.plus(payment);
    }
}
