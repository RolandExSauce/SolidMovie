package com.solidmovie.app.Backend.Model;
import com.solidmovie.app.Backend.Helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//movie api class
public class MovieAPI {

    public final String endPointUrl = "https://prog2.fh-campuswien.ac.at/movies?genre=ACTION";

    public MovieAPI() { };

    //http client
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endPointUrl))
            .build();

    //method to send get request
    public void sendRequest() {
        try {
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept((res) -> {
                        Helpers.convertResponse(res);
                    })
                    .join();
        }
        catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
