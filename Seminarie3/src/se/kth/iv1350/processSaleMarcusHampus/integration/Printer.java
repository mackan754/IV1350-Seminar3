package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;

public class Printer {

    public Printer() {
    }

    public void print(Receipt receipt) {
        System.out.println(receipt.toString());
    }
}
