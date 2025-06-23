import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepAPI {
    public static Endereco buscaEnderecoPorCep(String cep) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String body = response.body();

                Gson gson = new Gson();
                Endereco endereco = gson.fromJson(body, Endereco.class);

                if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
                    return null;
                }

                return endereco;
            } else {
                System.out.println("Erro ao acessar a API. Código de status: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar o CEP: " + e.getMessage());
            return null;
        }

    }
}
