import br.com.alura.challenge.services.ApiRequest;
import br.com.alura.challenge.utils.UrlBuilderMenu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcao, moedaEscolhida, quantia;
        String endpointType;
        String apiKey = System.getenv("EXCHANGERATE_API_KEY");
        ApiRequest apiRequest = new ApiRequest();
        Scanner scanner = new Scanner(System.in);
        UrlBuilderMenu builder = new UrlBuilderMenu(apiKey);

        System.out.println("Escolha a moeda base:");
        for (UrlBuilderMenu.Moeda moeda : UrlBuilderMenu.Moeda.values()) {
            System.out.println((moeda.ordinal() + 1) + " - " + moeda.getNome() + " (" + moeda.name() + ")");
        }

        String escolhaBaseStr = scanner.nextLine();
        UrlBuilderMenu.Moeda moedaBase = UrlBuilderMenu.Moeda.get(escolhaBaseStr);

        if (moedaBase != null) {
            builder.baseCurrency(moedaBase.name());
            System.out.println("Moeda base selecionada: " + moedaBase.getNome() + " (" + moedaBase.name() + ")");

            String menu_principal = """
                    O que você deseja fazer?:
                    1 - Obter taxa de câmbio;
                    2 - Converter moeda.
                    """;
            System.out.println(menu_principal);
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    String standardUrl = builder.buildStandardUrl();
                    System.out.println("URL para obter a taxa de câmbio base (" + moedaBase.name() + "): " + standardUrl);
                    System.out.println(apiRequest.getApiResponse(standardUrl));
                    break;
                case 2:
                    System.out.println("\nEscolha a moeda de destino:");
                    for (UrlBuilderMenu.Moeda moeda : UrlBuilderMenu.Moeda.values()) {
                        if (moeda != moedaBase) { // Não listar a moeda base novamente
                            System.out.println((moeda.ordinal() + 1) + " - " + moeda.getNome() + " (" + moeda.name() + ")");
                        }
                    }
                    String escolhaDestinoStr = scanner.nextLine();
                    UrlBuilderMenu.Moeda moedaDestino = UrlBuilderMenu.Moeda.get(escolhaDestinoStr);

                    if (moedaDestino != null) {
                        builder.targetCurrency(moedaDestino.name());
                        System.out.println("Moeda de destino selecionada: " + moedaDestino.getNome() + " (" + moedaDestino.name() + ")");

                        System.out.println("Digite o valor a ser convertido:");
                        try {
                            int valor = Integer.parseInt(scanner.nextLine());
                            builder.amount(valor);
                            String pairUrl = builder.buildPairUrl();
                            System.out.println("URL para conversão de par: " + pairUrl);
                            System.out.println(apiRequest.getApiResponse(pairUrl));
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido.");
                        }
                        break;
                    }
                default:
                    System.out.println("Opção inválida.");
            }
        } else{
            scanner.close();
            System.out.println("Selecione uma opção do menu.");
        }
    }
}