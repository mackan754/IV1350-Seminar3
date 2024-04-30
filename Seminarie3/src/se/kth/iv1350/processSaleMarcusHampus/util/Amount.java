package se.kth.iv1350.processSaleMarcusHampus.util;

public final class Amount {

    private final int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return Integer.toString(amount);
    }

    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }

    public Amount plus(Amount other) {
        return new Amount(amount + other.amount);
    }

    public Amount multiply(Amount other) {
        return new Amount(amount * other.amount);
    }
}
