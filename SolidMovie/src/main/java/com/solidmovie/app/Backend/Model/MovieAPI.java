package com.solidmovie.app.Backend.Model;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//for exercise 2, fetch from this swagger api: https://prog2.fh-campuswien.ac.at
// In browser you will get => no explicit mapping exist so  use e.g. for a GET request
// to fetch the array of json object: /movies


//movie api class to fetch data from endpoint
public class MovieAPI {

    public final String endPointUrl = "https://prog2.fh-campuswien.ac.at/movies";

    //http client
    static final HttpClient client = HttpClient.newHttpClient();

    // Method to fetch movies and return the raw JSON response
    public String fetchMovies() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endPointUrl))
                    .build();

            //send request synchronously and return the response body
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body(); // Returning the JSON string
        } catch (Exception e) {
            System.err.println("Error fetching movies: " + e.getMessage());
            return null;
        }
    }
}
