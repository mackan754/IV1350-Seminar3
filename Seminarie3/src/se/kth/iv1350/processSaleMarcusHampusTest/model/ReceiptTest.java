package se.kth.iv1350.processSaleMarcusHampusTest.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;

public class ReceiptTest {
    private Sale sale;
    private Receipt receipt;

    @Before
    public void setUp() {
        sale = new Sale();
        receipt = new Receipt(sale);
    }

    @Test
    public void testToStringWithEmptyReceipt() {
        
        String expected ="\n" +
        "-----RECEIPT-----\n" +
        sale.getFormattedSaleTime().toString() + "\n" +
        sale.toString() + "\n" +
        "-------END-------";

        assertEquals(expected, receipt.toString(), "Receipt string should match the format for an empty receipt.");
    }
}