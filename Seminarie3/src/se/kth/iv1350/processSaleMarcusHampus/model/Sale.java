package se.kth.iv1350.processSaleMarcusHampus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents a sale transaction, holding details about the items sold, total cost, and time of sale.
 * It allows for adding items to the sale, updating totals, and computing totals including taxes.
 */
public class Sale {

    private final ArrayList<Item> saleItems;
    private final Amount total;
    private final Amount totalIncludingTax;
    private final LocalDateTime saleTime;
    private final InventorySystem inventorySystem; //Update 
    private Printer printer; //Update
    private final AccountingSystem accountingSystem; //update
    //private GenerateItemDetails generateItemDetails; //update

    /**
    * Initializes a new Sale object with empty items and zero total cost (without tax).
    * Sale time is set to the current time.
    @param inventorySystem ...
    @param accountingSystem ..
    @param printer ...
    */
    public Sale(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer) { //Uppdaterade så Sale tar med InventorySystem inventorySystem
        this.inventorySystem = inventorySystem; //Och uppdaterade konstruktorn så den inkluderar denne referens
        this.accountingSystem = accountingSystem; // Update
        this.saleItems = new ArrayList<>();
        this.total = new Amount(0);
        this.totalIncludingTax = new Amount(0);
        this.saleTime = LocalDateTime.now();
    }

    /**
     * Gets the total cost of items in the sale without tax.
     *
     * @return the total amount as an Amount object.
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Gets the total cost including tax for the items in the sale.
     *
     * @return the total amount including tax as an Amount object.
     */
    public Amount getTotalIncludingTax() {
        return totalIncludingTax;
    }

    /**
     * Returns a list of items that are part of the sale.
     *
     * @return the list of Item objects.
     */
    public ArrayList<Item> getItems() {
        return saleItems;
    }

    /**
     * Gets the time when the sale was initiated.
     *
     * @return the LocalDateTime representing the sale initiation time.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * Gets the time when sale was initiadted.
     * 
     * @return a String representing the sale initiation time.
     */
    public String getFormattedSaleTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return saleTime.format(formatter);
    }

    /**
     * Generates a string representation of the sale, detailing items, prices, taxes, and totals.
     *
     * @return the string format of the sale details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : saleItems) {
            sb.append("\nItem: ").append(item.getItemInformation().getItemName())
                    .append("\nPrice: ").append(item.getItemInformation().getItemPrice())
                    .append("\nVAT amount: ").append(item.getItemInformation().getItemTaxAmount())
                    .append("\nQuantity: ").append(item.getQuantity()).append("\n");
        }
        sb.append("\nTotal: ").append(total);
        sb.append("\nTotal inc. VAT: ").append(totalIncludingTax);
        return sb.toString();
    }

    /**
     * Updates the total and totalIncludingTax properties based on the items in the sale.
     */
    /**private void updateTotals() {
        total = new Amount(0);
        totalIncludingTax = new Amount(0);
        for (Item saleItem : saleItems) {
            Amount totalPricePerItem = saleItem.getItemInformation().getItemPrice();
            Amount quantity = saleItem.getQuantity();
            total = total.plus(totalPricePerItem.multiply(quantity));

            Amount totalPriceIncludingTaxPerItem = totalPricePerItem
                    .plus(saleItem.getItemInformation().getItemTaxAmount());
            totalIncludingTax = totalIncludingTax.plus(totalPriceIncludingTaxPerItem.multiply(quantity));
        }
    }*/

    /**
     * Checks if an item is already present in the sale.
     *
     * @param itemIdentifier the identifier of the item to check.
     * @return true if the item is present, false otherwise.
     */
    /**private boolean itemIsPresent(String itemIdentifier) {
        for (Item saleItem : saleItems) {
            if (saleItem.getItemIdentifier().equals(itemIdentifier)) {
                return true;
            }
        }
        return false;
    }*/

    /**
     * Adds an item to the sale if it is not already present; increases quantity otherwise.
     *
     * @param itemIdentifier the item to be added or updated in the sale.
     * @param quantity ...
     * @return ....
     */

//Feedback update

    public String addItem(String itemIdentifier, Amount quantity) {
        Item itemToBeAdded = inventorySystem.fetchItem(itemIdentifier); //Hämta objektet
        if(itemToBeAdded == null){
            return "Item not found.";
        }

        for(Item existingItem : this.saleItems) {
            if (existingItem.getItemIdentifier().equals(itemIdentifier)) {
                //Finns objeket öka quantity
                existingItem.increaseQuantity(quantity);
                return "Item quantity updated: " + existingItem.generateItemDetails();
            }
        }
        
        itemToBeAdded.setQuantity(quantity); //Om varan inte finns, lägg till den i listan.
        this.saleItems.add(itemToBeAdded);
        return "New item added: " + itemToBeAdded.generateItemDetails() ;
    }

    public Amount calculateChange (Amount payment){
        //Amount totalIncludingTax = getTotalIncludingTax();
        return payment.minus(this.totalIncludingTax);
    }

    public Receipt generateReceipt(){
        return new Receipt(this); //Antag att Recipt konstruktorn tar en Sale instans?
    }

    
    public void finalizeSale(Amount payment) {
        if (printer != null) {
            Receipt receipt = new Receipt(this); // Skapar kvittot direkt från 'Sale'
            printer.print(receipt); // Använder 'printer' för att skriva ut 'receipt'
        } else {
            System.out.println("Printer not initialized");
        }
        accountingSystem.updateAccountingSystem(this, payment);
        inventorySystem.updateInventorySystem(this);
    
    }
}
