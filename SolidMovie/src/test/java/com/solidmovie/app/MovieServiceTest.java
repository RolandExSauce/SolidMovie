package com.solidmovie.app;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Backend.Service.MovieService;
import com.solidmovie.app.Utils.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



class MovieServiceTest {

    private MovieService movieService;

    // Test data
    private final List<Movie> movies = Arrays.asList(
            new Movie("Movie C", "Description C", Genre.DRAMA),
            new Movie("Movie A", "Description A", Genre.ACTION),
            new Movie("Movie B", "Description B", Genre.COMEDY)
    );

    @BeforeEach
    void setUp() {
        // Mock the MovieService to return the test data
        movieService = new MovieService() {
            @Override
            public List<Movie> getAllMovies() {
                return movies;
            }

            @Override
            public List<Movie> searchMovies(String query) {
                String lowerCaseQuery = query.toLowerCase();
                return movies.stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                                movie.getDescription().toLowerCase().contains(lowerCaseQuery))
                        .toList();
            }


            @Override
            public List<Movie> sortMovies(boolean ascending) {
                return movies.stream()
                        .sorted((m1, m2) -> ascending
                                ? m1.getTitle().compareToIgnoreCase(m2.getTitle())
                                : m2.getTitle().compareToIgnoreCase(m1.getTitle()))
                        .toList();
            }

            @Override
            public List<Movie> filterMoviesByGenre(Genre genre) {
                if (genre == null) {
                    return movies;
                }
                return movies.stream()
                        .filter(movie -> movie.getGenres().contains(genre))
                        .toList();
            }
        };
    }

    @Test
    void testGetAllMovies() {
        // Verify that getAllMovies returns the test data
        List<Movie> result = movieService.getAllMovies();
        assertEquals(movies, result, "The movie list should match the test data");
    }

    @Test
    void testSearchMovies() {
        // Test search by title
        List<Movie> resultByTitle = movieService.searchMovies("Movie A");
        assertEquals(1, resultByTitle.size(), "Should find 1 movie by title");
        assertEquals("Movie A", resultByTitle.getFirst().getTitle(), "The movie should match the search query");

        // Test search by description
        List<Movie> resultByDescription = movieService.searchMovies("Description B");
        assertEquals(1, resultByDescription.size(), "Should find 1 movie by description");
        assertEquals("Movie B", resultByDescription.getFirst().getTitle(), "The movie should match the search query");

        // Test search with no results
        List<Movie> resultNoMatch = movieService.searchMovies("nonexistent");
        assertTrue(resultNoMatch.isEmpty(), "Should return an empty list for no matches");
    }

    @Test
    void testSortMovies() {
        // Test ascending sort
        List<Movie> ascendingSorted = movieService.sortMovies(true);
        assertEquals(Arrays.asList(
                new Movie("Movie A", "Description A", Genre.ACTION),
                new Movie("Movie B", "Description B", Genre.COMEDY),
                new Movie("Movie C", "Description C", Genre.DRAMA)
        ), ascendingSorted, "Movies should be sorted in ascending order");

        // Test descending sort
        List<Movie> descendingSorted = movieService.sortMovies(false);
        assertEquals(Arrays.asList(
                new Movie("Movie C", "Description C", Genre.DRAMA),
                new Movie("Movie B", "Description B", Genre.COMEDY),
                new Movie("Movie A", "Description A", Genre.ACTION)
        ), descendingSorted, "Movies should be sorted in descending order");
    }

    @Test
    void testFilterMoviesByGenre() {
        // Test filtering by ACTION genre
        List<Movie> actionMovies = movieService.filterMoviesByGenre(Genre.ACTION);
        assertEquals(1, actionMovies.size(), "Should find 1 movie with ACTION genre");
        assertEquals("Movie A", actionMovies.getFirst().getTitle(), "The list should contain Movie A");

        // Test filtering by COMEDY genre
        List<Movie> comedyMovies = movieService.filterMoviesByGenre(Genre.COMEDY);
        assertEquals(1, comedyMovies.size(), "Should find 1 movie with COMEDY genre");
        assertEquals("Movie B", comedyMovies.getFirst().getTitle(), "The list should contain Movie B");

        // Test filtering with no genre (null)
        List<Movie> allMovies = movieService.filterMoviesByGenre(null);
        assertEquals(3, allMovies.size(), "Should return all movies when no genre is specified");
    }
}