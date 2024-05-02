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
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("-----RECEIPT-----");
        sb.append("\n");
        sb.append(sale.getFormattedSaleTime().toString());
        sb.append("\n");
        sb.append(sale.toString());
        sb.append("\n-------END-------");
        String expected = sb.toString();

        assertEquals(expected, receipt.toString(), "Receipt string should match the format for an empty receipt.");
    }
}