package se.kth.iv1350.processSaleMarcusHampusTest.model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

/**
 * Test class for Receipt.
 */
public class ReceiptTest {

    /**
     * A stub class for Sale to use in tests.
     */
    private class SaleStub extends Sale {
        @Override
        public LocalDateTime getSaleTime() {
            // Return a fixed point in time for test consistency.
            return LocalDateTime.of(2024, 4, 30, 15, 0);
        }

        @Override
        public String toString() {
            // Return a simplified sale detail for testing.
            return "Item1: 2 pcs, Total: $20";
        }
    }

    /**
     * Test of toString method of class Receipt.
     */
    @Test
    public void testToString() {
        SaleStub saleStub = new SaleStub();
        Receipt receipt = new Receipt(saleStub);

        String expectedOutput = "\n"
                                + "-----RECEIPT-----\n"
                                + "2024-04-30T15:00\n"
                                + "Item1: 2 pcs, Total: $20\n"
                                + "-------END-------";
        assertEquals("Receipt string should be formatted correctly.", expectedOutput, receipt.toString());
    }
}
