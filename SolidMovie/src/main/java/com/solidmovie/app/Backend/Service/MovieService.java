package com.solidmovie.app.Backend.Service;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Repo.MovieRepo;
import com.solidmovie.app.Utils.Genre;
import java.util.stream.Collectors;
import java.util.List;


// Movie service class
public class MovieService {

    private final MovieRepo movieRepository = new MovieRepo();

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }
    /******************************************************************************************************************/
    // Search movie by title or description
    public List<Movie> searchMovies(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllMovies(); // Return all movies if no search term is provided
        }

        String lowerCaseQuery = query.toLowerCase();
        return movieRepository.getAllMovies().stream()
                .filter(movie -> movie.title().toLowerCase().contains(lowerCaseQuery) ||
                        movie.description().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
    /******************************************************************************************************************/
    // Sorting movies alphabetically by title
    public List<Movie> sortMovies(boolean ascending) {

        return movieRepository.getAllMovies().stream()
                //We are using sorted with this method signature: Stream<T> sorted(Comparator<? super T> comparator)
                //therefore it expects a Comparator<T> as an arg, since it's a functional interface, we can pass a lambda
                //function instead of writing a full class, lambda function acts here as a custom comparator
                .sorted((m1, m2) ->
                        ascending
                                ? m1.title().compareToIgnoreCase(m2.title())
                                : m2.title().compareToIgnoreCase(m1.title())
                )
                .collect(Collectors.toList());
    };
    /******************************************************************************************************************/
    // Filter movies by genre
    public List<Movie> filterMoviesByGenre(Genre selectedGenre) {
        if (selectedGenre == null) {
            return getAllMovies(); // Return all movies if no genre filter is applied
        }

        return movieRepository.getAllMovies()
                .stream()
                .filter(movie -> movie.genres().contains(selectedGenre))
                .collect(Collectors.toList()); // convert the stream back to a list
    };
}
