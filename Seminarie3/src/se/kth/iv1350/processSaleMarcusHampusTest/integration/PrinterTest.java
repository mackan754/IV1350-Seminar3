package se.kth.iv1350.processSaleMarcusHampus.integration;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;

/**
 * Test class for Printer.
 */
public class PrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Redirects the standard output to a custom ByteArrayOutputStream before each test,
     * allowing the output to be captured for verification.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets the standard output to its original state after each test is completed.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test of print method of class Printer.
     */
    @Test
    public void testPrint() {
        Printer printer = new Printer();
        Receipt receipt = new Receipt(); // Assuming Receipt has a usable toString method.
        receipt.toString();             // Let's assume this returns a predefined string "Receipt Details".
        
        printer.print(receipt);

        String expectedOutput = "Receipt Details\n"; // Include the newline character as println adds it.
        assertEquals("Output should match the expected receipt details.", expectedOutput, outContent.toString());
    }
}
