package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents an item in the inventory. This class holds details about an item, including its identifier,
 * description, and quantity.
 */
public class Item {

    private final String itemIdentifier;      
    private final ItemDTO itemInformation;    
    private Amount quantity;            

    /**
     * Constructs an item with a specified identifier, description, and initial quantity.
     *
     * @param itemIdentifier A unique string that identifies the item
     * @param itemDescription Detailed information about the item including its price and category
     * @param quantity The initial quantity of the item
     */
    public Item(String itemIdentifier, ItemDTO itemDescription, Amount quantity) {
        this.itemIdentifier = itemIdentifier;
        this.itemInformation = itemDescription;
        this.quantity = quantity;
    }

    /**
     * Returns the unique identifier for the item.
     *
     * @return The unique identifier as a string
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Retrieves detailed information about the item.
     *
     * @return An instance of ItemDTO containing details such as name, price, and tax
     */
    public ItemDTO getItemInformation() {
        return itemInformation;
    }

    /**
     * Increases the quantity of this item by a specified amount.
     *
     * @param otherQuantity The amount to add to the current item quantity
     */
    public void increaseQuantity(Amount otherQuantity) {
        this.quantity = this.quantity.plus(otherQuantity);
    }

    /**
     * Decreases the quantity of this item by a specified amount.
     *
     * @param otherQuantity The amount to subtract from the current item quantity
     */
    public void decreaseQuantity(Amount otherQuantity) {
        this.quantity = this.quantity.minus(otherQuantity);
    }

    /**
     * Returns the current quantity of the item.
     *
     * @return The quantity of the item as an Amount
     */
    public Amount getQuantity() {
        return quantity;
    }

    /**
     * Sets the item's quantity to a specified new value.
     *
     * @param otherQuantity The new quantity of the item
     */
    public void setQuantity(Amount otherQuantity) {
        this.quantity = otherQuantity;
    }

    /**
     * Constructs a detailed description of an item's properties, including name, price, tax, quantity, and running total.
     @return ....
     */
    public String generateItemDetails() { //Flyttar generateItemDetails till Item-klassen från Controllern
        return "Item name: " + this.getItemInformation().getItemName() + //ändrar till this. från item. varför vet jag inte
                ", Price: " + this.getItemInformation().getItemPrice() + //*Här används this. för att tydligt referera till de aktuella objektinstansens attribut och metoder. Det ger också klarhet i att det är objektets egna data som hanteras. */
                ", VAT amount: " + this.getItemInformation().getItemTaxAmount() +
                ", Quantity: " + this.getQuantity();
                //*+ ", running total: " + this.getTotal(); Detta bör hanteras i Sale-klassen ist.*/
                //* Inkapsling: Genom att flytta ansvar för att hantera totaler och aggregerade värden till Sale-klassen, */
                //*förblir Item-klassen ren från beroenden på andra objekts tillstånd, vilket främjar bättre inkapsling och återanvändning av Item-klassen.*/
    }
}

