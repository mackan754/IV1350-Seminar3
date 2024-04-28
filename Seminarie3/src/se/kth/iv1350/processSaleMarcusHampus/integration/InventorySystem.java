package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class InventorySystem {
    private ArrayList<Item> inventory;

    public InventorySystem() {
        this.inventory = new ArrayList<>();
        addFakeInventory();
    }

    private void addFakeInventory() {
        ItemDTO milkDTO = new ItemDTO("Milk", "Dairy", new Amount(12), new Amount(2));
        ItemDTO bananaDTO = new ItemDTO("Banana", "Fruit", new Amount(5), new Amount(1));
        ItemDTO icecreamDTO = new ItemDTO("Icecream", "Frozen", new Amount(49), new Amount(6));
        ItemDTO pastaDTO = new ItemDTO("Pasta", "Dry goods", new Amount(15), new Amount(3));

        inventory.add(new Item("32001", milkDTO, new Amount(10)));
        inventory.add(new Item("32002", bananaDTO, new Amount(10)));
        inventory.add(new Item("32003", icecreamDTO, new Amount(10)));
        inventory.add(new Item("32004", pastaDTO, new Amount(10)));
    }

    public Item fetchItem(String itemIdentifier) {
        for (Item item : inventory) {
            if (itemIdentifier.equals(item.getItemIdentifier())) {
                return item;
            }
        }
        return null;
    }

    public void updateInventorySystem() {
        // Här ska vi uppdatera lagersaldo efter att köpet är genomfört.
    }
}
