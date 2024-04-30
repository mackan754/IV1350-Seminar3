package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * The InventorySystem class manages the stock levels of items available for sale.
 * It handles operations such as item retrieval and stock updates post-sale.
 */
public class InventorySystem {

    private ArrayList<Item> inventory; // List of items available in the inventory.

    /**
     * Constructs an InventorySystem and initializes it with a default set of inventory items.
     */
    public InventorySystem() {
        this.inventory = new ArrayList<>();
        addFakeInventory(); // Populates the inventory with a set of predefined items.
    }

    /**
     * Adds a predefined set of items to the inventory for demonstration purposes.
     * This method simulates an initial inventory load.
     */
    private void addFakeInventory() {
        // Define each item with a description and price details.
        ItemDTO milkDTO = new ItemDTO("Milk", "Dairy", new Amount(12), new Amount(2));
        ItemDTO bananaDTO = new ItemDTO("Banana", "Fruit", new Amount(5), new Amount(1));
        ItemDTO icecreamDTO = new ItemDTO("Icecream", "Frozen", new Amount(49), new Amount(6));
        ItemDTO pastaDTO = new ItemDTO("Pasta", "Dry goods", new Amount(15), new Amount(3));

        // Add items to the inventory with a specified quantity.
        inventory.add(new Item("32001", milkDTO, new Amount(10)));
        inventory.add(new Item("32002", bananaDTO, new Amount(10)));
        inventory.add(new Item("32003", icecreamDTO, new Amount(10)));
        inventory.add(new Item("32004", pastaDTO, new Amount(10)));
    }

    /**
     * Retrieves an item from the inventory based on its unique identifier.
     *
     * @param itemIdentifier A string representing the unique identifier of the item
     * @return The item if found; null if no item matches the identifier
     */
    public Item fetchItem(String itemIdentifier) {
        for (Item item : inventory) {
            if (itemIdentifier.equals(item.getItemIdentifier())) {
                return item;
            }
        }
        return null; // Returns null if no matching item is found
    }

    /**
     * Updates the inventory based on items sold in a completed sale.
     * It decreases the stock quantity of each sold item.
     *
     * @param sale The sale containing the list of items that have been sold
     */
    public void updateInventorySystem(Sale sale) {
        ArrayList<Item> soldItems = sale.getItems();

        for (Item soldItem : soldItems) {
            String itemIdentifier = soldItem.getItemIdentifier();
            Amount quantitySold = soldItem.getQuantity();

            Item inventoryItem = fetchItem(itemIdentifier);
            Amount updatedQuantity = inventoryItem.getQuantity().minus(quantitySold);
            inventoryItem.setQuantity(updatedQuantity);
        }
    }
}
