package com.solidmovie.app.Backend.Repo;
import com.solidmovie.app.Backend.Helpers;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Model.MovieAPI;
import java.util.List;


// MovieRepo will act like a temporary database
public class MovieRepo {

    private List<Movie> MOVIES;

    public MovieRepo() {
        String res = new MovieAPI().fetchMovies(); // Fetch movies as JSON

        // Convert JSON response to a Movie array (handle null properly)
        Movie[] movies = res != null ? Helpers.convertResponse(res) : new Movie[0];

        if(movies != null) {
            // Convert array to immutable list
            this.MOVIES = List.of(movies);
        }
    }

    // Return the list of movies
    public List<Movie> getAllMovies() {
        return MOVIES;
    }
}


