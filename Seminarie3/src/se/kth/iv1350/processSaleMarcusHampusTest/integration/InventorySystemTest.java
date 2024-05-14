package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDTO;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class InventorySystemTest {

    private InventorySystem inventorySystem;
    private Sale sale;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
        sale = new Sale();
        ItemDTO milkDTO = new ItemDTO("Milk", "Dairy", new Amount(12), new Amount(2));
        ItemDTO bananaDTO = new ItemDTO("Banana", "Fruit", new Amount(5), new Amount(1));
        ItemDTO icecreamDTO = new ItemDTO("Icecream", "Frozen", new Amount(49), new Amount(6));
        ItemDTO pastaDTO = new ItemDTO("Pasta", "Dry goods", new Amount(15), new Amount(3));
<<<<<<< Updated upstream
        sale.addItem(new Item("32001", milkDTO, new Amount(5)));
        sale.addItem(new Item("32002", bananaDTO, new Amount(3)));
        sale.addItem(new Item("32003", icecreamDTO, new Amount(2)));
        sale.addItem(new Item("32004", pastaDTO, new Amount(4)));
=======
        sale.addItem(new Item("32001", milkDTO, new Amount(5)), new Amount(5));
        sale.addItem(new Item("32002", bananaDTO, new Amount(3)), new Amount(3));
        sale.addItem(new Item("32003", icecreamDTO, new Amount(2)), new Amount(2));
        sale.addItem(new Item("32004", pastaDTO, new Amount(4)), new Amount(4));
        saleInformation = new SaleDTO(sale);
>>>>>>> Stashed changes
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        sale = null;
    }

    @Test
    public void testFetchItem() {
        String itemIdentifier = "32001";
        Item fetchedItem = inventorySystem.fetchItem(itemIdentifier);
        assertEquals(itemIdentifier, fetchedItem.getItemIdentifier(),
                "Fetched item's identifier should match requested.");
    }

    @Test
    public void testFetchItemNotFound() {
        String itemIdentifier = "99999";
        Item fetchedItem = inventorySystem.fetchItem(itemIdentifier);
        assertNull(fetchedItem, "No item should be fetched for a non-existing identifier.");
    }

    @Test
    public void testUpdateInventorySystem() {
<<<<<<< Updated upstream
        inventorySystem.updateInventorySystem(sale);
        
        assertEquals(5, inventorySystem.fetchItem("32001").getQuantity().getAmount(), "Quantity of fetched item should be updated after sale.");
        assertEquals(7, inventorySystem.fetchItem("32002").getQuantity().getAmount(), "Quantity of fetched item should be updated after sale.");
        assertEquals(8, inventorySystem.fetchItem("32003").getQuantity().getAmount(), "Quantity of fetched item should be updated after sale.");
        assertEquals(6, inventorySystem.fetchItem("32004").getQuantity().getAmount(), "Quantity of fetched item should be updated after sale.");
=======
        inventorySystem.updateInventorySystem(saleInformation);

        assertEquals(5, inventorySystem.fetchItem("32001").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(7, inventorySystem.fetchItem("32002").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(8, inventorySystem.fetchItem("32003").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(6, inventorySystem.fetchItem("32004").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
>>>>>>> Stashed changes
    }
}
