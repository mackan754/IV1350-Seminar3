package se.kth.iv1350.processSaleMarcusHampus.model;


/*
 * This is the receipt. 
 */

public class Receipt {
    private Sale sale;

    public Receipt(Sale sale) {
        this.sale = sale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("-----RECEIPT-----");
        sb.append("\n");
        sb.append(sale.getSaleTime().toString());
        sb.append("\n");
        sb.append(sale.toString());
        sb.append("\n-------END-------");
        return sb.toString();
    }
}


