package se.kth.iv1350.processSaleMarcusHampus.controller;

/*
 * Imports relevant code for interaction with accounting, inventory, printing services, and model management.
 */
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/*
 * The Controller class acts as the central part of the application, coordinating interactions
 * between the user interface and the integration/model layers.
 */
public class Controller {

    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;

    /*
     * Initializes a new Controller with necessary external systems for accounting, inventory management, and printing.
     * 
     * @param accountingSystem the accounting system to be used for financial transactions
     * @param inventorySystem the inventory system for item data retrieval and stock updates
     * @param printer the printer used for printing receipts
     */
    public Controller(AccountingSystem accountingSystem, InventorySystem inventorySystem, Printer printer) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
    }

    /*
     * Begins a new sale transaction, resetting the current Sale object to ensure a clean state.
     */
    public void startNewSale() {
        this.sale = new Sale();
    }

    /*
     * Adds an item to the current sale using an identifier. The quantity of the item is also specified.
     * 
     * @param itemIdentifier a unique string identifier for the item to be added
     * @param quantity the quantity of the item, encapsulated in an Amount object
     * @return a string summarizing the added item's details and the current sale total
     */
    public String addItem(String itemIdentifier, Amount quantity) {
        Item itemToBeAdded = inventorySystem.fetchItem(itemIdentifier);
        itemToBeAdded.setQuantity(quantity);
        sale.addItem(itemToBeAdded);

        return generateItemDetails(itemToBeAdded);
    }


    /*
     * Constructs a detailed description of an item's properties, including name, price, tax, quantity, and running total.
     *
     * @param item the item for which details are to be generated
     * @return a detailed string of the item's attributes
     */
    private String generateItemDetails(Item item) {
        String itemDetails = "item name: " + item.getItemInformation().getItemName()
                + ", price: " + item.getItemInformation().getItemPrice()
                + ", tax amount: " + item.getItemInformation().getItemTaxAmount()
                + ", quantity: " + item.getQuantity()
                + ", running total: " + sale.getTotal();
        return itemDetails;
    }

    /*
     * Provides the total cost of items in the current sale, excluding tax.
     *
     * @return the total amount as a string
     */
    public String displayTotal() {
        return sale.getTotal().toString();
    }

    /*
     * Provides the total cost including tax for all items in the current sale.
     *
     * @return the total amount including tax as a string
     */
    public String displayTotalIncludingTax() {
        return sale.getTotalIncludingTax().toString();
    }

    /*
     * Completes the sale by processing the payment, calculating change, printing a receipt,
     * and updating the inventory and accounting systems.
     *
     * @param payment the amount paid by the customer
     * @return a string representing the change to be given to the customer
     */
    public String enterPayment(Amount payment) {
        Amount change = payment.minus(sale.getTotalIncludingTax());
        Receipt receipt = new Receipt(sale);
        printer.print(receipt);
        inventorySystem.updateInventorySystem(sale);
        accountingSystem.updateAccountingSystem(sale, payment);
        sale = null; //Resets sale for futer transactions
        return change.toString();
    }
}
