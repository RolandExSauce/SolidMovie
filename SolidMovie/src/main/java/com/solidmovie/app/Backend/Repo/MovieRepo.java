package com.solidmovie.app.Backend.Repo;
import com.solidmovie.app.Backend.BackendHelper;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Model.MovieAPI;
import java.util.List;


// MovieRepo will act like a temporary database
public class MovieRepo {

    private List<Movie> MOVIES;

    public MovieRepo() {

        String res = new MovieAPI().fetchMovies(); // fetch movies as JSON

        //convert JSON response to a Movie array (handle null properly)
        Movie[] movies = res != null ? BackendHelper.convertResponse(res) : new Movie[0];

        if(movies != null) {
            // Convert array to immutable list
            this.MOVIES = List.of(movies);
        }
    };
    /******************************************************************************************************************/
    //return the list of movies so that service can use it
    public List<Movie> getAllMovies() {
        return MOVIES;
    }
};


