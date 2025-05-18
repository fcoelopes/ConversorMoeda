package br.com.alura.challenge.utils;

public class FiatCurrency extends Currency {
    private String country;

    public FiatCurrency(String name, String symbol, double amount, String country) {
        super(name, symbol, amount);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String getType() {
        return "Fiat";
    }
}

