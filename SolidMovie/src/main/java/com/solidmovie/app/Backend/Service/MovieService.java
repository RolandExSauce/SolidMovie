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
                .filter(movie -> movie.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                        movie.getDescription().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
    /******************************************************************************************************************/
    // Sorting movies alphabetically by title
    public List<Movie> sortMovies(boolean ascending) {
        return movieRepository.getAllMovies().stream()
                .sorted((m1, m2) -> ascending
                        ? m1.getTitle().compareToIgnoreCase(m2.getTitle())
                        : m2.getTitle().compareToIgnoreCase(m1.getTitle()))
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
                .filter(movie -> movie.getGenres().contains(selectedGenre))
                .collect(Collectors.toList()); // convert the stream back to a list
    };
}
