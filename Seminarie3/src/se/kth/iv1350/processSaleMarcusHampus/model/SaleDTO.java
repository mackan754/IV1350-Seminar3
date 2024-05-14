package se.kth.iv1350.processSaleMarcusHampus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class SaleDTO {
    private ArrayList<Item> items;
    private Amount total;
    private Amount totalIncludingTax;
    private LocalDateTime saleTime;

    public SaleDTO(Sale sale) {
        this.items = new ArrayList<>();
        for (Item item : sale.getItems()) {
            this.items.add(new Item(item));
        }
        this.total = new Amount(sale.getTotal().getAmount());
        this.totalIncludingTax = new Amount(sale.getTotalIncludingTax().getAmount());
        this.saleTime = sale.getSaleTime();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Amount getTotal() {
        return total;
    }

    public Amount getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * Gets the time when sale was initiadted.
     * 
     * @return a String representing the sale initiation time.
     */
    public String getFormattedSaleTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return saleTime.format(formatter);
    }

    /**
     * Generates a string representation of the saleDTO, detailing items, prices,
     * taxes, and totals.
     *
     * @return the string format of the sale details.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append("\n-----").append(item.getItemInformation().getItemName()).append("-----")
                    .append("\nPrice: ").append(item.getItemInformation().getItemPrice()).append(" SEK")
                    .append("\nVAT amount: ").append(item.getItemInformation().getItemTaxAmount()).append(" SEK")
                    .append("\nQuantity: ").append(item.getQuantity()).append(" st\n");
        }
        sb.append("\n--------------------\nTotal: ").append(total).append(" SEK");
        sb.append("\nVAT: ").append(totalIncludingTax.minus(total)).append(" SEK").append("\n--------------------");
        return sb.toString();
    }
    
}
