package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class ItemDTO {
    private final String itemName;
    private final String itemDescription;
    private final Amount itemPrice;
    private final Amount itemTaxAmount;

    public ItemDTO(String itemName, String itemDescription, Amount itemPrice, Amount itemTaxAmount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemTaxAmount = itemTaxAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Amount getItemPrice() {
        return itemPrice;
    }

    public Amount getItemTaxAmount() {
        return itemTaxAmount;
    }
}
