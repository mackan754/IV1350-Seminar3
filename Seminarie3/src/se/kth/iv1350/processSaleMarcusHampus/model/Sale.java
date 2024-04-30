package se.kth.iv1350.processSaleMarcusHampus.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents a sale transaction, holding details about the items sold, total cost, and time of sale.
 * It allows for adding items to the sale, updating totals, and computing totals including taxes.
 */
public class Sale {

    private ArrayList<Item> saleItems;
    private Amount total;
    private Amount totalIncludingTax;
    private LocalDateTime saleTime;

    /**
     * Gets the total cost of items in the sale without tax.
     *
     * @return the total amount as an Amount object.
     */
    public Sale() {
        this.saleItems = new ArrayList<>();
        this.total = new Amount(0);
        this.totalIncludingTax = new Amount(0);
        this.saleTime = LocalDateTime.now();
    }

    /**
     * Gets the total cost of items in the sale without tax.
     *
     * @return the total amount as an Amount object.
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Gets the total cost including tax for the items in the sale.
     *
     * @return the total amount including tax as an Amount object.
     */
    public Amount getTotalIncludingTax() {
        return totalIncludingTax;
    }

    /**
     * Returns a list of items that are part of the sale.
     *
     * @return the list of Item objects.
     */
    public ArrayList<Item> getItems() {
        return saleItems;
    }

    /**
     * Gets the time when the sale was initiated.
     *
     * @return the LocalDateTime representing the sale initiation time.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * Generates a string representation of the sale, detailing items, prices, taxes, and totals.
     *
     * @return the string format of the sale details.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : saleItems) {
            sb.append("\n").append(item.getItemInformation().getItemName())
                    .append("\nprice: ").append(item.getItemInformation().getItemPrice())
                    .append("\ntax amount: ").append(item.getItemInformation().getItemTaxAmount())
                    .append("\nquantity: ").append(item.getQuantity() + "\n");
        }
        sb.append("\ntotal: ").append(total);
        sb.append("\ntax: ").append((totalIncludingTax.minus(total)));
        return sb.toString();
    }

    /**
     * Updates the total and totalIncludingTax properties based on the items in the sale.
     */
    private void updateTotals() {
        total = new Amount(0);
        totalIncludingTax = new Amount(0);
        for (Item saleItem : saleItems) {
            Amount totalPricePerItem = saleItem.getItemInformation().getItemPrice();
            Amount quantity = saleItem.getQuantity();
            total = total.plus(totalPricePerItem.multiply(quantity));

            Amount totalPriceIncludingTaxPerItem = totalPricePerItem
                    .plus(saleItem.getItemInformation().getItemTaxAmount());
            totalIncludingTax = totalIncludingTax.plus(totalPriceIncludingTaxPerItem.multiply(quantity));
        }
    }

    /**
     * Checks if an item is already present in the sale.
     *
     * @param itemIdentifier the identifier of the item to check.
     * @return true if the item is present, false otherwise.
     */
    private boolean itemIsPresent(String itemIdentifier) {
        for (Item saleItem : saleItems) {
            if (saleItem.getItemIdentifier().equals(itemIdentifier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an item to the sale if it is not already present; increases quantity otherwise.
     *
     * @param item the item to be added or updated in the sale.
     */
    public void addItem(Item item) {
        if (!itemIsPresent(item.getItemIdentifier())) {
            saleItems.add(item);
            updateTotals();
        } else {
            for (Item saleItem : saleItems) {
                if (saleItem.getItemIdentifier().equals(item.getItemIdentifier())) {
                    saleItem.increaseQuantity(item.getQuantity());
                    updateTotals();
                }
            }
        }
    }
}
