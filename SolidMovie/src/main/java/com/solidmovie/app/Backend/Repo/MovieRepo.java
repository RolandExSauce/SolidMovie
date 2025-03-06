package com.solidmovie.app.Backend.Repo;
import com.solidmovie.app.Backend.BackendHelper;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Model.MovieAPI;
import lombok.Getter;
import java.util.List;


// MovieRepo will act like a temporary database
public class MovieRepo {

    @Getter
    private final List<Movie> MOVIES;

    public MovieRepo() {
        String res = new MovieAPI().fetchMovies(); // fetch movies as JSON
        //convert JSON response to a Movie array (handle null properly)
        Movie[] movies = res != null ? BackendHelper.convertResponse(res) : new Movie[0];
        this.MOVIES = List.of(movies == null ? new Movie[0] : movies);
    };
};


