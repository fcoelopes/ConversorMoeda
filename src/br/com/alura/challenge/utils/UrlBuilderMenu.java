package br.com.alura.challenge.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class UrlBuilderMenu {
    private final String apiKey;
    private String currencyBase;
    private String targetCurrency;
    private int amount;
    private String urlString;

    public UrlBuilderMenu(String apiKey) {
        this.apiKey = apiKey;
    }

    public UrlBuilderMenu baseCurrency(String currencyBase) {
        this.currencyBase = currencyBase;
        return this;
    }

    public UrlBuilderMenu targetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
        return this;
    }

    public UrlBuilderMenu amount(int amount) {
        this.amount = amount;
        return this;
    }

    public String urlMount(String endpointType) {
        Map<String, Supplier<String>> urlStrategies = new HashMap<>();
        urlStrategies.put("standard", this::buildStandardUrl);
        urlStrategies.put("pair", this::buildPairUrl);
        Supplier<String> urlBuilder = urlStrategies.get(endpointType);
        if (urlBuilder != null) {
            urlString = urlBuilder.get();
        } else {
            throw new IllegalArgumentException("Tipo de endpoint inválido: " + endpointType);
        }
        return urlString;
    }

    public String buildStandardUrl() {
        return String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s",
                apiKey,
                currencyBase
        );
    }

    public String buildPairUrl() {
        return String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%d",
                apiKey,
                currencyBase,
                targetCurrency,
                amount
        );
    }

    public String getUrlString() {
        return urlString;
    }

    public enum Moeda {
        USD("Dólar Americano"),
        BRL("Real Brasileiro"),
        EUR("Euro"),
        GBP("Libra Esterlina"),
        JPY("Iene Japonês"),
        CAD("Dólar Canadense"),
        AUD("Dólar Australiano"),
        CHF("Franco Suíço");

        private final String nome;

        Moeda(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        private static final Map<String, Moeda> lookup = new HashMap<>();

        static {
            for (Moeda moeda : Moeda.values()) {
                lookup.put(String.valueOf(moeda.ordinal() + 1), moeda);
            }
        }

        public static Moeda get(String menuInput) {
            return lookup.get(menuInput);
        }
    }
}

