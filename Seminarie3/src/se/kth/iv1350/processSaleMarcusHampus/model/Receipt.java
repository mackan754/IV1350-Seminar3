package se.kth.iv1350.processSaleMarcusHampus.model;

/**
 * This class represents a receipt that is generated after a sale is completed.
 * It includes details of the sale such as the time of the sale and a summary of the items purchased.
 */
public class Receipt {

    private final Sale sale;

    /**
     * Constructs a Receipt object with a reference to the Sale instance that the receipt will represent.
     *
     * @param sale the Sale object that contains the details of the completed sale.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
    }

    /**
     * Generates a formatted string representation of the receipt, which includes the sale time,
     * details of items purchased, and other relevant sale information.
     *
     * @return a string representation of the receipt, formatted for printing.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-----RECEIPT-----\n");
        sb.append("Sale Time: ").append(sale.getFormattedSaleTime()).append("\n\n");
        sb.append("Items Purchased:\n");
        sb.append(sale);  // Använder Sale klassens toString för att få item listan
        sb.append("\n-------END-------");
        return sb.toString();
    }
}
