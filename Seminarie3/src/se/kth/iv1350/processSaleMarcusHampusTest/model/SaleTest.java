package se.kth.iv1350.processSaleMarcusHampusTest.model;

import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDTO;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SaleTest {

    private Sale sale;
    private ItemDTO itemDTO1;
    private ItemDTO itemDTO2;

    @Before
    public void setUp() {
        // Create a sample sale
        sale = new Sale();
        itemDTO1 = new ItemDTO("Apple", "Fresh apple", new Amount(10), new Amount(2));
        itemDTO2 = new ItemDTO("Banana", "Fresh banana", new Amount(15), new Amount(3));
    }

    @Test
    public void testGetTotal() {
        assertEquals(0, sale.getTotal().getAmount(), 0);
    }

    @Test
    public void testGetTotalIncludingTax() {
        assertEquals(0, sale.getTotalIncludingTax().getAmount(), 0);
    }

    @Test
    public void testGetItems() {
        ArrayList<Item> items = sale.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void testGetSaleTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime saleTime = sale.getSaleTime();
        // Assert that the sale time is within one second of the current time
        assertEquals(currentTime.getYear(), saleTime.getYear());
        assertEquals(currentTime.getMonth(), saleTime.getMonth());
        assertEquals(currentTime.getDayOfMonth(), saleTime.getDayOfMonth());
        assertEquals(currentTime.getHour(), saleTime.getHour());
        assertEquals(currentTime.getMinute(), saleTime.getMinute());
    }

    @Test
    public void testAddItem() {
        Item item1 = new Item("123", itemDTO1, new Amount(5));
        Item item2 = new Item("456", itemDTO2, new Amount(3));

        sale.addItem(item1);
        assertEquals(1, sale.getItems().size());
        assertEquals(50, sale.getTotal().getAmount(), 0);

        sale.addItem(item2);
        assertEquals(2, sale.getItems().size());
        assertEquals(95, sale.getTotal().getAmount(), 0);

        // Adding same item should increase quantity
        sale.addItem(item1);
        assertEquals(2, sale.getItems().size());
        assertEquals(145, sale.getTotal().getAmount(), 0);
    }
}
