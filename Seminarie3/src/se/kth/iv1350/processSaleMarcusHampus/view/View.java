package se.kth.iv1350.processSaleMarcusHampus.view;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * This class represents the user interface of the application. It is
 * responsible for starting and displaying the outcomes of a simulated sale
 * process.
 */
public class View {

    private Controller contr;

    /**
     * Constructs a View instance which will interact with the specified
     * Controller for processing sales.
     *
     * @param contr the Controller that manages the operations and flow of the
     * sale process.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a sale by interacting with the Controller to add items, display
     * totals, and handle payments. This method is intended for demonstration
     * purposes and prints the progress of a sale to the console.
     */
    public void fakeSale() {
        contr.startNewSale();
        System.out.println("\nA new sale has been started.\n");
        System.out.println("Cashier enter items.\n");

        String output = contr.addItem("32001", new Amount(2));
        System.out.println(output);

        output += ("\nRunning total: " + contr.displayTotal());
        System.out.println(output + "\n");

        output = contr.addItem("32003", new Amount(4));
        output += ("\nRunning total: " + contr.displayTotal() + " SEK");
        System.out.println(output + "\n");

        output = contr.addItem("32004", new Amount(3));
        System.out.println(output);
        output += ("\nRunning total: " + contr.displayTotal() + " SEK");
        System.out.println(output + "\n");

        output = contr.addItem("32001", new Amount(2));
        System.out.println(output);
        output += ("\nRunning total: " + contr.displayTotal() + " SEK");
        System.out.println(output + "\n");
        output = contr.displayTotalIncludingTax();
        System.out.println("Total including VAT: " + output + " SEK");
        System.out.println("\n*Cashier enter amount paid*\n");
        output = contr.enterPayment(new Amount(350)) + " SEK";
        System.out.println("Change to give customer: " + output + " SEK");

    }
}
