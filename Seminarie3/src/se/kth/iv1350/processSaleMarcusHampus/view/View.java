package se.kth.iv1350.processSaleMarcusHampus.view;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * This program has no view, instead, this class is a
 * placeholder for the entire view.
 */

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void fakeSale() {
        contr.startNewSale();
        System.out.println("A new sale has been started.\n");

        System.out.println("Cashier enter items.\n");

        String output = contr.addItem("32001", new Amount(2));

        System.out.println(output);

        output = contr.addItem("32003", new Amount(4));

        System.out.println(output);

        output = contr.addItem("32004", new Amount(3));

        System.out.println(output);

        output = contr.addItem("32001", new Amount(2));

        System.out.println(output);

        System.out.println();
    
        output = contr.displayTotalIncludingTax();

        System.out.println("Total including VAT: " + output);
<<<<<<< Updated upstream
=======

        System.out.println("Cashie enter amount paid");

        output = contr.enterPayment(new Amount(350));

>>>>>>> Stashed changes
    }
}
