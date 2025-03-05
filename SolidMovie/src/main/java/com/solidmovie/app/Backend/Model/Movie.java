package com.solidmovie.app.Backend.Model;
import com.solidmovie.app.Utils.Genre;
import java.util.Arrays;
import java.util.List;
import lombok.Data;


//for exercise 2, fetch from this swagger API
//https://prog2.fh-campuswien.ac.at/swagger-ui/index.html#/movie-controller/getMovies

//movie model class
@Data
public class Movie {

    private final String title;
    private final String description;
    private final List<Genre> genres;

    public Movie(String title, String description, Genre... genres) {
        this.title = title;
        this.description = description;
        this.genres = Arrays.asList(genres);
    };
}

