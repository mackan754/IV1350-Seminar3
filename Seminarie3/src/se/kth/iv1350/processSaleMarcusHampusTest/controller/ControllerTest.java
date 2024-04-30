package se.kth.iv1350.processSaleMarcusHampusTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class ControllerTest {

    private Controller controller;
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Printer printer;

    @BeforeEach
    public void setUp() {
        // Assume these are properly initialized in your actual test setup
        accountingSystem = new AccountingSystem(); // Placeholder instance
        inventorySystem = new InventorySystem();   // Placeholder instance
        printer = new Printer();                   // Placeholder instance
        controller = new Controller(accountingSystem, inventorySystem, printer);
    }

    @AfterEach
    public void tearDown() {
        accountingSystem = null;
        inventorySystem = null;
        printer = null;
        controller = null;
    }

    @Test
    public void testStartNewSale() {
        controller.startNewSale();
        // Simply check if the controller can start a new sale, actual testing of sale reset is limited without mocks
        // Assuming displayTotal returns "0" if a new Sale object is properly initialized
        assertEquals("0", controller.displayTotal());
    }

    @Test
    public void testCompleteSaleWithDummyValues() {
        controller.startNewSale();
        // This test must be designed to avoid interactions with non-existent systems since no mocks are used
        Amount payment = new Amount(200);
        // Expected change calculation must be hardcoded or calculated based on dummy inputs
        String expectedChange = "200";  // Assuming there are no items added, and total including tax is 0
        String change = controller.enterPayment(payment);

        assertEquals(expectedChange, change);
        // Note: This does not test integration with the printer or accounting systems effectively
    }
}
