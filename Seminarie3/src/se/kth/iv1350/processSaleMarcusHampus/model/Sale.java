package se.kth.iv1350.processSaleMarcusHampus.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class Sale {
    private ArrayList<Item> saleItems;
    private Amount total;
    private Amount totalIncludingTax;
    private LocalDateTime saleTime;

    public Sale() {
        this.saleItems = new ArrayList<>();
        this.total = new Amount(0);
        this.totalIncludingTax = new Amount(0);
        this.saleTime = LocalDateTime.now();
    }

    public Amount getTotal() {
        return total;
    }

    public Amount getTotalIncludingTax(){
        return totalIncludingTax;
    }

    public ArrayList<Item> getItems() {
        return saleItems;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

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

    private boolean itemIsPresent(String itemIdentifier) {
        for (Item saleItem : saleItems) {
            if (saleItem.getItemIdentifier().equals(itemIdentifier)) {
                return true;
            }
        }
        return false;
    }

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
