package se.kth.iv1350.processSaleMarcusHampus.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

class ControllerTest {
    private Controller controller;
    private AccountingSystem accountingSystemMock;
    private InventorySystem inventorySystemMock;
    private Printer printerMock;
    private Sale saleMock;

    @BeforeEach
    void setUp() {
        accountingSystemMock = mock(AccountingSystem.class);
        inventorySystemMock = mock(InventorySystem.class);
        printerMock = mock(Printer.class);
        controller = new Controller(accountingSystemMock, inventorySystemMock, printerMock);
        saleMock = mock(Sale.class);
    }

    @Test
    void testStartNewSale() {
        controller.startNewSale();
        assertNotNull(controller.getSale(), "Sale should not be null after starting a new sale");
    }

    @Test
    void testAddItem() {
        when(controller.getSale()).thenReturn(saleMock);
        String itemIdentifier = "123ABC";
        Amount quantity = new Amount(2);
        String expectedOutput = "Item added successfully";
        when(saleMock.addItem(itemIdentifier, quantity)).thenReturn(expectedOutput);

        String result = controller.addItem(itemIdentifier, quantity);
        
        verify(saleMock, times(1)).addItem(itemIdentifier, quantity);
        assertEquals(expectedOutput, result, "The output should match the expected result.");
    }

    @Test
    void testDisplayTotal() {
        when(controller.getSale()).thenReturn(saleMock);
        when(saleMock.getTotal()).thenReturn(new Amount(500));

        String result = controller.displayTotal();
        assertEquals("500", result, "The displayed total should match the expected amount.");
    }

    @Test
    void testDisplayTotalIncludingTax() {
        when(controller.getSale()).thenReturn(saleMock);
        when(saleMock.getTotalIncludingTax()).thenReturn(new Amount(625));

        String result = controller.displayTotalIncludingTax();
        assertEquals("625", result, "The displayed total including tax should match the expected amount.");
    }

    @Test
    void testEnterPayment() {
        Amount payment = new Amount(1000);
        Amount expectedChange = new Amount(375);
        Receipt receipt = new Receipt(saleMock);

        when(controller.getSale()).thenReturn(saleMock);
        when(saleMock.calculateChange(payment)).thenReturn(expectedChange);
        when(saleMock.finalizeSale(payment)).thenReturn(receipt);

        String result = controller.enterPayment(payment);
        assertEquals("375", result, "Change should be calculated correctly.");

        verify(printerMock, times(1)).print(receipt);
        verify(saleMock, times(1)).finalizeSale(payment);
        assertNull(controller.getSale(), "Sale should be null after completion.");
    }
}
