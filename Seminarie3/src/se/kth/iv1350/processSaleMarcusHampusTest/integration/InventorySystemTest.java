package se.kth.iv1350.processSaleMarcusHampus.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.Item;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    @Before
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    @Test
    public void testFetchItem() {
        Item fetchedItem = inventorySystem.fetchItem("32001");
        assertNotNull("Fetching an existing item should not return null", fetchedItem);
        assertEquals("Fetched item should have the correct identifier", "32001", fetchedItem.getItemIdentifier());

        Item nonExistentItem = inventorySystem.fetchItem("99999");
        assertNull("Fetching a non-existent item should return null", nonExistentItem);
    }

    @Test
    public void testUpdateInventorySystem() {
        ArrayList<Item> soldItems = new ArrayList<>();
        soldItems.add(new Item("32001", new ItemDTO("Milk", "Dairy", new Amount(12), new Amount(2)), new Amount(5)));
        Sale sale = new Sale();
        sale.setItems(soldItems);

        inventorySystem.updateInventorySystem(sale);

        Item updatedItem = inventorySystem.fetchItem("32001");
        assertEquals("Inventory quantity should be updated after sale", new Amount(5), updatedItem.getQuantity());
    }
}
