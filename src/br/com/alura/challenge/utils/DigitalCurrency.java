package br.com.alura.challenge.utils;

public class DigitalCurrency extends Currency {
    private String blockchain;

    public DigitalCurrency(String name, String symbol, double amount, String blockchain) {
        super(name, symbol, amount);
        this.blockchain = blockchain;
    }

    public String getBlockchain() {
        return blockchain;
    }

    @Override
    public String getType() {
        return "Digital";
    }
}

