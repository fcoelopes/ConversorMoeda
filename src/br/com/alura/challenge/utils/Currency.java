package br.com.alura.challenge.utils;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class Currency {
    protected String name;
    protected String symbol;
    protected double amount;

    public Currency(String name, String symbol, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Currency amount cannot be negative.");
        }
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Currency amount cannot be negative.");
        }
        this.amount = amount;
    }

    public abstract String getType();

    public String formatByLocale(Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(amount);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s %.2f", getType(), name, symbol, amount);
    }
}

