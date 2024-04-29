package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class Item {
    private String itemIdentifier;
    private ItemDTO itemInformation;
    private Amount quantity;

    public Item(String itemIdentifier, ItemDTO itemDescription, Amount quantity) {
        this.itemIdentifier = itemIdentifier;
        this.itemInformation = itemDescription;
        this.quantity = quantity;
    }
    
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public ItemDTO getItemInformation() {
        return itemInformation;
    }

    public void increaseQuantity(Amount otherQuantity){
        this.quantity = this.quantity.plus(otherQuantity);
    }

    public void decreaseQuantity(Amount otherQuantity){
        this.quantity = this.quantity.minus(otherQuantity);
    }

    public Amount getQuantity() {
        return quantity;
    }

    public void setQuantity(Amount otherQuantity) {
        this.quantity = otherQuantity;
    }
}
