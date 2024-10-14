package moedas.conversor.challenge;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class RequestAPI {

    public Pair requestPair(String baseCode, String targetCode){
        baseCode = baseCode.toUpperCase();
        targetCode = targetCode.toUpperCase();

        String address = "https://v6.exchangerate-api.com/v6/3d9915288026af6bb928a4dc/pair/" + baseCode + "/" + targetCode;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(address)).build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível fazer a requisição a API");
        }

        String json = response.body();

        Gson gson =  new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Pair pair = gson.fromJson(json, Pair.class);
        if (Objects.equals(pair.result(), "success")){
            return pair;
        }
        else {
            throw new RuntimeException("A requisição a API falhou");
        }
    }

}