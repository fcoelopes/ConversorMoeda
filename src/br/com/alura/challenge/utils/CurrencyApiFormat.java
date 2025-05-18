package br.com.alura.challenge.utils;

public record CurrencyApiFormat(String base_code,
                                String target_code,
                                Double conversion_rate,
                                Double conversion_result) {
}
