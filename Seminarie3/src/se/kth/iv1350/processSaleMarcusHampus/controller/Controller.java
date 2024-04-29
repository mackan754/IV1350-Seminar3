package se.kth.iv1350.processSaleMarcusHampus.controller;

import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class Controller {
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;

    public Controller(AccountingSystem accountingSystem, InventorySystem inventorySystem, Printer printer) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
    }

    public void startNewSale() {
        this.sale = new Sale();
    }

    public String addItem(String itemIdentifier, Amount quantity) {
        Item itemToBeAdded = inventorySystem.fetchItem(itemIdentifier);
        itemToBeAdded.setQuantity(quantity);
        sale.addItem(itemToBeAdded);

        return generateItemDetails(itemToBeAdded);
    }

    private String generateItemDetails(Item item) {
        String itemDetails = "item name: " + item.getItemInformation().getItemName() +
                ", price: " + item.getItemInformation().getItemPrice() +
                ", tax amount: " + item.getItemInformation().getItemTaxAmount() +
                ", quantity: " + item.getQuantity() +
                ", running total: " + sale.getTotal();
        return itemDetails;
    }

    public String displayTotal() {
        return sale.getTotal().toString();
    }

    public String displayTotalIncludingTax() {
        return sale.getTotalIncludingTax().toString();
    }

    public String enterPayment(Amount payment) {
        Amount change = payment.minus(sale.getTotalIncludingTax());
        Receipt receipt = new Receipt(sale);
        printer.print(receipt);
        inventorySystem.updateInventorySystem(sale);
        accountingSystem.updateAccountingSystem(sale, payment);
        sale = null;
        return change.toString();
    }
}
