package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Utils.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;



//test for movie service class
class MovieServiceTest {

    private MovieService movieService;

    // Centralized test data
    private final List<Movie> movies = List.of(
            new Movie("Movie A", "Description A", List.of(Genre.ACTION)),
            new Movie("Movie B", "Description B", List.of(Genre.COMEDY)),
            new Movie("Movie C", "Description C", List.of(Genre.DRAMA))
    );

    @BeforeEach
    void setUp() {
        // Mock the MovieService with test data
        movieService = new MovieService() {
            @Override
            public List<Movie> getAllMovies() {
                return movies;
            }

            @Override
            public List<Movie> searchMovies(String query) {
                String lowerCaseQuery = query.toLowerCase();
                return movies.stream()
                        .filter(movie -> movie.title().toLowerCase().contains(lowerCaseQuery) ||
                                movie.description().toLowerCase().contains(lowerCaseQuery))
                        .toList();
            }

            @Override
            public List<Movie> sortMovies(boolean ascending) {
                return movies.stream()
                        .sorted((m1, m2) -> ascending
                                ? m1.title().compareToIgnoreCase(m2.title())
                                : m2.title().compareToIgnoreCase(m1.title()))
                        .toList();
            }

            @Override
            public List<Movie> filterMoviesByGenre(Genre genre) {
                return (genre == null)
                        ? movies
                        : movies.stream().filter(movie -> movie.genres().contains(genre)).toList();
            }
        };
    };


    @Test
    void testGetAllMovies() {
        assertEquals(movies, movieService.getAllMovies(), "The movie list should match the test data");
    };


    @Test
    void testSearchMovies() {
        assertEquals(
                List.of(movies.get(1)), // "Movie A"
                movieService.searchMovies("Movie A"),
                "Should find 1 movie by title"
        );

        assertEquals(
                List.of(movies.get(2)), // "Movie B"
                movieService.searchMovies("Description B"),
                "Should find 1 movie by description"
        );

        assertTrue(movieService.searchMovies("nonexistent").isEmpty(),
                "Should return an empty list for no matches");
    };


    @Test
    void testSortMovies() {
        List<Movie> ascendingExpected = movies.stream()
                .sorted((m1, m2) -> m1.title().compareToIgnoreCase(m2.title()))
                .collect(Collectors.toList());

        List<Movie> descendingExpected = movies.stream()
                .sorted((m1, m2) -> m2.title().compareToIgnoreCase(m1.title()))
                .collect(Collectors.toList());

        assertEquals(ascendingExpected, movieService.sortMovies(true), "Movies should be sorted in ascending order");
        assertEquals(descendingExpected, movieService.sortMovies(false), "Movies should be sorted in descending order");
    };


    @Test
    void testFilterMoviesByGenre() {
        assertEquals(
                List.of(movies.get(1)), // "Movie A" (ACTION)
                movieService.filterMoviesByGenre(Genre.ACTION),
                "Should find 1 movie with ACTION genre"
        );

        assertEquals(
                List.of(movies.get(2)), // "Movie B" (COMEDY)
                movieService.filterMoviesByGenre(Genre.COMEDY),
                "Should find 1 movie with COMEDY genre"
        );

        assertEquals(
                movies,
                movieService.filterMoviesByGenre(null),
                "Should return all movies when no genre is specified"
        );
    };
}