package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/*
 * Represents the data transfer object for an item. This class is designed to encapsulate and transfer the data
 * pertaining to an item, including its name, description, price, and the tax amount applicable to it.
 */
public class ItemDTO {

    private final String itemName;          // The name of the item
    private final String itemDescription;   // A description of the item, providing more details
    private final Amount itemPrice;         // The selling price of the item
    private final Amount itemTaxAmount;     // The tax amount applicable to the item

    /*
     * Constructs an ItemDTO with specified details.
     *
     * @param itemName The name of the item
     * @param itemDescription A detailed description of the item, such as category or characteristics
     * @param itemPrice The price at which the item is sold
     * @param itemTaxAmount The tax amount applied to the item
     */
    public ItemDTO(String itemName, String itemDescription, Amount itemPrice, Amount itemTaxAmount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemTaxAmount = itemTaxAmount;
    }

    /*
     * Retrieves the name of the item.
     *
     * @return The name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /*
     * Retrieves the description of the item.
     *
     * @return A string describing the item, providing additional detail beyond the name
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /*
     * Retrieves the selling price of the item.
     *
     * @return The price of the item encapsulated in an Amount object
     */
    public Amount getItemPrice() {
        return itemPrice;
    }

    /*
     * Retrieves the tax amount applicable to the item.
     *
     * @return The tax amount associated with the item, encapsulated in an Amount object
     */
    public Amount getItemTaxAmount() {
        return itemTaxAmount;
    }
}
