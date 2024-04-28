package se.kth.iv1350.processSaleMarcusHampus.startup;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.view.View;

public class Main {
    public static void main(String[] args) {
        AccountingSystem accountingSystem = new AccountingSystem();
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer);
        View view = new View(controller);
        view.fakeSale();
    }
}
