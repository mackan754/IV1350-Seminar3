package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class ItemTest {
    private Item item;

    @BeforeEach
    public void setUp() {
        String itemIdentifier = "12345";
        ItemDTO itemInformation = new ItemDTO("Test Item", "This is a test item", new Amount(50), new Amount(5));
        Amount quantity = new Amount(2);
        item = new Item(itemIdentifier, itemInformation, quantity);
    }

    @AfterEach
    public void cleanUp() {
        item = null;
    }

    @Test
    public void getItemIdentifier() {
        String expectedItemIdentifier = "12345";
        assertEquals(expectedItemIdentifier, item.getItemIdentifier());
    }

    @Test
    public void getItemInformation() {
        ItemDTO expectedItemInformation = new ItemDTO("Test Item", "This is a test item", new Amount(50), new Amount(5));
        assertEquals(expectedItemInformation.getItemName(), item.getItemInformation().getItemName());
        assertEquals(expectedItemInformation.getItemDescription(), item.getItemInformation().getItemDescription());
        assertEquals(expectedItemInformation.getItemPrice().getAmount(), item.getItemInformation().getItemPrice().getAmount());
        assertEquals(expectedItemInformation.getItemTaxAmount().getAmount(), item.getItemInformation().getItemTaxAmount().getAmount());
    }

    @Test
    public void increaseQuantity() {
        Amount increaseBy = new Amount(3);
        item.increaseQuantity(increaseBy);
        Amount expectedQuantity = new Amount(5);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount());
    }

    @Test
    public void decreaseQuantity() {
        Amount decreaseBy = new Amount(1);
        item.decreaseQuantity(decreaseBy);
        Amount expectedQuantity = new Amount(1);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount());
    }

    @Test
    public void getQuantity() {
        Amount expectedQuantity = new Amount(2);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount());
    }
    
    @Test
    public void setQuantity() {
        Amount newQuantity = new Amount(10);
        item.setQuantity(newQuantity);
        assertEquals(newQuantity.getAmount(), item.getQuantity().getAmount());
    }
}
