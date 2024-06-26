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
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
        printer = new Printer();
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
        assertEquals("0", controller.displayTotal(), "Sale did not start correctly.");
    }

    @Test
    public void testAddItem() {
        controller.startNewSale();
        Amount quantity = new Amount(2);
        controller.addItem("32001", quantity);
        String expectedTotal = "24";
        assertEquals(expectedTotal, controller.displayTotal(),
                "Adding and existing inventory item did not update the sale correctly.");
    }

    @Test
    public void testDisplayTotal() {
        controller.startNewSale();

        controller.addItem("32001", new Amount(2));
        controller.addItem("32002", new Amount(3));

        String expectedTotal = "39";

        assertEquals(expectedTotal, controller.displayTotal(),
                "Displayed total does not match the expected total without considering tax.");
    }

    @Test
    public void testDisplayTotalIncludingTax() {
        controller.startNewSale();

        controller.addItem("32001", new Amount(2));
        controller.addItem("32002", new Amount(3));

        String expectedTotal = "46";

        assertEquals(expectedTotal, controller.displayTotalIncludingTax(),
                "Displayed total does not match the expected total.");
    }

    @Test
    public void testEnterPayment() {
        controller.startNewSale();

        controller.addItem("32001", new Amount(2)); 

        Amount payment = new Amount(100);
        Amount expectedChange = payment.minus(new Amount(28)); 

        assertEquals(expectedChange.toString(), controller.enterPayment(payment), "Returned change does not match the expected change after payment.");
    }

}
