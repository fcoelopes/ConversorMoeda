package br.com.alura.challenge.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {
    private String urlString;
    private String apiResponse;


    public String getApiResponse(String urlString) throws IOException, RuntimeException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            apiResponse = response.body();
            return apiResponse;
        } catch(IOException | InterruptedException e){
            throw new IOException("Erro na comunicação via rede!");
        }
    }

}
